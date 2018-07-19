package com.cncg.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cncg.entity.Menu;
import com.cncg.entity.Role;
import com.cncg.model.Page;
import com.cncg.service.MenuService;
import com.cncg.service.RoleService;
import com.cncg.util.StringUtils;


@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 角色管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String roleManage(HttpServletRequest request,HttpServletResponse response,Role role,Model model){
		Page<Role> page = new Page<Role>(request,response);
		role.setPage(page);
		page.setCount(roleService.findCount(role));
		page.setList(roleService.findList(role));
		model.addAttribute("page",page);
		return "sys/role/roleList";
	}
	
	@RequestMapping("/form")
	public String form(HttpServletRequest request,Role role,Model model){
		if(role.getRoleId() != null){
			role = roleService.get(role);
		}
		model.addAttribute("roles",roleService.getRoles(new Role()));
		model.addAttribute("role",role);
		return "sys/role/roleForm";
	}
	
	/**
	 * 批量删除角色
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delRole(Role role,HttpServletRequest request) throws Exception{
		System.out.println(role.getRoleId());
		try{
			int delNum=roleService.delete(role);
			System.out.println("已删除"+role.getIds().split(",").length+"个角色");
			return super.resultMap(true,"您已成功删除<font color='red'>"+role.getIds().split(",").length+"</font>条数据！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "删除错误！");
		}
	}
	
	/**
	 * 编辑角色
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object saveRole(Role role) throws Exception{
		try{
			roleService.save(role);
			return super.resultMap(true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "编辑角色出错！");
		}
	}
	
	/**
	 * 保存角色授权菜单
	 * @param menu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveRoleMenu")
	@ResponseBody
	public Object saveRoleMenu(Role role,HttpServletRequest request) throws Exception{
		try{
			roleService.save(role);
			return super.resultMap(true);
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false);
		}
	}
	//*************
	/**
	 * 加载角色
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/loadRole")
	public Map<String,Object> selectRole(HttpServletRequest request,HttpServletResponse response,Role role){
		Map<String,Object> map = new HashMap<String,Object>();
		try{//TODO 分页查询角色
			Page page = new Page(request,response);
			role.setPage(page);
			List<Role> list=roleService.findList(role);
			int total = roleService.findCount(role);
			map.put("total",total);
			map.put("pageNum",list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return map;
	}*/
	
	/**
	 * 批量删除角色
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/delRole")
	public Object delUser(HttpServletRequest request,Role role){
		try{
			roleService.delete(role);
			return super.resultMap(true, "您已成功删除<font color=red>"+request.getParameter("count")+"</font>条数据！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "删除数据出错！");
		}
	}*/
	
	/**
	 * 新增角色
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/addRole")
	public Object addRole(HttpServletRequest request,Role role){
		try{
			roleService.insert(role);
			return super.resultMap(true);
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "<font color=red>新增用户出错！</font>");
		}
	}*/
	
	/**
	 * 修改角色
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/editRole")
	public Map<String, Object> editRole(Role role,HttpServletRequest request){
		try{
			roleService.update(role);
			return super.resultMap(true);
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "修改用户出错！");
		}
	}*/
	
	/**
	 * 授权加载
	 * @param model
	 * @param request 
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("setRole")
	public List<Menu> setRole(HttpServletRequest request,Role role){
		List<Menu> list = null;
		try{
			Role role2=roleService.get(role);
			//加载授权菜单
			list = getListByParentId(request.getParameter("parentId"),role2.getMenuIds());
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return list;
	}*/
	
	/**
	 * 加载授权菜单(循环拼接json树)
	 * @param parentId
	 * @param menuIds
	 * @return
	 * @throws Exception
	 */
	/*public List<Menu> getListByParentId(String parentId,String menuIds){
		List<Menu> list=null;
		try{
			list = getTreeGridMenuByParentId(parentId,menuIds);
			//easyui加载树必需字段
			for(Menu menu : list) {
				menu.setId(menu.getMenuId());
				menu.setText(menu.getMenuName());
			}
			
			for(int i=0;i<list.size();i++){
				Menu menu2 = list.get(i);
				if("open".equals(menu2.getState())){
					continue;
				}else{//注意这里取得是别名id
					menu2.setChildren(getListByParentId(menu2.getMenuId(),menuIds));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}*/
	
	/**
	 * 获取指定父节点的所有子节点
	 * @param parentId
	 * @param menuIds
	 * @return
	 * @throws Exception
	 */
	/*public List<Menu> getTreeGridMenuByParentId(String parentId,String menuIds)throws Exception{
		//此处取别名id,text以适应前台easyui框架的带复选框的树所需的。
		Menu menu = new Menu();
		menu.setParentId(parentId);
		List<Menu> menus = menuService.findList(menu);
		for(Menu menu2:menus){
			if(menuIds!=null && menuIds.indexOf(menu2.getMenuId())!=-1){
				menu2.setChecked(true);
			}
		}
		return menus;
	}*/
	
	/**
	 * 保存授权
	 * @param model
	 * @param request
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("setSaveRole")
	public Object setSaveRole(HttpServletRequest request,Role role){
		try{
			roleService.update(role);
			return super.resultMap(true, "授权成功！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "授权失败！");
		}
	}*/
}
