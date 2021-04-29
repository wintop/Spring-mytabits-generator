package net.hyjuki.smgen.genconfig.model;

/**
 * 数据字典信息
 */
public class DictInfo {
	// 主键ID
	private Integer id;
	// 字典名称
	private Integer name;
	// 1 list 2 map
	private Integer type;

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

	@Override
	public String toString() {
		return "DictInfo [id: " + id + ", name: " + name + ", type: " + type + "]";
	}
}