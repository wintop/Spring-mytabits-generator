package net.hyjuki.smgen.genconfig.model;

/**
 * 表的字段信息
 */
public class TableColumn {
	// 主键ID
	private Integer id;
	// 所属表的表名
	private String tableName;
	// 字段名
	private String name;
	// 数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)
	private Integer dataType;
	// 展示顺序
	private Integer sort;
	// 是否不空
	private Byte isNull;
	// 是否自增
	private Byte autoIncrement;
	// 默认值
	private String defaultValue;
	// 备注
	private String comment;
	// 页面编辑使用元素 默认使用input
	private Integer editElement;
	// (枚举型数据)选择数据，默认为空
	private Integer data;
	// 数据来源 1 空 2 配置 3 查询数据表
	private Byte dataFrom;
	// 新增数据时传入方式 1 前端传入，2 后台默认
	private Byte addType;
	// 是否可以update 0 不需要update 1 可以
	private Byte updateType;
	// 查询时计算方式(在分页查询时，使用哪种计算方式 =, >, <, !=, >=, <=, like {}%, like %{}%, not null)
	private Byte queryType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Byte getIsNull() {
		return isNull;
	}

	public void setIsNull(Byte isNull) {
		this.isNull = isNull;
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

	@Override
	public String toString() {
		return "TableColumn [id: " + id + ", tableName: " + tableName + ", name: " + name + ", dataType: "
				+ dataType + ", sort: " + sort + ", isNull: " + isNull + ", autoIncrement: "
				+ autoIncrement + ", defaultValue: " + defaultValue + ", comment: " + comment + ", editElement: "
				+ editElement + ", data: " + data + ", dataFrom: " + dataFrom + ", addType: " + addType
				+ ", updateType: " + updateType + ", queryType: " + queryType + "]";
	}
}