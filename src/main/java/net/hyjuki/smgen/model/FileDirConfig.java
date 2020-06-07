package net.hyjuki.smgen.model;

import java.util.Arrays;
import java.util.Map;

public class FileDirConfig {
    private String rootDir;
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
        final StringBuffer sb = new StringBuffer("FileDirConfig{");
        sb.append("rootDir='").append(rootDir).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", fileDirs=").append(fileDirs == null ? "null" : Arrays.asList(fileDirs).toString());
        sb.append(", xmlDir='").append(xmlDir).append('\'');
        sb.append(", modelDir='").append(modelDir).append('\'');
        sb.append(", daoDir='").append(daoDir).append('\'');
        sb.append(", serviceDir='").append(serviceDir).append('\'');
        sb.append(", implDir='").append(implDir).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

