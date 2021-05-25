package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.db.DbDataType;
import net.hyjuki.smgen.db.PrimaryKey;
import net.hyjuki.smgen.gencode.xml.base.MapperConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;
import net.hyjuki.smgen.gencode.xml.base.TextElement;

import java.util.List;

public class RemoveNode extends NodeElement {
    private String tableName;
    private List<PrimaryKey> keys;

    public RemoveNode(String tableName, List<PrimaryKey> keys) {
        super(MapperConstants.DELETE);
        this.tableName = tableName;
        this.keys = keys;
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
        WhereKeyElement wke = new WhereKeyElement(keys);
        this.addElement(wke);
    }

    private void setAttributes() {
        this.addAttribute(MapperConstants.ID, MapperConstants.REMOVE);
        if (keys != null) {
            if (keys.size() == 1) {
                this.addAttribute(MapperConstants.PARAMETER_TYPE,
                        DbDataType.getInstance().getFullName(keys.get(0).getDateType()));
            }
        }
    //    this.addAttribute(MapperConstants.RESULT_TYPE, Integer.class.getName());
    }
}
