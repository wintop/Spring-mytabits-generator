package net.hyjuki.smgen.genconfig.model;

/**
 * 表数据信息
 */
public class TableInfo {
	// 主键ID
	private Integer id;
	// 表名
	private String name;
	// 所属数据库
	private String schmName;
	// 引擎 InnoDb
	private String engine;
	// 数据类型
	private String charset;
	// 备注
	private String comment;
	// 是否需要缓存
	private Byte cache;
	// 数据增改展示方式
	private Byte auShow;
	// 数据查看展示方式 null 不用展示 dialog 弹框 page 页面
	private Byte detailShow;

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

	public String getSchmName() {
		return schmName;
	}

	public void setSchmName(String schmName) {
		this.schmName = schmName;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Byte getCache() {
		return cache;
	}

	public void setCache(Byte cache) {
		this.cache = cache;
	}

	public Byte getAuShow() {
		return auShow;
	}

	public void setAuShow(Byte auShow) {
		this.auShow = auShow;
	}

	public Byte getDetailShow() {
		return detailShow;
	}

	public void setDetailShow(Byte detailShow) {
		this.detailShow = detailShow;
	}

	@Override
	public String toString() {
		return "TableInfo [id: " + id + ", name: " + name + ", schmName: " + schmName + ", engine: "
				+ engine + ", charset: " + charset + ", comment: " + comment + ", cache: " + cache
				+ ", auShow: " + auShow + ", detailShow: " + detailShow + "]";
	}
}