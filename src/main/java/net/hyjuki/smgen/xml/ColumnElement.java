package net.hyjuki.smgen.xml;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.xml.base.TextElement;

import java.util.List;

public class ColumnElement extends TextElement {
    private int indent = 2;

    public void setIndent(int indent) {
        this.indent = indent;
    }
    public void setColumn(List<Column> columns) {
        for (Column column: columns) {
            addElement(column.getColumnName());
        }
    }

    @Override
    public String formatString() {
        setSeperator(", ");
        StringBuilder sb = new StringBuilder();
        List<String> columns = getElements();

        int colSize = columns.size() - 1;
        if (colSize == 0) {
            return columns.get(0);
        }

        int count = 0;
        for (int i = 0; i < colSize; i++) {
            if (count%4 == 0) {
                if (count > 0) {
                    sb.append(CommonUtils.lineAndIndent(indent));
                }
            }
            sb.append(columns.get(i))
                    .append(", ");
            count ++;
        }
        sb.append(columns.get(colSize));

        return sb.toString();
    }
}
