package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.xml.base.Element;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.TextElement;

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
