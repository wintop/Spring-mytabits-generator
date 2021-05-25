package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 表的索引信息
 */
public class TableIndex {
	// 主键ID
	private Integer id;
	// 所属表ID
	private Integer tableId;
	// 索引名称
	private String name;
	// 索引类型: key, primary key, unique key
	private String type;
	// 所属数据库
	private Long dbId;
	// 所属用户
	private Long userId;
	// 创建时间
	private Date createTime;

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

	public Long getDbId() {
		return dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
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
		return "TableIndex [id: " + id + ", tableId: " + tableId + ", name: " + name + ", type: " + type
				+ ", dbId: " + dbId + ", userId: " + userId + ", createTime: " + createTime + "]";
	}
}