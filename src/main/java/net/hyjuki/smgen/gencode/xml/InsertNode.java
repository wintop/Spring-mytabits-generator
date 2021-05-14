package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.List;

public class InsertNode extends NodeElement {
    private ColumnElement columnList = new ColumnElement();
    private ColumnElement valueList = new ColumnElement();

    public InsertNode(String tableName, List<TableColumn> columns) {
        super(MapperConstants.INSERT);
        this.addAttribute(MapperConstants.ID, MapperConstants.SAVE);
        this.addAttribute(MapperConstants.PARAMETER_TYPE, GenUtils.getClassName(tableName));
    //    this.addAttribute(MapperConstants.RESULT_TYPE, Integer.class.getName());
        this.addElement(renderInsertElement(tableName));
        this.renderColumnAndValue(columns);
        this.addElement(columnList);
        this.addElement(renderValueElement());
        this.addElement(valueList);
        this.addElement(renderEndElement());
        this.setSeperator(GenUtils.lineAndIndent(1));
    }

    public void setInertId(String insertId) {
        addAttribute(MapperConstants.ID, insertId);
    }

    private void renderColumnAndValue(List<TableColumn> columns) {
        for (TableColumn column: columns) {
            if (column.getAutoIncrement()) {
                continue;
            }
            columnList.addElement(column.getColumnName());
            valueList.addElement(GenUtils.renderProperty(column.getColumnName()));
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
        valueElement.setSeperator(GenUtils.SPACE);
        valueElement.addElement(") "+ MapperConstants.VALUES +" (");

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
            sb.append(this.getSeperator()).append(GenUtils.indent(1));
            if(i == 1 || i == 3) {
                sb.append(GenUtils.indent(1));
            }
            sb.append(this.getElements().get(i).formatString());
        }

        sb.append(this.getSeperator());
        sb.append("</").append(this.getTagName()).append(">");

        return sb.toString();
    }
}
