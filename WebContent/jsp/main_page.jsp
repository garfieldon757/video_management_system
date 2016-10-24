<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="" data-desktopbrowser="true"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>  
	  <title>Private Home Care: Find &amp; Book Carers in the UK | HomeTouch</title>
	
	  <link rel="stylesheet" type="text/css" href="css/material.custom.css">
	  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

	  <link rel="stylesheet" href="css/bootstrap.css">
	  <link rel="stylesheet" type="text/css" href="css/bootstrap(1).css">
	  <link rel="stylesheet" type="text/css" href="css/hometouch.css">
	  <link rel="stylesheet" type="text/css" href="css/carer-badges.css">


</head>
<body ng-cloak="">

  <div class="home-page">

      <header class="ht-header">
        <div class="row -padding-y-5">
          <div class="col-xs-5 col-sm-4 partial">
            <a href="https://www.myhometouch.com/" class="ht-image -image-logo pull-left" data-di-id="di-id-b1f3ea31-41876b7"><img src="adVideoManagement/img/ht-logo.png" alt="logo"></a>
            <a href="https://www.myhometouch.com/" class="ht-image -image-logo -mobile pull-left" data-di-id="di-id-b1f3ea31-41876b7"><img src="adVideoManagement/img/ht-logo-mobile.png" alt="logo"></a>
          </div>
          <div class="col-xs-7 col-sm-8 partial text-right">
		        <c:if test="${! (user == null) }">
					        <ul class="ht-list -list-main">
							
					            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenuDivider" data-toggle="dropdown">
					              		<span class="caret"> ${ user.userName }</span>
					            </button>
					
					            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenuDivider">
					              <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
					              <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
					              <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
					              <li role="presentation" class="divider"></li>
					              <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
					            </ul>
					
					            <li class="list-item -login">
					              <a href="">退出</a>
					            </li>
				
				          </ul>
		        </c:if>
		        
		        <c:if test="${ user == null }">
				        <ul class="ht-list -list-main">
				            <li class="list-item -login">
				              	<a href="" >登陆</a>
				            </li>
				            <li class="list-item -login">
				            	<a href="">注册</a>
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
              <i class="ht-icon icon-search"></i>
              <div class="content">
                <h3>1. 搜索待处理视频</h3>
                <div>输入你的搜索关键词来在<br>海量视频库中搜索视频资源。</div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="ht-box -box-intro clearfix">
              <i class="ht-icon icon-talk"></i>
              <div class="content">
                <h3>2. 选取视频分析算法</h3>
                <div>根据平台提供的多个分析<br> 阶段的分析算法选取相应算法。</div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="ht-box -box-intro clearfix">
              <i class="ht-icon icon-tick"></i>
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
            <button class="btn ht-btn -btn-blue -btn-lg">
              <span>进入平台</span>
              <i class="ht-icon icon-search"></i>
            </button>
          </div>
          <div class="col-md-5"></div>
      </div>

    </div>

  </article>

  <article class="container">
      <h3 class="ht-title -title-lg">Meet our Carers of the Month</h3>

      <div class="ht-wrapper -wrapper-carers row">
          <div class="col-sm-6">
            <div class="name">Jose</div>
            <div>
              <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="./Private Home Care_ Find &amp; Book Carers in the UK _ HomeTouch_files/QMHmnhM2p50.html" allowfullscreen=""></iframe>
              </div>
            </div>
            <div class="location">Live In Carer</div>
          </div>
          <div class="col-sm-6">
            <div class="name">Rebecca</div>
            <div>
              <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="./Private Home Care_ Find &amp; Book Carers in the UK _ HomeTouch_files/Gb2-w3kpkvE.html" allowfullscreen=""></iframe>
              </div>
            </div>
            <div class="location">Lancing, Brighton</div>
          </div>
      </div>

    <div class="ht-wrapper -wrapper-slider">
      <h2 class="ht-title -title-lg">What are people saying?</h2>

      <div class="ht-slider slick-initialized slick-slider" role="toolbar">
        <div aria-live="polite" class="slick-list draggable"><div class="slick-track" role="listbox" style="opacity: 1; width: 7966px; left: -2276px;"><div class="ht-slide slick-slide slick-cloned" data-slick-index="-1" aria-hidden="true" tabindex="-1" style="width: 1138px;">
          <div class="content">
            <strong>Above &amp; beyond the call of duty</strong><br>
            We needed to find a carer to go to a hotel location on xmas day at
            very short notice to support my elderly mother for one morning only.
            The service and communication from HomeTouch and the carer they put us
            in touch with were first class in every respect. No hesitation in recommending HomeTouch.
            I have dealt with similar organisations before but never had an experience as good as this.
          </div>
          <div class="signature">Paul Beard</div>
        </div><div class="ht-slide slick-slide" data-slick-index="0" aria-hidden="true" tabindex="-1" role="option" aria-describedby="slick-slide00" style="width: 1138px;">
          <p class="content">
            <strong>A great way to choose care</strong><br>The idea that you can choose the Carer and
            in our case see a video of the Carer is a major change that has great advantages.<br>
            This enabled us to find a real gem of a Carer.
          </p>
          <div class="signature">Derek Pryke</div>
        </div><div class="ht-slide slick-slide slick-current slick-active" data-slick-index="1" aria-hidden="false" tabindex="-1" role="option" aria-describedby="slick-slide01" style="width: 1138px;">
          <p class="content">
            <strong>Fabulous carer</strong><br>My sister has been visited once a week by the same carer
            for the past 5 weeks. She is in a word fantastic. My sister is very happy.
            I am so pleased to find a company that understands what the job entails helping us to find this fabulous
            lady.
            The future thankfully looks a lot more settled.
          </p>
          <div class="signature">Jo Johnson</div>
        </div><div class="ht-slide slick-slide" data-slick-index="2" aria-hidden="true" tabindex="-1" role="option" aria-describedby="slick-slide02" style="width: 1138px;">
          <p class="content">
            <strong>Invaluable resource during difficult time</strong><br>
            The website was easy to use and an invaluable resource for me as I was trying to find last minute night
            cover for a relative who had dementia and had been admitted to hospital.<br><br>
            Thank you for this service - it honestly made a hugely positive impact for not only my relative, but the
            family too.
          </p>
          <div class="signature">C smith</div>
        </div><div class="ht-slide slick-slide" data-slick-index="3" aria-hidden="true" tabindex="-1" role="option" aria-describedby="slick-slide03" style="width: 1138px;">
          <div class="content">
            <strong>Home Touch saved the day for us</strong><br>
            I am a full time carer from my wife who had always refused anyone else looking after her.
            I had to go into hospital for three days so was extremely concerned, as was my wife.
            My wife was totally happy with the carer and I was able to go into hospital knowing I had nothing to worry
            about.
            Should the need arise that we need a carer in future we will definitely return to HomeTouch.
          </div>
          <div class="signature">Michael Wood</div>
        </div><div class="ht-slide slick-slide" data-slick-index="4" aria-hidden="true" tabindex="-1" role="option" aria-describedby="slick-slide04" style="width: 1138px;">
          <div class="content">
            <strong>Above &amp; beyond the call of duty</strong><br>
            We needed to find a carer to go to a hotel location on xmas day at
            very short notice to support my elderly mother for one morning only.
            The service and communication from HomeTouch and the carer they put us
            in touch with were first class in every respect. No hesitation in recommending HomeTouch.
            I have dealt with similar organisations before but never had an experience as good as this.
          </div>
          <div class="signature">Paul Beard</div>
        </div><div class="ht-slide slick-slide slick-cloned" data-slick-index="5" aria-hidden="true" tabindex="-1" style="width: 1138px;">
          <p class="content">
            <strong>A great way to choose care</strong><br>The idea that you can choose the Carer and
            in our case see a video of the Carer is a major change that has great advantages.<br>
            This enabled us to find a real gem of a Carer.
          </p>
          <div class="signature">Derek Pryke</div>
        </div></div></div>


        


        


        


        
      <ul class="slick-dots" style="" role="tablist"><li class="" aria-hidden="true" role="presentation" aria-selected="true" aria-controls="navigation00" id="slick-slide00"><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">1</button></li><li aria-hidden="false" role="presentation" aria-selected="false" aria-controls="navigation01" id="slick-slide01" class="slick-active"><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">2</button></li><li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation02" id="slick-slide02" class=""><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">3</button></li><li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation03" id="slick-slide03" class=""><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">4</button></li><li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation04" id="slick-slide04" class=""><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">5</button></li></ul></div>
    </div>
  </article>

  <article class="ht-ppc-read-more container">
    <h4>Read more about our story</h4>
    <ul class="ht-list -list-read-about clearfix">
      <li class="list-item">
        <a href="http://www.theguardian.com/small-business-network/2016/jan/02/small-business-spotlight-hometouch" data-di-id="di-id-2987bdb9-aaebd8ba">
          <img src="./adVideoManagement.png" alt="guardian">
        </a>
      </li>
      <li class="list-item">
        <a href="http://www.telegraph.co.uk/finance/newsbysector/retailandconsumer/12062319/Start-up-that-can-send-a-carer-to-your-home-within-hours-raises-400000.html" data-di-id="di-id-95439f3d-cc93491b">
          <img src="adVideoManagement/img/ppc-logo-telegraph.png" alt="telegraph">
        </a>
      </li>
      <li class="list-item">
        <img src="adVideoManagement/img/ppc-logo-bbc.png" alt="bbc radio 4">
      </li>
      <li class="list-item">
        <img src="adVideoManagement/img/ppc-logo-london-evening.png" alt="london evening standard">
      </li>
      <li class="list-item">
        <a href="http://www.nhs.uk/Conditions/social-care-and-support-guide/Pages/national-homecare-providers.aspx" data-di-id="di-id-31ad2cf0-efbfe9c1">
          <img src="adVideoManagement/img/ppc-logo-nhs-choices.jpg" alt="nhs choices">
        </a>
      </li>
    </ul>
  </article>


  <article class="container">
    <h3 class="ht-title -title-lg">Why HomeTouch?</h3>

    <div class="ht-wrapper -wrapper-why row">
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-why -box-blue">
          <i class="ht-icon icon-clock"></i>
          <div>
            <br>Get set up in just hours.
          </div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-why -box-orange">
          <i class="ht-icon icon-people"></i>
          <div>
            Largest selection of<br> carers in the UK vetted<br> by NHS doctors.
          </div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-why -box-yellow">
          <i class="ht-icon icon-wallet"></i>
          <div>
            Transparent and<br> Affordable pricing<br> £10-20/hr with no hidden costs.
          </div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-why -box-pink">
          <i class="ht-icon icon-heart"></i>
          <div>
            Motivated carers.<br> They earn 75% more than<br> working for an agency.
          </div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-why -box-green">
          <i class="ht-icon icon-lock"></i>
          <div>
            Secure payments, simple<br> contracts and responsive<br> customer service.
          </div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-why -box-purple">
          <i class="ht-icon icon-bulb"></i>
          <div>
            Specialised expertise in<br> dementia care and other<br> long term conditions.
          </div>
        </div>
      </div>
    </div>

    <h3 class="ht-title -title-lg">Your Safety is Our Top Priority</h3>

    <div class="ht-box -box-wide -box-blue">
      Only 5% of carer applicants successfully<br> become part of our network.
    </div>

    <div class="ht-wrapper -wrapper-safety row">
      <div class="col-xs-6">
        <div class="ht-box -box-safety">
          <i class="ht-icon icon-interview"></i>
          <div>
            We interview all carers<br> face to face
          </div>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="ht-box -box-safety">
          <i class="ht-icon icon-star"></i>
          <div>
            We only select experienced<br> care professionals
          </div>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="ht-box -box-safety">
          <i class="ht-icon icon-dbs"></i>
          <div>
            We perform enhanced background<br> checks <span class="-highlight">(DBS/CRB)</span> of all carers
          </div>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="ht-box -box-safety">
          <i class="ht-icon icon-bupa"></i>
          <div>
            We are supported by leading healthcare<br> organizations such as <span class="-highlight">BUPA</span>
          </div>
        </div>
      </div>
    </div>

    <h4 class="ht-title -title-md">Any Questions?</h4>

    <div class="ht-wrapper -wrapper-questions row">
      <div class="col-xs-6">
        <div class="ht-box -box-questions -box-blue -left">
          <a href="tel:+44 20 7148 6746" data-di-id="di-id-c85c1732-873a2e4a">
            <i class="ht-icon icon-phone"></i>
            <span>0207 148 6746</span>
          </a>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="ht-box -box-questions -box-blue -right">
          <a href="https://www.myhometouch.com/faq-caremarketplace" data-di-id="di-id-cea906c2-32b32761">
            <i class="ht-icon icon-faq"></i>
            <span>Read FAQ</span>
          </a>
        </div>
      </div>
    </div>
  </article>


  <h3 class="ht-title -title-lg -title-team">Meet The Team</h3>
  <article class="ht-wrapper -wrapper-team">

    <div class="ht-team-bubble ht-team-bubble--arrow-left">
      <div>
        <div class="ht-team-bubble__title">Oliver Scanlon</div>
        <div class="ht-team-bubble__description">Head of Carer Community</div>
      </div>
    </div>

    <div class="ht-team-bubble">
      <div>
        <div class="ht-team-bubble__title">Corinna Herbst</div>
        <div class="ht-team-bubble__description">10 years in Risk management,<br>business operations and Insurance</div>
      </div>
    </div>

    <div class="ht-team-bubble">
      <div>
        <div class="ht-team-bubble__title">Dr Jamie Wilson CEO</div>
        <div class="ht-team-bubble__description">Dementia physician<br>15 years in NHS</div>
      </div>
    </div>

    <div class="ht-team-bubble">
      <div>
        <div class="ht-team-bubble__title">Dr Aisling Glennie</div>
        <div class="ht-team-bubble__description">Head of Carer Compliance<br>10 years across health and care</div>
      </div>
    </div>

    <div class="ht-team-bubble">
      <div>
        <div class="ht-team-bubble__title">Josh Lacumbre Gibbs</div>
        <div class="ht-team-bubble__description">Care adviser,<br>experienced carer and<br>background in NHS</div>
      </div>
    </div>

    <img class="ht-image -image-team" alt="Team" src="adVideoManagement/img/ht-team-multi.jpg">
    <img class="ht-image -image-team -mobile" alt="Team" src="adVideoManagement/img/ht-team-multi-mobile.jpg">

    <p>
      HomeTouch is the UK’s leading online destination to help families find trusted, quality and affordable care.
    </p>
  </article>


  <article class="container">
    <h3 class="ht-title -title-lg">Our Services</h3>
    <h3 class="ht-title -title-sm">We cover all your loved one's needs.</h3>

    <div class="ht-wrapper -wrapper-services row">
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-hand"></i>
          <div><br>Companionship</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-hospital"></i>
          <div><br>Post Hospital Care</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services -box-link">
          <a href="https://www.myhometouch.com/live-in-care" data-di-id="di-id-3c9e4acb-78aad51a">
            <i class="ht-icon icon-home"></i>
            <div><br>Live-in Care</div>
          </a>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-bed"></i>
          <div><br>Morning and bedtime routines</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-appointment"></i>
          <div><br>Attending Appointments</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-sandclock"></i>
          <div>Specialised care in dementia and<br>other long term conditions</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-chef"></i>
          <div><br>Nutrition and Food Preparation</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-care"></i>
          <div><br>Personal Care</div>
        </div>
      </div>
      <div class="col-xs-6 col-md-4">
        <div class="ht-box -box-services">
          <i class="ht-icon icon-medicine"></i>
          <div><br>Medication Prompting</div>
        </div>
      </div>
    </div>
  </article>


  <article class="ht-wrapper -wrapper-map">
    <h3 class="ht-title -title-lg">Find Carers Near You</h3>
    <div>
      <form class="ht-form -form-search -form-labeled clearfix" action="https://www.myhometouch.com/carers">
        <div class="partial -left">
          <input name="q" type="text" class="ht-form-text -text-lg js-location" placeholder="Enter postcode or keyword">
        </div>
        <div class="partial -right">
          <button class="btn ht-btn -btn-blue -btn-lg">
            <span>FIND A CARER</span>
            <i class="ht-icon icon-search"></i>
          </button>
        </div>
      </form>
    </div>

    <div class="ht-wrapper-inner -wrapper-map js-map">
      <div class="ht-wrapper -wrapper-map-list">
        <ul class="ht-list -list-map js-map-carer">
            <li class="list-item clearfix">
              <div class="avatar -purple">
                <img src="./Private Home Care_ Find &amp; Book Carers in the UK _ HomeTouch_files/picture">
              </div>
              <div class="user">
                <div class="name">Carmen M</div>
                <div class="location">Slough</div>
                <div class="price">£15 / hour</div>
              </div>
            </li>
            <li class="list-item clearfix">
              <div class="avatar -purple">
                <img src="./Private Home Care_ Find &amp; Book Carers in the UK _ HomeTouch_files/picture(1)">
              </div>
              <div class="user">
                <div class="name">Elizabeth J</div>
                <div class="location">Spelthorne</div>
                <div class="price">£15 / hour</div>
              </div>
            </li>
            <li class="list-item clearfix">
              <div class="avatar -purple">
                <img src="./Private Home Care_ Find &amp; Book Carers in the UK _ HomeTouch_files/picture(2)">
              </div>
              <div class="user">
                <div class="name">Mary D</div>
                <div class="location">Spelthorne</div>
                <div class="price">£12 / hour</div>
              </div>
            </li>
        </ul>

        <a class="btn ht-btn -btn-blue -btn-lg" href="https://www.myhometouch.com/carers" data-di-id="di-id-a309977b-ad67136b">See all Carers</a>
      </div>

      <div id="ht-map" class="ht-map" style="height: 530px;" data-lat="51.4706451" data-lng="-0.5746572"></div>
    </div>

    <ul class="ht-list -list-boroughs">
      <li class="list-item">
        <a href="https://www.myhometouch.com/london" data-di-id="di-id-8df0b00d-edbc998f">London</a>
      </li>
      <li class="list-item">
        <a href="https://www.myhometouch.com/london/camden" data-di-id="di-id-61994779-e50f6d92">Camden</a>
      </li>
      <li class="list-item">
        <a href="https://www.myhometouch.com/london/hackney" data-di-id="di-id-61994779-929f7aec">Hackney</a>
      </li>
      <li class="list-item">
        <a href="https://www.myhometouch.com/london/islington" data-di-id="di-id-c6622a85-64779232">Islington</a>
      </li>
      <li class="list-item">
        <a href="https://www.myhometouch.com/london/southwark" data-di-id="di-id-c6622a85-9edfdd31">Southwark</a>
      </li>
      <li class="list-item -item-wrapper js-map-menu">
        <span>Other Boroughs</span>
        <i class="ht-icon icon-arrow-left"></i>
        <ul class="ht-list -list-nested">
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/barking%20and%20dagenham" data-di-id="di-id-e0d5dca8-ad711e01">Barking and Dagenham</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/barnet" data-di-id="di-id-688fdab7-9556b3f3">Barnet</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/bexley" data-di-id="di-id-688fdab7-b29a74b5">Bexley</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/brent" data-di-id="di-id-5d033fca-4d46bd34">Brent</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/east-sussex/brighton-and-hove" data-di-id="di-id-f5fc60e-69f39e29">Brighton</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/bromley" data-di-id="di-id-688fdab7-bcab785a">Bromley</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/croydon" data-di-id="di-id-688fdab7-753332c4">Croydon</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/ealing" data-di-id="di-id-688fdab7-dd0e9f39">Ealing</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/enfield" data-di-id="di-id-688fdab7-99312bea">Enfield</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/greenwich" data-di-id="di-id-bc0166b0-dd0651a6">Greenwich</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/hammersmith%20and%20fulham" data-di-id="di-id-4986f8e1-f0a15376">Hammersmith and Fulham</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/haringey" data-di-id="di-id-bc0166b0-2074add5">Haringey</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/harrow" data-di-id="di-id-688fdab7-cc7d8b85">Harrow</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/havering" data-di-id="di-id-bc0166b0-6903bca1">Havering</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/hillingdon" data-di-id="di-id-236a0ca9-1b064500">Hillingdon</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/hounslow" data-di-id="di-id-bc0166b0-8a6facea">Hounslow</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/kensington%20and%20chelsea" data-di-id="di-id-4986f8e1-80d63b4">Kensington and Chelsea</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/kingston%20upon%20thames" data-di-id="di-id-e0d5dca8-227f6adf">Kingston upon Thames</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/lambeth" data-di-id="di-id-688fdab7-18c5ad30">Lambeth</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/lewisham" data-di-id="di-id-bc0166b0-95c648a8">Lewisham</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/merton" data-di-id="di-id-688fdab7-ec65ccad">Merton</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/newham" data-di-id="di-id-688fdab7-75aee914">Newham</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/redbridge" data-di-id="di-id-bc0166b0-3ce3eb00">Redbridge</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/richmond%20upon%20thames" data-di-id="di-id-e0d5dca8-19082948">Richmond upon Thames</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/sutton" data-di-id="di-id-688fdab7-34d39067">Sutton</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/tower%20hamlets" data-di-id="di-id-2d8c17eb-3cd8f3b1">Tower Hamlets</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/waltham%20forest" data-di-id="di-id-5bfc487a-fcb28153">Waltham Forest</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/wandsworth" data-di-id="di-id-236a0ca9-ff3f1c70">Wandsworth</a>
          </li>
          <li class="list-item">
            <a href="https://www.myhometouch.com/london/westminster" data-di-id="di-id-236a0ca9-80814e00">Westminster</a>
          </li>
          <li class="list-item -item-more js-map-menu">
            <a href="javascript:void(0)" data-di-id="di-id-6a68f9f2-8fcb245">Close</a>
          </li>
        </ul>
      </li>
    </ul>

  </article>


  <article>
    <div class="ht-app ht-app--lead">

      <div class="ht-app__wrapper">

        <div class="ht-app__description">
          <h3>Fill out the no-obligation form to get started</h3>

          <p>
            Whether you are looking for a trusted companion at home or need urgent live in care after hospital, our
            experienced team of NHS doctors and healthcare professionals can assist you with finding the right match.
          </p>
        </div>

        <form class="ht-app__form js-lead-capture-form" novalidate="true">
          <div class="ht-app__field-wrapper form-group">
            <input class="ht-app__form-input" type="text" name="name" placeholder="Your name" required="">
            <span class="ht-app__form-required help-block with-errors"></span>
          </div>

          <div class="ht-app__field-wrapper form-group">
            <input class="ht-app__form-input" type="email" name="email" placeholder="Email" data-native-error="The email address is invalid." required="">
            <span class="ht-app__form-required help-block with-errors"></span>
          </div>

          <div class="ht-app__field-wrapper form-group">
            <input class="ht-app__form-input" type="text" name="phone" placeholder="Phone number" required="">
            <span class="ht-app__form-required help-block with-errors"></span>
          </div>

          <div class="ht-app__field-wrapper ht-app__field-wrapper--single form-group">
            <input class="ht-app__form-input" type="text" name="postcode" placeholder="Post code" required="">
            <span class="ht-app__form-required help-block with-errors"></span>
          </div>

          <input class="hidden" type="text" name="address" placeholder="Please leave blank">
          <input type="hidden" name="source" value="home page">
          <div class="ht-app__form-submit-wrapper">
            <button class="ht-app__form-submit in-touch disabled" type="submit">Get in touch with me</button>

            <p class="ht-app__form-message">We will get back to you within an hour.</p>
          </div>
        </form>

        <div class="ht-app__form-message ht-app__form-message--submitted js-lead-capture-form-thanks" style="display: none;">
          Thank you for your enquiry.<br>One of the HomeTouch team will be in touch shortly.
        </div>
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
        
        
            <div class="row content">
              <div class="col-xs-8">
                <div class="row">
                  <div class="col-xs-6 text-center">
                    <i class="ht-icon icon-phone"></i>
                    <span>0207 148 6746</span>
                  </div>
                  <div class="col-xs-6 text-center">
                    <a href="mailto:hello@myhometouch.com" data-di-id="di-id-86fcdef-ad828529">
                      <span>hello@myhometouch.com</span>
                    </a>
                  </div>
                </div>
              </div>
              <div class="col-xs-4 text-center">
                <a class="" href="https://play.google.com/store/apps/details?id=com.conciergeapp" target="_blank" data-di-id="di-id-87c29b26-5f1d4d45">
                  <span>Get the app</span>
                  <i class="ht-icon icon-android"></i>
                </a>
              </div>
            </div>
        
        
            <div class="row">
              <div class="col-xs-12 display-mobile">
                HomeTouch Care Ltd 2nd Floor, White Bear Yard, 144a Clerkenwell Road, London, EC1R 5DF
              </div>
              <div class="col-xs-12">
                <p>
                  HomeTouch is an introductory agency as defined by the Care Quality Commission (CQC). We select, vet and
                  introduce self-employed carers to clients via an online platform and facilitate care contracts between
                  clients and self-employed carers. HomeTouch is not a "full service" care agency and is unable to influence
                  care delivery or manage care plans. "HomeTouch" is the trading name of HomeTouch Care Ltd, a company registered in England and Wales
                  (9410945). Registered office: 107 Bell St, London, NW1 6TL.
                </p>
              </div>
              <div class="col-xs-8 hide-mobile">
                HomeTouch Care Ltd 2nd Floor, White Bear Yard, 144a Clerkenwell Road, London, EC1R 5DF
              </div>
              <div class="col-xs-4 text-right">
                <a class="social" href="https://www.facebook.com/MyHomeTouch" target="_blank" data-di-id="di-id-e906b075-1ebd989d">
                  <i class="ht-icon icon-fb"></i>
                </a>
                <a class="social" href="https://twitter.com/myhometouch" target="_blank" data-di-id="di-id-cfccb138-36d80350">
                  <i class="ht-icon icon-twitter"></i>
                </a>
              </div>
            </div>
          </div>
        </footer>    
  </div>

  


  
  <script src="js/jquery.min.js"></script>
<script type="text/javascript" id="">!function(b,e,f,g,a,c,d){b.fbq||(a=b.fbq=function(){a.callMethod?a.callMethod.apply(a,arguments):a.queue.push(arguments)},b._fbq||(b._fbq=a),a.push=a,a.loaded=!0,a.version="2.0",a.queue=[],c=e.createElement(f),c.async=!0,c.src=g,d=e.getElementsByTagName(f)[0],d.parentNode.insertBefore(c,d))}(window,document,"script","https://connect.facebook.net/en_US/fbevents.js");fbq("init","1027644270654279");fbq("track","PageView");fbq("track","ViewContent");</script>
<noscript>&lt;img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=1027644270654279&amp;amp;ev=PageView&amp;amp;noscript=1"&gt;</noscript>

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


  <script>
    // Enable popover across the site
    $('[data-toggle="popover"]').popover();
  </script>

  <!-- /SCRIPTS -->

  

</body>
</html>