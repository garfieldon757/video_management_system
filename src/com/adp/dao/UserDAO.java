package com.adp.dao;

import java.util.List;

import com.adp.model.AuthorizationList;
import com.adp.model.Role;
import com.adp.model.User;

public interface UserDAO {
	public void addUser(User user);
	public User getUserByEmail(String email);
	public Role findRole(int roleID);
	public AuthorizationList findAuthList(int authListID);
	public void updateUser(User user);
	public String existUser(String userName);
	public User updateUserRole(User user);
	public AuthorizationList insertAuthorizationList(User applyAuthUser, Role role, String applyDateTime);
	public void updateAuthorizationList(int authListID, User giveAuthUser, String authStatus, String processDateTime);
	public List<AuthorizationList> getAuthListByApplyAuthUser(User applyAuthUser);
	public List<AuthorizationList> getAllAuthList();
}
