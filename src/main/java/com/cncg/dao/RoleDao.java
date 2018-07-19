package com.cncg.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cncg.entity.Role;
import com.cncg.util.StringUtils;

/**
 * 角色Dao
 * @author Administrator
 *
 */
@Repository
public class RoleDao extends BaseDao<Role> {
	
    public Role get(Role role) {
        return (Role) super.getSession().get(Role.class, role.getRoleId());
    }
    
    public List<Role> findList(Role role) {
        StringBuffer hql = new StringBuffer("from Role where 1=1 ");
        if(StringUtils.isNotEmpty(role.getRoleName()))
            hql.append("and roleName like :roleName ");
        Query query = super.getSession().createQuery(hql.toString());
        if(StringUtils.isNotEmpty(role.getRoleName()))
            query.setParameter("roleName", "%"+role.getRoleName()+"%");
        if(role.getPage() != null) {
            query.setFirstResult(role.getPage().getStart());
            query.setMaxResults(role.getPage().getEnd());
        }
        return query.list();
    }
    
    public int findCount(Role role) {
        StringBuffer hql = new StringBuffer("select count(*) from Role where 1=1 ");
        if(StringUtils.isNotEmpty(role.getRoleName()))
            hql.append("and roleName like :roleName ");
        Query query = super.getSession().createQuery(hql.toString());
        if(StringUtils.isNotEmpty(role.getRoleName()))
            query.setParameter("roleName", "%"+role.getRoleName()+"%");
        return ((Long) query.uniqueResult()).intValue();
    }
    
    public int update(Role role) {
    	super.getSession().update(role);
        return 0;
        
    }
    
    public int insert(Role role) {
    	super.getSession().save(role);
        return 0;
    }
    
    public int delete(Role role) {
        String hql = "delete from Role where roleId in (:ids)";
        List<Integer> delIds = new ArrayList<>();
        for(String id : role.getIds().split(",")){
            delIds.add(StringUtils.toInteger(id));
        }
        Query query = super.getSession().createQuery(hql);
        query.setParameterList("ids", delIds);
        return query.executeUpdate();
    }
    
    public void updateBatch(List<Role> roleList){
        
    }
    
    public void insertBatch(List<Role> roleList) {
        
    }
  
    //****************************扩展方法*************************************
	
	public String findRoleNameById(Integer id) {
	    String hql = "select roleName from Role where roleId = :roleId";
        Query query = super.getSession().createQuery(hql);
        query.setParameter("roleId", id);
        return (String) query.uniqueResult();
	}
	
}
