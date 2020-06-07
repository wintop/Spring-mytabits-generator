package net.hyjuki.smgen.codegen.java.base;

import net.hyjuki.smgen.codegen.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class DefineMethod extends AbstractElement implements Method {
    private String modifier;
    private String type;
    private String method;
    private List<Parameter> parameters = new ArrayList<>();
    private String body;

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
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

    @Override
    public void addParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }

    public String getListType(String type) {
        return "List<" + type + ">";
    }

    @Override
    public String getFormatParameters() {
        if (parameters == null) {
            return "";
        }
        int size = parameters.size();
        if (size <= 0) {
            return "";
        }
        if (size == 1) {
            return parameters.get(0).toString();
        }

        size = size - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(parameters.get(i).toString()).append(", ");
        }
        sb.append(parameters.get(size).toString());

        return sb.toString();
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.formatCommentAnnotations(1));
        // modifier可以为空
        if (!CommonUtils.strIsEmpty(this.getModifier())) {
            sb.append(this.getModifier()).append(" ");
        }
        sb.append(this.getType()).append(" ")
                .append(this.getMethod()).append("(");
        sb.append(this.getFormatParameters()).append(")");
        // 函数体为空
        if (CommonUtils.strIsEmpty(this.getBody())) {
            sb.append(";");
            return sb.toString();
        }
        sb.append(" {").append(CommonUtils.lineAndIndent(2))
                .append(this.getBody()).append(";")
                .append(CommonUtils.lineAndIndent(1))
                .append("}")
                .append(CommonUtils.line());
        return sb.toString();
    }
}
