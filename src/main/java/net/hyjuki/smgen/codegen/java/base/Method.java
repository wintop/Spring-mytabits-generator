package net.hyjuki.smgen.codegen.java.base;

import java.util.List;

public interface Method extends Element {
    void setModifier(String modifier);
    String getModifier();
    void setType(String type);
    String getType();
    void setMethod(String method);
    String getMethod();
    void addParameter(Parameter parameter);
    void addParameters(List<Parameter> parameters);
    List<Parameter> getParameters();
    String getFormatParameters();
    // 主体内容，一般就一行，或者只有return；
    void setBody(String body);
    String getBody();
}
