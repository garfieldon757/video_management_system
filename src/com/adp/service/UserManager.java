package com.adp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.adp.model.AuthorizationList;
import com.adp.model.User;

public interface UserManager {

	public String login(HttpServletRequest request);
	public String signUp(HttpServletRequest request);
	public User getSession(HttpServletRequest request, String sessionKey);
	public User updateSession(HttpServletRequest request, User user);//???泛型
	public User edit_personalProfile(HttpServletRequest request);
	public String existUser(String userName);
	public User elevationPrivilege2ProUser(HttpServletRequest request);
	public List<AuthorizationList> getAuthListByApplyAuthUser(User applyAuthUser);
	
}
