<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="no-js">
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
			            <li  class="ng-scope active" style="">
				            <a href="videoSearchInit?videoCategoryID=1&page=1" class="icon-settings">视频库主页</a>
				        </li>
				        <li  class="ng-scope active" style="">
				            <a href="edit_personalProfile_load" class="icon-settings">个人设置</a>
				        </li>
            </ul>
        </div>
    </div>
</div>
<div ng-include="'/public/app/templates/horizontal-header.html'" class="ng-scope"><!-- ngIf: currentUser && currentUser.isConsumer -->
    <div class="horizontal-header ng-scope" ng-if="currentUser &amp;&amp; currentUser.isConsumer">
        <div class="container">
            <img class="pic-thumb pull-left" src="/api/users/5807359745f8421c006a0a7b/picture?_=1476867524356">
            <h4 class="ng-binding">${user.userName}</h4>
        </div>
    </div>
</div>

<div id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-3 ng-scope" >
                <div class="ng-scope" style="">
                    <div class="page-header">
                        <h2>设置</h2>
                    </div>
                    <hr style="margin-top: 0;">
                    <ul class="nav nav-stacked side-nav">
	                    <li  >
	                        <a href="edit_personalProfile_load">个人信息编辑</a>
	                    </li>
	                    <li class="active">
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
                        <h1>个人权限申请</h1>
                    </div>
                    <div class="ng-scope">
                        <div class="panel panel-default">
                            <div class="panel-heading">当前角色</div>
                            <div class="panel-body">
                                <p>当前您的角色为：</p>
                                <c:if test="${user.role.roleID == 1 }">
                                	<span class="label label-success">普通用户</span>
                                </c:if>
                                <c:if test="${user.role.roleID == 2 }">
                                	<span class="label label-success">专业用户</span>
                                </c:if>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">提权申请</div>

                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="thumbnail">
                                            <img data-src="holder.js/300x300" alt="普通用户icon">
                                            <div class="caption">
                                                <h3>普通用户</h3>
                                                <p>普通用户再注册登陆之后，拥有一些基本的视频信息浏览，以及基础算法使用的权限。具体权限如下：</p>
                                                <span class="label label-default" >视频浏览</span>
                                                <span class="label label-default" >视频下载</span>
                                                <br><br>
                                                <span class="label label-default" >基础视频分析算法使用</span>
                                                <br><br>
                                                <p>
	                                                <c:if test="${user.role.roleID == 1 }">
	                                                    <a href="#" class="btn btn-default" role="button" style="display: block" disabled="true">已经拥有当前角色</a>
	                                                    <a href="#" class="btn btn-default" role="button" style="display: none" >您当前无法申请此角色</a>
		                                            </c:if>
		                                            <c:if test="${user.role.roleID == 2 }">
	                                                    <a href="#" class="btn btn-default" role="button" style="display: none">已经拥有当前角色</a>
	                                                    <a href="#" class="btn btn-default" role="button" style="display: block"  disabled="true">您当前无法申请此角色</a>
		                                            </c:if>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="thumbnail">
                                            <img data-src="holder.js/300x300" alt="专业用户icon">
                                            <div class="caption">
                                                <h3>专业用户</h3>
                                                <p>专业用户通过从普通用户提升权限，不仅拥有普通用户的基本功能，并且系统向其开放了整个平台算法系统的使用权限，以及提供算法上传的权限。
                                                具体权限如下：</p>

                                                <span class="label label-default" >【普通用户全部权限】</span>
                                                <br><br>
                                                <span class="label label-default" >完整视频分析算法使用</span>
                                                <br><br>
                                                <span class="label label-default" >视频分析算法上传</span>
                                                <br><br>
                                                <p>
		                                                <c:if test="${user.role.roleID == 1 }">
			                                                <a href="elevationPrivilege2ProUser_apply" class="btn btn-danger" role="button" style="display: block" >申请此角色</a>
			                                                <a href="#" class="btn btn-default" role="button" style="display: none" >已经拥有当前角色</a>
			                                            </c:if>
			                                            <c:if test="${user.role.roleID == 2 }">
			                                                <a href="#" class="btn btn-default" role="button" style="display: block" disabled="true">已经拥有当前角色</a>
			                                            </c:if>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        
                        <div class="panel panel-default">
                        <div class="panel-heading">角色申请历史记录</div>
                        <div class="panel-body">
                        
                        <c:forEach items="${authList}" var="al">    
                        		 
			                        <c:if test="${al.authStatus == 1 }">
			                        
					                    	<div class="panel panel-default">
					                        <div class="panel-body">
					                            <div class="row">
					                                <div class="col-md-1"></div>
					                                <div class="col-md-7">
					                                    <p>当前申请ID：${al.authListID}</p>
                                                        <p>申请请求时间：${al.applyDateTime}</p>
                                                        <p>申请处理时间：</p>
                                                        <p>申请权限人：${al.applyAuthUser.userName }(ID: ${ al.applyAuthUser.userID } )</p>
					                                    <p>当前角色：普通用户</p>
					                                    <p>申请角色：专业用户</p>
					                                </div>
					                                <div class="col-md-4" >
					                                    <p>角色申请待审核</p>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					                    
					                </c:if>
					                
					                <c:if test="${al.authStatus == 2 }">
					                
					                    	 <div class="alert alert-success" role="alert">
					                            <div class="row">
					                                <div class="col-md-1"></div>
					                                <div class="col-md-7">
						                                <p>当前申请ID：${al.authListID}</p>
	                                                    <p>申请请求时间：${al.applyDateTime}</p>
	                                                    <p>申请处理时间：${al.processDateTime}</p>
	                                                    <p>申请权限人：${al.applyAuthUser.userName }(ID: ${ al.applyAuthUser.userID } )</p>
					                                    <p>当前角色：普通用户</p>
					                                    <p>申请角色：专业用户</p>
					                                </div>
					                                <div class="col-md-4" >
					                                    <p>已通过角色申请提醒</p>
					                                </div>
					                            </div>
					                        </div>
					                
					                </c:if>
					                
					                <c:if test="${al.authStatus == 3 }">
					                
					                    <div class="alert alert-danger" role="alert">
					                        <div class="row">
					                            <div class="col-md-1"></div>
					                            <div class="col-md-7">
						                            <p>当前申请ID：${al.authListID}</p>
	                                                <p>申请请求时间：${al.applyDateTime}</p>
	                                                <p>申请处理时间：${al.processDateTime}</p>
	                                                <p>申请权限人：${al.applyAuthUser.userName }(ID: ${ al.applyAuthUser.userID } )</p>
				                                    <p>当前角色：普通用户</p>
				                                    <p>申请角色：专业用户</p>
					                            </div>
					                            <div class="col-md-4">
					                                <p>已拒绝角色提升申请</p>
					                            </div>
					                        </div>
					                    </div>
					                
					                </c:if>
	                        
                        </c:forEach>
                        

                        </div>
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
<!-- endbuild -->


</div>
</body>
</html>