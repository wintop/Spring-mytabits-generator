package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;

/**
 * 类的名称部分
 * public class ClassName extends Extend<T> implements Implement<T>
 */
public class ClassDefine extends Element {
    Package pkgName;
    Modifier modifier;
    ClassType type;
    // 类名称
    String name;
    // 定义类的时候只需要一个字符串做模板就行，不需要定义类
    String template;
    // extends的类和模板: extends ClassName<T>
    TypeClass extend;
    // implements的类和模板: implements InterfaceName<T>
    TypeClass implement;
    // 注释
    String comment;

    // public class ClassName
    public ClassDefine(ClassType type, String name) {
        this.type = type;
        this.name = name;
    }
    // public class ClassName
    public ClassDefine(Modifier modifier, ClassType type, String name) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
    }

    // public class ClassName<T>
    public ClassDefine(Modifier modifier, ClassType type, String name, String template) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.template = template;
    }

    // public class ClassName<T>
    public ClassDefine(ClassType type, String name, String template) {
        this.type = type;
        this.name = name;
        this.template = template;
    }

    // public class ClassName<T> extends ParentClass<T>
    public ClassDefine(Modifier modifier, ClassType type, String name, String template,
                       TypeClass extend) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.template = template;
        this.extend = extend;
        this.addImport(extend);
    }

    // public class ClassName<T> extends ParentClass implements ParentInterface
    public ClassDefine(Modifier modifier, ClassType type, String name, String template,
                       TypeClass extend, TypeClass implement) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.template = template;
        this.extend = extend;
        this.implement = implement;
        this.addImport(extend);
        this.addImport(implement);
    }

    public Package getPkgName() {
        return pkgName;
    }

    public void setPkgName(Package pkgName) {
        this.pkgName = pkgName;
    }

    public void setPkgName(String packageName) {
        this.pkgName = new Package(packageName);
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public TypeClass getExtend() {
        return extend;
    }

    public void setExtend(TypeClass extend) {
        this.extend = extend;
    }

    public TypeClass getImplement() {
        return implement;
    }

    public void setImplement(TypeClass implement) {
        this.implement = implement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String formatString() {
        StringBuilder sb = new StringBuilder();
        String template = "";
        String extendName = "";
        String implementName = "";

        if (!GenUtils.isEmpty(this.template)) {
            template = "<" + this.template + ">";
        }

        if (this.extend != null) {
            extendName = GenUtils.SPACE + ClassType.EXTENDS.type()
                    + GenUtils.SPACE + this.extend.getClassName();
        }

        if (this.implement != null) {
            implementName = GenUtils.SPACE + ClassType.IMPLEMENTS.type()
                    + GenUtils.SPACE + this.implement.getClassName();
        }

        sb.append(this.getModifier().modifier())
                .append(GenUtils.SPACE)
                .append(this.getType().type())
                .append(GenUtils.SPACE)
                .append(this.name)
                .append(template)
                .append(extendName)
                .append(implementName);

        return sb.toString();
    }
}
