package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.DbDataType;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

public class RemoveNode extends NodeElement {
    private String tableName;
    private PrimaryKey key;

    public RemoveNode(String tableName, PrimaryKey key) {
        super(MapperConstants.DELETE);
        this.tableName = tableName;
        this.key = key;
        this.setSeperator(GenUtils.lineAndIndent(1));
    }

    public void setRemoveId(String removeId) {
        addAttribute(MapperConstants.ID, removeId);
    }
    public void setElements(){
        this.setAttributes();
        TextElement textElement = new TextElement();
        textElement.setSeperator(" ");
        textElement.addElement(MapperConstants.DELETE_FROM);
        textElement.addElement(tableName);
        this.addElement(textElement);
        WhereKeyElement wke = new WhereKeyElement(key);
        this.addElement(wke);
    }

    private void setAttributes() {
        this.addAttribute(MapperConstants.ID, MapperConstants.REMOVE);
        if (key != null) {
            this.addAttribute(MapperConstants.PARAMETER_TYPE,
                    DbDataType.getInstance().getFullName(key.getDateType()));
        }
    //    this.addAttribute(MapperConstants.RESULT_TYPE, Integer.class.getName());
    }
}
