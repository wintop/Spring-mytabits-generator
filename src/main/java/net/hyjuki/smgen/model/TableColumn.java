package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 表的字段信息
 */
public class TableColumn {
	// 主键ID
	private Integer id;
	// 所属表ID，不用名字，有重复
	private Integer tableId;
	// 所属数据库
	private Integer dbId;
	// 字段名
	private String name;
	// 展示名称，考虑到comment中内容比较丰富，增加此字段
	private String label;
	// 数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)
	private Integer dataType;
	// 字段的数据类型，date_type字段信息不是全
	private String typeName;
	// 字段数据的长度，一般用于字符串和数值型，varchar(10), int(11)
	private Integer dataLength;
	// 展示顺序
	private Integer sort;
	// 是否空1 可以为空 0 not null
	private Byte nullable;
	// 是否自增
	private Byte autoIncrement;
	// 默认值
	private String defaultValue;
	// 备注，支持原生
	private String comment;
	// 页面编辑使用元素 默认使用input
	private Integer editElement;
	// (枚举型数据)选择数据，默认为空
	private Integer data;
	// 数据来源 1 空 2 配置 3 查询数据表
	private Byte dataFrom;
	// 新增数据时传入方式 1 前端传入，2 后台默认, 从data字段中取
	private Byte addType;
	// 是否可以update 0 不需要update 1 可以
	private Byte updateType;
	// 查询时计算方式(在分页查询时，使用哪种计算方式 EQ: =, GT: >, LT:<, NE: !=, GE: >=, LE: <=, LK: like {}%, LA: like %{}%, NN: not null)
	private Byte queryType;
	// 所属用户
	private Long userId;
	// 创建时间
	private Date createTime;

	public TableColumn() {}

	TableColumn(String name, Integer dataType, String typeName, Integer dataLength, Byte nullable,
				Byte autoIncrement, String comment, Integer tableId, Integer dbId, Long userId) {
		this.name = name;
		this.dataType = dataType;
		this.typeName = typeName;
		this.dataLength = dataLength;
		this.nullable = nullable;
		this.autoIncrement = autoIncrement;
		this.comment = comment;
		this.tableId = tableId;
		this.dbId = dbId;
		this.userId = userId;
	}
    public TableColumn(String name, Integer dataType, String typeName, Integer dataLength, Byte nullable,
                       Byte autoIncrement, String comment) {
        this.name = name;
        this.dataType = dataType;
        this.typeName = typeName;
        this.dataLength = dataLength;
        this.nullable = nullable;
        this.autoIncrement = autoIncrement;
        this.comment = comment;
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Integer getDbId() {
		return dbId;
	}

	public void setDbId(Integer dbId) {
		this.dbId = dbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Byte getNullable() {
		return nullable;
	}

	public void setNullable(Byte nullable) {
		this.nullable = nullable;
	}

	public Byte getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(Byte autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getEditElement() {
		return editElement;
	}

	public void setEditElement(Integer editElement) {
		this.editElement = editElement;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Byte getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(Byte dataFrom) {
		this.dataFrom = dataFrom;
	}

	public Byte getAddType() {
		return addType;
	}

	public void setAddType(Byte addType) {
		this.addType = addType;
	}

	public Byte getUpdateType() {
		return updateType;
	}

	public void setUpdateType(Byte updateType) {
		this.updateType = updateType;
	}

	public Byte getQueryType() {
		return queryType;
	}

	public void setQueryType(Byte queryType) {
		this.queryType = queryType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "TableColumn [id: " + id + ", tableId: " + tableId + ", dbId: " + dbId + ", name: "
				+ name + ", label: " + label + ", dataType: " + dataType + ", dataType: " + dataType
				+ ", dataLength: " + dataLength + ", sort: " + sort + ", nullable: " + nullable
				+ ", autoIncrement: " + autoIncrement + ", defaultValue: " + defaultValue + ", comment: "
				+ comment + ", editElement: " + editElement + ", data: " + data + ", dataFrom: "
				+ dataFrom + ", addType: " + addType + ", updateType: " + updateType + ", queryType: "
				+ queryType + ", userId: " + userId + ", createTime: " + createTime + "]";
	}
}