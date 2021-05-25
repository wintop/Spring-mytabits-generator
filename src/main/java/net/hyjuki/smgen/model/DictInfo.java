package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 数据字典信息
 */
public class DictInfo {
	// 主键ID
	private Integer id;
	// 字典名称
	private Integer name;
	// 数据展示方式: 1 list 2 map
	private Integer type;
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

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
		return "DictInfo [id: " + id + ", name: " + name + ", type: " + type + ", userId: " + userId
				+ ", createTime: " + createTime + "]";
	}
}