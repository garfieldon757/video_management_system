package com.adp.dao;

import com.adp.model.Role;
import com.adp.model.User;

public interface UserDAO {
	public void addUser(User user);
	public User findUser(String email);
	public Role findRole(int roleID);
	public void updateUser(User user);
	public String existUser(String userName);
}
