package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.model.TableColumn;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.ArrayList;
import java.util.List;

public class SelectPageNode extends NodeElement {
    private String tableName;
    private String resultMap;
    private String idValue;
    private TextElement limitElement;
    private TextElement sortElement = new TextElement();

    public SelectPageNode(String tableName, String resultMap) {
        super(MapperConstants.SELECT);
        this.tableName = tableName;
        this.resultMap = resultMap;
        this.idValue = MapperConstants.PAGE;
        setSeperator(GenUtils.lineAndIndent(1));
        addAttribute(MapperConstants.ID, this.idValue);
        addAttribute(MapperConstants.PARAMETER_TYPE, Object.class.getName());
        addAttribute(MapperConstants.RESULT_MAP, resultMap);
    }

    /**
     * 设置select语句的id的值: <select id="" ...></select>
     * @param idValue id的值
     */
    public void setSelectId(String idValue) {
        addAttribute(MapperConstants.ID, idValue);
    }

    public void setElements(String obj, List<TableColumn> columns, List<PrimaryKey> keys,
                String pageNo, String pageSize) {
        TextElement select = new SelectElement(tableName);
        select.setNormalize(false);
        addElement(select);
        WhereFindNode whereFindNode = new WhereFindNode();
        whereFindNode.setElement(obj, columns);
        this.addElement(whereFindNode);
        if (keys != null) {
            List<String> list = new ArrayList<>();
            for (PrimaryKey key: keys) {
                list.add(GenUtils.getProperty(key.getColumnName()));
            }
            this.setSort(MapperConstants.DESC, list);
            this.addElement(this.sortElement);
        }
        this.setLimit(pageNo, pageSize);
        this.addElement(this.limitElement);
    }

    public void setElements(String obj, List<TableColumn> columns, List<PrimaryKey> keys) {
        this.setElements(obj, columns, keys, "page.pageNo", "paeg.pageSizes");
    }

    /**
     * 设置limit条件
     * @param beginNo 表示起始行的名字
     * @param pageSize 表示每页数量的名字
     */
    private void setLimit(String beginNo, String pageSize) {
        StringBuffer sb = new StringBuffer();
        sb.append("limit #{")
                .append(beginNo)
                .append("}, #{")
                .append(pageSize)
                .append('}');

        TextElement limitElement = new TextElement();
        limitElement.setNormalize(false);
        limitElement.addElement(sb.toString());
        this.limitElement = limitElement;
    }

    public void setSort(String sortType, List<String> columns) {
        if (sortType != null) {
            String sortCols = GenUtils.concatBySeparator(GenUtils.COMMA, columns);
            this.sortElement.addElement(GenUtils.concatBySeparator(GenUtils.SPACE,
                    MapperConstants.ORDER_BY, sortCols, sortType));
        }
    }

    public void setSort(String sortType, String... columns) {
        if (sortType != null) {
            String sortCols = GenUtils.concatBySeparator(GenUtils.COMMA, columns);
            this.sortElement.addElement(GenUtils.concatBySeparator(GenUtils.SPACE,
                    MapperConstants.ORDER_BY, sortCols, sortType));
        }
    }
}
