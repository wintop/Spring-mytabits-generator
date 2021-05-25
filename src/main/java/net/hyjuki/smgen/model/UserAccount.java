package net.hyjuki.smgen.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户帐号信息
 */
public class UserAccount implements Serializable {
	// 帐号ID
	private Long id;
	// 登录用户名
	private String userName;
	// 登录密码
	private String password;
	// 盐值
	private String salt;
	// 角色
	private Integer roleId;
	// 对应人员ID
	private Long userId;
	// 创建时间
	private Date createTime;
	// 最近登录时间
	private Date loginTime;
	// 状态 0 无效 1 有效 2 冻结
	private Byte status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAccount [id: " + id + ", userName: " + userName + ", password: " + password + ", salt: "
				+ salt + ", roleId: " + roleId + ", userId: " + userId + ", createTime: "
				+ createTime + ", loginTime: " + loginTime + ", status: " + status + "]";
	}
}