package net.hyjuki.smgen.codegen.xml.base;

import net.hyjuki.smgen.codegen.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class NodeElement implements Node {
    private String tagName;
    private String seperator = "";
    private List<Attribute> attributes;
    private List<Element> elements;

    public NodeElement(String tagName) {
        this.tagName = tagName;
        attributes = new ArrayList<>();
        elements = new ArrayList<>();
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void addAttribute(String name, String value) {
        for (int i = 0; i < attributes.size(); i++) {
            if (name.equals(attributes.get(i).getName())){
                if (value.equals(attributes.get(i).getValue())) {
                    return;
                }
                else {
                    attributes.set(i, new Attribute(name, value));
                    return;
                }
            }
        }
        attributes.add(new Attribute(name, value));
    }

    @Override
    public void addAttribute(Attribute attribute) {
        for (int i = 0; i < attributes.size(); i++) {
            if (attribute.getName().equals(attributes.get(i).getName())){
                attributes.set(i, attribute);
                return;
            }
        }
        attributes.add(attribute);
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
    public boolean hasChildElements() {
        return elements.size() > 0;
    }

    @Override
    public boolean addElement(Element element) {
        return elements.add(element);
    }

    @Override
    public boolean addElements(List<Element> elements) {
        return elements.addAll(elements);
    }

    @Override
    public List<Element> getElements() {
        return elements;
    }

    public String renderAttribute(List<Attribute> attributes) {
        StringBuilder sb = new StringBuilder();
        int size = attributes.size();

        if (size > 0) {
            for (Attribute attribute: attributes) {
                sb.append(" ")
                        .append(attribute.getName())
                        .append(" = \"")
                        .append(attribute.getValue())
                        .append("\"");
            }
        }
        return sb.toString();
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tagName);
        sb.append(this.renderAttribute(this.attributes));
        if (hasChildElements()) {
            sb.append(">");
            for (Element element: elements) {
                sb.append(seperator);
                sb.append(CommonUtils.indent(1));
                sb.append(element.formatString());
            }
            sb.append(seperator).append("</").append(tagName).append(">");
        }
        else {
            sb.append(" />");
        }
        return sb.toString();
    }
}
