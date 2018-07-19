package com.cncg.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cncg.entity.Menu;
import com.cncg.entity.Tree;
import com.cncg.entity.User;
import com.cncg.model.Page;
import com.cncg.service.MenuService;
import com.cncg.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuService menuService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 主框架加载左侧导航菜单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/mainMenu")
	public String LoadMainMenu(HttpServletRequest request, Model model){
		User currentUser = (User) request.getSession().getAttribute("user");
		List<Menu> menus = menuService.LoadUserMenus(currentUser);
		model.addAttribute("menus", menus);
		return "sys/main/mainMenu";
	}
	
	/**
	 * 菜单管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String menuManage(HttpServletRequest request,HttpServletResponse response,Menu menu,ModelMap model){
		Page<Menu> page = new Page<Menu>(request,response);
		menu.setPage(page);
		page.setCount(menuService.findCount(menu));
		page.setList(menuService.findList(menu));
		model.addAttribute("page",page);
		return "sys/menu/menuList";
	}
	
	/**
	 * 菜单管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/menuList")
	@ResponseBody
	public Object menuList(HttpServletRequest request,HttpServletResponse response,Menu menu,ModelMap model){
		Page<Menu> page = new Page<Menu>(request,response);
		menu.setPage(page);
		page.setCount(menuService.findCount(menu));
		page.setList(menuService.findList(menu));
		return page;
	}
	
	/**
	 * 上级菜单树（单选）
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/pareMenu")
	public String LoadPareMenu(HttpServletRequest request, Menu menu,Model model){
		List<Tree> trees = new ArrayList<Tree>();
		List<Menu> menuList = menuService.findList(new Menu());
		for(Menu menu2 : menuList){
			Tree tree = new Tree(menu2); 
			if(menu2.getMenuId().equals(menu.getParentId())) {
				tree.setChecked(true);
			}
			trees.add(tree);
		}
		String treeStr=null;
		try {
			treeStr = objectMapper.writeValueAsString(trees);
		} catch (JsonProcessingException e) {
			treeStr = "";
		}
		model.addAttribute("treeStr",treeStr);
		return "sys/menu/pareMenu";
	}
	
	/**
	 * 角色菜单授权树
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/roleMenu")
	public String LoadRoleMenu(HttpServletRequest request, User user,Model model){
		List<Tree> trees = new ArrayList<Tree>();
		List<Menu> menuList = menuService.findList(new Menu());
		List<Menu> menus = menuService.LoadUserMenus(user);
		List<Integer> list = new ArrayList<>();
		for(Menu menu : menus){
			list.add(menu.getMenuId());
		}
		for(Menu menu : menuList){
			Tree tree = new Tree(menu); 
			if(list.contains(menu.getMenuId())) {
				tree.setChecked(true);
			}
			trees.add(tree);
		}
		String treeStr;
		try {
			treeStr = objectMapper.writeValueAsString(trees);
		} catch (JsonProcessingException e) {
			treeStr = "";
		}
		model.addAttribute("treeStr",treeStr);
		return "sys/menu/roleMenu";
	}
	
	@RequestMapping("/form")
	public String form(HttpServletRequest request,Menu menu,Model model){
		if(menu.getMenuId() != null){
			menu = menuService.get(menu);
		}
		model.addAttribute("menu",menu);
		return "sys/menu/menuForm";
	}
	
	/**
	 * 批量删除菜单
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delMenu(Menu menu,HttpServletRequest request) throws Exception{
		System.out.println(menu.getIds());
		try{
			int delNum=menuService.delete(menu);
			System.out.println("已删除"+menu.getIds().split(",").length+"个用户");
			return super.resultMap(true,"您已成功删除<font color='red'>"+menu.getIds().split(",").length+"</font>条数据！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "删除错误！");
		}
	}
	
	/**
	 * 编辑菜单
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object saveMenu(Menu menu) throws Exception{
		try{
			menuService.save(menu);
			return super.resultMap(true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "编辑用户出错！");
		}
	}
	//**********************
	/**
	 * 加载菜单项(不分页)
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/loadMenu")
	public List<Menu> selectMenu(HttpServletRequest request,Menu menu){
		List<Menu> list = null;
		try {
			list =  getListByParentId(menu);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/
	/**
	 * 加载菜单项(循环拼接json树)
	 * @param parentId 
	 * @return
	 * @throws Exception
	 */
	/*public List<Menu> getListByParentId(Menu menu)throws Exception{
		List<Menu> menus = menuService.findList(menu);
		for(int i=0;i<menus.size();i++){
			Menu menu2 = menus.get(i);
			if("open".equals(menu2.getState())){
				continue;
			}else{
				Menu menu3 = new Menu();
				menu3.setParentId(menu2.getMenuId());
				menu2.setChildren(getListByParentId(menu3));
			}
		}
		return menus;
	}*/

	/**
	 * 批量删除菜单项
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/delMenu")
	public Object delMenu(HttpServletRequest request,Menu menu){
		try{
			// 是否已授权否则无法删除
			Menu child = new Menu();
			child.setParentId(menu.getMenuId());
			List<Menu> list = menuService.findList(child);
			if(list.size()>0){//先判断是否有子节点
				return super.resultMap(false, "该菜单存在子节点不能删除!");
			}else{
				menuService.delete(menu);
				Menu sibling = new Menu();
				sibling.setParentId(menu.getParentId());
				List<Menu> childs = menuService.findList(sibling);
				if(childs==null || childs.size()==0) {
					Menu menu3 = new Menu();
					menu3.setMenuId(menu.getParentId());
					List<Menu> parent = menuService.findList(menu3);
					if(parent!=null && parent.size()>0) {
						parent.get(0).setState("open");
						parent.get(0).setMenuId(menu.getParentId());
						menuService.update(parent.get(0));
					}
				}
				return super.resultMap(true);
			}
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "删除数据出错！");
		}
	}*/
	
	/**
	 * 新增菜单项
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/addMenu")
	public Object addMenu(Menu menu,HttpServletRequest request){
		try{
			Menu menu2 = new Menu();
			menu2.setMenuId(menu.getParentId());
			List<Menu> parent = menuService.findList(menu2);
			if(parent!=null && parent.size()>0) {
				parent.get(0).setState("closed");
				menuService.update(parent.get(0));
			}
			menuService.insert(menu);
			return super.resultMap(true);
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "新增菜单出错！");
		}
	}*/
	
	/**
	 * 修改菜单项
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/editMenu")
	public Object editMenu(Menu menu,HttpServletRequest request){
		try{
			menuService.update(menu);
			return super.resultMap(true);
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "修改菜单出错！");
		}
	}*/

}
