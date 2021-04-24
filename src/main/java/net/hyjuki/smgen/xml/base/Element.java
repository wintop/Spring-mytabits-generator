package net.hyjuki.smgen.xml.base;

import java.util.List;

public interface Element<T> {
    String getTagName();
    List<Attribute> getAttributes();
    void addAttribute(String name, String value);
    void addAttribute(Attribute attribute);
    String getSeperator();
    void setSeperator(String seperator);
    boolean hasChildElements();
    boolean addElement(T element);
    List<T> getElements();
    boolean addElements(List<T> elements);
    String formatString();
}
