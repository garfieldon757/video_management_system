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
    <link href="css/carer-badges.css" rel="stylesheet" type="text/css">
    <!-- endbuild -->
   <body class="ng-scope">

<div class="ng-scope">
    <div class="navbar navbar-static-top ng-scope">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#/"></a>
            </div>
            <ul class="nav navbar-nav pull-right ng-scope">
                <li class="ng-scope ng-hide"><a href="/app/login">登陆</a></li>
                <li class="ng-scope"><a style="padding-left: 0;">登出</a></li>
                <li  class="ng-scope active" style="">
                    <a href="#/settings" class="icon-settings">设置</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div ng-include="'/public/app/templates/horizontal-header.html'" class="ng-scope"><!-- ngIf: currentUser && currentUser.isConsumer -->
    <div class="horizontal-header ng-scope" ng-if="currentUser &amp;&amp; currentUser.isConsumer">
        <div class="container">
            <img class="pic-thumb pull-left" src="/api/users/5807359745f8421c006a0a7b/picture?_=1476867524356">
            <h4 class="ng-binding">oliver fan</h4>
        </div>
    </div><!-- end ngIf: currentUser && currentUser.isConsumer -->
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
	                <li >
	                    <a href="authSettings_load">个人权限申请</a>
	                </li>
	                <li >
	                    <a href="authProcess_load">角色申请处理</a>
	                </li>
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
                                                    <option>${user.sex}</option>
                                                    
                                                    <c:if test=" ${user.sex eq 'female'} ">
                                                    	<option>男</option>
                                                    </c:if>
                                                    <c:if test=" ${user.sex eq 'male'} ">
	                                                	<option>女</option>
	                                                </c:if>
	                                                <!--???????????????????????????????????????? -->
                                                    
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


<!-- build:js(.) /public/js/vendor.js -->
<script src="js/jquery.min.js"></script>
<script src="js/lodash.js"></script>
<script type="text/javascript" id="">!function(b,e,f,g,a,c,d){b.fbq||(a=b.fbq=function(){a.callMethod?a.callMethod.apply(a,arguments):a.queue.push(arguments)},b._fbq||(b._fbq=a),a.push=a,a.loaded=!0,a.version="2.0",a.queue=[],c=e.createElement(f),c.async=!0,c.src=g,d=e.getElementsByTagName(f)[0],d.parentNode.insertBefore(c,d))}(window,document,"script","https://connect.facebook.net/en_US/fbevents.js");fbq("init","1027644270654279");fbq("track","PageView");fbq("track","ViewContent");</script>
<noscript>&lt;img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=1027644270654279&amp;amp;ev=PageView&amp;amp;noscript=1"&gt;</noscript>

<script src="js/moment.js"></script>
<script src="js/js/bootstrap.min.js"></script>
<script src="js.js"></script>
<script src="js/angular-resource.js"></script>
<script src="js/angular-cookies.js"></script>
<script src="js/angular-sanitize.js"></script>
<script src="js/release/angular-ui-router.js"></script>
<script src="js/angular-animate.js"></script>
<script src="js/ui-bootstrap-tpls.min.js"></script>
<script src="js/checklist-model.js"></script>
<script src="js/ng-file-upload-shim.js"></script>
<script src="js/ng-file-upload.js"></script>
<script src="js/mask.js"></script>
<script src="js/angular-filter.js"></script>
<script src="js/angular-strap.js"></script>
<script src="js/angular-strap.tpl.js"></script>
<script src="js/jquery.placeholder.js"></script>
<!-- endbuild -->

<script src="/app/config.js"></script>

<!-- build:js(.) /public/js/app.js -->
<script src="js/intercom.js"></script>
<script src="js/utils.js"></script>
<script src="js/auth.js"></script>
<script src="js/alerts.js"></script>
<script src="js/remote-error.js"></script>
<script src="js/l10n.js"></script>
<script src="js/pw-check.js"></script>
<script src="js/pagination.js"></script>
<script src="js/days.js"></script>
<script src="js/experience.js"></script>
<script src="js/formatters.filter.js"></script>
<script src="js/schedule-week.js"></script>
<script src="js/ui-view-smooth-scroll.js"></script>
<script src="js/intercom-communications.js"></script>
<script src="js/app.js"></script>
<script src="js/resources.js"></script>
<script src="js/resources.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/messages.js"></script>
<script src="js/messages.send.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/carers.js"></script>
<script src="js/item/carers.item.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/contracts.js"></script>
<script src="js/item/contracts.item.js"></script>
<script src="js/contract-state-directive.js"></script>
<script src="js/contract-time-table-directive.js"></script>
<script src="js/contract-reject-form-directive.js"></script>
<script src="js/logbook-table-directive.js"></script>
<script src="js/ctrl.js"></script>
<script src="js/payment-ctrl.js"></script>
<script src="js/payment-list-directive.js"></script>

<script src="js/account.controller.js"></script>
<script src="js/dashboard.js"></script>

<script type="text/javascript">


    $(document).ready(function(){
    	

        $("#userName").blur(function () {


             var userNameValue = $('#userName').val();
             alert("111111");
            $.ajax({
                url: "ajax_userNameValidation",
                data: { userName : userNameValue},
                type: "GET",
                success: function (response, status, xhr) {
                    //alert(response);
                    
                    reg_result = response ;//返回"available"表示userName可用，“unavailable“表示不可用
alert(reg_result);
                    if(reg_result == "available"){
                        $("#userName_div").removeClass("has-error");
                        $("#userName_div").addClass("has-success");
                        $("#userName_div > span:last").css('display', 'none');
                        $("#userName_div > span:first").css('display', 'block');
                    }else if(reg_result == "unavailable"){
                        $("#userName_div").removeClass("has-success");
                        $("#userName_div").addClass("has-error");
                        $("#userName_div > span:first").css('display', 'none');
                        $("#userName_div > span:last").css('display', 'block');
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