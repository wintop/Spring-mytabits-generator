package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.model.TableColumn;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.ArrayList;
import java.util.List;

public class UpdateNode extends NodeElement {
    private String tableName;
    private List<PrimaryKey> keys;
    private List<TableColumn> columns = new ArrayList<TableColumn>();

    public UpdateNode(Table table) {
        super(MapperConstants.UPDATE);
        this.setTableName(table.getTableName());
        this.setKey(table.getPrimaryKeys());
        this.setColumns(table.getColumns());

        setSeperator(GenUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, MapperConstants.UPDATE);
        addAttribute(MapperConstants.PARAMETER_TYPE, GenUtils.getClassName(tableName));
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

    public void setKey(List<PrimaryKey> keys) {
        this.keys = keys;
    }

    /**
     * 设置要update的字段信息：
     *    因为where条件为会包含除主键（primary key）以外的所有字段
     * @param columns 表中的字段
     */
    public void setColumns(List<TableColumn> columns) {
        // 主键中的数据不允许被update
        List<String> strKeys = new ArrayList<>();

        for (PrimaryKey key: keys) {
            strKeys.add(key.getColumnName());
        }

        for (TableColumn column: columns) {
            if (!strKeys.contains(column.getName())) {
                this.columns.add(column);
            }
        }
    }
}
