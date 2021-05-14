package net.hyjuki.smgen.gencode.java.base;

/**
 * 修饰符 public private protected
 */
public enum Modifier {
    PUBLIC("public"),
    PUBLIC_ABSTRACT("public abstract"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PRIVATE_STATIC_FINAL("private static final");

    private String modifier;

    Modifier(String modifier) {
        this.modifier = modifier;
    }

    public String modifier() {
        return this.modifier;
    }
}
