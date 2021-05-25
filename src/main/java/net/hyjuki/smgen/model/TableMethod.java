package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 表生成的对象下的方法信息
 */
public class TableMethod {
	// 主键ID
	private Integer id;
	// 方法名称 get, find, save, update, remove, total, pagination
	private String name;
	// 方法的调用方式method：POST, GET, PUT, DELETE
	private String requestType;
	// 是否需要在路径中传入参数
	private Boolean pathVariable;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Boolean getPathVariable() {
		return pathVariable;
	}

	public void setPathVariable(Boolean pathVariable) {
		this.pathVariable = pathVariable;
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
		return "TableMethod [id: " + id + ", name: " + name + ", requestType: " + requestType + ", pathVariable: "
				+ pathVariable + ", tableId: " + tableId + ", dbId: " + dbId + ", userId: " + userId
				+ ", createTime: " + createTime + "]";
	}
}