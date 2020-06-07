package net.hyjuki.smgen.codegen.java.base;

import net.hyjuki.smgen.codegen.CommonUtils;

// 类属性
public class ClassProperty extends AbstractElement implements Property {
    private String modifier;
    private String type;
    private String property;
    private String seperator;

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String getModifier() {
        return modifier;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String getProperty() {
        return property;
    }

    @Override
    public String getSeperator() {
        return seperator;
    }

    @Override
    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        String line = CommonUtils.lineAndIndent(1);
        sb.append(this.formatCommentAnnotations(1));
        sb.append(modifier).append(" ")
                .append(type).append(" ")
                .append(property).append(";");
        return sb.toString();
    }
}
