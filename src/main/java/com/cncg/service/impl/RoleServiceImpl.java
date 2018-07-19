package com.cncg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cncg.dao.RoleDao;
import com.cncg.entity.Role;
import com.cncg.service.RoleService;
import com.cncg.util.StringUtils;

/**
 * 角色Service实现类
 * @author Administrator
 *
 */
@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;

	
	@Override
	public Role get(Role role) {
		return roleDao.get(role);
	}
	
	@Override
	public List<Role> findList(Role role) {
		return roleDao.findList(role);
	}
	
	@Override
	public int findCount(Role role) {
		return roleDao.findCount(role);
	}
	
	@Override
	public List<Role> findPageList(Role role) {
		return roleDao.findList(role);
	}

	@Override
	@Transactional(readOnly = false)
	public int save(Role role) {
		if(role.getRoleId() == null){
			return roleDao.insert(role);
		} else{
			return roleDao.update(role);
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public int delete(Role role) {
		return roleDao.delete(role);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void insertBatch(List<Role> roleList) {
		roleDao.insertBatch(roleList);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void updateBatch(List<Role> roleList) {
		roleDao.updateBatch(roleList);
	}
	
	//--------------------add--------------------
	
	@Override
	public List<Role> getRoles(Role role) {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role(null,"全部"));
		roles.addAll(findList(new Role()));
		return roles;
	}
	
}
