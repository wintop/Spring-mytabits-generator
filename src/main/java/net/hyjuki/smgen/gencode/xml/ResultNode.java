package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;

public class ResultNode extends NodeElement {

    public ResultNode() {
        super(MapperConstants.RESULT);
    }

    public void addAttribute(String column) {
        addAttribute(MapperConstants.COLUMN, column);
        addAttribute(MapperConstants.PROPERTY, GenUtils.getProperty(column));
    }
}
