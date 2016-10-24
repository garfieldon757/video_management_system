package com.adp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.UserDAO;
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
	public User findUser(String email) {
		// TODO Auto-generated method stub
		String jpql = "select u from User u where u.email=:email";
		List<User> resultList = em.createQuery(jpql).setParameter("email", email).getResultList();
		User user = null;
		for (User u : resultList) {
			user = u;
		}
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

}
