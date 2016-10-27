package com.adp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adp.model.AuthorizationList;
import com.adp.model.User;
import com.adp.service.UserManager;


@Controller
public class UserController {
	@Autowired(required=true)
	UserManager um;
	
	@RequestMapping(value="login_page")
	public String login_page(){
		
		return "Login";
	}

	@RequestMapping(value="login")
	public String login(HttpServletRequest request){
		
		String result_page_url = um.login(request) ;
		return result_page_url;
	}
	
	@RequestMapping(value="signUp_page")
	public String signUp_page(){
		
		return "SignUp";
	}
	
	@RequestMapping(value="signUp")
	public String signUp(HttpServletRequest request){

				String result_page_url = um.signUp(request);//返回值怎么使用呢？		
				return result_page_url;
	}
	
	@RequestMapping("edit_personalProfile_load")
	public ModelAndView edit_personalProfile_load( HttpServletRequest request){
		ModelAndView mv = new ModelAndView("personalProfile");
		User user = um.getSession(request, "user");//session获取当前用户对象
		if(user != null){
			mv.addObject(user);
		}
		
		return mv;//跳转至personalProfile页面
	}
	
	@RequestMapping("edit_personalProfile_action")
	public ModelAndView edit_personalProfile_action(HttpServletRequest request){
			System.out.println("edit");
		    ModelAndView mv = new ModelAndView("personalProfile");
			User user = um.edit_personalProfile(request);
			mv.addObject(user);
			return mv;//依然跳转至personalProfile页面
	}
	
	@RequestMapping("ajax_userNameValidation")
	@ResponseBody
	public String ajax_userNameValidation(String userName){////
		
		String result = um.existUser(userName);
		return result;
	}
	
	@RequestMapping("authSettings_load")
	public ModelAndView authSettings_load(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("AuthSettings");
		User user = um.getSession(request, "user");//session获取当前用户对象
		List<AuthorizationList> authList = um.getAuthListByApplyAuthUser(user);
		if(user != null){
			mv.addObject(user);
			mv.addObject("authList", authList);
		}
		return mv;//跳转至AuthSettings.jsp页面
	}
	
	@RequestMapping("elevationPrivilege2ProUser")
	public ModelAndView elevationPrivilege2ProUser(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("AuthSettings");
		User user = um.elevationPrivilege2ProUser(request);
		List<AuthorizationList> authList = um.getAuthListByApplyAuthUser(user);
		if(user != null){
			mv.addObject(user);
			mv.addObject("authList", authList);
		}
		return mv;
	}
	
	@RequestMapping("authProcess_load")
	public ModelAndView authProcess_load(){
		return null;
	}
	
}
