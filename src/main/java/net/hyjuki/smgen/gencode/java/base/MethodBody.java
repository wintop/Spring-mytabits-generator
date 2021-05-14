package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.JavaConstants;

import java.util.List;

public class MethodBody extends Element {
    // return objectName(param);
    private String body;
    private ClassReturn classReturn;

    public MethodBody(ClassReturn classReturn) {
        this.classReturn = classReturn;
    }

    public MethodBody(Method method) {
        this.classReturn = new ClassReturn(method);
    }

    public MethodBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void appendBody(ClassReturn clsRet) {
        this.body = this.body + clsRet.formatString();
    }

    public void appendBody(String body) {
        if (GenUtils.isEmpty(this.body)) {
            this.body = "";
        }
        this.body = this.body + body;
    }

    public void appendBody(Method method) {
        this.appendBody(method.formatString() + ";");
    }

    public void appendCheckParam(List<Parameter> parameters, TypeClass retType, TypeClass msgType) {
        if (!GenUtils.isEmpty(parameters)) {
            StringBuffer sb = new StringBuffer();
            sb.append("if (");
            for (int i = 0; i < parameters.size(); i ++) {
                if (i == 0) {
                    sb.append(parameters.get(0).getVariable())
                            .append(" == null");
                }
                else {
                    sb.append(" || ").append(parameters.get(i).getVariable())
                            .append(" == null");
                }
            }
            sb.append(") {");

            Method hjkMethod = new Method(GenUtils.concatByDot(retType.getSimpleName(),
                    JavaConstants.METHOD_FAIL),
                    GenUtils.concatByDot(msgType.getClassName(), "ERROR_PARAM_NULL"));
            this.addImport(msgType);
            ClassReturn classReturn = new ClassReturn(hjkMethod);
            sb.append(GenUtils.lineAndIndent(3))
                    .append(classReturn.formatString())
                    .append(";")
                    .append(GenUtils.lineAndIndent(2))
                    .append("}");
            this.appendBody(sb.toString());
        }
    }

    public ClassReturn getClassReturn() {
        return classReturn;
    }

    public void setClassReturn(ClassReturn classReturn) {
        this.classReturn = classReturn;
    }

    @Override
    public String formatString() {
        String strBody = "";
        if (!GenUtils.isEmpty(this.body)) {
            strBody += this.body;
        }

        if (this.classReturn != null) {
            strBody += this.classReturn.formatString();
        }

        return strBody;
    }
}
