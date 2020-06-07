package net.hyjuki.smgen.codegen.java.base;

import net.hyjuki.smgen.codegen.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class ClassMethod implements ExecuteMethod {
    // Property中声明的调用对象
    private String objectName;
    // 对象下的方法
    private String method;
    // 参数
    private List<String> parameters = new ArrayList<>();
    private String seperator;
    private List<String> annotations = new ArrayList<>();
    private String comment;
    private String returnType;
    private String returnValue;

    @Override
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public String getObjectName() {
        return this.objectName;
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
    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    @Override
    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter.getVariable());
    }

    @Override
    public void addParameters(List<Parameter> parameters) {
        for (Parameter parameter: parameters) {
            this.parameters.add(parameter.getVariable());
        }
    }

    @Override
    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public void setReturnValue(String returnType, String returnValue) {
        this.returnType = returnType;
        this.returnValue = returnValue;
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
    public void setComment(String comment) {
        this.comment = "/* " + comment + " */";
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    @Override
    public List<String> getAnnotations() {
        return annotations;
    }

    private String formatParameters() {
        StringBuilder sb = new StringBuilder();
        if (this.parameters != null && this.parameters.size() > 0) {
            int size = this.parameters.size();
            if (size == 1) {
                sb.append(this.parameters.get(0));
            }
            else {
                size = size - 1;
                for (int i = 0; i < size; i++) {
                    sb.append(this.parameters.get(i)).append(", ");
                }
                sb.append(this.parameters.get(size));
            }
        }

        return sb.toString();
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();

        if (!CommonUtils.strIsEmpty(returnValue)) {
            sb.append(this.returnType).append(" ")
                    .append(this.returnValue).append(" = ");
        }

        if (!CommonUtils.strIsEmpty(this.objectName)) {
            sb.append(objectName).append(".");
        }
        sb.append(method)
                .append("(").append(formatParameters()).append(")");

        return sb.toString();
    }
}
