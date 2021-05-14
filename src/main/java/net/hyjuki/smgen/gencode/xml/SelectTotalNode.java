package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.List;

public class SelectTotalNode extends NodeElement {
    private String tableName;
    private String resultMap;
    private String idValue;
    private TextElement limitElement;

    public SelectTotalNode(String tableName, String resultMap) {
        super(MapperConstants.SELECT);
        this.tableName = tableName;
        this.resultMap = resultMap;
        this.idValue = MapperConstants.TOTAL;
        setSeperator(GenUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, this.idValue);
        // 暂时不支持传入复杂参数
        addAttribute(MapperConstants.PARAMETER_TYPE, GenUtils.getClassName(tableName));
        addAttribute(MapperConstants.RESULT_TYPE, Integer.class.getName());
    }

    /**
     * 设置select语句的id的值: <select id="" ...></select>
     * @param idValue id的值
     */
    public void setSelectId(String idValue) {
        addAttribute(MapperConstants.ID, idValue);
    }

    public void setElements(String obj, List<TableColumn> columns) {
        TextElement select = new SelectElement(tableName, "count(*)");
        select.setNormalize(false);
        addElement(select);
        WhereFindNode whereFindNode = new WhereFindNode();
        whereFindNode.setElement(obj, columns);
        this.addElement(whereFindNode);
    }

    public void setElements(List<TableColumn> columns) {
        TextElement select = new SelectElement(tableName, "count(*)");
        select.setNormalize(false);
        addElement(select);
        WhereFindNode whereFindNode = new WhereFindNode();
        whereFindNode.setElement(columns);
        this.addElement(whereFindNode);
    }
}
