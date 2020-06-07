package net.hyjuki.smgen.codegen.java.base;

import java.util.List;

public interface Element {
    String getSeperator();
    void setSeperator(String seperator);
    String formatString();
    void setComment(String comment);
    String getComment();
    void addAnnotation(String annotation);
    List<String> getAnnotations();
}
