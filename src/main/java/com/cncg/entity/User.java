package com.cncg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name="t_user")
@DynamicInsert @DynamicUpdate
public class User extends BaseEntity<User>{
	
	//表实体
    private Integer userId;

    private String userName;

    private String password;

    private Integer roleId;
    
    private String userDescription;
    
    private Role role;
    
	public User() {
		super();
	}

	public User(Integer userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	public User(String userName, String password, Integer roleId, String roleName) {
		super();
		this.userName = userName;
		this.password = password;
		this.role.setRoleId(roleId);
		this.role.setRoleName(roleName);
	}

	@Id
	@GeneratedValue
    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
    
    public String getPassword() {
        return password;
    }

    @Transient
    public Integer getRoleId() {
        return roleId;
    }

    public String getUserDescription() {
        return userDescription;
    }
    
    @OneToOne
    @JoinColumn(name="roleId",referencedColumnName="roleId")
    public Role getRole() {
        return role;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}