package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.gencode.xml.base.*;

import java.util.List;

public class IfNode extends NodeElement {
    public IfNode() {
        super(MapperConstants.IF);
        this.setSeperator(GenUtils.lineAndIndent(2));
    }

    /**
     * select查询的where部分
     *   根据字段生成where查询语句中的一个条件，固定条件为“=”
     *   and column_name = #{columnName}
     * @param column
     * @return
     */
    public boolean setWhereElement(String column) {
        this.addAttribute(MapperConstants.TEST, this.getAttrValue(column));
        StringBuilder sb = new StringBuilder();
        sb.append(MapperConstants.AND)
                .append(" ")
                .append(column).append(" = ")
                .append(GenUtils.renderProperty(column));

        TextElement textElement = new TextElement();
        textElement.addElement(sb.toString());
        return this.addElement(textElement);
    }

    /**
     * select查询的where条件，主要针对分页查询
     *    支持对象传入，支持自定义符号判断，如：= > >= < <= like
     * @param obj 对象名称
     * @param column 列名
     * @param operator 条件符号
     * @return
     */
    public boolean setWhereElement(String obj, String column, String operator) {
        String property = GenUtils.concatByDot(obj, GenUtils.getProperty(column));
        this.addAttribute(MapperConstants.TEST, this.getAttrValue(property));
        StringBuilder sb = new StringBuilder();
        sb.append(MapperConstants.AND)
                .append(GenUtils.SPACE)
                .append(column).append(GenUtils.SPACE)
                .append(operator)
                .append(GenUtils.SPACE)
                .append(property);

        TextElement textElement = new TextElement();
        textElement.addElement(sb.toString());
        return this.addElement(textElement);
    }

    // update的set部分
    public boolean setSetElement(String column) {
        this.addAttribute(MapperConstants.TEST, this.getAttrValue(column));
        StringBuilder sb = new StringBuilder();
        sb.append(column).append(" = ")
                .append(GenUtils.renderProperty(column))
                .append(",");

        TextElement textElement = new TextElement();
        textElement.addElement(sb.toString());
        return this.addElement(textElement);
    }

    private String getElement() {
        return this.getElements().get(0).formatString();
    }

    private String getAttrValue(String property) {
        return property + " != null";
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
