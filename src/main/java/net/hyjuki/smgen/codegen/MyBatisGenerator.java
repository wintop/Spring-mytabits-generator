/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package net.hyjuki.smgen.codegen;

import net.hyjuki.smgen.codegen.java.ClassServiceImplFile;
import net.hyjuki.smgen.codegen.java.InterfaceDaoFile;
import net.hyjuki.smgen.codegen.java.InterfaceServiceFile;
import net.hyjuki.smgen.codegen.java.JavaBeanFile;
import net.hyjuki.smgen.codegen.java.base.JavaConstants;
import net.hyjuki.smgen.codegen.xml.MapperXml;
import net.hyjuki.smgen.codegen.xml.MybatisConfigXml;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.db.Table;

import java.io.*;

public class MyBatisGenerator {
    private Table table;
    private String rootDir;

    // package and package's children dir;
    private String packageName;
    private String modelDir = JavaConstants.DIR_MODEL;
    private String daoDir = JavaConstants.DIR_DAO;
    private String serviceDir = JavaConstants.DIR_SERVICE;
    private String implDir = JavaConstants.DIR_IMPL;
    private String xmlDir;
    private String projectDir;
    private String resourcesDir;

    private String xmlFile;

    public MyBatisGenerator(String packageName, String rootDir, Table table) {
        this.packageName = packageName;
        this.rootDir = rootDir;
        this.table = table;
        this.setProjectDir();
    }

    public void setProjectDir() {
        if (File.separator.equals(rootDir.substring(rootDir.length()-1))) {
            this.projectDir = rootDir.substring(0, rootDir.length() - 1);
        }

        this.projectDir = CommonUtils.concatDir(rootDir, CommonUtils.DIR_SRC, CommonUtils.DIR_MAIN);
    }

    public String getProjectDir() {
        return projectDir;
    }

    public String getClassPackage(String childPackage) {
        return CommonUtils.concatPackage(packageName, childPackage);
    }

    public void generatorComboFiles() throws IOException {
        generaterJavaModelFile();
        generatorJavaDaoFile();
        generatorJavaServiceFile();
        generaterMapperXmlFile();
        generatorJavaImplFile();
    }

    public void generaterJavaModelFile() throws IOException {
        JavaBeanFile javaBeanFile = new JavaBeanFile(packageName, table);
        String modelFileName = getJavaFileName(modelDir, javaBeanFile.getClassName());
        javaBeanFile.setClassPackage(packageName + JavaConstants.DIR_MODEL);
        javaBeanFile.generatorJavaBean();
        if (writeFile(new File(modelFileName), javaBeanFile.formatString())) {
            System.out.println("==Java file [" + modelFileName + "] is created!");
        }
    }

    public void generatorJavaDaoFile() throws IOException {
        InterfaceDaoFile daoFile = new InterfaceDaoFile(packageName, table);
        String daoFileName = getJavaFileName(daoDir, daoFile.getClassName());
        daoFile.generatorInterfaceMapper();
        if (writeFile(new File(daoFileName), daoFile.formatString())) {
            System.out.println("==Java file [" + daoFileName + "] is created!");
        }
    }

    public void generatorJavaServiceFile() throws IOException {
        InterfaceServiceFile serviceFile = new InterfaceServiceFile(packageName, table);
        String serviceFileName = getJavaFileName(serviceDir, serviceFile.getClassName());
        serviceFile.generatorInterfaceService();
        if (writeFile(new File(serviceFileName), serviceFile.formatString())){
            System.out.println("==Java file [" + serviceFileName + "] is created!");
        }
    }

    public void generaterMapperXmlFile() throws IOException {
        MapperXml mapperXml = new MapperXml(packageName, table);
        String xmlFile = CommonUtils.concatDir(projectDir, MapperConstants.DIR_RESOURCES, MapperConstants.DIR_MAPPER,
                mapperXml.getClassName()+CommonUtils.EXT_NAME_XML);
        mapperXml.setElements();
        if (writeFile(new File(xmlFile), mapperXml.formatString())) {
            System.out.println("==MapperXml file [" + xmlFile + "] is created!");
        }
    }

    public void generatorJavaImplFile() throws IOException {
        ClassServiceImplFile implFile = new ClassServiceImplFile(packageName, table);
        String implFileName = getJavaFileName(CommonUtils.concatDir(serviceDir, implDir), implFile.getClassName());
        implFile.generatorServiceImpl();
        if (writeFile(new File(implFileName), implFile.formatString())) {
            System.out.println("==Java file [" + implFileName + "] is created!");
        }
    }

    public void generatorMybatisConfigXmlFile() throws IOException {
        MybatisConfigXml configXml = new MybatisConfigXml(packageName);
        String xmlFile = CommonUtils.concatDir(projectDir, MapperConstants.DIR_RESOURCES, "config",
                "mybatis-config.xml");
        if (writeFile(new File(xmlFile), configXml.formatString())) {
            System.out.println("==Java file [" + xmlFile + "] is created!");
        }
    }
    /**
     * Writes, no overwrites, the contents of the specified file.
     * @param file
     * @param content
     * @param fileEncoding
     * @throws IOException Signals that an I/O exception has occurred.
                */
        public boolean writeFile(File file, String content, String fileEncoding) throws IOException {
            if (file.exists()) {
                System.out.println("==文件["+ file.getAbsolutePath() + file.getName() +
                        "]已经存在，为防止损失，请先备份文件并清空目录。==");
                return false;
            }
            if (!file.getParentFile().exists()) {
                boolean result = file.getParentFile().mkdirs();
                if (!result) {
                    System.out.println("文件目录创建失败");
                }
            }

            FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        try (BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(content);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Writes, no overwrites, the contents of the specified file.
     * @param fileName
     * @param content
     * @param fileEncoding
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public boolean writeFile(String fileName, String content, String fileEncoding) throws IOException {
        File file = new File(fileName);
        return writeFile(file, content, fileEncoding);
    }

    public boolean writeFile(File file, String content) throws IOException {
        return writeFile(file, content, "UTF-8");
    }

    private String getJavaFileName(String dirName, String className) {

        String[] dirs = packageName.split("\\.");

        String[] projDirs = new String[dirs.length+2];
        projDirs[0] = projectDir;
        projDirs[1] = JavaConstants.DIR_JAVA;

        for (int i = 0; i < dirs.length; i++) {
            projDirs[i+2] = dirs[i];
        }

        String dir = CommonUtils.concatDir(CommonUtils.concatDir(projDirs),
                dirName, className + CommonUtils.EXT_NAME_JAVA);
        System.out.println(dir);

        return dir;
    }
}
