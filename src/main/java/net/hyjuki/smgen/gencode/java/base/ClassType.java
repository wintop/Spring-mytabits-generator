package net.hyjuki.smgen.gencode.java.base;

/**
 * 定义类的类型：是class还是interface
 * 扩展为定义类时用的关键字
 * extends, implements
 */
public enum ClassType {
    CLASS("class"),
    INTERFACE("interface"),
    ABSTRACT("abstract class"),
    EXTENDS("extends"),
    IMPLEMENTS("implements");

    private String classType;

    ClassType(String classType) {
        this.classType = classType;
    }

    public String type() {
        return this.classType;
    }
}
