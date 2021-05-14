package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.List;

/**
 * 根据字段名称生成字段和对象的元素的对应
 */
public class ColumnElement extends TextElement {
    private int indent = 2;

    public void setIndent(int indent) {
        this.indent = indent;
    }
    public void setColumn(List<TableColumn> columns) {
        for (TableColumn column: columns) {
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
                    sb.append(GenUtils.lineAndIndent(indent));
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
