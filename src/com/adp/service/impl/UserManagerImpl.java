package com.adp.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.adp.dao.UserDAO;
import com.adp.model.AuthorizationList;
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

		User user = userDAO.getUserByEmail(email);
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
	public User getSession(HttpServletRequest request, String sessionKey) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(sessionKey);
		return user;
	}
	
	@Override
	public User updateSession(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
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

	@Override
	public User elevationPrivilege2ProUser_apply(HttpServletRequest request) {
		User user = getSession(request, "user");//session获取当前用户对象
		user = userDAO.updateUserRole(user);
		user = updateSession (request, user) ;
		
		Role role = userDAO.findRole(2);
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String applyDateTime = df.format(now);
		
		userDAO.insertAuthorizationList(user, role, applyDateTime);
		
		return user;
	}
	
	@Override
	public List<AuthorizationList> getAuthListByApplyAuthUser(User user) {
		List<AuthorizationList> authList = userDAO.getAuthListByApplyAuthUser(user);
		return authList;
	}

	@Override
	public List<AuthorizationList> getAllAuthList() {
		List<AuthorizationList> authList = userDAO.getAllAuthList();
		return authList;
	}

	@Override
	public void elevationPrivilege2ProUser_process_agree(HttpServletRequest request, int authListID) {
		
		User giveAuthUser = getSession(request, "user");//session获取当前用户对象
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processDateTime = df.format(now);
		
		userDAO.updateAuthorizationList(authListID, giveAuthUser, "2", processDateTime);//修改authStatus参数为2，表示处理申请为“同意”状态
		
		return;
	}

	@Override
	public void elevationPrivilege2ProUser_process_deny(HttpServletRequest request, int authListID) {
		
		User giveAuthUser = getSession(request, "user");//session获取当前用户对象
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processDateTime = df.format(now);
		
		userDAO.updateAuthorizationList(authListID, giveAuthUser, "3", processDateTime);//修改authStatus参数为2，表示处理申请为“同意”状态
		
		return;
	}

	@Override
	public List<AuthorizationList> searchProcessedAuthListByApplyAuthUserID(int applyAuthUserID) {
		User applyAuthUser = userDAO.getUserByID(applyAuthUserID);
		List<AuthorizationList> al = userDAO.getAuthListByApplyAuthUser(applyAuthUser);
		return al;
	}

	



	

	



}
