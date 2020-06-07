package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;

public class IncludeElement extends NodeElement {

    public IncludeElement() {
        super(MapperConstants.INCLUDE);
        addAttribute(MapperConstants.REFID, MapperConstants.COLUMN_LIST);
    }
}
