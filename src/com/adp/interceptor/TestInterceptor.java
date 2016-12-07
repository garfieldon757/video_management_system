package com.adp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.adp.dao.UserDAO;
import com.adp.model.AuthorizationRoleRelation;
import com.adp.model.Role;
import com.adp.model.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Oliver
 * @DATE 2016/11/28.
 * aim:   com.adp.interceptor
 */
public class TestInterceptor implements HandlerInterceptor {

	@Autowired(required=true)
	private UserDAO userDAO;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        
    	Boolean returnFlag = false;
    	
    	/*************************1.登陆验证************************************/
    	User user = (User) request.getSession().getAttribute("user");   
        if(user == null){  
            System.out.println("Interceptor：跳转到login页面！");  
            request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);  
            return returnFlag;
        }else{
        	System.out.println("Interceptor：已登陆，正常跳转！");  
        	returnFlag = true; 
        }
        
        /*************************2.权限查询************************************/
        Role role = user.getRole();
        List<AuthorizationRoleRelation> authRoleRelationList = userDAO.getAuthRoleRelationListByRole(role);
        
        HttpSession session = request.getSession();
		session.setAttribute("authRoleRelationList", authRoleRelationList);//将用户对应角色的权限acl存储在session中
        
        return returnFlag;
      //todo 此处为false时，请求不会到达control层
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView mv) throws Exception {
        
    	System.out.println("postHandle----start"); 
    	/****做一个权限的小测试 start*****************/
		List<AuthorizationRoleRelation> authRoleRelationList = (List<AuthorizationRoleRelation>) request.getSession().getAttribute("authRoleRelationList");
		for(int i = 0 ; i < authRoleRelationList.size(); i++){
			AuthorizationRoleRelation authRoleRelation= authRoleRelationList.get(i);
			String resourceURI = authRoleRelation.getAuthorization().getResource().getResourceURI();//资源URL
			String operationValue = authRoleRelation.getAuthorization().getOperation().getOperationValue();//操作值
			
			mv.addObject( resourceURI , operationValue );
		}
		/****做一个权限的小测试 end*****************/
    	
    	System.out.println("postHandle----end");  //todo 可以用来修改信息，跳转等;ModelAndView是接受controller返回的对象
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion"); //todo 最后执行,主要用于清理资源使用
    }
}