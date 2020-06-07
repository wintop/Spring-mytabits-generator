package net.hyjuki.smgen.codegen.java.base;

public class Parameter {
    private String type;
    private String variable;

    public Parameter() {
        super();
    }

    public Parameter(String type, String variable) {
        this.type = type;
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return type + " " + variable;
    }
}
