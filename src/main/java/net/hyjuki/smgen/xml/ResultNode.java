package net.hyjuki.smgen.xml;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.xml.base.MapperConstants;
import net.hyjuki.smgen.xml.base.NodeElement;

public class ResultNode extends NodeElement {

    public ResultNode() {
        super(MapperConstants.RESULT);
    }

    public void addAttribute(String column) {
        addAttribute(MapperConstants.COLUMN, column);
        addAttribute(MapperConstants.PROPERTY, CommonUtils.getProperty(column));
    }
}
