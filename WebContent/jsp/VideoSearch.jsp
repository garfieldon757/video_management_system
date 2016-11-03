<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html class="no-js">
<head>
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
</head>
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
        <div class="ng-scope"><!-- ngIf: currentUser && currentUser.isConsumer -->
            <div class="horizontal-header ng-scope" >
                <div class="container">
                    <img class="pic-thumb pull-left" src="/api/users/5807359745f8421c006a0a7b/picture?_=1476867524356">
                    <h4 class="ng-binding">oliver fan</h4>
                </div>
            </div><!-- end ngIf: currentUser && currentUser.isConsumer -->
        </div>

        <div id="main">

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-2">
                    <div class="ng-scope">
                        <div class="page-header">
                            <h2>广告视频库分类</h2>
                        </div>
                        <hr style="margin-top: 0;">
                        <ul class="nav nav-stacked side-nav">
                            <c:forEach items="${videoCategoryList}" var="vc">
                            	<c:if test="${ vc.videoCategoryID == '1' }">
                                	<li  class="active">
                                </c:if>
                                <c:if test="${vc.videoCategoryID != '1'}">
	                            	<li  class="">
	                            </c:if>
	                                    <a href="${vc.videoCategoryUrl}">${vc.videoCategoryName}</a>
	                                </li>
                           </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            search
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-body">
                        
		                    <c:forEach items="${videoList}" var="v" varStatus="status" begin="1" end="12"> 
		                    
			                    	<c:choose>
				                    		<c:when test="${status.index % 4 == 1}">
				                    		
				                    		<div class="row">
				                    		
					                    		<div class="col-md-3">
					                                <div class="thumbnail">
					                                    <img data-src="holder.js/300x300" alt="..." class="img-responsive" src="http://v.cnad.cn/vg/qqgg1/2015.4.23/【化妆与日用】迪奥广告（4）_512x280_2.00M_h.264_201542484458.JPG">
					                                    <div class="caption">
					                                        <h3>videoName</h3>
					                                        <p>
					                                            <a href="#" class="btn btn-primary">视频播放</a>
					                                            <a href="#" class="btn btn-default">视频分析</a>
					                                        </p>
					                                    </div>
					                                </div>
					                            </div>
				                    		
				                    		</c:when>
				                    		
				                    		<c:when test="${status.index % 4 == 2 || status.index % 4 ==3 }">
				                    		
						                    		<div class="col-md-3">
						                                <div class="thumbnail">
						                                    <img data-src="holder.js/300x300" alt="..." class="img-responsive" src="http://v.cnad.cn/vg/qqgg1/2015.4.23/【化妆与日用】迪奥广告（4）_512x280_2.00M_h.264_201542484458.JPG">
						                                    <div class="caption">
						                                        <h3>videoName</h3>
						                                        <p>
						                                            <a href="#" class="btn btn-primary">视频播放</a>
						                                            <a href="#" class="btn btn-default">视频分析</a>
						                                        </p>
						                                    </div>
						                                </div>
						                            </div>
				                    		
				                    		</c:when>
				                    		
				                    		<c:when test="${status.index % 4 == 0}">
				                    		
						                    		<div class="col-md-3">
						                                <div class="thumbnail">
						                                    <img data-src="holder.js/300x300" alt="..." class="img-responsive" src="http://v.cnad.cn/vg/qqgg1/2015.4.23/【化妆与日用】迪奥广告（4）_512x280_2.00M_h.264_201542484458.JPG">
						                                    <div class="caption">
						                                        <h3>videoName</h3>
						                                        <p>
						                                            <a href="#" class="btn btn-primary">视频播放</a>
						                                            <a href="#" class="btn btn-default">视频分析</a>
						                                        </p>
						                                    </div>
						                                </div>
						                            </div>
				                    		
					                    		</div>
				                    		
				                    		</c:when>
			                    	</c:choose>
	                    	
		                    	</c:forEach>
	                   

                        </div>
                    </div>
                    
                    <nav>
	                    <ul class="pagination">
	                        <li class="disabled"><a href="#">«</a></li>
	                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
	                        <li><a href="#">2</a></li>
	                        <li><a href="#">3</a></li>
	                        <li><a href="#">4</a></li>
	                        <li><a href="#">5</a></li>
	                        <li><a href="#">...</a></li>
	                        <li><a href="#">${videoListSize}</a></li>
	                    </ul>
	                </nav>
                    
                </div>
                <div class="col-md-1"></div>
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
        <script src="js/js/bootstrap.min.js"></script>

</body>
</html>