package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.*;

import java.util.List;

public class SqlNode extends NodeElement {
    private ColumnElement text;

    public SqlNode(List<TableColumn> columns) {
        super(MapperConstants.SQL);
        text = new ColumnElement();

        addAttribute(MapperConstants.ID, MapperConstants.COLUMN_LIST);
        addColumn(columns);
        text.setSeperator(", ");
        setSeperator(GenUtils.lineAndIndent(1));
        addElement(text);
    }

    public void addColumn(List<TableColumn> columns) {
        text.setColumn(columns);
    }
}
