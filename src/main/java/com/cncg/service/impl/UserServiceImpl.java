package com.cncg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cncg.dao.UserDao;
import com.cncg.entity.User;
import com.cncg.service.UserService;
import com.cncg.util.StringUtils;

/**
 * 用户Service实现类
 * @author Administrator
 *
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;

	
	@Override
	public User get(User user) {
		return userDao.get(user);
	}
	
	@Override
	public List<User> findList(User user) {
		return userDao.findList(user);
	}
	
	@Override
	public int findCount(User user) {
		return userDao.findCount(user);
	}
	
	@Override
	public List<User> findPageList(User user) {
		return userDao.findList(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int save(User user) {
		if(user.getUserId() == null){
			return userDao.insert(user);
		} else{
			return userDao.update(user);
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public int delete(User user) {
		return userDao.delete(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void insertBatch(List<User> userList) {
		userDao.insertBatch(userList);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void updateBatch(List<User> userList) {
		userDao.updateBatch(userList);
	}

	//--------------------add--------------------
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}
	
	@Override
	public List<User> findRepeatUserName(User user) {
		return userDao.findRepeatUserName(user);
	}

	
}
