<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html  >
<head>
    <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ad</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="shortcut icon" href="/public/images/favicons/favicon.ico">

    <link href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <!-- build:css(.) /public/css/app.min.css -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/app.css" rel="stylesheet" type="text/css">
    <!-- endbuild -->
   <body class="ng-scope">

<div class="ng-scope">
    <div class="navbar navbar-static-top ng-scope">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#/"></a>
            </div>
            <ul class="nav navbar-nav pull-right ng-scope">
	            <li  class="ng-scope" style="">
		            <a href="videoSearchInit?videoCategoryID=1&page=1" class="icon-settings">视频库主页</a>
		        </li>
		        <li id="dropdown_index" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">个人设置<span class="caret"></span></a>
	                <ul class="dropdown-menu" role="menu" >
			                <li>
			                    <a href="edit_personalProfile_load">个人信息编辑</a>
			                </li>
			                <c:if test="${ user.role.roleID == 1 or user.role.roleID == 2 }">
			                    <li>
			                        <a href="authSettings_load">个人权限申请</a>
			                    </li>
			                </c:if>
			                <c:if test="${ user.role.roleID == 3 }">
			                    <li>
			                        <a href="authProcess_load">角色申请处理</a>
			                    </li>
			                 </c:if>
	                </ul>
	            </li>
            </ul>
        </div>
    </div>
</div>
<div ng-include="'/public/app/templates/horizontal-header.html'" class="ng-scope"><!-- ngIf: currentUser && currentUser.isConsumer -->
    <div class="horizontal-header ng-scope" ng-if="currentUser &amp;&amp; currentUser.isConsumer">
        <div class="container ">
            <img class="pic-thumb pull-left" src="img/logo.jpg">
            <h4 id="userName" class="ng-binding" value="${user.userName}">${user.userName}</h4>
            <button type="button" class="btn btn-default pull-right" class="logout" ><a href="logout">退出</a></button>
        </div>
    </div>
</div>

<div id="main">
<div class="container">
    <div class="row">
        <div class="col-xs-3 ng-scope" >
            <div ng-show="isActive('/settings')" class="ng-scope" style="">
                <div class="page-header">
                    <h2>设置</h2>
                </div>
                <hr style="margin-top: 0;">
                <ul class="nav nav-stacked side-nav">
		                <li  class="active">
			                <a href="edit_personalProfile_load">个人信息编辑</a>
			            </li>
			            <c:if test="${ user.role.roleID == 1 or user.role.roleID == 2 }">
			                <li >
			                    <a href="authSettings_load">个人权限申请</a>
			                </li>
			            </c:if>
			            <c:if test="${ user.role.roleID == 3 }">
			                <li >
			                    <a href="authProcess_load">角色申请处理</a>
			                </li>
			            </c:if>
	            </ul>
            </div>
        </div>
        <div class="col-xs-9 col-xs-9-remove">
           <div class="ng-scope">
               <div class="page-header ng-scope">
                    <h1>个人信息编辑</h1>
                </div>
                <div class="ng-scope">
                    <div ng-switch-when="form" class="ng-scope">

                        <form name="form" action="edit_personalProfile_action">

                            <div class="ng-scope">
                                <div class="panel ng-scope">
                                    <div class="panel-body">


                                        <div id="userID_div" class="form-group" >
                                            <label for="userID">用户ID</label>
                                            <input id="userID" name="userID" class="form-control" type="text"  value="${user.userID}" disabled="true">
                                        </div>
                                        <div class="dropdown">
                                            <label for="sex">性别</label>
                                            <div id="sex_div">
                                                <select class="form-control" id="sex" name="sex">
                                                    
                                                    <c:if test="${user.sex == '男'}">
                                                    	<option>男</option>
                                                    	<option>女</option>
                                                    </c:if>
                                                    <c:if test="${user.sex == '女'}">
	                                                	<option>女</option>
	                                                	<option>男</option>
	                                                </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div id="userName_div" class="form-group" >
                                            <label for="userName" >昵称</label>
                                            <input id="userName" class="form-control" type="text" name="userName" value="${user.userName}">
                                            <span class="glyphicon glyphicon-ok form-control-feedback" style="display: none"></span>
                                            <span class="glyphicon glyphicon-remove form-control-feedback" style="display: none"></span>
                                        </div>
                                        <div id="userPassword_div" class="form-group  has-feedback" >
                                            <label for="userPassword">用户密码</label>
                                            <input id="userPassword" class="form-control" type="text" name="userPassword" value="${user.userPassword}" >
                                            <span class="glyphicon glyphicon-ok form-control-feedback" style="display: none"></span>
                                            <span class="glyphicon glyphicon-remove form-control-feedback" style="display: none"></span>
                                        </div>
                                        <div id="email_div" class="form-group has-feedback" >
                                            <label for="email">电子邮箱</label>
                                            <input id="email" class="form-control ng-valid-remote" type="text" name="email" value="${user.email}">
                                            <span class="glyphicon glyphicon-ok form-control-feedback" style="display: none"></span>
                                            <span class="glyphicon glyphicon-remove form-control-feedback" style="display: none"></span>
                                        </div>
                                        <div class="form-group" >
                                            <label for="roleName">用户角色</label>
                                            <input id="roleName" class="form-control" type="text" name="roleName"  value="${user.role.roleName}" disabled="true">
                                            <input id="roleID" class="form-control" type="text" name="roleID" style="display: none"  value="${user.role.roleID}" >
                                        </div>


                                    </div>
                                </div>
                                <button class="btn btn-primary" onclick="before_submit()">保存</button>
                            </div>

                        </form>

                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
</div>
<footer>
    <div class="container">
        <ul class="nav">
            <li><a href="/contact" target="_blank">Contact Us</a></li>
            <li><a href="/terms-caremarketplace" target="_blank">Terms - Care Marketplace</a></li>
            <li><a href="/faq-caremarketplace" target="_blank">FAQs - Care Marketplace</a></li>
        </ul>
    </div>
</footer>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<script type="text/javascript">


    $(document).ready(function(){
    	

        $("#userName").blur(function () {


             var userNameValue = $('#userName').val();
            $.ajax({
                url: "ajax_userNameValidation",
                data: { userName : userNameValue},
                type: "GET",
                success: function (response, status, xhr) {
                    
                    reg_result = response ;//返回用户名和当前页面session的用户名就不做为
                    									//,返回"available"表示userName可用，“unavailable“表示不可用
                    
                    if(reg_result == "available"){
                        $("#userName_div").removeClass("has-error");
                        $("#userName_div").addClass("has-success");
                        $("#userName_div > span:last").css('display', 'none');
                        $("#userName_div > span:first").css('display', 'block');
                        alert("用户名可用");
                    }else if( reg_result != "available" ){
                    	
                    	if( reg_result == $("#userName").val() ){
                            
                    		//不作为
                    		
                        }else if(reg_result == "unavailable"){
                        	$("#userName_div").removeClass("has-success");
	                        $("#userName_div").addClass("has-error");
	                        $("#userName_div > span:first").css('display', 'none');
	                        $("#userName_div > span:last").css('display', 'block');
	                        alert("用户名已被占用");
                        }
                    	
                    }
                    
                }
            });
            
        });

        $("#userPassword").blur(function () {
            /*
            var reg =  new RegExp(" "); // 检测密码是否符合足够的密码强度
            var userPassword = $('#userPassword').val();
            reg_result = reg.test(userPassword);

            if(reg_result){
                $("#userPassword_div").removeClass("has-error");
                $("#userPassword_div").addClass("has-success");
                $("#userPassword_div > span:last").css('display', 'none');
                $("#userPassword_div > span:first").css('display', 'block');
            }else {
                $("#userPassword_div").removeClass("has-success");
                $("#userPassword_div").addClass("has-error");
                $("#userPassword_div > span:first").css('display', 'none');
                $("#userPassword_div > span:last").css('display', 'block');
            }
            */
        });

        $("#email").blur(function () {
            var reg =  new RegExp(".*@.*\.?.*?\.(com|cn)");
            var email = $('#email').val();
            reg_result = reg.test(email);

            if(reg_result){
                $("#email_div").removeClass("has-error");
                $("#email_div").addClass("has-success");
                $("#email_div > span:last").css('display', 'none');
                $("#email_div > span:first").css('display', 'block');
            }else {
                $("#email_div").removeClass("has-success");
                $("#email_div").addClass("has-error");
                $("#email_div > span:first").css('display', 'none');
                $("#email_div > span:last").css('display', 'block');
            }

        });
    })

    function before_submit(){
    	$("#userID").attr("disabled", false);
    	//$("#roleName").attr("disabled", false);
    	//随后进行提交表单就可以将所有的信息字段封装在request里传给后台
    	
    }
    
</script>


</div>
</body>
</html>