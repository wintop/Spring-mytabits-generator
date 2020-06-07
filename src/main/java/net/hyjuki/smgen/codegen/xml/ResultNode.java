package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.xml.base.MapperConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;

public class ResultNode extends NodeElement {

    public ResultNode() {
        super(MapperConstants.RESULT);
    }

    public void addAttribute(String column) {
        addAttribute(MapperConstants.COLUMN, column);
        addAttribute(MapperConstants.PROPERTY, CommonUtils.getProperty(column));
    }
}
