package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.db.DbDataType;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.codegen.xml.base.*;

import java.util.List;


public class SelectGetNode extends NodeElement {
    private String tableName;
    private String resultMap;

    public SelectGetNode(String tableName, String resultMap) {
        super(MapperConstants.SELECT);
        this.tableName = tableName;
        this.resultMap = resultMap;
        setSeperator(CommonUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, MapperConstants.GET);
    }

    /**
     * 允许修改id的值
     * @param idValue
     */
    public void setSelectId(String idValue) {
        addAttribute(MapperConstants.ID, idValue);
    }

    public void setElements(List<PrimaryKey> keys) {
        TextElement select = new SelectElememt(tableName);
        select.setNormalize(false);
        if (keys.size() == 1) {
            addAttribute(MapperConstants.PARAMETER_TYPE,
                    DbDataType.getInstance().getFullName(keys.get(0).getDateType()));
        }
        addAttribute(MapperConstants.RESULT_MAP, resultMap);
        addElement(select);
        addElement(new WhereKeyElement(keys));
    }

    public void setElements(Table table) {

    }
}
