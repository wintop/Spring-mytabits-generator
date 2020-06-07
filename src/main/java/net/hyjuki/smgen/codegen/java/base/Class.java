package net.hyjuki.smgen.codegen.java.base;

import java.util.List;

public interface Class {
    void setPackage(String pkg);
    String getPackage();
    void setClassPackage(String classPackage);
    String getClassName();
    void addImportPackage(String importPkg);
    void addImportPackage(int index, String importPkg);
    boolean contains(String importPkg);
    void addImportPackages(List<String> importPkgs);
    void setModifier(String modifier);
    void setType(String type);
    void setClassName(String className);
    // 就用一个变量就行，如果有多个，写成字符串
    void setGenericity(String genericity);
    void addProperty(Property property);
    void addProperties(List<Property> properties);
    void addMethod(Method method);
    void addMothods(List<Method> methods);
    void addParentClass(ParentClass parentClass);
    String formatString();
}
