package com.adp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.UserDAO;
import com.adp.model.AuthorizationList;
import com.adp.model.Role;
import com.adp.model.User;

@Repository("ud")
@Transactional
public class UserDAOImpl implements UserDAO{

	@PersistenceContext(name="un")
	private EntityManager em ;
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		em.persist(user);
	}
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		String jpql = "select u from User u where u.email=:email";
		List<User> resultList = em.createQuery(jpql).setParameter("email", email).getResultList();
		User user = resultList.get(0);
		return user;
	}

	@Override
	public Role findRole(int roleID) {
		// TODO Auto-generated method stub
		String jpql = "select r from Role r where r.roleID=:roleID";
		List<Role> resultList = em.createQuery(jpql).setParameter("roleID", roleID).getResultList();
		Role role = resultList.get(0);
		
		return role;
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		em.merge(user);
		return;
	}
	@Override
	public String existUser(String userName) {
		String jpql = "select u from User u where u.userName =:userName";
		List<User> resultList = em.createQuery(jpql).setParameter("userName", userName).getResultList();
		if( resultList.isEmpty() ){
			return "available";
		}else{
			return "unavailable";
		}
	}
	
	@Override
	public User updateUserRole(User user) {
		Role role = findRole(2);
		user.setRole(role);
		em.merge(user);//更新user的role信息
		return user;
	}
	@Override
	public AuthorizationList insertAuthorizationList(User applyAuthUser, Role role) {
		AuthorizationList authList = new AuthorizationList();
		authList.setApplyAuthUser(applyAuthUser);
		authList.setGiveAuthUser(null);
		authList.setRole(role);
		authList.setAuthStatus(1);//1--待审核; 2--申请通过; 3--申请被拒
		
		em.persist(authList);
		return authList;
	}
	
//	@Override
//	public AuthorizationList updateAuthorizationList(User giveAuthorizationUser) {
//		AuthorizationList authList = new AuthorizationList();
//		authList.setApplyAuthUser(applyAuthUser);
//		authList.setGiveAuthUser(null);
//		authList.setRole(null);
//		authList.setAuthStatus(1);//1--待审核; 2--申请通过; 3--申请被拒
//		
//		em.persist(authList);
//		return authList;
//	}
	
	@Override
	public List<AuthorizationList> getAuthListByApplyAuthUser(User applyAuthUser) {
		String jpql = "select al from AuthorizationList al where al.applyAuthUser=:applyAuthUser";
		//Integer applyAuthUserID = applyAuthUser.getuserID();
		List<AuthorizationList> authList = em.createQuery(jpql).setParameter("applyAuthUser", applyAuthUser).getResultList();
		
		return authList;
	}
	
	

}
