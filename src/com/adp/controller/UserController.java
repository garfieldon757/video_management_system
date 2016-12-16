package com.adp.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adp.model.AuthorizationList;
import com.adp.model.DaoFunctionUpdateDetail;
import com.adp.model.FunctionLog;
import com.adp.model.User;
import com.adp.service.AlgorithmManager;
import com.adp.service.UserManager;
import com.adp.service.VideoManager;
import com.adp.util.FunctionLogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Component
@Controller
public class UserController {
	@Autowired(required=true)
	UserManager um;
	
	@Autowired(required=true)
	VideoManager vm;
	
	@Autowired(required=true)
	AlgorithmManager am;
	
	@RequestMapping(value="plainTest")
	public void plainTest(){
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
		
		for(int i=0; i<32; i++){
			for(int j=0; j<5; j++){
				for(int k=0; k<3; k++){
					
				}
			}
		}
		System.out.println("***********************plain test1*********************");
		now = new Timestamp(System.currentTimeMillis()); 
		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
		
		System.out.println("************************" + dateTimeStart + "************************");
		System.out.println("************************" + dateTimeEnd + "************************");
		
		return ;
	}
	
	@RequestMapping(value="plainTest2")
	public void plainTest2(){
		
		um.getUser(1);
		
		return ;
	}
	
	@RequestMapping(value="plainTest3")
	public void plainTest3(){
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
		
		for(int i=0; i<32; i++){
			for(int j=0; j<5; j++){
				um.getFunctionLogByDatetime("maninit", "2016-12-13 15:30:43" , "2016-12-13 15:32:43");
				for(int k=0; k<3; k++){
					um.getFunctionLogByDatetime("maninit", "2016-12-13 15:30:43" , "2016-12-13 15:32:43");
				}
			}
		}
		System.out.println("***********************plain test3*********************");
		now = new Timestamp(System.currentTimeMillis()); 
		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
		
		System.out.println("************************" + dateTimeStart + "************************");
		System.out.println("************************" + dateTimeEnd + "************************");
		
		return ;
	}
	
	@RequestMapping(value="plainTest4")
	public void plainTest4(){
		
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
		
		um.getFunctionLogByDatetime("maninit", "2016-12-13 15:30:43" , "2016-12-13 15:32:43");
		
		for(int i=0; i<32; i++){
			um.getSubFunctionLogByFatherFunctionIDAndDateTime(121, "2016-12-13 15:30:43" , "2016-12-13 15:32:43");
			for(int j=0; j<5; j++){

				um.getSubFunctionLogByFatherFunctionIDAndDateTime(121, "2016-12-13 15:30:43" , "2016-12-13 15:32:43");
			}
		}
		System.out.println("***********************plain test4*********************");
		now = new Timestamp(System.currentTimeMillis()); 
		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
		
		System.out.println("************************" + dateTimeStart + "************************");
		System.out.println("************************" + dateTimeEnd + "************************");
		
		return ;
	}
	
	@RequestMapping(value="login_page")
	public String login_page(){
		
		return "Login";
	}

	@RequestMapping(value="login")
	public String login(HttpServletRequest request){
		
		String result_page_url = um.login(request) ;
		return result_page_url;
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "main_page";
	}
	
	@RequestMapping(value="signUp_page")
	public String signUp_page(){
		
		return "SignUp";
	}
	
	@RequestMapping(value="signUp")
	public String signUp(HttpServletRequest request){

				String result_page_url = um.signUp(request);
				return result_page_url;
	}
	
	@RequestMapping("edit_personalProfile_load")
	public ModelAndView edit_personalProfile_load( HttpServletRequest request){
		ModelAndView mv = new ModelAndView("personalProfile");
		
		User user = um.getSession(request, "user");//session获取当前用户对象
		if(user != null){
			mv.addObject(user);
		}
		
		return mv;//跳转至personalProfile页面
	}
	
	@RequestMapping("edit_personalProfile_action")
	public ModelAndView edit_personalProfile_action(HttpServletRequest request){
			System.out.println("edit");
		    ModelAndView mv = new ModelAndView("personalProfile");
			User user = um.edit_personalProfile(request);
			mv.addObject(user);
			return mv;//依然跳转至personalProfile页面
	}
	
	@RequestMapping("ajax_userNameValidation")
	@ResponseBody
	public String ajax_userNameValidation(String userName){////
		
		String result = um.existUser(userName);
		return result;
	}
	
	@RequestMapping("authSettings_load")
	public ModelAndView authSettings_load(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("AuthSettings");
		User user = um.getSession(request, "user");//session获取当前用户对象
		List<AuthorizationList> authList = um.getAuthListByApplyAuthUser(user);
		
		int apply_status = user.getApply_status() ;//0代表未申请，1代表正在申请待审核
		
		if(user != null ){
			mv.addObject(user);
			mv.addObject("authList", authList);
			mv.addObject("apply_status", apply_status);
		}
		return mv;//跳转至AuthSettings.jsp页面
	}
	
	@RequestMapping("elevationPrivilege2ProUser_apply")
	public ModelAndView elevationPrivilege2ProUser_apply(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("AuthSettings");
		User user = um.elevationPrivilege2ProUser_apply(request);
		List<AuthorizationList> authList = um.getAuthListByApplyAuthUser(user);
		
		user.setApply_status(1);
		um.updateUser(user);
		int apply_status = user.getApply_status() ;//0代表未申请，1代表正在申请待审核
		
		if(user != null && authList != null){
			mv.addObject(user);
			mv.addObject("authList", authList);
			mv.addObject("apply_status", apply_status);
		}
		return mv;
	}
	
	@RequestMapping("authProcess_load")
	public ModelAndView authProcess_load(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("AuthProcess");
		User user = um.getSession(request, "user");//session获取当前用户对象
		List<AuthorizationList> authListProcessing = um.getProcessingAuthList();
		List<AuthorizationList> authListProcessed = um.getProcessedAuthList();
		if(authListProcessing != null){
			mv.addObject(user);
			mv.addObject("authListProcessing", authListProcessing);
			mv.addObject("authListProcessed", authListProcessed);
		}
		
		return mv;//跳转至AuthProcess.jsp页面
	}
	
	@RequestMapping("elevationPrivilege2ProUser_process_agree")
	public ModelAndView elevationPrivilege2ProUser_process_agree(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("AuthProcess");
		int authListID = Integer.parseInt(request.getParameter("authListID"));
		int applyAuthUserID = Integer.parseInt( request.getParameter("applyAuthUserID") );
		um.elevationPrivilege2ProUser_process_agree(request, authListID , applyAuthUserID);
		
		User user = um.getUser(applyAuthUserID);
		user.setApply_status(2);
		um.updateUser(user);
		
		List<AuthorizationList> authListProcessing = um.getProcessingAuthList();
		List<AuthorizationList> authListProcessed = um.getProcessedAuthList();
		if(authListProcessing != null){
			mv.addObject(user);
			mv.addObject("authListProcessing", authListProcessing);
			mv.addObject("authListProcessed", authListProcessed);
		}
		return mv;//跳转至AuthProcess.jsp页面
	}
	
	@RequestMapping("elevationPrivilege2ProUser_process_deny")
	public ModelAndView elevationPrivilege2ProUser_process_deny(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("AuthProcess");
		int authListID = Integer.parseInt(request.getParameter("authListID"));
		int applyAuthUserID = Integer.parseInt( request.getParameter("applyAuthUserID") );
		um.elevationPrivilege2ProUser_process_deny(request, authListID);
		
		User user = um.getUser(applyAuthUserID);
		user.setApply_status(3);
		um.updateUser(user);
		
		List<AuthorizationList> authListProcessing = um.getProcessingAuthList();
		List<AuthorizationList> authListProcessed = um.getProcessedAuthList();
		if(authListProcessing != null){
			mv.addObject(user);
			mv.addObject("authListProcessing", authListProcessing);
			mv.addObject("authListProcessed", authListProcessed);
		}
		return mv;//跳转至AuthProcess.jsp页面
	}
	
	@RequestMapping("ajax_searchProcessedAuthListByApplyAuthUserID")
	@ResponseBody
	public String ajax_searchProcessedAuthListByApplyAuthUserID(String ApplyAuthUserID){
		
		List<AuthorizationList> al = um.searchProcessedAuthListByApplyAuthUserID( Integer.parseInt(ApplyAuthUserID) );
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String s = JSONArray.fromObject(al , config).toString();
//		String s = JSONArray.fromObject(al).toString();
		
		return s;	
	}
	
	@RequestMapping("backToMainPage")
	public String backToMainPage(){
		return "main_page";
	}
	
	@RequestMapping("processedAuthListSearch")
	//@ResponseBody
	public ModelAndView processedAuthListSearch(HttpServletRequest request){		//ajax_
		ModelAndView mv = new ModelAndView("AuthProcess");
		
		String applyUserNickName = request.getParameter("applyUserNickName");
		String applyDateTimeStart = request.getParameter("applyDateTimeStart");
		String applyDateTimeEnd = request.getParameter("applyDateTimeEnd");
		String processDateTimeStart = request.getParameter("processDateTimeStart");
		String processDateTimeEnd = request.getParameter("processDateTimeEnd");
		String processResult = request.getParameter("processResult");
		
		List<AuthorizationList> al = um.searchProcessedAuthListByMultiParam(applyUserNickName , applyDateTimeStart , applyDateTimeEnd ,
																														processDateTimeStart , processDateTimeEnd , processResult);
		
		User user = um.getSession(request, "user");//session获取当前用户对象
		mv.addObject(user);
		mv.addObject("authListProcessing", null);
		mv.addObject("authListProcessed", al);
		//String a = "[{ 'firstName': 'Brett' }]";

//		Role r = new Role();
//		r.setRoleID(4);
//		r.setRoleName("fachrs");
		
		
//		List<Algorithm> a = am.getAllAlgorithm();
//		List<AuthorizationList> al = um.getAllAuthList();
		
		
//		
//		JsonConfig config = new JsonConfig();
//		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		String authListJson = JSONArray.fromObject(u).toString();		
		
		return mv;//authListJson;
		
	}
	
	@RequestMapping("monitor_load")
	public ModelAndView monitor_load(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("Monitor");
		HashMap<String, Integer> userMonitorData = um.getUserMonitorData();//用户信息查询
		HashMap<String , Integer> authorizationListMonitorData = um.getAuthListMonitorData();//提权信息查询
		HashMap<String , Integer> videoMonitorData = um.getVideoMonitorData();//视频库查询
		HashMap<String , Integer> algorithmMonitorData = um.getAlgorithmMonitorData();//算法库查询
		mv.addObject("userMonitorData", userMonitorData);
		mv.addObject("authorizationListMonitorData", authorizationListMonitorData);
		mv.addObject("videoMonitorData", videoMonitorData);
		mv.addObject("algorithmMonitorData", algorithmMonitorData);
		
		return mv;
	}
	
	@RequestMapping("logMonitor_load")
	public ModelAndView logMonitor_load(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("LogMonitor");
		
		
		return mv;
	}
	
	@RequestMapping("logSearch_load")
	public ModelAndView logSearch_load(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("LogMonitor");
		return mv;
	}
	
	@RequestMapping("logSearch")
	public ModelAndView logSearch(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("LogMonitor");
		
		String userName = request.getParameter("userName");
		String logDateTimeStart = request.getParameter("logDateTimeStart");
		String logDateTimeEnd = request.getParameter("logDateTimeEnd");
	
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String dateTimeStart = df.format(now);//记录controller方法执行的起始时间，精确到毫秒
		System.out.println(dateTimeStart);
		
		List<FunctionLog> controllerFunctionLogList = um.getFunctionLogByDatetime(userName, logDateTimeStart, logDateTimeEnd);
		
		now = new Timestamp(System.currentTimeMillis()); 
		String dateTimeEnd = df.format(now);//记录controller方法执行的终止时间，精确到毫秒
		System.out.println(dateTimeEnd);
		
		List<FunctionLogUtil> controllerFunctionLogUtilList = new ArrayList<FunctionLogUtil>();
		for( int i = 0 ; i < controllerFunctionLogList.size() ; i++ ){
			if( controllerFunctionLogList.get(i).getFunction().getFunctionUrl().equals("logSearch") ){
                continue;
            }//将“logSearch”这一controllerFunction加入黑名单，因为查询负担太重
			FunctionLogUtil controllerFunctionLogUtil = new FunctionLogUtil();
			controllerFunctionLogUtil.setFunctionLog( controllerFunctionLogList.get(i) );
			
			
			List<FunctionLog> serviceFunctionLogList = um.getSubFunctionLogByFatherFunctionIDAndDateTime( controllerFunctionLogList.get(i).getFunction().getFunctionID() , controllerFunctionLogList.get(i).getDateTimeStart(), controllerFunctionLogList.get(i).getDateTimeEnd() );

			
		
						List<FunctionLogUtil> serviceFunctionLogUtilList = new ArrayList<FunctionLogUtil>();
			for(int j=0; j < serviceFunctionLogList.size(); j++){
				FunctionLogUtil serviceFunctionLogUtil = new FunctionLogUtil();
				serviceFunctionLogUtil.setFunctionLog( serviceFunctionLogList.get(j) );
				
				List<FunctionLog> daoFunctionLogList = um.getSubFunctionLogByFatherFunctionIDAndDateTime( serviceFunctionLogList.get(j).getFunction().getFunctionID() , serviceFunctionLogList.get(j).getDateTimeStart(), serviceFunctionLogList.get(j).getDateTimeEnd() );
				
				serviceFunctionLogUtil.setSubFunctionLogList( daoFunctionLogList );;
				serviceFunctionLogUtilList.add( serviceFunctionLogUtil );
			}
			
			controllerFunctionLogUtil.setSubFunctionLogUtilList( serviceFunctionLogUtilList );
			controllerFunctionLogUtilList.add( controllerFunctionLogUtil );
		} 
		
		
		//先根据条件查询controller，在根据时间约束，逐条查询service；同样的，进行查询dao。
		//最后返回值组成一个ArrayList。
		//返回给前台，去显示。
		
		mv.addObject( "controllerFunctionLogUtilList" , controllerFunctionLogUtilList  );
		
		return mv;
	}
	
	@RequestMapping("ajax_searchDaoFunctionUpdateDetailByFunctionLogID")
	@ResponseBody
	public String ajax_searchDaoFunctionUpdateDetailByFunctionLogID(String functionLogID) throws JsonProcessingException{
		
		List<DaoFunctionUpdateDetail> daoFunctionUpdateDetail = um.getDaoFunctionUpdateDetailByFunctionLogID(functionLogID);
//		String ss = "";
//		System.out.println(ss);
		//String s = JSONArray.fromObject(daoFunctionUpdateDetail).toString();
		ObjectMapper mapper = new ObjectMapper(); 
	    String s = mapper.writeValueAsString(daoFunctionUpdateDetail); 
	    
		return s ;	
	}
	
}
