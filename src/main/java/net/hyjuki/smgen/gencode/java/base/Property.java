package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.JavaConstants;

import java.util.ArrayList;
import java.util.List;

public class Property extends Element {
    List<Annotation> annotations;
    Modifier modifier;
    private TypeClass type;
    private String property;
    private String comment;
    private String value;

    public Property(Modifier modifier, TypeClass type, String property) {
        this.modifier = modifier;
        this.type = type;
        this.property = property;
        this.addImport(type);
    }

    public Property(TypeClass type, String property, String comment) {
        this.modifier = Modifier.PRIVATE;
        this.type = type;
        this.property = property;
        this.comment = comment;
        this.addImport(type);
    }

    public Property(TypeClass type, String property) {
        this.modifier = Modifier.PRIVATE;
        this.type = type;
        this.property = property;
        this.addImport(type);
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(Annotation annotation) {
        if (this.annotations == null) {
            annotations = new ArrayList<Annotation>();
        }
        this.annotations.add(annotation);
        this.addImports(annotation.getImports());
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public TypeClass getType() {
        return type;
    }

    public void setType(TypeClass type) {
        this.type = type;
        this.imports.add(type.getPkgName());
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        String line = GenUtils.lineAndIndent(1);
        sb.append(GenUtils.indent(1));
        if (!GenUtils.isEmpty(this.getComment())) {
            sb.append(JavaConstants.LINE_COMMENT)
                    .append(this.getComment().replace("\n", ""));
            sb.append(line);
        }

        if (!GenUtils.isEmpty(this.annotations)) {
            sb.append(GenUtils.formatList(this.annotations));
            sb.append(GenUtils.indent(1));
        }
        sb.append(modifier.modifier()).append(" ")
                .append(type.getClassName()).append(" ")
                .append(property);
        if (value != null) {
            sb.append(" = ").append(value);
        }
        sb.append(";");
        return sb.toString();
    }
}
