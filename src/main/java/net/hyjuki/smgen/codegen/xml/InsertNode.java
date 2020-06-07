package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;
import net.hyjuki.smgen.codegen.xml.base.TextElement;

import java.util.List;

public class InsertNode extends NodeElement {
    private ColumnElement columnList = new ColumnElement();
    private ColumnElement valueList = new ColumnElement();

    public InsertNode(String tableName, List<Column> columns) {
        super(MapperConstants.INSERT);
        this.addAttribute(MapperConstants.ID, MapperConstants.SAVE);
        this.addAttribute(MapperConstants.PARAMETER_TYPE, CommonUtils.getClassName(tableName));
    //    this.addAttribute(MapperConstants.RESULT_TYPE, Integer.class.getName());
        this.addElement(renderInsertElement(tableName));
        this.renderColumnAndValue(columns);
        this.addElement(columnList);
        this.addElement(renderValueElement());
        this.addElement(valueList);
        this.addElement(renderEndElement());
        this.setSeperator(CommonUtils.lineAndIndent(1));
    }

    public void setInertId(String insertId) {
        addAttribute(MapperConstants.ID, insertId);
    }

    private void renderColumnAndValue(List<Column> columns) {
        for (Column column: columns) {
            if (column.getIsAutoIncrement()) {
                continue;
            }
            columnList.addElement(column.getColumnName());
            valueList.addElement(CommonUtils.renderProperty(column.getColumnName()));
        }

        columnList.setIndent(3);
        valueList.setIndent(3);
    }

    private TextElement renderInsertElement(String tableName) {
        TextElement insertElement = new TextElement();
        insertElement.setSeperator(" ");
        insertElement.addElement(MapperConstants.INSERT_INTO);
        insertElement.addElement(tableName);
        insertElement.addElement("(");

        return insertElement;
    }

    private TextElement renderValueElement() {
        TextElement valueElement = new TextElement();
        valueElement.setSeperator(" ");
        valueElement.addElement(") values (");

        return valueElement;
    }

    private TextElement renderEndElement() {
        TextElement endElement = new TextElement();
        endElement.addElement(")");

        return endElement;
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(this.getTagName())
                .append(this.renderAttribute(this.getAttributes()))
                .append(">");

        for (int i = 0; i < this.getElements().size(); i++) {
            sb.append(this.getSeperator()).append(CommonUtils.indent(1));
            if(i == 1 || i == 3) {
                sb.append(CommonUtils.indent(1));
            }
            sb.append(this.getElements().get(i).formatString());
        }

        sb.append(this.getSeperator());
        sb.append("</").append(this.getTagName()).append(">");

        return sb.toString();
    }
}
