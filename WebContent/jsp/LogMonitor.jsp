<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                <li  class="ng-scope" style="">
                    <c:if test="${ videoLibMainPageLink == 1 }">
                        <a href="videoSearchInit?videoCategoryID=1&page=1" class="icon-settings">视频库主页</a>
                    </c:if>
                </li>
                <li id="dropdown_index" class="dropdown">
                    <c:if test="${ personalProfileDropDownList == 1 }">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">个人设置<span class="caret"></span></a>
                    </c:if>
                    <ul class="dropdown-menu" role="menu" >
                        <li>
                            <c:if test="${ personalProfileEditLink == 1 }">
                                <a href="edit_personalProfile_load">个人信息编辑</a>
                            </c:if>
                        </li>
                        <li>
                            <c:if test="${ authSettingsLink == 1 }">
                                <a href="authSettings_load">个人权限申请</a>
                            </c:if>
                        </li>
                        <li>
                            <c:if test="${ authProcessLink == 1 }">
                                <a href="authProcess_load">角色申请处理</a>
                            </c:if>
                        </li>
                        <li>
                            <c:if test="${ monitorLink == 1 }">
                                <a href="monitor_load">后台运行监控台</a>
                            </c:if>
                        </li>
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

                        <c:if test="${ personalProfileEditLink == 1 }">
                            <li  >
                                <a href="edit_personalProfile_load">个人信息编辑</a>
                            </li>
                        </c:if>
                        <c:if test="${ user.role.roleID == 1 or user.role.roleID == 2 and authSettingsLink == 1 }">
                            <li >
                                <a href="authSettings_load">个人权限申请</a>
                            </li>
                        </c:if>
                        <c:if test="${ user.role.roleID == 3 and authProcessLink == 1 }">
                            <li class="active">
                                <a href="authProcess_load">角色申请处理</a>
                            </li>
                        </c:if>
                        <c:if test="${ monitorLink == 1 }">
                            <li >
                                <a href="monitor_load">后台运行监控台</a>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </div>
            <div class="col-xs-9 col-xs-9-remove">
                <div class="ng-scope">
                    <div class="page-header ng-scope">
                        <h1>角色申请处理</h1>
                    </div>
                    <div class="ng-scope">
                        <div ng-switch-when="form" class="ng-scope">

                            <div class="ng-scope">

                                <div class="panel panel-default">
                                    <div class="panel-body">

                                        <br>

                                        <form class="form-horizontal" action="logSearch">

                                            <div class="row">
                                                <div class="col-md-8">

                                                    <div class="form-group">
                                                        <label for="userName" class="col-sm-2 control-label">用户昵称</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control" id="userName" name="userName" placeholder="输入用户名昵称">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="logDateTimeStart" class="col-sm-2 control-label">操作开始</label>
                                                        <div class="col-sm-10">
                                                            <input type="date"  name="logDateTimeStart" style="float:left"/>
                                                            <h3 style="float:left">_</h3>
                                                            <input type="date" name="logDateTimeEnd" style="float:left"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <div class="col-sm-offset-2 col-sm-10">
                                                            <button type="submit" id="daoFunctionLogSearchBtn" class="btn btn-primary btn-lg" >查询</button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>


                                        </form>
                                        <br>
                                        
                                        <c:if test="${empty controllerFunctionLogUtilList}">
		                                        <div class="panel panel-default">
			                                        <div class="panel-body  bg-success">
			                                        	<h2>暂时没有日志查询结果</h2>
			                                        </div>
		                                        </div>
                                        </c:if>
                                        <c:if test="${ !empty controllerFunctionLogUtilList}">

		                                        <div class="panel panel-default">
			                                        <div class="panel-body  bg-success">
			
			                                            <ul class="list-group">
				                                            <c:forEach items="${ controllerFunctionLogUtilList }" var="controllerFunctionLogUtil">
				                                                <li class="list-group-item">
				                                                	<p class="text-success"> ${ fn:substring( controllerFunctionLogUtil.controllerFunctionLog.dateTimeStart , 0 , 19 ) }    ：【Controller】${ controllerFunctionLogUtil.controllerFunctionLog.controllerFunction.controllerFunctionUrl }</p>
					                                                <c:forEach items="${ controllerFunctionLogUtil.serviceFunctionLogUtilList }" var="serviceFunctionLogUtil">
					                                                	<p class="text-muted">${ fn:substring( serviceFunctionLogUtil.serviceFunctionLog.dateTimeStart , 0 , 19 ) }   ：【Service】${ serviceFunctionLogUtil.serviceFunctionLog.serviceFunction.serviceFunctionUrl }</p>
					                                                	<c:forEach items="${ serviceFunctionLogUtil.daoFunctionLogList }" var="daoFunctionLog">
						                                                	<p class="text-danger">
						                                                		${ fn:substring( daoFunctionLog.dateTime , 0 , 19 ) }   ：【DAO】${ daoFunctionLog.daoFunction.daoFunctionUrl }
						                                                		<button type="button" id="??" class="btn btn-default" data-toggle="modal" data-target=".bs-example-modal-lg">数据库操作详情</button>
					                                                		</p>
						                                                </c:forEach>
					                                                </c:forEach>
				                                                </li>
				                                             </c:forEach >
			                                            </ul>
			
			                                        </div>
			                                    </div>

                                        </c:if>

                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>
<button id="btn1" type="button" data-toggle="modal" data-target="#daoLogDetail">adsffsff</button>
<footer>
    <div class="container">
        <ul class="nav">
            <li><a href="/contact" target="_blank">Contact Us</a></li>
            <li><a href="/terms-caremarketplace" target="_blank">Terms - Care Marketplace</a></li>
            <li><a href="/faq-caremarketplace" target="_blank">FAQs - Care Marketplace</a></li>
        </ul>
    </div>
</footer>

<div id="daoLogDetail" class="modal fade bs-example-modal-lg in" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="false">
	<div class="modal-dialog modal-lg">
		  <div class="modal-content">
		
			    <div class="modal-header">
			      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
			      <h4 class="modal-title" id="myLargeModalLabel">Large modal</h4>
			    </div>
			    <div class="modal-body">
			      ...
			    </div>
			      
		  </div>
	</div>
</div>

</body>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<script type="text/javascript">

	$("ducument").ready(function(){
		
		
		
	})

</script>


</html>