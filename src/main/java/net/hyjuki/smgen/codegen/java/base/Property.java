package net.hyjuki.smgen.codegen.java.base;

public interface Property extends Element {
    void setModifier(String modifier);
    String getModifier();
    void setType(String type);
    String getType();
    void setProperty(String property);
    String getProperty();
}
