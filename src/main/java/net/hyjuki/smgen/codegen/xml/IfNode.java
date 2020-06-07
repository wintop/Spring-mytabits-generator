package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.xml.base.*;

import java.util.List;

public class IfNode extends NodeElement {
    public IfNode() {
        super(MapperConstants.IF);
        this.setSeperator(CommonUtils.lineAndIndent(2));
    }

    public boolean setWhereElement(String column) {
        this.addAttribute(MapperConstants.TEST, this.getAttrValue(column));
        StringBuilder sb = new StringBuilder();
        sb.append(MapperConstants.AND)
                .append(" ")
                .append(column).append(" = ")
                .append(CommonUtils.renderProperty(column));

        TextElement textElement = new TextElement();
        textElement.addElement(sb.toString());
        return this.addElement(textElement);
    }

    public boolean setSetElement(String column) {
        this.addAttribute(MapperConstants.TEST, this.getAttrValue(column));
        StringBuilder sb = new StringBuilder();
        sb.append(column).append(" = ")
                .append(CommonUtils.renderProperty(column))
                .append(",");

        TextElement textElement = new TextElement();
        textElement.addElement(sb.toString());
        return this.addElement(textElement);
    }

    private String getElement() {
        return this.getElements().get(0).formatString();
    }

    private String getAttrValue(String column) {
        return CommonUtils.getProperty(column) + " != null";
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(getTagName());
        List<Attribute> attributes = this.getAttributes();
        int size = attributes.size();

        for (Attribute attribute: attributes) {
            sb.append(" ")
                    .append(attribute.getName())
                    .append(" = \"")
                    .append(attribute.getValue())
                    .append("\"");
        }

        if (hasChildElements()) {
            sb.append(">");
            sb.append(getElement());
            sb.append("</").append(this.getTagName()).append(">");
        }
        else {
            sb.append(" />");
        }
        return sb.toString();
    }
}
