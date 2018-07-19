package com.cncg.model;

public class CurrentUser {
	
	private String userName;
	private String password;
	private int roleId;
	private String roleName;
	
	
	public CurrentUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public CurrentUser(String userName, String password, int roleId, String roleName) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
		this.roleName = roleName;
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
