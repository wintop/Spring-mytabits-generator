package net.hyjuki.smgen.codegen.xml;

import net.hyjuki.smgen.codegen.CommonUtils;
import net.hyjuki.smgen.codegen.java.base.JavaConstants;
import net.hyjuki.smgen.codegen.xml.base.NodeElement;

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
        packageElement.addAttribute("name", CommonUtils.concatPackage(this.packageName, JavaConstants.DIR_DAO));
        typeAliasesElement.addElement(packageElement);
        typeAliasesElement.setSeperator(CommonUtils.lineAndIndent(1));
        this.addElement(typeAliasesElement);
        this.setSeperator(CommonUtils.line());
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
