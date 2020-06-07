package net.hyjuki.smgen.codegen;

import java.util.Arrays;

public class MethodConfig {
    private String[] methodNames;
    private String getName;
    private String findName;
    private String saveName;
    private String updateName;
    private String removeName;

    public String[] getMethodNames() {
        return methodNames;
    }

    public void setMethodNames(String[] methodNames) {
        this.methodNames = methodNames;
    }

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public String getFindName() {
        return findName;
    }

    public void setFindName(String findName) {
        this.findName = findName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getRemoveName() {
        return removeName;
    }

    public void setRemoveName(String removeName) {
        this.removeName = removeName;
    }

    public boolean contains(String methodName) {
        for (String method: methodNames) {
            if (method.equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MethodConfig{");
        sb.append("methodNames=").append(methodNames == null ? "null" : Arrays.asList(methodNames).toString());
        sb.append(", getName='").append(getName).append('\'');
        sb.append(", findName='").append(findName).append('\'');
        sb.append(", saveName='").append(saveName).append('\'');
        sb.append(", updateName='").append(updateName).append('\'');
        sb.append(", removeName='").append(removeName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
