package com.adp.dao;

import java.util.List;

import com.adp.model.AuthorizationList;
import com.adp.model.Role;
import com.adp.model.User;

public interface UserDAO {
	public void addUser(User user);
	public User getUserByEmail(String email);
	public Role findRole(int roleID);
	public void updateUser(User user);
	public String existUser(String userName);
	public User updateUserRole(User user);
	public AuthorizationList insertAuthorizationList(User applyAuthUserID, Role role);
	//public AuthorizationList updateAuthorizationList(User giveAuthorizationUserID);
	public List<AuthorizationList> getAuthListByApplyAuthUser(User applyAuthUser);
}
