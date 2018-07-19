package com.cncg.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cncg.entity.Menu;
import com.cncg.entity.User;
import com.cncg.util.StringUtils;


/**
 * 菜单Dao
 * @author Administrator
 *
 */
@Repository
public class MenuDao extends BaseDao<Menu>{

    public Menu get(Menu menu) {
        return (Menu) super.getSession().get(Menu.class, menu.getMenuId());
    }
    
    public List<Menu> findList(Menu menu) {
        StringBuffer hql = new StringBuffer("from Menu where 1=1 ");
        if(StringUtils.isNotEmpty(menu.getMenuName()))
            hql.append("and menuName like :menuName ");
        Query query = super.getSession().createQuery(hql.toString());
        if(StringUtils.isNotEmpty(menu.getMenuName()))
            query.setParameter("menuName", "%"+menu.getMenuName()+"%");
        if(menu.getPage() != null) {
            query.setFirstResult(menu.getPage().getStart());
            query.setMaxResults(menu.getPage().getEnd());
        }
        return query.list();
    }
    
    public int findCount(Menu menu) {
        StringBuffer hql = new StringBuffer("select count(*) from Menu where 1=1 ");
        if(StringUtils.isNotEmpty(menu.getMenuName()))
            hql.append("and menuName like :menuName ");
        Query query = super.getSession().createQuery(hql.toString());
        if(StringUtils.isNotEmpty(menu.getMenuName()))
            query.setParameter("menuName", "%"+menu.getMenuName()+"%");
        return ((Long) query.uniqueResult()).intValue();
    }
    
    public int update(Menu menu) {
    	super.getSession().update(menu);
        return 0;
        
    }
    
    public int insert(Menu menu) {
    	super.getSession().save(menu);
        return 0;
        
    }
    
    public int delete(Menu menu) {
        String hql = "delete from Menu where menuId in (:ids)";
        List<Integer> delIds = new ArrayList<>();
        for(String id : menu.getIds().split(",")){
            delIds.add(StringUtils.toInteger(id));
        }
        Query query = super.getSession().createQuery(hql);
        query.setParameterList("ids", delIds);
        return query.executeUpdate();
    }
    
    public void updateBatch(List<Menu> menuList){
        
    }
    
    public void insertBatch(List<Menu> menuList) {
        
    }
    
	/**
	 * 根据权限加载用户的主菜单
	 * @param user
	 * @return
	 */
	public List<Menu> findUserMenus(User user) {
	    String hql = "select menuIds from Role where roleId = :roleId";
        Query query = super.getSession().createQuery(hql);
        query.setParameter("roleId", user.getRole().getRoleId());
        String menuIds = (String) query.uniqueResult();
        if(StringUtils.isBlank(menuIds)) 
            return new ArrayList<Menu>();
        List<Integer> list = new ArrayList<>();
        for(String menuId : menuIds.split(",")) {
        	list.add(StringUtils.toInteger(menuId));
        }
	    String hql2 = "from Menu where menuId IN (:menuList)";
        Query query2 = super.getSession().createQuery(hql2);
        query2.setParameterList("menuList", list);
        return query2.list();
	}
	
	
}
