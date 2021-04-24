package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.CommonUtils;

public class Parameter extends Element {
    private TypeClass type;
    private String variable;
    private Annotation annotation;

    public Parameter() {
        super();
    }

    public Parameter(TypeClass type, String variable) {
        this.type = type;
        this.variable = variable;
        this.addImport(type.getPkgName());
        if (type.getTemplate() != null && type.getTemplate().getPkgName() != null) {
            this.addImport(type.getTemplate().getPkgName());
        }
    }

    public Parameter(TypeClass type, String variable, Annotation annotation) {
        this.type = type;
        this.variable = variable;
        this.annotation = annotation;
        this.addImport(annotation.getAnnotation().getAnnotPackage());
        this.addImport(type.getPkgName());
        if (type.getTemplate() != null && type.getTemplate().getPkgName() != null) {
            this.addImport(type.getTemplate().getPkgName());
        }
    }

    public Parameter(String type, String variable) {
        this.type = new TypeClass(type, null);
        this.variable = variable;
    }

    public Parameter(String type, String pkgName, String variable) {
        this.type = new TypeClass(type, pkgName);
        this.variable = variable;
        this.addImport(pkgName);
    }

    public TypeClass getType() {
        return type;
    }

    public void setType(TypeClass type) {
        this.type = type;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getPkgName() {
        return this.type.getPkgName();
    }

    @Override
    public String formatString() {
        if (annotation != null) {
            return CommonUtils.concatBySeparator(" ", annotation.getAnnotation().formatAnnotation(),
                    type.getClassName(), variable);
        }
        return type.getClassName() + " " + variable;
    }
}
