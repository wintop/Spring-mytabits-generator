package net.hyjuki.smgen.gencode.xml;

import net.hyjuki.smgen.base.utils.GenUtils;
import net.hyjuki.smgen.gencode.MethodConfig;
import net.hyjuki.smgen.db.*;
import net.hyjuki.smgen.base.utils.JavaConstants;
import net.hyjuki.smgen.gencode.xml.base.*;

import java.util.List;

public class MapperXml extends NodeElement {

    public static final String MAPPER_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
            + "<!DOCTYPE mapper\n"
            + "        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n"
            + "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";

    private String packageName;
    private Table table;
    private String tableName;
    private String resultMap;
    private PrimaryKey key;
    private List<TableColumn> columns;
    private String className;
    private String namespace;
    private String paramObjName;

    public MapperXml() {
        super(MapperConstants.MAPPER);
    }

    public MapperXml(String packageName, Table table) {
        super(MapperConstants.MAPPER);
        this.packageName = packageName;
        this.table = table;
        this.tableName = table.getTableName();
        this.paramObjName = MapperConstants.PARAM_OBJ;
        setResultMap(GenUtils.getClassName(tableName) + "Map");
        this.key = table.getPrimaryKey();
        this.columns = table.getColumns();
        this.className = GenUtils.getClassName(tableName);
        this.namespace = GenUtils.concatPackage(this.packageName, JavaConstants.DIR_DAO,
                className+JavaConstants.NAME_DAO);
        this.addAttribute(MapperConstants.NAMESPACE, namespace);
        this.setSeperator(GenUtils.line(1));
    }

    public String getClassName() {
        return this.className;
    }

    public void setParamObjName(String objName) {
        this.paramObjName = objName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
        this.addAttribute(MapperConstants.NAMESPACE, this.namespace);
    }

    private void setResultMap(String resultMap) {
        this.resultMap = resultMap;
    }

    private TextElement renderLine() {
        TextElement line = new TextElement();
        line.addElement("");
        return line;
    }

    /**
     * 使用默认配置
     */
    public void setElements() {
        // <resultMap id=""> ... </resultMap>
        setResultMapNode();
        //<sql id = "column_list"> ... </sql>
        setSqlNode();
        // <insert id = "save" ...> ... </insert>
        setInsertNode();
        // <select id = "get" ....> ... </select>
        setSelectGetNode();
        // <select id = "find" ....> ... </select>
        setSelectFindNode();
        // <select id = "pagination" ....> ... </select>
        setSelectPageNode();
        // <select id = "total" ....> ... </select>
        setSelectTotalNode();
        // <update id = "update" ....> ... </update>
        setUpdateNode();
        // <delete id = "remove" ....> ... </delete>
        setRemoveNode();
    }

    /**
     * 支持用户配置信息
     */
    public void setElements(MethodConfig method) {
        // <resultMap> ... </resultMap>
        setResultMapNode();
        //<sql id = "column_list"> ... </sql>
        setSqlNode();
        // <insert id = "save" ...> ... </insert>
        if (!GenUtils.isEmpty(method.getSaveName())) {
            setInsertNode(method.getSaveName());
        }
        // <select id = "get" ....> ... </select>
        if (!GenUtils.isEmpty(method.getGetName())) {
            setSelectGetNode(method.getGetName());
        }
        // <select id = "find" ....> ... </select>
        if (!GenUtils.isEmpty(method.getFindName())) {
            setSelectFindNode(method.getFindName());
        }
        // <select id = "pagination" ....> ... </select>
        if (!GenUtils.isEmpty(method.getPageName())) {
            setSelectPageNode(method.getPageName());

        }
        // <select id = "total" ....> ... </select>
        if (!GenUtils.isEmpty(method.getTotalName())) {
            setSelectTotalNode(method.getPageName());
        }
        // <update id = "update" ....> ... </update>
        if (!GenUtils.isEmpty(method.getUpdateName())) {
            setUpdateNode(method.getUpdateName());
        }
        // <delete id = "remove" ....> ... </delete>
        if (!GenUtils.isEmpty(method.getRemoveName())) {
            setRemoveNode(method.getRemoveName());
        }
    }

    public void setResultMapNode() {
        // <resultMap id="">
        ResultMapNode resultMapNode = new ResultMapNode();
        resultMapNode.addAttributes(className, resultMap);
        //     <result column="" property=""/>
        resultMapNode.setResultNodes(columns);
        this.addElement(resultMapNode);
        this.addElement(renderLine());
        // </resultMap>
    }

    public void setSqlNode() {
        //<sql id = "column_list"> ... </sql>
        SqlNode sqlNode = new SqlNode(columns);
        this.addElement(sqlNode);
        this.addElement(renderLine());
    }

    public void setInsertNode() {
        // <insert id = "save" ...> ... </insert>
        InsertNode insertNode = new InsertNode(tableName, columns);
        this.addElement(insertNode);
        this.addElement(renderLine());
    }

    public void setInsertNode(String insertId) {
        // <insert id = "save" ...> ... </insert>
        InsertNode insertNode = new InsertNode(tableName, columns);
        insertNode.setInertId(insertId);
        this.addElement(insertNode);
        this.addElement(renderLine());
    }

    public void setSelectGetNode() {
        // <select id = "get" ....> ... </select>
        SelectGetNode selectGetNode = new SelectGetNode(tableName, resultMap);
        selectGetNode.setElements(key);
        this.addElement(selectGetNode);
        this.addElement(renderLine());
    }

    public void setSelectGetNode(String getId) {
        // <select id = "get" ....> ... </select>
        SelectGetNode selectGetNode = new SelectGetNode(tableName, resultMap);
        selectGetNode.setSelectId(getId);
        selectGetNode.setElements(key);
        this.addElement(selectGetNode);
        this.addElement(renderLine());
    }

    public void setSelectFindNode() {
        // <select id = "find" ....> ... </select>
        SelectFindNode findNode = new SelectFindNode(tableName, resultMap);
        findNode.setElements(columns);
        this.addElement(findNode);
        this.addElement(renderLine());
    }

    public void setSelectFindNode(String findId) {
        // <select id = "find" ....> ... </select>
        SelectFindNode findNode = new SelectFindNode(tableName, resultMap);
        findNode.setSelectId(findId);
        findNode.setElements(columns);
        this.addElement(findNode);
        this.addElement(renderLine());
    }

    public void setSelectPageNode() {
        // <select id = "find" ....> ... </select>
        SelectPageNode pageNode = new SelectPageNode(tableName, resultMap);
        pageNode.setElements(this.paramObjName, columns, table.getPrimaryKey());
        this.addElement(pageNode);
        this.addElement(renderLine());
    }

    public void setSelectPageNode(String pageId) {
        // <select id = "find" ....> ... </select>
        SelectPageNode pageNode = new SelectPageNode(tableName, resultMap);
        pageNode.setSelectId(pageId);
        pageNode.setElements(this.paramObjName, columns, table.getPrimaryKey());
        this.addElement(pageNode);
        this.addElement(renderLine());
    }

    public void setSelectTotalNode() {
        // <select id = "total" ....> ... </select>
        SelectTotalNode totalNode = new SelectTotalNode(tableName, resultMap);
        // 暂时不用带参数的
        totalNode.setElements(columns);
        this.addElement(totalNode);
        this.addElement(renderLine());
    }

    public void setSelectTotalNode(String totalId) {
        // <select id = "total" ....> ... </select>
        SelectTotalNode totalNode = new SelectTotalNode(tableName, resultMap);
        totalNode.setSelectId(totalId);
        totalNode.setElements(columns);
        this.addElement(totalNode);
        this.addElement(renderLine());
    }

    public void setUpdateNode() {
        // <update id = "update" ....> ... </update>
        UpdateNode updateNode = new UpdateNode(table);
        updateNode.setElements();
        this.addElement(updateNode);
        this.addElement(renderLine());
    }

    public void setUpdateNode(String updateId) {
        // <update id = "update" ....> ... </update>
        UpdateNode updateNode = new UpdateNode(table);
        updateNode.setUpdateId(updateId);
        updateNode.setElements();
        this.addElement(updateNode);
        this.addElement(renderLine());
    }

    public void setRemoveNode() {
        // <delete id = "remove" ....> ... </delete>
        RemoveNode removeNode = new RemoveNode(tableName, key);
        removeNode.setElements();
        this.addElement(removeNode);
    }

    public void setRemoveNode(String removeId) {
        // <delete id = "remove" ....> ... </delete>
        RemoveNode removeNode = new RemoveNode(tableName, key);
        removeNode.setRemoveId(removeId);
        removeNode.setElements();
        this.addElement(removeNode);
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
