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

<div class="container">

		<div class="row">
		    <div class="col-xs-3 ng-scope" >
		        <div class="ng-scope">
		            <div class="page-header">
		                <h2>图像分析步骤</h2>
		            </div>
		            <hr style="margin-top: 0;">
		            <ul class="nav nav-stacked side-nav">
		                <li  class="active"><a href="">第一步：视频场景分割</a></li>
		                <li >
		                    <a href="">第二步：场景关键帧提取</a>
		                </li>
		                <li >
		                    <a href="">第三步：图像对象检测</a>
		                </li>
		                <li >
		                    <a href="">第四步：图像语义分析</a>
		                </li>
		            </ul>
		        </div>
		    </div>
		    <div class="col-xs-9 col-xs-9-remove">
		        <div class="ng-scope">
		
		            <div class="panel panel-default">
		                <div class="panel-heading">待分割视频</div>
		                <div class="panel-body">
		
		                    <div id="video-block-footer" class="row">
		                        <br />
		                        <div class="col-md-1"></div>
		                        <div class="col-md-3">
		                            <img data-src="holder.js/140x140" class="img-rounded" alt="140x140"
		                                 src="${video.videoSourceLink}"
		                                 data-holder-rendered="true"
		                                 style="width: 200px; height: 200px;">
		                        </div>
		                        <div class="col-md-6">

	                                <dl class="dl-horizontal">
	                                    <dt>品牌：</dt>
	                                    <dd>${video.brand}</dd>
	                                </dl>
	                                <dl class="dl-horizontal">
	                                    <dt>国家：</dt>
	                                    <dd>${video.country}</dd>
	                                </dl>
	                                <dl class="dl-horizontal">
	                                    <dt>语言：</dt>
	                                    <dd>${video.language}</dd>
	                                </dl>
	                                <dl class="dl-horizontal">
	                                    <dt>发布年份：</dt>
	                                    <dd>${video.publishYear}</dd>
	                                </dl>
	                                <dl class="dl-horizontal">
	                                    <dt>发布者信息：</dt>
	                                    <dd>${video.user.userName} (ID : ${video.user.userID})</dd>
	                                </dl>
	
	                            </div>
		                        <div class="col-md-2"></div>
		
		                    </div>
		
		                </div>
		            </div>
		            <div class="panel panel-default">
		                <div class="panel-heading">视频场景分割算法</div>
		                <div class="panel-body">
		
		                    <div class="input-group">
		                        <input type="text" class="form-control" disabled>
		                        <div class="input-group-btn">
		                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">算法选择 <span class="caret"></span></button>
		                            <ul class="dropdown-menu dropdown-menu-right">
		                                <li><a href="#">Action</a></li>
		                                <li><a href="#">Another action</a></li>
		                                <li><a href="#">Something else here</a></li>
		                                <li><a href="#">Separated link</a></li>
		                            </ul>
		                        </div>
		                    </div>
		
		                </div>
		            </div>
		            <div class="panel panel-default">
		                <div class="panel-heading">
		                    <h3 class="panel-title">结果展示</h3>
		                </div>
		                <div class="panel-body">
		                    Panel content
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

        $('#myTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        })

    })


</script>

</body>
</html>