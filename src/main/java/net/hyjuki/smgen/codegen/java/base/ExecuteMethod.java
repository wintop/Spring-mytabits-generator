package net.hyjuki.smgen.codegen.java.base;

import java.util.List;

public interface ExecuteMethod extends Element {
    void setObjectName(String objectName);
    String getObjectName();
    void setMethod(String method);
    String getMethod();
    void addParameter(String parameter);
    void addParameter(Parameter parameter);
    void addParameters(List<Parameter> parameters);
    List<String> getParameters();
    void setReturnValue(String returnType, String returnValue);
}
