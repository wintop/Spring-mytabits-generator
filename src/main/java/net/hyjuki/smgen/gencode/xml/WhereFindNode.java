package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;

import java.util.List;

public class WhereFindNode extends NodeElement {
    public WhereFindNode() {
        super(MapperConstants.WHERE);
        this.setSeperator(GenUtils.lineAndIndent(2));
    }

    /**
     * 普通的where条件生成
     * @param columns 字段名
     */
    public void setElement(List<TableColumn> columns) {
        for (TableColumn column: columns) {
            IfNode ifNode = new IfNode();
            ifNode.setWhereElement(column.getColumnName());
            this.addElement(ifNode);
        }
    }

    /**
     * 支持对象传入参数，用于初期的page分页
     * @param obj 对象名称
     * @param columns 表中的字段信息
     */
    public void setElement(String obj, List<TableColumn> columns) {
        for (TableColumn column: columns) {
            IfNode ifNode = new IfNode();
            ifNode.setWhereElement(obj, column.getColumnName(), "=");
            this.addElement(ifNode);
        }
    }

    /**
     * where条件语句，支持对象传入和自定义条件
     * @param obj 对象名称
     * @param columns 表中的字段
     * @param operator 查询条件符号
     */
    public void setElement(String obj, List<TableColumn> columns, String operator) {
        for (TableColumn column: columns) {
            IfNode ifNode = new IfNode();
            ifNode.setWhereElement(obj, column.getColumnName(), operator);
            this.addElement(ifNode);
        }
    }
}
