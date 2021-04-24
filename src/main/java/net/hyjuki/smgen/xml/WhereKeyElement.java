package net.hyjuki.smgen.xml;

import net.hyjuki.smgen.base.CommonUtils;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.xml.base.MapperConstants;
import net.hyjuki.smgen.xml.base.TextElement;

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
        return column + " = " + CommonUtils.renderProperty(column);
    }
}
