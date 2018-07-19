package com.cncg.service;

import java.util.List;
import java.util.Map;

import com.cncg.entity.Role;

/**
 * 角色Service接口
 * @author Administrator
 *
 */
public interface RoleService extends BaseService<Role>{
	
	public List<Role> getRoles(Role role);
	
}
