package com.cncg.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cncg.entity.Role;
import com.cncg.entity.User;
import com.cncg.model.Page;
import com.cncg.service.RoleService;
import com.cncg.service.UserService;
import com.cncg.util.StringUtils;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 用户管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String userManage(HttpServletRequest request,HttpServletResponse response,User user,Model model){
		Page<User> page = new Page<User>(request,response);
		user.setPage(page);
		page.setCount(userService.findCount(user));
		List<User> list = userService.findList(user);
		page.setList(userService.findList(user));
		model.addAttribute("roles",roleService.getRoles(new Role()));
		model.addAttribute("page",page);
		return "sys/user/userList";
	}
	
	@RequestMapping("/form")
	public String form(HttpServletRequest request,User user,Model model){
		if(user.getUserId() != null){
			user = userService.get(user);
		}
		model.addAttribute("roles",roleService.getRoles(new Role()));
		model.addAttribute("user",user);
		return "sys/user/userForm";
	}
	
	/**
	 * 批量删除用户
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delUser(User user,HttpServletRequest request) throws Exception{
		System.out.println(user.getUserId());
		try{
			int delNum=userService.delete(user);
			System.out.println("已删除"+user.getIds().split(",").length+"个用户");
			return super.resultMap(true,"您已成功删除<font color='red'>"+user.getIds().split(",").length+"</font>条数据！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "删除错误！");
		}
	}
	
	/**
	 * 编辑用户
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object saveUser(User user) throws Exception{
		try{
			List<User> list = userService.findRepeatUserName(user);
			if(list.size()>0) {
				return super.resultMap(false, "用户名重复！");
			}
			userService.save(user);
			return super.resultMap(true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			return super.resultMap(false, "编辑用户出错！");
		}
	}
	
}
