package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;

import java.util.List;

public class UpdateSetNode extends NodeElement {
    public UpdateSetNode() {
        super(MapperConstants.SET);
        this.setSeperator(CommonUtils.lineAndIndent(2));
    }

    public void setElement(List<Column> columns) {
        for (Column column: columns) {
            IfNode ifNode = new IfNode();
            ifNode.setSetElement(column.getColumnName());
            this.addElement(ifNode);
        }
    }
}
