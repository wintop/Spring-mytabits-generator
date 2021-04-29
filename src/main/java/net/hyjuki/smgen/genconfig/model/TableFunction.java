package net.hyjuki.smgen.genconfig.model;

/**
 * 表生成的对象下的方法信息
 */
public class TableFunction {
	// 主键ID
	private Integer id;
	// 表名
	private String tableName;
	// 方法名称 get, find, save, update, remove, findForPage
	private String name;

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

	@Override
	public String toString() {
		return "TableFunction [id: " + id + ", tableName: " + tableName + ", name: " + name + "]";
	}
}