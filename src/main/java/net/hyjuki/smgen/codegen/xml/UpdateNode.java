package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;
import net.hyjuki.smgen.codegen.xml.base.TextElement;

import java.util.ArrayList;
import java.util.List;

public class UpdateNode extends NodeElement {
    private String tableName;
    private List<PrimaryKey> keys;
    private List<Column> columns = new ArrayList<Column>();

    public UpdateNode(Table table) {
        super(MapperConstants.UPDATE);
        this.setTableName(table.getTableName());
        this.setKeys(table.getPrimaryKeys());
        this.setColumns(table.getColumns());

        setSeperator(CommonUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, MapperConstants.UPDATE);
        addAttribute(MapperConstants.PARAMETER_TYPE, CommonUtils.getClassName(tableName));
    //    addAttribute(MapperConstants.RESULT_TYPE, Integer.class.getName());
        TextElement updateElement = new TextElement();
        updateElement.addElement(MapperConstants.UPDATE);
        updateElement.addElement(tableName);
        updateElement.setSeperator(" ");
        addElement(updateElement);
    }

    public void setUpdateId(String updateId) {
        addAttribute(MapperConstants.ID, updateId);
    }

    public void setElements() {
        UpdateSetNode updateSetNode = new UpdateSetNode();
        updateSetNode.setElement(this.columns);
        this.addElement(updateSetNode);

        this.addElement(new WhereKeyElement(keys));
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setKeys(List<PrimaryKey> keys) {
        this.keys = keys;
    }

    /**
     * 设置要update的字段信息：
     *    因为where条件为会包含除主键（primary key）以外的所有字段
     * @param columns 表中的字段
     */
    public void setColumns(List<Column> columns) {
        // 主键中的数据不允许被update
        List<String> strKeys = new ArrayList<>();

        for (PrimaryKey key: this.keys) {
            strKeys.add(key.getColumnName());
        }

        for (Column column: columns) {
            if (!strKeys.contains(column.getColumnName())) {
                this.columns.add(column);
            }
        }
    }
}
