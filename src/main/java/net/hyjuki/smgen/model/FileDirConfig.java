package net.hyjuki.smgen.model;

import java.util.Arrays;
import java.util.Map;

public class FileDirConfig {
    private String rootDir;
    private String projectName;
    private String packageName;
    private String[] fileDirs;
    private String xmlDir;
    private String modelDir;
    private String daoDir;
    private String serviceDir;
    private String implDir;

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String[] getFileDirs() {
        return fileDirs;
    }

    public void setFileDirs(String[] fileDirs) {
        this.fileDirs = fileDirs;
    }

    public String getXmlDir() {
        return xmlDir;
    }

    public void setXmlDir(String xmlDir) {
        this.xmlDir = xmlDir;
    }

    public String getModelDir() {
        return modelDir;
    }

    public void setModelDir(String modelDir) {
        this.modelDir = modelDir;
    }

    public String getDaoDir() {
        return daoDir;
    }

    public void setDaoDir(String daoDir) {
        this.daoDir = daoDir;
    }

    public String getServiceDir() {
        return serviceDir;
    }

    public void setServiceDir(String serviceDir) {
        this.serviceDir = serviceDir;
    }

    public String getImplDir() {
        return implDir;
    }

    public void setImplDir(String implDir) {
        this.implDir = implDir;
    }

    @Override
    public String toString() {
        return "FileDirConfig{" +
                "rootDir='" + rootDir + '\'' +
                ", projectName='" + projectName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", fileDirs=" + Arrays.toString(fileDirs) +
                ", xmlDir='" + xmlDir + '\'' +
                ", modelDir='" + modelDir + '\'' +
                ", daoDir='" + daoDir + '\'' +
                ", serviceDir='" + serviceDir + '\'' +
                ", implDir='" + implDir + '\'' +
                '}';
    }
}

