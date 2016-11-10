package com.adp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adp.model.Algorithm;
import com.adp.model.AuthorizationList;
import com.adp.model.User;
import com.adp.model.Video;
import com.adp.model.VideoCategory;
import com.adp.service.AlgorithmManager;
import com.adp.service.UserManager;
import com.adp.service.VideoManager;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


@Controller
public class UserController {
	@Autowired(required=true)
	UserManager um;
	
	@Autowired(required=true)
	VideoManager vm;
	
	@RequestMapping(value="login_page")
	public String login_page(){
		
		return "Login";
	}

	@RequestMapping(value="login")
	public String login(HttpServletRequest request){
		
		String result_page_url = um.login(request) ;
		return result_page_url;
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "main_page";
	}
	
	@RequestMapping(value="signUp_page")
	public String signUp_page(){
		
		return "SignUp";
	}
	
	@RequestMapping(value="signUp")
	public String signUp(HttpServletRequest request){

				String result_page_url = um.signUp(request);
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
	
	@RequestMapping("elevationPrivilege2ProUser_apply")
	public ModelAndView elevationPrivilege2ProUser_apply(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("AuthSettings");
		User user = um.elevationPrivilege2ProUser_apply(request);
		List<AuthorizationList> authList = um.getAuthListByApplyAuthUser(user);
		if(user != null){
			mv.addObject(user);
			mv.addObject("authList", authList);
		}
		return mv;
	}
	
	@RequestMapping("authProcess_load")
	public ModelAndView authProcess_load(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("AuthProcess");
		User user = um.getSession(request, "user");//session获取当前用户对象
		List<AuthorizationList> authList = um.getAllAuthList();
		if(authList != null){
			mv.addObject(user);
			mv.addObject("authList", authList);
		}
		return mv;//跳转至AuthProcess.jsp页面
	}
	
	@RequestMapping("elevationPrivilege2ProUser_process_agree")
	public ModelAndView elevationPrivilege2ProUser_process_agree(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("AuthProcess");
		int authListID = Integer.parseInt(request.getParameter("authListID"));
		um.elevationPrivilege2ProUser_process_agree(request, authListID);
		
		List<AuthorizationList> authList = um.getAllAuthList();
		if(authList != null){
			mv.addObject("authList", authList);
		}
		return mv;//跳转至AuthProcess.jsp页面
	}
	
	@RequestMapping("elevationPrivilege2ProUser_process_deny")
	public ModelAndView elevationPrivilege2ProUser_process_deny(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("AuthProcess");
		int authListID = Integer.parseInt(request.getParameter("authListID"));
		um.elevationPrivilege2ProUser_process_deny(request, authListID);
		
		List<AuthorizationList> authList = um.getAllAuthList();
		if(authList != null){
			mv.addObject("authList", authList);
		}
		return mv;//跳转至AuthProcess.jsp页面
	}
	
	@RequestMapping("ajax_searchProcessedAuthListByApplyAuthUserID")
	@ResponseBody
	public String ajax_searchProcessedAuthListByApplyAuthUserID(String ApplyAuthUserID){
		
		List<AuthorizationList> al = um.searchProcessedAuthListByApplyAuthUserID( Integer.parseInt(ApplyAuthUserID) );
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String s = JSONArray.fromObject(al , config).toString();
		
		return s;	
	}
	
	@RequestMapping("backToMainPage")
	public String backToMainPage(){
		return "main_page";
	}
	
}
