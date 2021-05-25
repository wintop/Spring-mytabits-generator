package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 表的索引字段信息
 */
public class IndexColumn {
	// 主键ID
	private Integer id;
	// 索引Id
	private Integer idxId;
	// 字段名称
	private String colName;
	// 字段数据类型
	private Integer dataType;
	// 顺序
	private Integer sort;
	// 表ID
	private Integer tableId;
	// 所属数据库
	private Integer dbId;
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

	public Integer getIdxId() {
		return idxId;
	}

	public void setIdxId(Integer idxId) {
		this.idxId = idxId;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
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
		return "IndexColumn [id: " + id + ", idxId: " + idxId + ", colName: " + colName + ", dataType: "
				+ dataType + ", sort: " + sort + ", tableId: " + tableId + ", dbId: " + dbId + ", userId: "
				+ userId + ", createTime: " + createTime + "]";
	}
}