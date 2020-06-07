package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.Node;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;

import java.util.List;

public class ResultMapNode extends NodeElement {

    public ResultMapNode() {
        super(MapperConstants.RESULT_MAP);
        setSeperator(CommonUtils.lineAndIndent(1));
    }

    public boolean addResultNode(ResultNode resultNode) {
        return addElement(resultNode);
    }

    public void addAttributes(String tableName, String resultMap){
        addAttribute(MapperConstants.TYPE, tableName);
        addAttribute(MapperConstants.ID, resultMap);
    }

    public Node setResultNodes(List<Column> columns) {
        if (columns.size() <= 0) {
            return null;
        }
        for (Column column: columns) {
            ResultNode resultNode = new ResultNode();
            resultNode.addAttribute(column.getColumnName());
            addResultNode(resultNode);
        }

        return this;
    }
}
