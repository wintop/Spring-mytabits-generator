package net.hyjuki.smgen.genconfig.model;

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
	private Integer root;

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

	public Integer getRoot() {
		return root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "DictConfig [id: " + id + ", value: " + value + ", type: " + type + ", label: " + label + ", sort: "
				+ sort + ", parentId: " + parentId + ", root: " + root + "]";
	}
}