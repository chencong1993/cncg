package com.cncg.service;

import java.util.List;
import java.util.Map;

import com.cncg.entity.Menu;
import com.cncg.entity.User;

/**
 * 菜单Service接口
 * @author Administrator
 *
 */
public interface MenuService extends BaseService<Menu>{

	/**
	 * 加载用户菜单
	 * @param currentUser
	 * @return
	 */
	public List<Menu> LoadUserMenus(User user);
}
