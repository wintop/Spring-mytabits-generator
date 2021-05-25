package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 表数据信息
 */
public class TableInfo {
	// 主键ID
	private Integer id;
	// 表名
	private String name;
	// 前端展示名称
	private String label;
	// 引擎 InnoDb
	private String engine;
	// 数据类型
	private String charset;
	// 备注
	private String comment;
	// 是否需要缓存
	private Byte cache;
	// 是否返回ID
	private Byte retPrimary;
	// 数据创建(add)修改(update)展示方式 row 行内修改 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面
	private Byte auShow;
	// 数据查看展示方式 null 不用展示 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面
	private Byte detailShow;
	// databasemeta中的TABLE_CAT或者，实际数据同database.name
	private String dbName;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public Byte getRetPrimary() {
		return retPrimary;
	}

	public void setRetPrimary(Byte retPrimary) {
		this.retPrimary = retPrimary;
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

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
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
		return "TableInfo [id: " + id + ", name: " + name + ", label: " + label + ", engine: " + engine
				+ ", charset: " + charset + ", comment: " + comment + ", cache: " + cache + ", retPrimary: "
				+ retPrimary + ", auShow: " + auShow + ", detailShow: " + detailShow + ", dbName: "
				+ dbName + ", dbId: " + dbId + ", userId: " + userId + ", createTime: " + createTime + "]";
	}
}