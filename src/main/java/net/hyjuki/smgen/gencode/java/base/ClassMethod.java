package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;

import java.util.List;

public class ClassMethod extends Method {
    /*
     * {
     *      method();
     *      return abcd;
     * }
     */
    private MethodBody body;

    public ClassMethod(Modifier modifier, TypeClass type, String name, List<Parameter> parameters) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.addImport(type);
        if (!GenUtils.isEmpty(parameters)) {
            for (Parameter param: parameters) {
                this.addImport(param.getType());
            }
        }
    }

    public void setBody(MethodBody body) {
        this.body = body;
    }

    public void appendBody(String strBody) {
        this.body.appendBody(strBody);
    }

    public void appendBody(Method method) {
        this.body.appendBody(method.formatString());
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();

        sb.append(GenUtils.line());
        if (!GenUtils.isEmpty(this.getAnnotations())) {
            sb.append(GenUtils.formatList(this.getAnnotations(), this.getIndent()));
        }
        sb.append(GenUtils.indent(1));

        sb.append(this.getDefineMethod())
                .append(" {")
                .append(GenUtils.lineAndIndent(this.getIndent() + 1))
                .append(this.body.formatString()).append(";")
                .append(GenUtils.lineAndIndent(this.getIndent()))
                .append("}");

        return sb.toString();
    }
}
