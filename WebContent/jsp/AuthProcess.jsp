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
	            <li  class="ng-scope" style="">
		            <a href="videoSearchInit?videoCategoryID=1&page=1" class="icon-settings">视频库主页</a>
		        </li>
		        <li id="dropdown_index" class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">个人设置<span class="caret"></span></a>
	                <ul class="dropdown-menu" role="menu" >
	                    <li>
	                        <a href="edit_personalProfile_load">个人信息编辑</a>
	                    </li>
	                    <li>
	                        <a href="authSettings_load">个人权限申请</a>
	                    </li>
	                    <li>
	                        <a href="authProcess_load">角色申请处理</a>
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
           <img class="pic-thumb pull-left" src="/api/users/5807359745f8421c006a0a7b/picture?_=1476867524356">
           <h4 id="userName" class="ng-binding" value="${user.userName}">${user.userName}</h4>
           <button type="button" class="btn btn-default pull-right" class="logout" >退出</button>
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
                    <li >
                        <a href="authSettings_load">个人权限申请</a>
                    </li>
                    <li class="active">
                        <a href="authProcess_load">角色申请处理</a>
                    </li>
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
                                                            
                                                            <c:if test="${!empty authList}">
                                                            	<h2>暂时没有待处理申请</h2>
                                                            </c:if>
                                                            
                                                            <c:forEach items="${authList}" var="al">    
                                                   		 
	                        			                        <c:if test="${al.authStatus == 1 }">
			                        			                        
	                        			                        		<form id="process_form_${al.authListID}" action="" method="post">
				                                                            <div class="panel panel-default">
				                                                                <div class="panel-body">
				                                                                    <div class="row">
				                                                                        <div class="col-md-1"></div>
				                                                                        <div class="col-md-7">
					                                                                        <p>当前申请ID：<bold name="authListID"> ${al.authListID} </bold> </p>
					                	                                                    <p>申请请求时间：${al.applyDateTime}</p>
					                	                                                    <p>申请处理时间：</p>
					                	                                                    <p>申请权限人：${al.applyAuthUser.userName }(ID: ${ al.applyAuthUser.userID } )</p>
					                					                                    <p>当前角色：普通用户</p>
					                					                                    <p>申请角色：专业用户</p>
				                                                                        </div>
				                                                                        <div class="col-md-3">
				                                                                            <button type="submit" id="agreeAuthBtn" class="btn btn-success btn-lg btn-block" onclick="agreeAuthProcess(${al.authListID})">同意提升角色</button>
				                                                                            <button type="submit" id="denyAuthBtn" class="btn btn-danger btn-lg btn-block" onclick="denyAuthProcess(${al.authListID})">拒绝提升角色</button>
				                                                                        </div>
				                                                                        <div class="col-md-1"></div>
				                                                                    </div>
				                                                                </div>
				                                                            </div>
				                                                          </form>  
		                                                            </c:if>
		                                	                        
		                                                        </c:forEach>
                                                            
                                                        </div>
                                                    </div>

                                                </div>
                                                <div role="tabpanel" class="tab-pane fade" id="processed" name="processed">

                                                    <div class="panel panel-default">
                                                        <div class="panel-body">

                                                            <br>
                                                            
                                                            <c:forEach items="${authList}" var="al">    
                                                      		 
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
		                                                                    <div class="col-md-4" style="vertical-align: bottom">
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

				                                              <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#searchAuthListModal">
					                                             搜索历史申请列表
					                                           </button>
				                                              
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

<div class="modal fade" id="searchAuthListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		  <div class="modal-content">
			    <div class="modal-header">
			      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			      <h4 class="modal-title" id="myModalLabel">搜索历史申请列表</h4>
			    </div>
			    <div class="modal-body">
			    	<form id="searchAuthListForm" action="">
			    		<input type="text" id="searchAuthListInput" placeholder="请输入申请权限人ID"></input>
			    		<button type="button" id="searchAuthListBtn" class="btn btn-primary">搜索</button>
			    	</form>
			    </div>
			    <div id="modal-footer" class="modal-footer">
			      	
			    </div>
		  </div>
	</div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<script type="text/javascript">


    $(document).ready(function(){

        $('#myTab a').click(function (e) {

            $(this).tab('show');
        });
        
        $('#searchAuthListBtn').click(function(e) {

        	var ApplyAuthUserID_value = $('#').val();
        	
        	$.ajax({
                url: "ajax_searchProcessedAuthListByApplyAuthUserID",
                data: { ApplyAuthUserID : ApplyAuthUserID_value},
                type: "GET",
                success: function (response) {
                    
	                  var json = eval(response);
	                  var concatStr = "";
	                  $.each(json, function(index){
	                  	var authListID = json[index].authListID;
	                  	var authStatus = json[index].authStatus;
	                  	var applyAuthUserName = json[index].applyAuthUser.userName;
	                  	var applyAuthUserID = json[index].applyAuthUser.userID;
	                  	var giveAuthUserID = json[index].giveAuthUser.userID;
	                  	var roleID = json[index].role.roleID;
	                  	var applyDateTime = json[index].applyDateTime;
	                  	var processDateTime = json[index].processDateTime;
	//                  	concatStr += authListID + "__" + authStatus + "__" + applyAuthUserID + "__" 
	//                  						+giveAuthUserID + "__" + roleID + "__" + applyDateTime + "__" + processDateTime;
	                  	concatStr = "";
	                  	if(authStatus == 2){
	                  		
	                  		concatStr =			 "<div class=\"alert alert-success\" role=\"alert\"> "
	                  								+		"<div class=\"row\">"
								                        +      "<div class=\"col-md-8\">"
										                    +      "<p>当前申请ID：" + authListID + "</p>"
										                    +      "<p>申请请求时间：" + applyDateTime + "</p>"
								                            + 		"<p>申请处理时间：" + processDateTime + "</p>"
								                            + 		"<p>申请权限人：" + applyAuthUserName + " ( ID : " + applyAuthUserID + " )</p>"
								                            + 		"<p>当前角色：普通用户 </p>"
								                            + 		"<p>申请角色：专业用户 </p>"
						                             	+		"</div>"
						                                +      "<div class=\"col-md-4\" style=\"vertical-align: bottom\">"
						                                	+      "<p>已通过角色申请提醒</p>"
						                                +      "</div>"
					                                +   	 "</div>"
				                                + 		 "</div>" ;
					                                  		
	                  	}else if(authStatus == 3){
	                  		
	                  		concatStr =			 "<div class=\"alert alert-danger\" role=\"alert\"> "
				      								+		"<div class=\"row\">"
								                        +      "<div class=\"col-md-7 text-left \">"
										                    +      "<p >当前申请ID：" + authListID + "</p>"
										                    +      "<p>申请请求时间：" + applyDateTime + "</p>"
								                            + 		"<p>申请处理时间：" + processDateTime + "</p>"
								                            + 		"<p>申请权限人：" + applyAuthUserName + " ( ID : " + applyAuthUserID + " )</p>"
								                            + 		"<p>当前角色：普通用户 </p>"
								                            + 		"<p>申请角色：专业用户 </p>"
						                             	+		"</div>"
						                                +      "<div class=\"col-md-4\" style=\"vertical-align: bottom\">"
						                                	+      "<p><strong>已拒绝角色申请提醒</strong></p>"
						                                +      "</div>"
					                                +   	 "</div>"
				                                + 		 "</div>" ;
	                  		
	                  	}
	                  	
	                  	
	                  	
	                  });
	                  
	                  $("#modal-footer").html('');
	                  $("#modal-footer").html(concatStr);
                	
                }
            });
        	
        });
        

    })
    
    function agreeAuthProcess(authListID){

    	$('#process_form_'+authListID).attr("action", "elevationPrivilege2ProUser_process_agree?authListID=" + authListID);
    	$('#process_form_'+authListID).submit();

    }
    
    function denyAuthProcess(authListID){
    	alert("deny");
    	$('#process_form_'+authListID).attr("action", "elevationPrivilege2ProUser_process_deny?authListID=" + authListID);
    	$('#process_form_'+authListID).submit();

    }


</script>


</html>