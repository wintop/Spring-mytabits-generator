package net.hyjuki.smgen.genconfig.model;

/**
 * 表的索引信息
 */
public class TableIndex {
	// 主键ID
	private Integer id;
	// 索引名称
	private String name;
	// 索引类型: key, primary, unique
	private String type;
	// 索引的字段(用,分开)
	private String colName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	@Override
	public String toString() {
		return "TableIndex [id: " + id + ", name: " + name + ", type: " + type + ", colName: " + colName + "]";
	}
}