package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.JavaConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 主要包含Java常用的类
 */
public enum JavaClass {
    INT(int.class.getSimpleName()),
    L(long.class.getSimpleName()),
    VOID(void.class.getSimpleName()),

    OBJECT(Object.class.getSimpleName()),
    SERIALIZABLE(Serializable.class.getSimpleName()),
    STRING(String.class.getSimpleName()),

    BYTE(Byte.class.getSimpleName()),

    NUMBER(Number.class.getSimpleName()),
    INTEGER(Integer.class.getSimpleName()),
    LONG(Long.class.getSimpleName()),
    DOUBLE(Double.class.getSimpleName()),
    FLOAT(Float.class.getSimpleName()),

    DATE(Date.class.getSimpleName(), Date.class.getName()),
    LIST(List.class.getSimpleName(), List.class.getName(), new TypeClass(JavaConstants.TEMPLATE, "")),
    ARRAY_LIST(ArrayList.class.getSimpleName(), ArrayList.class.getName()),

    TEMPLATE(JavaConstants.TEMPLATE),
    ;

    private String name;
    private String pkgName;
    private TypeClass template;

    JavaClass(String name, String pkgName, TypeClass template) {
        this.name = name;
        this.pkgName = pkgName;
        this.template = template;
    }

    JavaClass(String name, String pkgName) {
        this.name = name;
        this.pkgName = pkgName;
    }

    JavaClass(String name) {
        this.pkgName = "";
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getName(String t) {
        return name + "<" + t + ">";
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeClass getTypeClass() {
        return new TypeClass(this.name, this.pkgName, this.template);
    }

    public TypeClass getTypeClass(TypeClass type) {
        return new TypeClass(this.name, this.pkgName, type);
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
