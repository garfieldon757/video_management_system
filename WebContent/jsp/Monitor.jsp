<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ad</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="shortcut icon" href="/public/images/favicons/favicon.ico">

    <link href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">

    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/app.css" rel="stylesheet" type="text/css">

</head>
<body >

    <div   >
        <div class="navbar navbar-static-top ng-scope" >
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
					                <li >
					                    <a href="authProcess_load">角色申请处理</a>
					                </li>
					            </c:if>
					            <c:if test="${ monitorLink == 1 }">
						            <li class="active">
					                    <a href="monitor_load">后台运行监控台</a>
					                </li>
			                    </c:if>
			                    
			            </ul>
                    </div>
                </div>
                <div class="col-xs-9 col-xs-9-remove">
                   <div class="ng-scope">
                       <div class="page-header ng-scope">
                           <ol class="breadcrumb">
                               <li><a href="#">主页</a></li>
                               <li><a href="#">设置</a></li>
                               <li class="active">管理员平台监控</li>
                           </ol>
                        </div>
                        <div class="ng-scope">
                            <div ng-switch-when="form" class="ng-scope">

                                <form name="form" class="ng-pristine ng-valid ng-valid-required ng-valid-remote ng-valid-email">

                                    <div class="ng-scope">
                                        <div class="panel ng-scope">
                                            <div class="well">


                                                <label for="userMonitor">用户监控</label>
                                                <br/><br/>
                                                <div class="row">
                                                    <div class="col-lg-5">

                                                        <ul class="list-group text-center">
                                                            <li class="list-group-item ">
                                                                用户总数：<Strong>${userMonitorData["userTotalNum"]}</Strong>个
                                                            </li>
                                                                <li class="list-group-item ">
                                                                普通用户总数：<Strong>${userMonitorData["trialUserTotalNum"]}</Strong>个
                                                            </li>
                                                            <li class="list-group-item ">
                                                                专业用户总数：<Strong>${userMonitorData["proUserTotalNum"]}</Strong>个
                                                            </li>
                                                                <li class="list-group-item ">
                                                                管理员总数：<Strong>${userMonitorData["adminTotalNum"]}</Strong>个
                                                            </li>
                                                        </ul>

                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-5">
                                                            <p>数据分析</p>
                                                            <canvas id="userMonitorPieChart" width="200" height="200"></canvas>

                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="ng-scope">
                                        <div class="panel ng-scope">
                                            <div class="well">

                                                <label for="authMonitor">提权监控</label>
                                                <br/><br/>
                                                <div class="row">
                                                    <div class="col-lg-5">
                                                        <ul class="list-group text-center">
                                                            <li class="list-group-item">
                                                                <p>
                                                                    权限申请总数：<Strong>${authorizationListMonitorData["authListTotalNum"]}</Strong>个
                                                                </p>
                                                            </li>
                                                            <li class="list-group-item">
                                                                <p>
                                                                    待审批申请数：<Strong>${authorizationListMonitorData["authList1TotalNum"]}</Strong>个
                                                                </p>
                                                            </li>
                                                            <li class="list-group-item">
                                                                <p>
                                                                    审批通过数：<Strong>${authorizationListMonitorData["authList2TotalNum"]}</Strong>个
                                                                </p>
                                                            </li>
                                                            <li class="list-group-item">
                                                                <p>
                                                                    审批拒绝总数：<Strong>${authorizationListMonitorData["authList3TotalNum"]}</Strong>个
                                                                </p>
                                                            </li>
                                                        </ul>

                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-5">
                                                        <canvas id="authMonitorLineChart" width="200" height="200"></canvas>
                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                </div>




                                            </div>
                                        </div>
                                    </div>

                                    <div class="ng-scope">
                                        <div class="panel ng-scope">
                                            <br/><br/>
                                            <div class="well">

                                                <label for="videoMonitor">视频库监控</label>
                                                <div class="row">
                                                    <div class="col-lg-5">
                                                        <ul class="list-group text-center">
                                                            <li class="list-group-item">
                                                                视频总数：<Strong>${videoMonitorData["videoTotalNum"]}</Strong>个
                                                            </li>
                                                            <li class="list-group-item">
                                                                ??
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-5">
                                                        <canvas id="videoMonitorPieChart" width="200" height="200"></canvas>
                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                </div>




                                            </div>
                                        </div>
                                    </div>

                                    <div class="ng-scope">
                                        <div class="panel ng-scope">
                                            <br/><br/>
                                            <div class="well">

                                                <label for="algorithmMonitor">算法库监控</label>
                                                <div class="row">
                                                    <div class="col-lg-5">
                                                        <ul class="list-group text-center">
                                                            <li class="list-group-item">
                                                                算法库总数：<Strong>${algorithmMonitorData["algorithmTotalNum"]}</Strong>个
                                                            </li>
                                                            <li class="list-group-item">
                                                                ??
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-5">
                                                        <canvas id="algorithmMonitorPieChart" width="200" height="200"></canvas>
                                                    </div>
                                                    <div class="col-lg-1"></div>
                                                </div>




                                            </div>
                                        </div>
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
    <script src="js/chart.js"></script>

    <button id="xxx" name="xxx" onclick="drop_click()"></button>
    <script type="text/javascript">


        $(document).ready(function(){

            $("#userName_div").blur(function () {


                 var userName_divValue = $('#userName_div').val();

                $.ajax({
                    url: "ajaxTest",
                    data: userName_divValue,
                    success: function (response, status, xhr) {
                        alert(response);
                    }
                });

                 //reg_result = ;

                 if(reg_result){
                     $("#userName_div").removeClass("has-error");
                     $("#userName_div").addClass("has-success");
                     $("#userName_div > span:last").css('display', 'none');
                     $("#userName_div > span:first").css('display', 'block');
                 }else{
                     $("#userName_div").removeClass("has-success");
                     $("#userName_div").addClass("has-error");
                     $("#userName_div > span:first").css('display', 'none');
                     $("#userName_div > span:last").css('display', 'block');
                 }

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

            /******************初始化用户监控模块的饼状图******************/
            var ctx = $("#userMonitorPieChart");
            var data4userMonitorPieChart = {
                labels: [
                    "普通用户",
                    "专业用户",
                    "管理员"
                ],
                datasets: [
                    {
                        data: [20, 5, 1],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ]
                    }]
            };
            var myDoughnutChart = new Chart(ctx, {
                type: 'doughnut',
                data: data4userMonitorPieChart
            });
            /************************************************************/

            /******************初始化提权监控模块的折线图******************/
            var ctx = $("#authMonitorLineChart");
            var data4authMonitorLineChart = {
                labels: [
                    "1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"
                ],
                datasets: [
                    {
                        label:"权限提升申请数",
                        data: [65, 59, 80, 81, 56, 55, 40, 59, 80, 81, 56, 55],
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "rgba(54, 162, 235, 0.2)",
                        borderColor: "rgba(54, 162, 235, 1)",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: "rgba(54, 162, 235,1)",
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(54, 162, 235,1)",
                        pointHoverBorderColor: "rgba(220,220,220,1)",
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10
                    },
                    {
                        label:"审批通过数",
                        data: [55, 50, 69, 80, 45, 50, 30, 50, 69, 80, 45, 50],
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "rgba(75,192,192,0.2)",
                        borderColor: "rgba(75,192,192,1)",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: "rgba(75,192,192,1)",
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(75,192,192,1)",
                        pointHoverBorderColor: "rgba(220,220,220,1)",
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10
                    },
                    {
                        label:"审批拒绝数",
                        data: [10, 9, 11, 1, 11, 5, 10, 9, 11, 1, 11, 5],
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: "rgba(255, 99, 132, 0.2)",
                        borderColor: "rgba(255, 99, 132, 1)",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: "rgba(255, 99, 132,1)",
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 1,
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(255, 99, 132,1)",
                        pointHoverBorderColor: "rgba(220,220,220,1)",
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10
                    }
                ]
            };
            var myDoughnutChart = new Chart(ctx, {
                type: 'line',
                data: data4authMonitorLineChart
            });
            /************************************************************/

            /******************初始化视频资源模块的饼状图******************/
            var ctx = $("#videoMonitorPieChart");
            var data4videoMonitorPieChart = {
                labels: [
                    "消费电子与通讯","酒类","服装","教育培训","体育运动","时尚","文化娱乐","生活服务" ,"医疗医院","零售业","汽车"
                ],
                datasets: [
                    {
                        data: [1578, 1023, 927, 53, 764, 1412, 947, 201, 138, 889, 3305],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56",
                            "#3FC384",
                            "#5F55EB",
                            "#AACE56",
                            "#5F6684",
                            "#36A2EB",
                            "#4F4556",
                            "#AF7864",
                            "#3ADFEB"
                        ],
                        hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56",
                            "#3FC384",
                            "#5F55EB",
                            "#AACE56",
                            "#5F6684",
                            "#36A2EB",
                            "#4F4556",
                            "#AF7864",
                            "#3ADFEB"
                        ]
                    }]
            };
            var myDoughnutChart = new Chart(ctx, {
                type: 'doughnut',
                data: data4videoMonitorPieChart
            });
            /************************************************************/

            /******************初始化算法模块的饼状图******************/
            var ctx = $("#algorithmMonitorPieChart");
            var data4algorithmMonitorPieChart = {
                labels: [
                    "视频场景分割","关键帧提取","目标对象提取","高层语义对接"
                ],
                datasets: [
                    {
                        data: [5, 5, 9, 2],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ]
                    }]
            };
            var myDoughnutChart = new Chart(ctx, {
                type: 'doughnut',
                data: data4algorithmMonitorPieChart
            });
            /************************************************************/

        })


    </script>

</body>
</html>