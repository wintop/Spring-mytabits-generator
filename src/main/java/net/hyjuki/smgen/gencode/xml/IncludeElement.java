package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;

public class IncludeElement extends NodeElement {

    public IncludeElement() {
        super(MapperConstants.INCLUDE);
        addAttribute(MapperConstants.REFID, MapperConstants.COLUMN_LIST);
    }
}
