package com.adp.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.adp.dao.UserDAO;
import com.adp.model.AuthorizationList;
import com.adp.model.FunctionLog;
import com.adp.model.Role;
import com.adp.model.User;
import com.adp.model.VideoCategory;
import com.adp.service.UserManager;

@Service("um")
public class UserManagerImpl implements UserManager{
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Override
	public void aopTest() {
		System.out.println("com.adp.service.ipml  userManagerIpml aopTest.");
		
	}
	
	@Override
	public String login(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userDAO.getUserByEmail(email);
		String db_pwd = user.getUserPassword();
		if(db_pwd.equals(password)){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "main_page";
		}else{
			return "error_page";
		}
		
	}
	
	@Override
	public String signUp(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		String sex = request.getParameter("sex");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String email = request.getParameter("email");
		user.setSex(sex);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setEmail(email);
		
		Role role = userDAO.getRole(1);//设置用户注册默认权限为“普通用户”,roleID为1	
		role.getUserList().add(user);
		user.setRole(role);
		userDAO.addUser(user);//存储这条user数据到数据库
		
		File file = new File("E:\\workspace2\\ADP\\WebContent\\ImageProcess\\" + userName);
		file.mkdirs();
		//建立一个属于该用户的文件夹
		
		return "main_page";
	}


	@Override
	public User getSession(HttpServletRequest request, String sessionKey) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(sessionKey);
		return user;
	}
	
	@Override
	public User updateSession(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return user;
	}

	@Override
	public User edit_personalProfile(HttpServletRequest request) {

		User user = new User();
		int userID = Integer.parseInt( request.getParameter("userID") );
		String sex = request.getParameter("sex");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String email = request.getParameter("email");
		int roleID = Integer.parseInt(request.getParameter("roleID")) ;
		Role role = userDAO.getRole(roleID);//role_value  这两行从前端即可获取role对象
		
		user.setUserID(userID);
		user.setSex(sex);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setEmail(email);
		user.setRole(role);
		
		userDAO.updateUser(user);//更新这条user数据到数据库
		
		return user;
	}

	@Override
	public String existUser(String userName) {
		String result = userDAO.existUser(userName);
		return result;
	}

	@Override
	public User elevationPrivilege2ProUser_apply(HttpServletRequest request) {
		User user = getSession(request, "user");//session获取当前用户对象
//		user = userDAO.updateUserRole(user);
//		user = updateSession (request, user) ;
		
		Role role = userDAO.getRole(2);
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String applyDateTime = df.format(now);
		
		userDAO.insertAuthorizationList(user, role, applyDateTime);
		
		return user;
	}
	
	@Override
	public List<AuthorizationList> getAuthListByApplyAuthUser(User user) {
		List<AuthorizationList> authList = userDAO.getAuthListByApplyAuthUser(user);
		return authList;
	}

	@Override
	public List<AuthorizationList> getAllAuthList() {
		List<AuthorizationList> authList = userDAO.getAllAuthList();
		return authList;
	}
	
	@Override
	public List<AuthorizationList> getProcessingAuthList() {
		List<AuthorizationList> authListProcessing = userDAO.getProcessingAuthList() ;
		return authListProcessing;
	}

	@Override
	public List<AuthorizationList> getProcessedAuthList() {
		List<AuthorizationList> authListProcessed = userDAO.getProcessedAuthList();
		return authListProcessed;
	}

	@Override
	public void elevationPrivilege2ProUser_process_agree(HttpServletRequest request, int authListID, int applyAuthUserID) {
		
		User giveAuthUser = getSession(request, "user");//session获取当前用户对象
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processDateTime = df.format(now);
		
		User applyAuthUser = userDAO.getUserByID(applyAuthUserID);
		Role rolePro = userDAO.getRole(2);
		applyAuthUser.setRole(rolePro);
		
		userDAO.updateAuthorizationList(authListID, giveAuthUser, "2", processDateTime);//修改authStatus参数为2，表示处理申请为“同意”状态
		userDAO.updateUser(applyAuthUser);
		
		return;
	}

	@Override
	public void elevationPrivilege2ProUser_process_deny(HttpServletRequest request, int authListID) {
		
		User giveAuthUser = getSession(request, "user");//session获取当前用户对象
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processDateTime = df.format(now);
		
//		User applyAuthUser = userDAO.getUserByID(applyAuthUserID);
//		Role rolePro = userDAO.getRole(2);
//		applyAuthUser.setRole(rolePro);
		
		userDAO.updateAuthorizationList(authListID, giveAuthUser, "3", processDateTime);//修改authStatus参数为2，表示处理申请为“同意”状态
//		userDAO.updateUser(applyAuthUser);
		
		return;
	}

	@Override
	public List<AuthorizationList> searchProcessedAuthListByApplyAuthUserID(int applyAuthUserID) {
		User applyAuthUser = userDAO.getUserByID(applyAuthUserID);
		List<AuthorizationList> al = userDAO.getAuthListByApplyAuthUser(applyAuthUser);
		return al;
	}

	@Override
	public User updateUser(User user) {
		
		User userReturn = userDAO.updateUser(user);//更新这条user数据到数据库
		return userReturn;
	}

	@Override
	public User getUser(int userID) {
		
		User user = userDAO.getUserByID(userID);
		return user;
	}

	@Override
	public List<AuthorizationList> searchProcessedAuthListByMultiParam(String applyUserNickName,
																														String applyDateTimeStart, 
																														String applyDateTimeEnd, 
																														String processDateTimeStart, 
																														String processDateTimeEnd,
																														String processResult) 
	{
		List<AuthorizationList> al = userDAO.searchProcessedAuthListByMultiParam(applyUserNickName , applyDateTimeStart , applyDateTimeEnd ,
																																processDateTimeStart , processDateTimeEnd , processResult);
		
		return al;
	}

	@Override
	public HashMap<String, Integer> getUserMonitorData() {
		
		HashMap<String , Integer> userMonitorData = new HashMap<String , Integer>();
		int userTotalNum = userDAO.getUserTotalNum();
		Role role_trial = userDAO.getRole(1);
		int trialUserTotalNum = userDAO.getSpecificUserTotalNum(role_trial);
		Role role_pro = userDAO.getRole(2);
		int proUserTotalNum = userDAO.getSpecificUserTotalNum(role_pro); 
		Role role_admin = userDAO.getRole(3);
		int adminTotalNum = userDAO.getSpecificUserTotalNum(role_admin);
		userMonitorData.put("userTotalNum", userTotalNum);
		userMonitorData.put("trialUserTotalNum", trialUserTotalNum);
		userMonitorData.put("proUserTotalNum", proUserTotalNum);
		userMonitorData.put("adminTotalNum", adminTotalNum);
		
		return userMonitorData;
	}
	
	@Override
	public HashMap<String, Integer> getAuthListMonitorData() {
		
		HashMap<String , Integer> authMonitorData = new HashMap<String , Integer>();
		int authListTotalNum = userDAO.getAuthListTotalNum();
		int authList1TotalNum = userDAO.getSpecificAuthListTotalNum("1");//待审核
		int authList2TotalNum = userDAO.getSpecificAuthListTotalNum("2");//审核通过
		int authList3TotalNum = userDAO.getSpecificAuthListTotalNum("3");//审核被拒
		authMonitorData.put("authListTotalNum", authListTotalNum);
		authMonitorData.put("authList1TotalNum" , authList1TotalNum );
		authMonitorData.put("authList2TotalNum" , authList2TotalNum );
		authMonitorData.put("authList3TotalNum" , authList3TotalNum );
		
		return authMonitorData;
	}

	@Override
	public HashMap<String, Integer> getVideoMonitorData() {
		
		HashMap<String , Integer>videoMonitorData = new HashMap<String , Integer>();
		int videoTotalNum = userDAO.getVideoTotalNum();
		videoMonitorData.put("videoTotalNum", videoTotalNum);
		
		return videoMonitorData;
	}

	@Override
	public HashMap<String, Integer> getAlgorithmMonitorData() {

		HashMap<String , Integer> algorithmMonitorData = new HashMap<String , Integer>();
		int algorithmTotalNum = userDAO.getAlgorithmTotalNum();
		algorithmMonitorData.put("algorithmTotalNum", algorithmTotalNum);
		
		return algorithmMonitorData;
	}
	
	@Override
	public List<FunctionLog> getFunctionLogByDatetime(String dateTimeStart, String dateTimeEnd) {
		List<FunctionLog> functionLogList = userDAO.getFunctionLogByDatetime(dateTimeStart, dateTimeEnd);
		return functionLogList;
	}

	@Override
	public List<FunctionLog> getSubFunctionLogByFatherFunctionID(int fatherFunctionID) {
		
		List<FunctionLog> subFunctionLogList = userDAO.getSubFunctionLogByFatherFunctionID(fatherFunctionID);
		
		return subFunctionLogList;
	}






}
