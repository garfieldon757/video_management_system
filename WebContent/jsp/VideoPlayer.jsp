<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <div class="col-md-2"></div>
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="page-header">
                        <h2>${video.videoName} <small>${video.videoCategory.videoCategoryName}</small></h2>
                    </div>

                    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="500" height="400">
                        <param name="movie" value="util/flvplayer.swf" />
                        <param name="quality" value="high" />
                        <param name="allowFullScreen" value="true" />
                        <embed src="util/flvplayer.swf"
                               allowfullscreen="true"
                               flashvars="vcastr_file=${video.videoSourceLink}&IsAutoPlay=1"
                               quality="high"
                               pluginspage="http://www.macromedia.com/go/getflashplayer"
                               type="application/x-shockwave-flash"
                               width="100%" height="500">
                        </embed>
                    </object>

                    <div id="video-block-footer" class="row">
                        <br />
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
                                <dd id="userName" value="${video.user.userName}">${video.user.userName} (ID: ${video.user.userID} )</dd>
                            </dl>
                            
                        </div>
                        <div class="col-md-5">
                            <div class="">
                                <button type="button" id="downloadBtn" value="${video.videoSourceLink}" class="btn btn-primary btn-lg btn-block">下载视频</button>
                                <a href="loadImageProcess4Trial_keyFrameSegmentation?videoID=${video.videoID}"><button type="button" class="btn btn-default btn-lg btn-block">分析视频</button></a>
                            </div>
                        </div>
                        <div class="col-md-1">
                        </div>

                    </div>

                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="page-header">
                        <h2>相关视频推荐</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
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
<script type="text/javascript">
	
	$(document).ready(function(){
	
	    $('#downloadBtn').click(function(e) {
	    		
	    		var urlStr_value = $('#downloadBtn').val();
	    		var userName = ${'#userName'}.val();
	    		
	    		$.ajax({
		            url: "downloadVideo?urlStr=" + urlStr_value + "&userName=" + userName ,
		            data: {},
		            type: "GET",
		            success: function (response) {
		                
		                 alert(response);
		            	
		            }
		        })
	    	
	    })
	    
	
	})

</script>

</div>
</body>
</html>