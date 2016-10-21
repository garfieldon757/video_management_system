package com.adp.service;

import com.adp.model.Role;
import com.adp.model.User;

public interface UserManager {
	public void addUser(User user);
	public User findUser(String username);
	public Role findRole(int roleID); 
	
}
