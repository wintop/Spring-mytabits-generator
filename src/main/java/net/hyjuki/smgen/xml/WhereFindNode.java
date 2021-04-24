package net.hyjuki.smgen.xml;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.xml.base.MapperConstants;
import net.hyjuki.smgen.xml.base.NodeElement;

import java.util.List;

public class WhereFindNode extends NodeElement {
    public WhereFindNode() {
        super(MapperConstants.WHERE);
        this.setSeperator(CommonUtils.lineAndIndent(2));
    }

    public void setElement(List<Column> columns) {
        for (Column column: columns) {
            IfNode ifNode = new IfNode();
            ifNode.setWhereElement(column.getColumnName());
            this.addElement(ifNode);
        }
    }
}
