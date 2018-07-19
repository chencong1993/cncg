package com.cncg.service;

import java.util.List;

public interface BaseService<T> {
	
	public T get(T entity);
	
	public List<T> findList(T entity);
	
	public int findCount(T entity);
	
	public List<T> findPageList(T entity);
	
	public int save(T entity);
	
	public int delete(T entity);
	
	public void insertBatch(List<T> entityList);
	
	public void updateBatch(List<T> entityList);
	
}
