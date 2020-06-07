package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.codegen.xml.base.*;

import java.util.List;

public class SqlNode extends NodeElement {
    private ColumnElement text;

    public SqlNode(List<Column> columns) {
        super(MapperConstants.SQL);
        text = new ColumnElement();

        addAttribute(MapperConstants.ID, MapperConstants.COLUMN_LIST);
        addColumn(columns);
        text.setSeperator(", ");
        setSeperator(CommonUtils.lineAndIndent(1));
        addElement(text);
    }

    public void addColumn(List<Column> columns) {
        text.setColumn(columns);
    }
}
