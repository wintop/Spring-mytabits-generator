package net.hyjuki.smgen.xml;

import net.hyjuki.smgen.xml.base.Element;
import net.hyjuki.smgen.xml.base.MapperConstants;
import net.hyjuki.smgen.xml.base.TextElement;

public class SelectElememt extends TextElement {
    private Element include = new IncludeElement();

    public SelectElememt(String tableName) {
        setSeperator(" ");
        addElement(MapperConstants.SELECT);
        addElement(include.formatString());
        addElement(MapperConstants.FROM);
        addElement(tableName);
    }
}
