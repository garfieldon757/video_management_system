<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="" data-desktopbrowser="true"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
        <title>Private Home Care: Find &amp; Book Carers in the UK | HomeTouch</title>

        <link rel="stylesheet" type="text/css" href="css/material.custom.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/hometouch.css">
        <link rel="stylesheet" type="text/css" href="css/carer-badges.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


    </head>
<body ng-cloak="">

<div class="home-page">

    <header class="ht-header">
        <div class="row -padding-y-5">
            <div class="col-xs-5 col-sm-4 partial">
                <a href="backToMainPage" class="ht-image -image-logo pull-left" ><img src="img/ht-logo.png" alt="logo"></a>
            </div>
            <div class="col-xs-7 col-sm-8 partial text-right">
                <c:if test="${! (user == null) }">
                    <ul class="ht-list -list-main">
                    
                        
                        <li class="list-item -login">
                        	<a href="edit_personalProfile_load">用户名： ${ user.userName }</a>
	                    </li>
                        <li class="list-item -login">
                            <a href="logout">退出</a>
                        </li>

                    </ul>
                </c:if>

                <c:if test="${ user == null }">
                    <ul class="ht-list -list-main">
                        <li class="list-item -login">
                            <a href="login_page" >登陆</a>
                        </li>
                        <li class="list-item -login">
                            <a href="signUp_page">注册</a>
                        </li>
                    </ul>
                </c:if>



            </div>
        </div>

    </header>

    <section>
        <article class="ht-wrapper -wrapper-cta -lg">
            <div class="ht-wrapper-inner -wrapper-cta">
                <h1 class="ht-title -title-lg">视频分析&高层语义提取平台</h1>

                <div class="container">
                    <div class="ht-wrapper -wrapper-negative row">
                        <div class="col-md-4">
                            <div class="ht-box -box-intro clearfix">
                                <span class="glyphicon glyphicon-search"></span>
                                <div class="content">
                                    <h3>1. 搜索待处理视频</h3>
                                    <div>输入你的搜索关键词来在<br>海量视频库中搜索视频资源。</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="ht-box -box-intro clearfix">
                            	<span class="glyphicon glyphicon-signal"></span>
                                <div class="content">
                                    <h3>2. 选取视频分析算法</h3>
                                    <div>根据平台提供的多个分析<br> 阶段的分析算法选取相应算法。</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="ht-box -box-intro clearfix">
                            <span class="glyphicon glyphicon-ok"></span>
                                <div class="content">
                                    <h3>3. 运行，得到结果</h3>
                                    <div>得到所选算法处理所选<br> 择视频的分析结果，可视化展示。</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/><br/><br/><br/><br/>
                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-2">
                        <a href="videoSearchInit?videoCategoryID=1&page=1">
	                        <button class="btn ht-btn -btn-blue -btn-lg">
	                            <span>进入平台</span>
	                            <span class="glyphicon glyphicon-hand-up"></span>
	                        </button>
                        </a>
                    </div>
                    <div class="col-md-5"></div>
                </div>

            </div>

        </article>

    </section>


    <footer class="ht-footer">
    <div class="container">
        <ul class="ht-list -list-footer clearfix">
            <li class="list-item">
                <a href="https://www.myhometouch.com/about" data-di-id="di-id-5e9d5f0a-7a142ade">About us</a>
            </li>
            <li class="list-item">
                <a href="http://blog.myhometouch.com/" data-di-id="di-id-aa0e2b66-8e674eb5">Blog</a>
            </li>
            <li class="list-item">
                <a href="https://www.myhometouch.com/faq-caremarketplace" data-di-id="di-id-bfc48601-4caed9ac">FAQ</a>
            </li>
            <li class="list-item">
                <a href="https://www.myhometouch.com/terms-caremarketplace" data-di-id="di-id-f2d2aeb8-5f150c8e">Customer T&amp;Cs</a>
            </li>
            <li class="list-item">
                <a href="https://www.myhometouch.com/terms-carer" data-di-id="di-id-275ee6c3-c26e1353">Carer T&amp;Cs</a>
            </li>
            <li class="list-item">
                <a href="https://www.myhometouch.com/carers" data-di-id="di-id-71e072a6-3d227fa">Profiles</a>
            </li>
            <li class="list-item">
                <a href="https://www.myhometouch.com/contact" data-di-id="di-id-71e072a6-5002b3a1">Contact Us</a>
            </li>
        </ul>


        <div class="row">
            <div class="col-xs-8 hide-mobile">
                HomeTouch Care Ltd 2nd Floor, White Bear Yard, 144a Clerkenwell Road, London, EC1R 5DF
            </div>
    </div>
</footer>

</div>





<script src="js/jquery.min.js"></script>




</body>
</html>