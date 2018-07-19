package com.cncg.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cncg.entity.Menu;
import com.cncg.entity.Role;
import com.cncg.entity.User;
import com.cncg.service.MenuService;
import com.cncg.service.RoleService;
import com.cncg.service.UserService;
@Controller
public class MainController extends BaseController{

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private MenuService menuService;
	
	/**
	 * 登陆页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	public String Login(Model model,HttpServletRequest request){
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null){
			return "redirect:/main";
		}else{
			// 加载角色列表
			List<Role> roles = roleService.findList(new Role());
			model.addAttribute("roles", roles);
			return "sys/login";
		}
	}
	
	/**
	 * 登陆信息验证
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="main")
	public String UserLogin(User user,Model model,HttpServletRequest request){
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null){
			//加载下拉框角色
			List<Role> roles = roleService.findList(new Role());
			model.addAttribute("roles", roles);
			return "sys/login";
		}else{
			//根据权限加载菜单项
			User currentUser = (User) session.getAttribute("user");
			List<Menu> menus = menuService.LoadUserMenus(currentUser);
			model.addAttribute("user", currentUser);
			model.addAttribute("menus", menus);
			return "sys/main/main";	
		}
	}
	
	/**
	 * 登陆信息验证
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="userLogin")
	@ResponseBody
	public Object UserLogin(User user,ModelMap model,HttpServletRequest request){
		HttpSession session=request.getSession();
		User currentUser = userService.login(user);
		if(currentUser!=null){
			session.setAttribute("user", currentUser);
			return super.resultMap(true);
		}else{
			return super.resultMap(false, "用户名或密码错误！");
		}
	}
	
	/**
	 * 修改用户密码
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyPassword")
	public Object modifyPassword(HttpServletRequest request){
		String newPassword = request.getParameter("newPassword");
		String oldPassword = request.getParameter("oldPassword");
		HttpSession session=request.getSession();
		User currentUser = (User) session.getAttribute("user");
		if(!oldPassword.equals(currentUser.getPassword())){
		    return super.resultMap(false, "原密码输入错误！");
		}
		try{
			userService.save(new User(currentUser.getUserId(),newPassword));
			currentUser.setPassword(newPassword);//更新session里密码
			return super.resultMap(true, "密码修改成功，下一次登录生效！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "密码修改失败！");
		}
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		request.getSession().invalidate();
		//response.sendRedirect("/mvc/login");
		return "redirect:/login";
	}
}
