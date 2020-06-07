package net.hyjuki.smgen.codegen.java.base;

import net.hyjuki.smgen.codegen.CommonUtils;

public class ParentClass {
    private String type;
    private String className;

    public ParentClass(String type, String className) {
        this.type = type;
        this.className = className;
    }

    @Override
    public String toString() {
        if (CommonUtils.strIsEmpty(className)) {
            return "";
        }
        return type + " " + className;
    }
}
