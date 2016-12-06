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
      <a class="ht-app__return js-return" href="backToMainPage" >
      	<span class="glyphicon glyphicon-hand-left"></span>
        返回首页
      </a>

      <div class="ht-app__header">
        <div class="ht-app__header-content">
          <h1 class="ht-app__header-title ng-binding">
            注册
          </h1>
        </div>
      </div>


      <form  class="ht-app__form" action="signUp" name="form">
        
        <div class="ht-app__field-wrapper" >
          <input id="userName" name="userName" class="ht-app__form-input" type="text" placeholder="昵称">
          <div id="checkDiv_userName" >
            <br id="br_userName" style="display: block">
            <p id="error_userName" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_userName"style="display:none; color:green">该输入符合要求</p>
          </div>
        </div>

        <div class="ht-app__field-wrapper" >
	        <input id="sex" name="sex" class="ht-app__form-input" type="text" placeholder="性别">
	        <div id="checkDiv_Sex" >
	          <br id="br_Sex" style="display: block">
	          <p id="error_Sex" style="display:none; color:red">该输入不符合要求</p>
	          <p id="success_Sex"><img src="" /></p>
	        </div>
	      </div>

        <div class="ht-app__field-wrapper">
	        <input id="userPassword" name="userPassword" class="ht-app__form-input" type="text" placeholder="密码">
	        <div id="checkDiv_userPassword" >
	          <br id="br_userPassword" style="display: block">
	          <p id="error_userPassword" style="display:none; color:red">该输入不符合要求</p>
	          <p id="success_userPassword"><img src="" /></p>
	        </div>
	      </div>

        <div class="ht-app__field-wrapper" >
          <input id="userPasswordConfirm" name="userPasswordConfirm" class="ht-app__form-input" type="text" placeholder="密码确认">
          <div id="checkDiv_userPasswordConfirm" >
            <br id="br_userPasswordConfirm" style="display: block">
            <p id="error_userPasswordConfirm" style="display:none; color:red">该输入不符合要求</p>
            <p id="success_userPasswordConfirm"><img src="" /></p>
          </div>
        </div>

        <div class="ht-app__field-wrapper" >
	        <input id="email" name="email" class="ht-app__form-input" type="email" placeholder="电子邮箱">
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
              <a class="btn ht-btn -btn-blue" href="login_page">登陆</a>
            </div>
          </div>
        </div>
      </form>
    </div>
    </div>
    </div>
  </section>

</div>




<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">


$(document).ready(function(){
	

    $("#userName").blur(function () {


         var userNameValue = $('#userName').val();
        $.ajax({
            url: "ajax_userNameValidation",
            data: { userName : userNameValue},
            type: "GET",
            success: function (response) {
                
                reg_result = response ;//返回用户名和当前页面session的用户名就不做为
                									//,返回"available"表示userName可用，“unavailable“表示不可用
                alert(response);
//                if(reg_result == "available"){
//                    $("#userName_div").removeClass("has-error");
//                    $("#userName_div").addClass("has-success");
//                    $("#checkDiv_userName > p:last").css('display', 'block');
//                    $("#checkDiv_userName > p:first").css('display', 'none');
//                    alert("用户名可用");
//                }else if( reg_result != "available" ){
//                	
//                	if( reg_result == $("#userName").val() ){
//                        
//                		//不作为
//                		
//                    }else if(reg_result == "unavailable"){
//                    	$("#userName_div").removeClass("has-success");
//                        $("#userName_div").addClass("has-error");
//                        $("#checkDiv_userName > p:first").css('display', 'none');
//                        $("#checkDiv_userName > p:last").css('display', 'block');
//                        alert("用户名已被占用");
//                    }
//                	
//                }
                
            }
        });
        
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
})


</script>


</body>
</html>