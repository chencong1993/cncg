package com.cncg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cncg.dao.MenuDao;
import com.cncg.entity.Menu;
import com.cncg.entity.User;
import com.cncg.service.MenuService;
import com.cncg.util.StringUtils;

/**
 * 菜单Service实现类
 * @author Administrator
 *
 */
@Service("menuService")
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuDao menuDao;


	@Override
	public Menu get(Menu menu) {
		return menuDao.get(menu);
	}

	@Override
	public List<Menu> findList(Menu menu) {
		return menuDao.findList(menu);
	}

	@Override
	public int findCount(Menu menu) {
		return menuDao.findCount(menu);
	}

	@Override
	public List<Menu> findPageList(Menu menu) {
		return menuDao.findList(menu);
	}

	@Override
	@Transactional(readOnly = false)
	public int save(Menu menu) {
		if(menu.getMenuId() != null){
			return menuDao.insert(menu);
		} else{
			return menuDao.update(menu);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int delete(Menu menu) {
		return menuDao.delete(menu);
	}

	@Override
	@Transactional(readOnly = false)
	public void insertBatch(List<Menu> menuList) {
		menuDao.insertBatch(menuList);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateBatch(List<Menu> menuList) {
		menuDao.updateBatch(menuList);
	}
	
	//--------------------add--------------------

	@Override
	public List<Menu> LoadUserMenus(User user) {
		return menuDao.findUserMenus(user);
	}

}
