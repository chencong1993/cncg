package com.cncg.entity;

public class Role extends BaseEntity<Role>{
	
	//表实体
    private Integer roleId;

    private String roleName;

    private String menuIds;

    private String roleDescription;

	public Role(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Role() {
		super();
	}
	
    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
	
}