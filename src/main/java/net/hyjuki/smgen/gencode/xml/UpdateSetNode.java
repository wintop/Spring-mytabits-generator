package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.model.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;

import java.util.List;

public class UpdateSetNode extends NodeElement {
    public UpdateSetNode() {
        super(MapperConstants.SET);
        this.setSeperator(GenUtils.lineAndIndent(2));
    }

    public void setElement(List<TableColumn> columns) {
        for (TableColumn column: columns) {
            IfNode ifNode = new IfNode();
            ifNode.setSetElement(column.getName());
            this.addElement(ifNode);
        }
    }
}
