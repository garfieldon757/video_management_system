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
		
		<div  class="ng-scope">
			<div class="horizontal-header ng-scope" ng-if="currentUser &amp;&amp; currentUser.isConsumer">
			    <div class="container ">
			        <img class="pic-thumb pull-left" src="/api/users/5807359745f8421c006a0a7b/picture?_=1476867524356">
			        <h4 id="userName" class="ng-binding" value="${user.userName}">${user.userName}</h4>
			        <a href="logout"><button type="button" class="btn btn-default pull-right">退出</button></a>
			    </div>
			</div>
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
		                                 src="${video.videoCoverLink}"
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
	                                <button id="videoSourceLink_Btn" value="${video.videoSourceLink}" style="display:none"></button>
	                            </div>
		                        <div class="col-md-2"></div>
		
		                    </div>
		
		                </div>
		            </div>
		            <div class="panel panel-default">
		                <div class="panel-heading">视频场景分割算法</div>
		                <div class="panel-body">
			                <form role="form">
			                	<input class="form-control" id="disabledInput" type="text" placeholder="默认算法" disabled="">
				              </form>
		                </div>
		            </div>
		           
		            <div class="text-center">
		            	<button id="execute_btn" type="button" class="btn btn-danger btn-lg">运行算法</button>
		            </div>
		            
		            <br/>
		            
		            <div class="panel panel-default">
		                <div class="panel-heading">
		                    <h3 class="panel-title">结果展示</h3>
		                </div>
		                <div id="result-div" class="panel-body">

		                	<div class="well">
			                        <div class="row">
			                            <div class="col-md-1"></div>
			                            <div class="col-md-10 text-center">
			                                <h3>当前视频尚未进行算法分析，暂无结果。</h3>
			                            </div>
			                            <div class="col-md-1"></div>
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

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">


    $(document).ready(function(){
        
        $('#execute_btn').click(function(e){
        	
        	var videoSourceLink_value = $('#videoSourceLink_Btn').val();//传入后台controller之后需要截取文件名的字符串片段~
        	var algorithmID_value = 1;
        	
        	$.ajax({
                url: "ajax_imageProcess4Trial_frameSegmentation",
                data: { 	sourceVideoLink : encodeURI( videoSourceLink_value ) ,
        					algorithmID : algorithmID_value
        					},
                type: "GET",
                success: function (boundryListJsonObject) {
                    
	                  var jsonObject = eval("("+boundryListJsonObject+")");

	                  var boundryListJsonObject = jsonObject.boundryList;
	                  var boundryListSize = jsonObject.boundryList.length;
	                  var destFolderLink = jsonObject.destFolderLink;
	                  var concatStr = "";
	                  var ImgIndex = 0;
	                  var firstFlag = 1;
	                  var columeCountFlag = 0;
	                  concatStr = "";
	                  
	                  concatStr += " <div class=\"well\"> "
					                		    +" <div class=\"row\"> "
						                	        +" <div class=\"col-md-1\"></div> "
						                	        +" <div class=\"col-md-10\"> "
						                	            +" <h3>当前视频已通过算法分析，经检测，包含场景数目：" + boundryListSize + "个；</h3> "
						                	        +" </div> "
						                	        +" <div class=\"col-md-1\"></div> "
					                	        +" </div> "
					                	+" </div> " ; 
	                  concatStr += "<div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">";
	                  $.each( boundryListJsonObject, function(index){
	                	  
                  		concatStr +=	" <div class=\"panel panel-default\"> "
													    +" <div class=\"panel-heading\" role=\"tab\" id=\"headingOne\"> "
													        +" <h4 class=\"panel-title\"> "
													            +" <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse" + ImgIndex + "\" aria-expanded=\"false\" aria-controls=\"collapseOne\" class=\"collapsed\"> "
													            +" 场景：#" + (index+1) + " "
													            +" </a> "
													        +" </h4> "
													    +" </div> "
													    +" <div id=\"collapse" + ImgIndex + "\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingOne\" aria-expanded=\"false\" style=\"height: 0px;\"> "
														        +" <div class=\"panel-body\"> ";
														    
														            for( ; ImgIndex < jsonObject.boundryList[index]; ImgIndex++ ){
														            	
															            	if( firstFlag == 1){
															            		
			            			concatStr +=					            "<br />"
			            																+" <div class=\"row\"> "
															            					+" <div class=\"col-md-2\"> "
																			                    +" <img data-src=\"holder.js/100%x180\" alt=\"...\" src=\" "+ destFolderLink + (ImgIndex+1) +".jpg  \" style=\"height: 80px; width: 100%\"> "
																			                +" </div> ";
			            																firstFlag = 0;
			            																columeCountFlag++;
			            																if( ImgIndex + 1 == jsonObject.boundryList[index] ){
	                	  							concatStr +=				" </div> ";     
	                	  																		firstFlag = 1;
	                	  																		columeCountFlag = 0;
	                	  																		continue;
																		                  }
															            	
															            	}else if( columeCountFlag == 5 || ImgIndex + 1 == jsonObject.boundryList[index]){
															            		
								concatStr +=					            " <div class=\"col-md-2\"> "
																	                    +" <img data-src=\"holder.js/100%x180\" alt=\"...\" src=\" "+  destFolderLink + (ImgIndex+1) +".jpg \" style=\"height: 80px; width: 100%\"> "
																	                +" </div> "
															            		+" </div> ";
															            				firstFlag = 1;
															            				columeCountFlag = 0;
															            	}else{
															            		
									concatStr +=					            " <div class=\"col-md-2\"> "
																		                    +" <img data-src=\"holder.js/100%x180\" alt=\"...\" src=\" "+ destFolderLink + (ImgIndex+1) +".jpg \" style=\"height: 80px; width: 100%\"> "
																		                +" </div> ";
																						columeCountFlag++;
															            	}
														           
														            }
														        
						            concatStr +=		" </div> "
														
														        +" <div class=\"row\"> "
														            +" <div class=\"col-md-4\"></div> "
														            +" <div class=\"col-md-4\"> "
														                +" <button type=\"button\" class=\"btn btn-danger\">对该场景进行进行关键帧提取</button> "
														            +" </div> "
														            +" <div class=\"col-md-4\"></div> "
														        +" </div> "
													    +" </div> "
												    +" </div> ";		        
											
	                  });
	                  concatStr +=		" </div> ";
	                  
	                  $("#result-div").html('');
	                  $("#result-div").html(concatStr);
	                  
                }
            });
        })

    })


</script>

</body>
</html>