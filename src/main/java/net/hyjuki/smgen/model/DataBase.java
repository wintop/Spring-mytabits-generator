package net.hyjuki.smgen.model;

import java.util.Date;

/**
 * 数据库信息
 */
public class DataBase {
	// 主键ID
	private Integer id;
	// 数据库类型, Mysql PostgreSQL
	private String type;
	// 数据库名称
	private String name;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 主机名或IP
	private String host;
	// 端口号
	private String port;
	// schema
	private String charSet;
	// Driver
	private String driver;
	// 所属人员
	private Long userId;
	// 创建时间
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
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
		return "DataBase [id: " + id + ", type: " + type + ", name: " + name + ", userName: " + userName
				+ ", password: " + password + ", host: " + host + ", port: " + port + ", charSet: " + charSet
				+ ", driver: " + driver + ", userId: " + userId + ", createTime: " + createTime + "]";
	}
}