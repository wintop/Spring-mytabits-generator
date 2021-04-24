package net.hyjuki.smgen.xml;

import net.hyjuki.smgen.xml.base.MapperConstants;
import net.hyjuki.smgen.xml.base.NodeElement;

public class IncludeElement extends NodeElement {

    public IncludeElement() {
        super(MapperConstants.INCLUDE);
        addAttribute(MapperConstants.REFID, MapperConstants.COLUMN_LIST);
    }
}
