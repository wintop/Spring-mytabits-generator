package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.DbDataType;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.gencode.xml.base.*;

import java.util.List;


public class SelectGetNode extends NodeElement {
    private String tableName;
    private String resultMap;

    public SelectGetNode(String tableName, String resultMap) {
        super(MapperConstants.SELECT);
        this.tableName = tableName;
        this.resultMap = resultMap;
        setSeperator(GenUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, MapperConstants.GET);
    }

    /**
     * 允许修改id的值
     * @param idValue
     */
    public void setSelectId(String idValue) {
        addAttribute(MapperConstants.ID, idValue);
    }

    public void setElements(PrimaryKey key) {
        TextElement select = new SelectElement(tableName);
        select.setNormalize(false);
        if (key != null) {
            addAttribute(MapperConstants.PARAMETER_TYPE,
                    DbDataType.getInstance().getFullName(key.getDateType()));
        }
        addAttribute(MapperConstants.RESULT_MAP, resultMap);
        addElement(select);
        addElement(new WhereKeyElement(key));
    }

    public void setElements(Table table) {

    }
}
