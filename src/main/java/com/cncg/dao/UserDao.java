package com.cncg.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cncg.entity.Role;
import com.cncg.entity.User;
import com.cncg.util.StringUtils;

/**
 * 用户Dao接口
 * @author Administrator
 *
 */
@Repository
public class UserDao extends BaseDao<User>{
	
    public User get(User user) {
        return (User) super.getSession().get(User.class,user.getUserId());
    }
    
    public List<User> findList(User user) {
        StringBuffer hql = new StringBuffer("from User where 1=1 ");
        if(StringUtils.isNotEmpty(user.getUserName()))
            hql.append("and userName like :userName ");
        if(user.getRoleId() != null)
            hql.append("and roleId=:roleId ");
        Query query = super.getSession().createQuery(hql.toString());
        if(StringUtils.isNotEmpty(user.getUserName()))
            query.setParameter("userName", "%"+user.getUserName()+"%");
        if(user.getRoleId() != null)
            query.setParameter("roleId", user.getRoleId());
        if(user.getPage() != null) {
            query.setFirstResult(user.getPage().getStart());
            query.setMaxResults(user.getPage().getEnd());
        }
        return query.list();
    }
    
    public int findCount(User user) {
        StringBuffer hql = new StringBuffer("select count(*) from User where 1=1 ");
        if(StringUtils.isNotEmpty(user.getUserName()))
            hql.append("and userName like :userName ");
        if(user.getRoleId() != null)
            hql.append("and roleId=:roleId ");
        Query query = super.getSession().createQuery(hql.toString());
        if(StringUtils.isNotEmpty(user.getUserName()))
            query.setParameter("userName", "%"+user.getUserName()+"%");
        if(user.getRoleId() != null)
            query.setParameter("roleId", user.getRoleId());
        return ((Long) query.uniqueResult()).intValue();
    }
    
    public int update(User user) {
        if(user.getRoleId() !=null) {
            Role role = (Role) super.getSession().get(Role.class, user.getRoleId());
            user.setRole(role);
        }
        super.getSession().update(user);
        return 0;
    }
    
    public int insert(User user) {
        if(user.getRoleId() !=null) {
            Role role = (Role) super.getSession().get(Role.class, user.getRoleId());
            user.setRole(role);
        }
        return (int) super.getSession().save(user);
    }
    
    public int delete(User user) {
        String hql = "delete from User where userId in (:ids)";
        List<Integer> delIds = new ArrayList<>();
        for(String id : user.getIds().split(",")){
            delIds.add(StringUtils.toInteger(id));
        }
        Query query = super.getSession().createQuery(hql);
        query.setParameterList("ids", delIds);
        return query.executeUpdate();
    }
    
    public void updateBatch(List<User> userList){
        
    }
    
    public void insertBatch(List<User> userList) {
        
    }
    
    //****************************扩展方法*************************************
    
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
	    String hql = "from User where userName=:userName and password=:password and roleId=:roleId";
	    Query query = super.getSession().createQuery(hql);
	    query.setParameter("userName", user.getUserName());
        query.setParameter("password", user.getPassword());
        query.setParameter("roleId", user.getRoleId());
	    return (User) query.uniqueResult();
	}

	/**
	 * 查找重复登录名
	 * @param user
	 * @return
	 */
	public List<User> findRepeatUserName(User user) {
	    StringBuffer hql = new StringBuffer("from User where userName=:userName ");
	    if(user.getUserId() != null)
	        hql.append("and userId!=:userId ");
        Query query = super.getSession().createQuery(hql.toString());
        query.setParameter("userName", user.getUserName());
        if(user.getUserId() != null)
            query.setParameter("userId", user.getUserId());
        return query.list();
	}
	
}
