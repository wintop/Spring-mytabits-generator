package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.model.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.Node;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;

import java.util.List;

public class ResultMapNode extends NodeElement {

    public ResultMapNode() {
        super(MapperConstants.RESULT_MAP);
        setSeperator(GenUtils.lineAndIndent(1));
    }

    public boolean addResultNode(ResultNode resultNode) {
        return addElement(resultNode);
    }

    public void addAttributes(String tableName, String resultMap){
        addAttribute(MapperConstants.TYPE, tableName);
        addAttribute(MapperConstants.ID, resultMap);
    }

    public Node setResultNodes(List<TableColumn> columns) {
        if (columns.size() <= 0) {
            return null;
        }
        for (TableColumn column: columns) {
            ResultNode resultNode = new ResultNode();
            resultNode.addAttribute(column.getName());
            addResultNode(resultNode);
        }

        return this;
    }
}
