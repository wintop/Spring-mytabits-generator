package net.hyjuki.smgen.gencode.java.base;

import net.hyjuki.smgen.base.utils.JavaConstants;

public class ClassReturn extends Element {
    private Method method;
    private String value;

    public ClassReturn(Method method) {
        this.method = method;
        this.addImports(method.getImports());
    }

    public ClassReturn(String value) {
        this.value = value;
    }

    public String getMethodReturn() {
        return JavaConstants.RETURN_SPC + method.getExecMethod();
    }

    public String getValueReturn() {
        return JavaConstants.RETURN_SPC + value;
    }

    @Override
    public String formatString() {
        if (this.value == null) {
            return this.getMethodReturn();
        }
        return this.getValueReturn();
    }
}
