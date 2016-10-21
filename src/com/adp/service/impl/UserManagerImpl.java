package com.adp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.dao.UserDAO;
import com.adp.model.Role;
import com.adp.model.User;
import com.adp.service.UserManager;

@Service("um")
public class UserManagerImpl implements UserManager{
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user);
	}

	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		return userDAO.findUser(username);
	}

	@Override
	public Role findRole(int roleID) {
		// TODO Auto-generated method stub
		return userDAO.findRole(roleID);
	}


}
