package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.gencode.xml.base.Element;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

public class SelectElement extends TextElement {
    private Element include = new IncludeElement();

    /**
     * 查询表中多有字段，用<include />格式
     * @param tableName
     */
    public SelectElement(String tableName) {
        setSeperator(" ");
        addElement(MapperConstants.SELECT);
        addElement(include.formatString());
        addElement(MapperConstants.FROM);
        addElement(tableName);
    }

    /**
     * 获取表的其他内容
     * @param tableName
     * @param content select 和 from中的语句内容
     */
    public SelectElement(String tableName, String content) {
        setSeperator(" ");
        addElement(MapperConstants.SELECT);
        addElement(content);
        addElement(MapperConstants.FROM);
        addElement(tableName);
    }
}
