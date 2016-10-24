package com.adp.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.adp.model.User;

public interface UserManager {

	public String login(HttpServletRequest request);
	public String signUp(HttpServletRequest request);
	public User getSession(HttpServletRequest request);
	public User edit_personalProfile(HttpServletRequest request);
	public String existUser(String userName);
	
}
