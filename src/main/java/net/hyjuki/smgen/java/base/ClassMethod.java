package net.hyjuki.smgen.java.base;

import net.hyjuki.smgen.base.CommonUtils;

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
        if (!CommonUtils.isEmpty(parameters)) {
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

        sb.append(CommonUtils.line());
        if (!CommonUtils.isEmpty(this.getAnnotations())) {
            sb.append(CommonUtils.formatList(this.getAnnotations(), this.getIndent()));
        }
        sb.append(CommonUtils.indent(1));

        sb.append(this.getDefineMethod())
                .append(" {")
                .append(CommonUtils.lineAndIndent(this.getIndent() + 1))
                .append(this.body.formatString()).append(";")
                .append(CommonUtils.lineAndIndent(this.getIndent()))
                .append("}");

        return sb.toString();
    }
}
