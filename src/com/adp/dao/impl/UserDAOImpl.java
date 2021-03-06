package com.adp.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.UserDAO;
import com.adp.model.AuthorizationList;
import com.adp.model.AuthorizationRoleRelation;
import com.adp.model.DaoFunctionUpdateDetail;
import com.adp.model.FunctionLog;
import com.adp.model.Role;
import com.adp.model.User;
import com.adp.model.Video;
import com.adp.model.VideoCategory;

@Repository("ud")
@Transactional
public class UserDAOImpl implements UserDAO{

	@PersistenceContext(name="un")
	private EntityManager em ;
	
	@Override
	public void testDaoAspect(){
		System.out.println("This is userDAOIpml.testDaoAspect().");
		return ;
	}
	
	@Override
	public void fuck(int a, String s) {
		System.out.println(a);
		System.out.println(s);
		return ;
	}
	
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
	public User getUserByID(int userID) {
		// TODO Auto-generated method stub
		String jpql = "select u from User u where u.userID=:userID";
		List<User> resultList = em.createQuery(jpql).setParameter("userID", userID).getResultList();
		User user = null;
		if (resultList.size() != 0){
			user = resultList.get(0);
		}
		
		return user;
	}

	@Override
	public Role getRole(int roleID) {
		// TODO Auto-generated method stub
		String jpql = "select r from Role r where r.roleID=:roleID";
		List<Role> resultList = em.createQuery(jpql).setParameter("roleID", roleID).getResultList();
		Role role = resultList.get(0);
		return role;
	}
	
	@Override
	public AuthorizationList findAuthList(int authListID) {
		String jpql = "select al from AuthorizationList al where al.authListID=:authListID";
		List<AuthorizationList> resultList = em.createQuery(jpql).setParameter("authListID", authListID).getResultList();
		AuthorizationList authList = resultList.get(0);
		return authList;
	}
	
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		em.merge(user);
		return user;
	}
	@Override
	public String existUser(String userName) {
		String jpql = "select u from User u where u.userName =:userName";
		List<User> resultList = em.createQuery(jpql)
													.setParameter("userName", userName)
													.getResultList();
		if( resultList.isEmpty() ){
			return "available";
		}else{
			return resultList.get(0).getUserName();//"unavailable";
		}
	}
	
	@Override
	public User updateUserRole(User user) {
		Role role = getRole(2);
		user.setRole(role);
		em.merge(user);//更新user的role信息
		return user;
	}
	@Override
	public AuthorizationList insertAuthorizationList(User applyAuthUser, Role role, String applyDateTime) {
		AuthorizationList authList = new AuthorizationList();
		authList.setApplyAuthUser(applyAuthUser);
		authList.setGiveAuthUser(null);
		authList.setRole(role);
		authList.setAuthStatus("1");//1--待审核; 2--申请通过; 3--申请被拒
		authList.setApplyDateTime(applyDateTime);
		authList.setProcessDateTime(null);
		
		em.persist(authList);
		return authList;
	}
	
	@Override
	public void updateAuthorizationList(int authListID, User giveAuthUser, String authStatus, String processDateTime) {
		
		AuthorizationList authList = findAuthList(authListID);
		authList.setGiveAuthUser(giveAuthUser);
		authList.setAuthStatus(authStatus);//1--待审核; 2--申请通过; 3--申请被拒
		authList.setProcessDateTime(processDateTime);
		
		em.persist(authList);
		return;
	}
	
	@Override
	public List<AuthorizationList> getAuthListByApplyAuthUser(User applyAuthUser) {
		String jpql = "select al from AuthorizationList al where al.applyAuthUser=:applyAuthUser";
		//Integer applyAuthUserID = applyAuthUser.getuserID();
		List<AuthorizationList> authList = em.createQuery(jpql).setParameter("applyAuthUser", applyAuthUser).getResultList();
		
		return authList;
	}
	@Override
	public List<AuthorizationList> getAllAuthList() {
		String jpql = "select al from AuthorizationList al ";
		//Integer applyAuthUserID = applyAuthUser.getuserID();
		List<AuthorizationList> authList = em.createQuery(jpql).getResultList();
		
		return authList;
	}
	
	@Override
	public List<AuthorizationList> getProcessingAuthList() {
		String jpql = "select al from AuthorizationList al where al.authStatus =:authStatus";
		List<AuthorizationList> authList = em.createQuery(jpql)
																	.setParameter("authStatus", "1")
																	.getResultList();
		return authList;
	}
	@Override
	public List<AuthorizationList> getProcessedAuthList() {
		String jpql = "select al from AuthorizationList al where al.authStatus =:authStatusAgree or al.authStatus =:authStatusDeny";
		List<AuthorizationList> authList = em.createQuery(jpql)
																.setParameter("authStatusAgree", "2")
																.setParameter("authStatusDeny", "3")
																.getResultList();
		return authList;
	}
	
	@Override
	public VideoCategory getVideoCategoryByVideoCategoryName(String videoCategoryName) {
		String jpql = "select vc from VideoCategory vc where vc.videoCategoryName =:videoCategoryName";
		VideoCategory videoCategory = (VideoCategory) em.createQuery(jpql).setParameter("videoCategoryName", videoCategoryName).getResultList().get(0);
		return videoCategory;
	}
	
	@Override
	public void insertVideo(Video video) {
		// TODO Auto-generated method stub
		em.persist(video);
		return ;
	}
	
	@Override
	public List<AuthorizationList> searchProcessedAuthListByMultiParam(String applyUserNickName,
			String applyDateTimeStart, String applyDateTimeEnd, String processDateTimeStart, String processDateTimeEnd,
			String processResult) {
		
		String jpql = "select al from AuthorizationList al where al.applyAuthUser.userName =:applyUserNickName"
				+ " and al.applyDateTime >:applyDateTimeStart and al.applyDateTime <:applyDateTimeEnd "
				+ " and al.processDateTime >:processDateTimeStart and al.processDateTime <:processDateTimeEnd "
				+ " and al.authStatus =:processResult";
		List<AuthorizationList> al = em.createQuery(jpql).setParameter("applyUserNickName", applyUserNickName)
																					.setParameter("applyDateTimeStart", applyDateTimeStart)
																					.setParameter("applyDateTimeEnd", applyDateTimeEnd)
																					.setParameter("processDateTimeStart", processDateTimeStart)
																					.setParameter("processDateTimeEnd", processDateTimeEnd)
																					.setParameter("processResult", processResult)
																					.getResultList();
		
		return al;
	}
	@Override
	public List<AuthorizationRoleRelation> getAuthRoleRelationListByRole(Role role) {
		
		String jpql = "select arr from AuthorizationRoleRelation arr where arr.role =:role";
		List<AuthorizationRoleRelation> authRoleRelationList = em.createQuery(jpql).setParameter("role", role).getResultList();
		
		return authRoleRelationList;
	}
	@Override
	public int getUserTotalNum() {
		String jpql = "select count(u) from User u";
		int userTotalNum =  Integer.parseInt( em.createQuery(jpql).getResultList().get(0).toString() ) ;
		return userTotalNum;
	}
	@Override
	public int getSpecificUserTotalNum(Role role) {
		String jpql = "select count(u) from User u where u.role =:role";
		int specificUserTotalNum = Integer.parseInt( em.createQuery(jpql).setParameter("role", role).getResultList().get(0).toString() );
		return specificUserTotalNum;
	}
	
	@Override
	public int getAuthListTotalNum() {
		String jpql = "select al from AuthorizationList al";
		List<AuthorizationList> al = em.createQuery(jpql).getResultList();
		//int authListTotalNum =  Integer.parseInt( em.createQuery(jpql).getResultList().get(0).toString() ) ;
		return 1;//authListTotalNum;
	}
	
	@Override
	public int getSpecificAuthListTotalNum(String authStatus) {
		String jpql = "select count(al) from AuthorizationList al where al.authStatus =:authStatus";
		int authListSpecificTotalNum =  Integer.parseInt( em.createQuery(jpql).setParameter("authStatus", authStatus ).getResultList().get(0).toString() ) ;
		return authListSpecificTotalNum;
	}
	
	@Override
	public int getVideoTotalNum() {
		String jpql = "select count(v) from Video v";
		int videoTotalNum =  Integer.parseInt( em.createQuery(jpql).getResultList().get(0).toString() ) ;
		return videoTotalNum;
	}
	@Override
	public int getAlgorithmTotalNum() {
		String jpql = "select count(al) from Algorithm al";
		int algorithmTotalNum =  Integer.parseInt( em.createQuery(jpql).getResultList().get(0).toString() ) ;
		return algorithmTotalNum;
	}
	
	@Override
	public List<FunctionLog> getFunctionLogByDatetime(String userName, String dateTimeStart, String dateTimeEnd) {
		String functionType = "controller";
		String jpql = "select fl from FunctionLog fl where fl.user.userName =:userName and fl.dateTimeStart >:dateTimeStart and fl.dateTimeEnd <:dateTimeEnd and fl.function.functionType =:functionType";
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String dateTimeStart_dao = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
		
		System.out.println("dao:" + dateTimeStart_dao);
		
		List<FunctionLog> functionLogList = em.createQuery(jpql).setParameter("userName", userName)
																									.setParameter("dateTimeStart", dateTimeStart)
																									.setParameter("dateTimeEnd", dateTimeEnd)
																									.setParameter("functionType", functionType )
																									.getResultList();
		
		now = new Timestamp(System.currentTimeMillis()); 
		String dateTimeEnd_dao = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
		
		System.out.println("dao" + dateTimeEnd_dao);
		
		return functionLogList;
	}

	@Override
	public List<FunctionLog> getSubFunctionLogByFatherFunctionIDAndDateTime(int fatherFunctionID, String dateTimeStart, String dateTimeEnd) {
		
		String jpql = "select fl from FunctionLog fl where fl.function.function.functionID =:fatherFunctionID and fl.dateTimeStart >=:dateTimeStart and fl.dateTimeEnd <=:dateTimeEnd ";
		List<FunctionLog> subFunctionLogList = em.createQuery(jpql).setParameter("fatherFunctionID", fatherFunctionID)
																											.setParameter("dateTimeStart", dateTimeStart)
																											.setParameter("dateTimeEnd", dateTimeEnd)
																											.getResultList();
				
		return subFunctionLogList;
	}

	@Override
	public List<DaoFunctionUpdateDetail> getDaoFunctionUpdateDetailByFunctionLogID(String functionLogID) {
		String jpql = "select dfud from DaoFunctionUpdateDetail dfud where dfud.functionLog.functionLogID =:functionLogID ";
		List<DaoFunctionUpdateDetail> daoFunctionUpdateDetail = em.createQuery(jpql).setParameter("functionLogID", Integer.parseInt( functionLogID ) ).getResultList();
		return daoFunctionUpdateDetail;
	}


	

}
