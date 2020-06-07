package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.Column;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;
import net.hyjuki.smgen.codegen.xml.base.TextElement;

import java.util.List;

/**
 * 用于生成返回多条记录的select语句
 */

public class SelectFindNode extends NodeElement {
    private String tableName;
    private String resultMap;

    public String idValue;

    public SelectFindNode(String tableName, String resultMap) {
        super(MapperConstants.SELECT);
        this.tableName = tableName;
        this.resultMap = resultMap;
        this.idValue = MapperConstants.FIND;
        setSeperator(CommonUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, this.idValue);
        addAttribute(MapperConstants.PARAMETER_TYPE, CommonUtils.getClassName(tableName));
        addAttribute(MapperConstants.RESULT_MAP, resultMap);
    }

    /**
     * 设置select语句的id的值: <select id="" ...></select>
     * @param idValue id的值
     */
    public void setSelectId(String idValue) {
        addAttribute(MapperConstants.ID, idValue);
    }

    public void setElements(List<Column> columns) {
        TextElement select = new SelectElememt(tableName);
        select.setNormalize(false);
        addElement(select);
        WhereFindNode whereFindNode = new WhereFindNode();
        whereFindNode.setElement(columns);
        this.addElement(whereFindNode);
    }
}
