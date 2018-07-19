package com.cncg.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基类Dao
 * @author chencong
 * @date 2018年6月22日 下午4:59:45
 * @version 1.0 
 * @param <T>
 */
public class BaseDao<T> {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 获取 Session
     */
    public Session getSession(){  
      return sessionFactory.getCurrentSession();
    }

    /**
     * 强制与数据库同步
     */
    public void flush(){
        getSession().flush();
    }

    /**
     * 清除缓存数据
     */
    public void clear(){ 
        getSession().clear();
    }
    
    
    //******************************************************************
    /*public T get(T entity) {
        return getSession().createQuery("").uniqueResult();
	}
	
	public List<T> findList(T entity) {
        return null;
	    
	}
	
	public int findCount(T entity) {
        return 0;
	    
	}
	
	public int update(T entity) {
        return 0;
	    
	}
	
	public int insert(T entity) {
        return 0;
	    
	}
	
	public int delete(T entity) {
        return 0;
	    
	}
	
	public void updateBatch(List<T> entityList){
	    
	}
	
	public void insertBatch(List<T> entityList) {
	    
	}*/

}
