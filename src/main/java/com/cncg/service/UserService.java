package com.cncg.service;

import java.util.List;
import java.util.Map;

import com.cncg.entity.User;

/**
 * 用户Service接口
 * @author Administrator
 *
 */
public interface UserService extends BaseService<User>{

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 判断用户名重复
	 * @param user
	 * @return
	 */
	public List<User> findRepeatUserName(User user);


}
