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
			                <li  >
				                <a href="edit_personalProfile_load">个人信息编辑</a>
				            </li>
				            <c:if test="${ user.role.roleID == 1 or user.role.roleID == 2 }">
				                <li >
				                    <a href="authSettings_load">个人权限申请</a>
				                </li>
				            </c:if>
				            <c:if test="${ user.role.roleID == 3 }">
				                <li class="active">
				                    <a href="authProcess_load">角色申请处理</a>
				                </li>
				            </c:if>
		            </ul>
                </div>
            </div>
            <div class="col-xs-9 col-xs-9-remove">
               <div class="ng-scope">
                   <div class="page-header ng-scope">
                        <h1>角色申请处理</h1><h1>${user.role.roleID }</h1>
                    </div>
                    <div class="ng-scope">
                        <div ng-switch-when="form" class="ng-scope">

                                <div class="ng-scope">
                                    <div class="panel ng-scope">


                                            <ul id="myTab" class="nav nav-tabs" role="tablist">
                                                <li role="presentation" class="active">
                                                    <a href="#toBeProcessed" id="toBeProcessed-tab" name="toBeProcessed-tab" role="tab" data-toggle="tab">待处理申请列表</a>
                                                </li>
                                                <li role="presentation" class="">
                                                    <a href="#processed"  id="processed-tab" name="processed-tab"  role="tab" data-toggle="tab">已处理申请列表</a>
                                                </li>
                                            </ul>
                                            <div id="myTabContent" class="tab-content">
                                                <div role="tabpanel" class="tab-pane fade active in" id="toBeProcessed" name="toBeProcessed">

                                                    <div class="panel panel-default">
                                                        <div class="panel-body">

                                                            <br>
                                                           
                                                            <c:if test="${ empty authListProcessing}">
	                                                        	<h2>暂时没有待处理申请</h2>
	                                                        </c:if>
	                                                        <c:if test="${ !empty authListProcessing}">
                                                            
                                                        		<table class="table table-hover">
		                                                            <thead>
		                                                              <tr>
		                                                                <th>申请ID</th>
		                                                                <th>申请人</th>
		                                                                <th>申请时间</th>
		                                                                <th>当前角色</th>
		                                                                <th>专业角色</th>
		                                                                <th>处理结果</th>
		                                                              </tr>
		                                                            </thead>
		                                                            <tbody>
		                                                            
		                                                            	<c:forEach items="${authListProcessing}" var="al">
	                                                            			
					                                                              <tr>
						                                                                <td>${al.authListID}</td>
						                                                                <td>${al.applyAuthUser.userName }</td>
						                                                                <td>${al.applyDateTime}</td>
						                                                                <td>普通用户</td>
						                                                                <td>专业用户</td>
						                                                                <td>
						                                                                	<a href="elevationPrivilege2ProUser_process_agree?authListID=${al.authListID}&applyAuthUserID=${al.applyAuthUser.userID}">
						                                                                		<button type="button" id="agreeBtn_${al.authListID}" class="btn btn-success">同意</button>
					                                                                		</a>
						                                                                	<a href="elevationPrivilege2ProUser_process_deny?authListID=${al.authListID}&applyAuthUserID=${al.applyAuthUser.userID}">
						                                                                		<button type="button" id="denyBtn_${al.authListID}" class="btn btn-danger">拒绝</button>
					                                                                		</a>
						                                                                </td>
					                                                              </tr>
				                                                             
			                                                               </c:forEach>
		                                                            </tbody>
		                                                        </table>
	                                                       
		                                                      </c:if>
		                                                        
                                                        </div>
                                                    </div>

                                                </div>
                                                <div role="tabpanel" class="tab-pane fade" id="processed" name="processed">

                                                    <div class="panel panel-default">
                                                        <div class="panel-body">

                                                            <br>
                                                            
                                                            <form class="form-horizontal" action="processedAuthListSearch">
                                                            
                                                            	<div class="row">
                                                            		<div class="col-md-8">
                                                            		
                                                            			<div class="form-group">
			                                                              <label for="applyUserNickName" class="col-sm-2 control-label">用户昵称</label>
			                                                              <div class="col-sm-6">
			                                                                <input type="text" class="form-control" id="applyUserNickName" name="applyUserNickName" placeholder="输入用户名昵称">
			                                                              </div>
			                                                            </div>
			                                                            <div class="form-group">
			                                                              <label for="inputPassword3" class="col-sm-2 control-label">申请时间</label>
			                                                              <div class="col-sm-10">
			                                                              	<input type="date"  name="applyDateTimeStart" style="float:left"/>
			                                                              	<h3 style="float:left">_</h3>
			                                                              	<input type="date" name="applyDateTimeEnd" style="float:left"/>
			                                                              </div>
			                                                            </div>
			                                                            <div class="form-group">
			                                                              <label for="inputPassword3" class="col-sm-2 control-label">处理时间</label>
			                                                              <div class="col-sm-10">
			                                                              	<input type="date" name="processDateTimeStart" style="float:left"/>
			                                                              	<h3 style="float:left">_</h3>
			                                                              	<input type="date" name="processDateTimeEnd" style="float:left"/>
			                                                              </div>
			                                                            </div>
			                                                            <div class="form-group">
			                                                              <label for="applyUserNickName" class="col-sm-2 control-label">处理结果</label>
			                                                              <div id="radio" class="col-sm-6">
				                                                              <input type="radio" name="processResult" value="2"  checked >申请通过</input>
				                                                              <input type="radio" name="processResult" value="3"  >申请拒绝</input>
			                                                              </div>
			                                                            </div>
			                                                          </div>
			                                                         <div class="col-md-4">
			                                                         		<div class="form-group">
				                                                              <div class="col-sm-offset-2 col-sm-10">
				                                                                <button type="submit" id="processedAuthListSearchBtn" class="btn btn-primary btn-lg" >查询</button>
				                                                              </div>
				                                                            </div>
			                                                         </div>
			                                                         
                                                            	</div>
	                                                            
	                                                           
	                                                          </form>
                                                            <br>
	                                                          
                                                            <c:if test="${empty authListProcessed}">
	                                                        	<h2>暂时没有处理过申请</h2>
	                                                        </c:if>
	                                                        <c:if test="${ !empty authListProcessed}">
                                                            
	                                                            <table class="table table-hover">
		                                                            <thead>
		                                                              <tr>
		                                                                <th>申请ID</th>
		                                                                <th>申请人</th>
		                                                                <th>申请时间</th>
		                                                                <th>处理时间</th>
		                                                                <th>当前角色</th>
		                                                                <th>专业角色</th>
		                                                                <th>处理结果</th>
		                                                              </tr>
		                                                            </thead>
		                                                            <tbody>
				                                                        
				                                                        <c:forEach items="${authListProcessed}" var="al">
			                                                        
				                                                              <tr>
				                                                                <td>${al.authListID}</td>
				                                                                <td>${al.applyAuthUser.userName }</td>
				                                                                <td>${al.applyDateTime}</td>
				                                                                <td>${al.processDateTime}</td>
				                                                                <td>普通用户</td>
				                                                                <td>专业用户</td>
				                                                                <c:if test="${al.authStatus == 2 }">
				                                                                	<td class="text-success">申请通过</td>
				                                                                </c:if>
						                                                         <c:if test="${al.authStatus == 3 }">
						                                                         	<td class="text-danger">申请拒绝</td>
				                                                                </c:if>
				                                                              </tr>

				  	                                                        </c:forEach>
			                                                              
		                                                            </tbody>
		                                                        </table>
				                                             
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
</div>
</body>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<script type="text/javascript">


    $(document).ready(function(){

    	$('#processedAuthListSearchBtn').click(function(e){
    		
    		var applyUserNickName_value = $('#applyUserNickName').value();
    		var applyDateTimeStart_value = $('#applyDateTimeStart').value();
    		var applyDateTimeEnd_value = $('#applyDateTimeEnd').value() ;
    		var processDateTimeStart_value = $('#processDateTimeStart').value();
    		var processDateTimeEnd_value = $('#processDateTimeEnd').value();
    		var processResult_value = $('#processResult').value();
    		
    		$.ajax({
    			url:"processedAuthListSearch",
    			data:{
					applyUserNickName : applyUserNickName_value ,
					applyDateTimeStart : applyDateTimeStart_value ,
					applyDateTimeEnd : applyDateTimeEnd_value ,
					processDateTimeStart : processDateTimeStart_value ,
					processDateTimeEnd : processDateTimeEnd_value ,
					processResult : processResult_value
				},
    			type:"GET",
    			success:function(response){
    			
					
					
    		}
    		})
    		
    	})
        

    })
    


</script>


</html>