package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;

/**
 * 类型的类名，主要用于参数类型和方法返回值类型
 * 这里这种定义的方式为了方便参数类型所使用的的类的引用
 */
public class TypeClass {
    private String className;
    private String pkgName;
    private TypeClass template;

    public TypeClass(String className, String pkgName, TypeClass template) {
        this.className = className;
        this.pkgName = pkgName;
        this.template = template;
    }

    public TypeClass(String className, String pkgName) {
        this.className = className;
        this.pkgName = pkgName;
    }

    public TypeClass(String className) {
        this.className = className;
    }

    public TypeClass(JavaClass javaClass) {
        this.className = javaClass.getName();
        this.pkgName = javaClass.getPkgName();
    }

    public String getClassName() {
        if (this.template == null
                || GenUtils.isEmpty(this.template.getClassName())) {
            return className;
        }
        return className + "<" + template.getClassName() + ">";
    }

    public String getSimpleName() {
        return className;
    }

    public void setTemplate(TypeClass template) {
        this.template = template;
    }

    public static TypeClass getTypeClass(String className) {
        for (JavaClass cls: JavaClass.values()) {
            if (cls.getName().equals(className)) {
                return cls.getTypeClass();
            }
        }

        return null;
    }

    public TypeClass getTemplate() {
        return this.template;
    }

    public String getPkgName() {
        return pkgName;
    }

    @Override
    public String toString() {
        return "TypeClass{" +
                "className='" + className + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", template=" + template +
                '}';
    }
}
