package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.List;

public class WhereKeyElement extends TextElement {
    public WhereKeyElement(PrimaryKey key) {
        addElement(MapperConstants.WHERE);
        addElement(renderCondition(key.getColumnName()));
        setSeperator(" ");
    }

    private String renderCondition(String column) {
        return column + " = " + GenUtils.renderProperty(column);
    }
}
