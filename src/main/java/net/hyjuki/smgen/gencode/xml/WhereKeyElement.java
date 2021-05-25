package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.List;

public class WhereKeyElement extends TextElement {
    public WhereKeyElement(List<PrimaryKey> keys) {
        addElement(MapperConstants.WHERE);
        for (int i = 0; i < keys.size(); i++) {
            if (i > 0) {
                addElement(MapperConstants.AND);
            }
            addElement(renderCondition(keys.get(i).getColumnName()));
        }
        setSeperator(" ");

    }

    private String renderCondition(String column) {
        return column + " = " + GenUtils.renderProperty(column);
    }
}
