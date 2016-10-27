package com.adp.test;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;


import com.adp.model.Role;
import com.adp.model.User;

public class TestPage {
	
	
	@PersistenceContext(name="un")
	private EntityManager em ;
	
	
	public Role findRole(int roleID) {
		// TODO Auto-generated method stub
		String jpql = "select r from Role r where r.roleID=:roleID";
		Query query= em.createQuery(jpql);
		List<Role> resultList = query.setParameter("roleID", roleID).getResultList();
		Role role = resultList.get(0);
		
		return role;
	}
	
	public User findUser(String username) {
		// TODO Auto-generated method stub
		String jpql = "select u from User u where u.userName=:username";
		List<User> resultList = em.createQuery(jpql).setParameter("username", username).getResultList();
		User user = resultList.get(0);
		return user;
	}
	
	@Test
	public void testTime(){
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(now);
		 System.out.println(str);
	}
	
	@Test
	public void main(){
		testTime();
	}
	
	
}
