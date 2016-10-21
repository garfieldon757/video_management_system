package com.adp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.adp.model.Role;
import com.adp.model.User;
import com.adp.service.UserManager;


@Controller
public class UserController {
	@Autowired(required=true)
	UserManager um;
	
	@RequestMapping(value="login_page")
	public String login_page(){
		System.out.println("ttttt");
		return null;//"login";
	}

	@RequestMapping(value="login")
	public String login(@RequestParam String email, String password){
		
		System.out.println(email);
		System.out.println(password);
		
		return "homePage";
	}
	
	@RequestMapping(value="signUp_page")
	public String signUp_page(Model model, HttpServletRequest request){
		
		
		return "SignUp";
	}
	
	@RequestMapping(value="signUp")
	public String signUp(Model model, HttpServletRequest request){

		//User user = new User("男","ss","4123","233232@dd");
				User user = new User();
				String sex = request.getParameter("sex");
				String userName = request.getParameter("userName");
				String userPassword = request.getParameter("userPassword");
				String email = request.getParameter("email");
				user.setSex(sex);
				user.setUserName(userName);
				user.setUserPassword(userPassword);
				user.setEmail(email);
				
				Role role = um.findRole(1);//设置用户注册默认权限为“普通用户”,roleID为1
//				Role role = new Role();
//				role.setRoleID(1);
//				role.setRoleName("普通用户");
				
				role.getUserList().add(user);
				user.setRole(role);
				um.addUser(user);//存储这条user数据到数据库
			
				//User findUser = um.findUser("ss");
				//model.addAttribute("user", findUser);
				return "adVideoManagement";
		//		ModelAndView mv = new ModelAndView("homePage");
//		mv.addObject(userName);
//		mv.addObject("s", "success!!");
//		return mv;
	}
	@RequestMapping(value="test")
	public String signUp_page(){
		
		Role findRole = um.findRole(1);
		System.out.println(findRole.getRoleName());
		return "SignUp";
	}
	
}
