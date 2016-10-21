<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" class="" data-desktopbrowser="true"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>AdVideoManagement</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <link rel="stylesheet" type="text/css" href="css/material.custom.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap(1).css">
  <link rel="stylesheet" type="text/css" href="css/hometouch.css">
  <link rel="stylesheet" type="text/css" href="css/carer-badges.css">



</head>
<body>

<div class="">


  <section>
    <div class="ht-app ht-app--register">
      <div ui-view="" class="ng-scope"><div class="ht-app__wrapper ng-scope">
      <a class="ht-app__return js-return" href="" >
        <i class="ht-icon icon-ui-return"></i>
        返回首页
      </a>

      <div class="ht-app__header">
        <div class="ht-app__header-content">
          <h1 class="ht-app__header-title ng-binding">
            注册
          </h1>
        </div>
      </div>


      <form  class="ht-app__form ng-pristine ng-invalid ng-invalid-required ng-valid-remote ng-valid-email Formisimo_clocked_71994" action="signUp" name="form">
        <div class="ht-app__field-wrapper" >
          <input id="sex" name="sex" class="ht-app__form-input ng-pristine ng-untouched ng-invalid ng-invalid-required ng-valid-remote" type="text" placeholder="性别">
          <div id="checkDiv_Sex" >
            <br id="br_Sex" style="display: block">
            <p id="error_Sex" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_Sex"><img src="" /></p>
          </div>
        </div>


        <div class="ht-app__field-wrapper" >
          <input id="username" name="userName" class="ht-app__form-input ng-pristine ng-untouched ng-invalid ng-invalid-required ng-valid-remote" type="text" placeholder="昵称">
          <div id="checkDiv_userName" >
            <br id="br_userName" style="display: block">
            <p id="error_userName" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_userName"><img src="" /></p>
          </div>
        </div>


        <div class="ht-app__field-wrapper" >
          <input id="userPassword" name="userPassword" class="ht-app__form-input ng-pristine ng-untouched ng-valid-email ng-invalid ng-invalid-required ng-valid-remote" type="email" placeholder="密码">
          <div id="checkDiv_userPassword" >
            <br id="br_userPassword" style="display: block">
            <p id="error_userPassword" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_userPassword"><img src="" /></p>
          </div>
        </div>

        <div class="ht-app__field-wrapper" >
          <input id="userPasswordConfirm" name="userPasswordConfirm" class="ht-app__form-input ng-pristine ng-untouched ng-valid-email ng-invalid ng-invalid-required ng-valid-remote" type="text" placeholder="密码确认">
          <div id="checkDiv_userPasswordConfirm" >
            <br id="br_userPasswordConfirm" style="display: block">
            <p id="error_userPasswordConfirm" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_userPasswordConfirm"><img src="" /></p>
          </div>
        </div>


        <div class="ht-app__field-wrapper">
          <input id="email" name="email" class="ht-app__form-input ng-pristine ng-untouched ng-invalid ng-invalid-required ng-valid-remote" type="text" placeholder="电子邮箱">
          <div id="checkDiv_email" >
            <br id="br_email" style="display: block">
            <p id="error_email" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_email"><img src="" /></p>
          </div>
        </div>


        <div class="ht-app__form-submit-wrapper">
          <div>
            <button id="sign_up_form_button" class="ht-app__form-submit" type="submit" >注册</button>

            <div class="ht-app__additional">
              <span class="margin-right-10">已注册过账户？</span>
              <a class="btn ht-btn -btn-blue"href="/app/login#/">登陆</a>
            </div>
          </div>
        </div>
      </form>
    </div>
    </div>
    </div>
  </section>

</div>




<!-- build:js(.) /public/js/website/index.js -->
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
<!-- endbuild -->




</body>
</html>