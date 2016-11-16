<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en" class="" data-desktopbrowser="true"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">



  <link rel="stylesheet" type="text/css" href="css/material.custom.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap(1).css">
  <link rel="stylesheet" type="text/css" href="css/hometouch.css">
  <link rel="stylesheet" type="text/css" href="css/carer-badges.css">



  <style>html.ng-cloak {display: none;}</style>
</head>
<body >

  <div class="">


      <section>
        <div class="ht-app" ng-controller="LoginCtrl">

          <div class="ht-app__wrapper">
          <a class="ht-app__return js-return" href="backToMainPage" >
          		<span class="glyphicon glyphicon-hand-left"></span>
	          返回首页
	        </a>

            <div class="ht-app__header">
              <div class="ht-app__header-content">
                <h1 class="ht-app__header-title">登陆</h1>
              </div>
            </div>


            <form class="ht-app__form" name="form" action="login">
              <div class="ht-app__field-wrapper">
                <input id="email" name="email" placeholder="输入账号(Email)" class="ht-app__form-input" type="text" required>
                <div id="checkDiv_email">
                  <br id="br_email" style="display: block">
                  <p id="error_email" style="display:none; color:red">该输入不符合要求</p>
                  <p id="success_email"><img src="" /></p>
                </div>
              </div>

              <div class="ht-app__field-wrapper">
                <input id="password" name="password" type="password" placeholder="输入密码" class="ht-app__form-input" type="text" required>
                <div id="checkDiv_password">
                  <br id="br_password" style="display: block">
                  <p id="error_password" style="display:none; color:red">该输入不符合要求</p>
                  <p id="success_password"><img src="" /></p>
                </div>
              </div>

              <a class="ht-app__form-link ht-app__form-link--forgot" href="/app/reset-password/">
                忘记密码?
              </a>

              <div class="ht-app__form-submit-wrapper ht-app__form-submit-wrapper--login">
                <button id="log_in_form_button" class="ht-app__form-submit">登陆</button>

                <div class="ht-app__additional">
                  <span class="margin-right-10">从未来到过视频分析平台?</span>
                  <a class="btn ht-btn -btn-blue" href="signUp_page" >注册</a>
                </div>
              </div>
            </form>
          </div>
        </div>

      </section>

    
  </div>


  <script src="js/jquery.min.js"></script>

    <script src="js/js.cookie.js"></script>
    <script src="js/validator.js"></script>
    <script src="js/slick.min.js"></script>
    <script src="js/app.js"></script>
    <script src="js/navigation.js"></script>
    <script src="js/quick-filter.js"></script>
    <script src="js/google-map.js"></script>
    <script src="js/init.js"></script>
    <script src="js/lead-capture.js"></script>
    <script src="js/contact.js"></script>
    <script src="js/tooltip.js"></script>
    <script src="js/popover.js"></script>
    <script src="js/dropdown.js"></script>
    <script src="js/lodash.js"></script>
    <script src="js/requestAnimationFrame.js"></script>
    <script src="js/material.min.js"></script>
    <script src="js/jquery.placeholder.js"></script>



</body>
</html>
