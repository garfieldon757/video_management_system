package com.adp.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.adp.dao.UserDAO;
import com.adp.model.Role;
import com.adp.model.User;
import com.adp.service.UserManager;

@Service("um")
public class UserManagerImpl implements UserManager{
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Override
	public String login(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userDAO.findUser(email);
		String db_pwd = user.getUserPassword();
		if(db_pwd.equals(password)){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "main_page";
		}else{
			return "error_page";
		}
		
	}
	
	@Override
	public String signUp(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		String sex = request.getParameter("sex");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String email = request.getParameter("email");
		user.setSex(sex);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setEmail(email);
		
		Role role = userDAO.findRole(1);//设置用户注册默认权限为“普通用户”,roleID为1			
		role.getUserList().add(user);
		user.setRole(role);
		userDAO.addUser(user);//存储这条user数据到数据库
		
		return "main_page";
	}


	@Override
	public User getSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}

	@Override
	public User edit_personalProfile(HttpServletRequest request) {

		User user = new User();
		int userID = Integer.parseInt( request.getParameter("userID") );
		String sex = request.getParameter("userID");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String email = request.getParameter("email");
		int roleID = Integer.parseInt(request.getParameter("roleID")) ;
		Role role = userDAO.findRole(roleID);//role_value  这两行从前端即可获取role对象
		
		user.setuserID(userID);
		user.setSex(sex);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setEmail(email);
		user.setRole(role);
		
		userDAO.updateUser(user);//更新这条user数据到数据库
		
		return user;
	}

	@Override
	public String existUser(String userName) {
		String result = userDAO.existUser(userName);
		return result;
	}

	



}
