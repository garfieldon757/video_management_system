package com.adp.dao;

import com.adp.model.Role;
import com.adp.model.User;

public interface UserDAO {
	public void addUser(User user);
	public User findUser(String username);
	public Role findRole(int roleID);
}
