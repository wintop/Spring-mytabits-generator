package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 数据字典配置信息
 */
public class DictConfig {
	// 主键ID
	private Integer id;
	// 值
	private String value;
	// 数据类型
	private Byte type;
	// 展示内容
	private String label;
	// 顺序
	private Integer sort;
	// 父节点
	private Integer parentId;
	// 根节点
	private Integer groupId;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
		return "DictConfig [id: " + id + ", value: " + value + ", type: " + type + ", label: " + label + ", sort: "
				+ sort + ", parentId: " + parentId + ", groupId: " + groupId + ", userId: " + userId
				+ ", createTime: " + createTime + "]";
	}
}