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
package net.hyjuki.smgen.gencode;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.FileUtils;
import net.hyjuki.smgen.gencode.java.ProjectJava;
import net.hyjuki.smgen.gencode.xml.MapperXml;
import net.hyjuki.smgen.gencode.xml.MybatisConfigXml;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.db.Table;

import java.io.*;

/**
 * 生成固定规则配置的java文件，目前不可配置
 * 文件类型: Model, Dao, Service, ServiceImpl, Mapper.xml
 * 方法: get, find, save, update, remove
 * 目录: 在同一个文件目录下
 *      model, dao/ service/ service/impl/ mapper/
 */
public class MyBatisGenerator {
    private Table table;
    private String rootDir;
    private String projectName;
    private ProjectJava projectJava;

    // package    and package's children dir;
    private String packageName;
    private String projectDir;

    public MyBatisGenerator(String packageName, String projectName, String rootDir, Table table) {
        this.packageName = packageName;
        this.rootDir = rootDir;
        this.projectName = projectName;
        this.table = table;

        this.projectJava = new ProjectJava(projectName, rootDir, packageName);

        this.setProjectDir();
    }

    public void setProjectDir() {
        if (File.separator.equals(rootDir.substring(rootDir.length()-1))) {
            this.projectDir = rootDir.substring(0, rootDir.length() - 1);
        }

        this.projectDir = GenUtils.concatDir(rootDir, projectName, GenUtils.DIR_SRC, GenUtils.DIR_MAIN);
    }

    public String getProjectDir() {
        return projectDir;
    }

    public String getClassPackage(String childPackage) {
        return GenUtils.concatPackage(packageName, childPackage);
    }

    public String generatorComboFiles() throws Exception {
        System.out.println(this.table);
        return this.projectJava.generatorByTable(this.table);
    }

    public void generaterMapperXmlFile() throws IOException {
        MapperXml mapperXml = new MapperXml(packageName, table);
        String xmlFile = GenUtils.concatDir(projectDir,
                MapperConstants.DIR_RESOURCES,
                MapperConstants.DIR_MAPPER,
                mapperXml.getClassName()+ GenUtils.EXT_NAME_XML);
        mapperXml.setElements();
        if (FileUtils.writeFile(new File(xmlFile), mapperXml.formatString())) {
            System.out.println("==MapperXml file [" + xmlFile + "] is created!");
        }
    }

    public void generatorBaseAndConfigFile() throws IOException {
        this.projectJava.generatorBaseClass();
        MybatisConfigXml configXml = new MybatisConfigXml(packageName);
        String xmlFile = GenUtils.concatDir(projectDir, MapperConstants.DIR_RESOURCES, "config",
                "mybatis-config.xml");
        if (FileUtils.writeFile(new File(xmlFile), configXml.formatString())) {
            System.out.println("==Java file [" + xmlFile + "] is created!");
        }
    }
}
