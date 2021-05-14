package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.base.utils.JavaConstants;
import net.hyjuki.smgen.gencode.xml.base.NodeElement;

public class MybatisConfigXml extends NodeElement {
    public static final String MAPPER_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
            + "<!DOCTYPE configuration\n"
            + "        PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n"
            + "        \"http://mybatis.org/dtd/mybatis-3-config.dtd\">";

    private String packageName;
    private NodeElement typeAliasesElement;
    private NodeElement packageElement;

    public MybatisConfigXml(String packageName) {
        super("configuration");
        this.packageName = packageName;
        typeAliasesElement = new NodeElement("typeAliases");
        packageElement = new NodeElement("package");
        packageElement.addAttribute("name", GenUtils.concatPackage(this.packageName, JavaConstants.DIR_DAO));
        typeAliasesElement.addElement(packageElement);
        typeAliasesElement.setSeperator(GenUtils.lineAndIndent(1));
        this.addElement(typeAliasesElement);
        this.setSeperator(GenUtils.line());
    }

    @Override
    public String formatString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MAPPER_HEADER)
                .append(this.getSeperator())
                .append(super.formatString());
        return sb.toString();
    }
}
