<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<template:page pageTitle="${pageTitle}">
	<!DOCTYPE html>
	<html lang="zh-tw">
<!-- InstanceBegin template="/Templates/hola.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>單一品牌分類頁</title>



<!-- CSS -->
<link rel="stylesheet" href="${commonResourcePath}/css/normalize.css">
<%-- <link rel="stylesheet" href="${commonResourcePath}/css/bootstrap.css"> --%>
<link rel="stylesheet"
	href="${commonResourcePath}/css/non-responsive.css">
<link rel="stylesheet" href="${commonResourcePath}/css/main.css">
<link rel="stylesheet" href="${commonResourcePath}/css/style.css">

</head>
<body>

	<c:if test="${not empty ecpList}">
		<ul>
			<c:forEach var="val" items="${ecpList}">
				<li>${val.code}</li>
				<li>${val.name}</li>
				<li>${val.story}</li>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${not empty productList}">
		<ul>
			<c:forEach var="element" items="${productList}">
				<li id='code'>${element.name}</li>
				
				<li id='manufacturer'>${element.manufacturer}</li>
		   </c:forEach>
		</ul>
	</c:if>




	<!--購物車-->
	<div class="shopping_icon">
		<a chref="#">+購物車</a>
	</div>

	<!--上版位-->
	<div class="container" id="header">

		<section id="headerTop">
			<h1 class="logo">
				<img
					src="${commonResourcePath}/${commonResourcePath}/images/logo.png">
			</h1>
			<div class="searchZone">
				<input type="text" value="請輸入商品名稱、編號或關鍵字"> <a><img
					src="${commonResourcePath}/images/icon_search.jpg"></a>
			</div>
			<div class="subLogo">
				<a href="#"><img
					src="${commonResourcePath}/images/logo_casa.png"></a> <a href="#"><img
					src="${commonResourcePath}/images/logo_hola_s.png"></a>
			</div>
		</section>

		<section class="MTB15">
			<nav id="mainNav">
				<ul class="inlineBlock">
					<li><a href="#">線上購物</a></li>
					<li><a href="#">網購獨享</a></li>
					<li><a href="#">出清</a></li>
					<li><a href="#">團購</a></li>
					<li><a href="#">預購</a></li>
					<li><a href="#">熱銷排行</a></li>
					<li><a href="#">品牌總覽</a></li>
					<li><a href="#">線上型錄</a></li>
					<li><a href="#">生活提案</a></li>
					<li><a href="#">活動情報</a></li>
					<li><a href="#">服務諮詢</a></li>
					<li><a href="#">Gift</a></li>
				</ul>
			</nav>

			<nav id="loginNav">
				<ul class="inlineBlock">
					<li><span>您好!</span></li>
					<li><a href="#">登入</a></li>
					<li><a href="#">註冊</a></li>
					<li><a href="#">顧客中心</a></li>
				</ul>
			</nav>
		</section>

		<section id="subNav">
			<ul class="inlineBlock">
				<li><a href="#">傢俱</a></li>
				<li><a href="#">寢室</a></li>
				<li><a href="#">浴室</a></li>
				<li><a href="#">廚房</a></li>
				<li><a href="#">餐具</a></li>
				<li><a href="#">收納</a></li>
				<li><a href="#">裝飾</a></li>
				<li><a href="#">地毯</a></li>
				<li><a href="#">清潔</a></li>
				<li><a href="#">燈飾</a></li>
				<li><a href="#">美食</a></li>
				<li><a href="#">家電</a></li>
			</ul>
		</section>

	</div>
	<!--上版位end-->


	<!--主版位-->
	<div class="container" id="wrapper">
		<!-- InstanceBeginEditable name="MainZone" -->
		<section class="breadCrumbs">
			<span class="grayColor">首頁</span> > <span class="grayColor">品牌總覽</span>
			> <span>Joseph Joseph</span>
		</section>

		<!--brand Txt-->
		<section class="brandTxt">
			<h3 class="brownColor">
				<img src="${commonResourcePath}/images/sample/brandLogo.jpg" />
			</h3>
			<p>乾淨，整潔，有組織的廚房，您可以專注於更重要的事情，如創建美味的自製美食。我們的實用產品範圍，提供切實可行的解決方案，以更現實的工作與他們的時尚，創新設計的廚房。創新、多功能性與繽紛的色彩，獨特的廚房用具和餐具系列非常適合送給廚藝精湛的朋友，在廚房一展身手。</p>
		</section>

		<!--brand Slider-->
		<section class="brandSlider">
			<a class="btnPrev"><img
				src="${commonResourcePath}/images/btn_front.png"></a> <a
				class="btnNext"><img
				src="${commonResourcePath}/images/btn_back.png"></a>

			<ul class="brandSliderButton inlineBlock">
				<li><a href="#" class="active"><i></i>鍋具料理用具5折起</a></li>
				<li><a href="#"><i></i>浴室花小錢換然一新</a></li>
				<li><a href="#"><i></i>活動標題最多九全字</a></li>
				<li><a href="#"><i></i>收納用品買足最便宜</a></li>
				<li><a href="#"><i></i>收納商品最低折起</a></li>
			</ul>

			<ul class="mainSliderImg">
				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/brandBN.jpg"></a></li>
			</ul>

		</section>

		<!--Button-->
		<section>
			<div class="btn-group btn-group-justified brandBtn" role="group"
				aria-label="...">
				<div class="btn-group" role="group">
					<button type="button" class="btn active">
						全部商品<i></i>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn">
						新品上市<i></i>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn">
						新品預購<i></i>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn">
						出清商品<i></i>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn">
						品牌故事<i></i>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" class="btn">
						文章分享<i></i>
					</button>
				</div>
			</div>
		</section>

		<!--次分類-->
		<section class="brandSub MB30">
			<div class="title-1 MTB15">
				<h3 class="letter_3px">調理烘焙</h3>
				<section>
					<HR>
				</section>
			</div>
			<ul class="inlineBlock indexPrds">
				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_1.png"></a>
					<h3>九陽料理奇機</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_discount">75折</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_18.png"></a>
					<h3>斑馬高型調理碗4入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_3.png"></a>
					<h3>日本青森完熟蘋果汁1L</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_new">新品</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_16.png"></a>
					<h3>馬來貘LED造型小夜燈3入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_5.png"></a>
					<h3>馬來貘方巾/毛巾2入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>
				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_1.png"></a>
					<h3>九陽料理奇機</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_discount">75折</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_18.png"></a>
					<h3>斑馬高型調理碗4入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_3.png"></a>
					<h3>日本青森完熟蘋果汁1L</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_new">新品</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_16.png"></a>
					<h3>馬來貘LED造型小夜燈3入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_5.png"></a>
					<h3>馬來貘方巾/毛巾2入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>
			</ul>
		</section>

		<!--次分類名稱-->
		<section class="brandSub MB30">
			<div class="title-1 MTB15">
				<h3 class="letter_3px">(次品類)名稱</h3>
				<section>
					<HR>
				</section>
			</div>
			<ul class="inlineBlock indexPrds">
				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_1.png"></a>
					<h3>九陽料理奇機</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_discount">75折</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_18.png"></a>
					<h3>斑馬高型調理碗4入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_3.png"></a>
					<h3>日本青森完熟蘋果汁1L</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_new">新品</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_16.png"></a>
					<h3>馬來貘LED造型小夜燈3入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_5.png"></a>
					<h3>馬來貘方巾/毛巾2入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>
				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_1.png"></a>
					<h3>九陽料理奇機</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_discount">75折</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_18.png"></a>
					<h3>斑馬高型調理碗4入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_3.png"></a>
					<h3>日本青森完熟蘋果汁1L</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><i class="icon_new">新品</i> <a href="#"><img
						src="${commonResourcePath}/images/sample/prd_16.png"></a>
					<h3>馬來貘LED造型小夜燈3入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>

				<li><a href="#"><img
						src="${commonResourcePath}/images/sample/prd_5.png"></a>
					<h3>馬來貘方巾/毛巾2入組</h3>
					<p>
						<span class="MR5">售價$5,400</span><span class="redColor">特價</span><span
							class="redColor font_20">$4,980</span>
					</p></li>
			</ul>
		</section>


		<!-- InstanceEndEditable -->
	</div>
	<!--主版位end->

<!--Footer function-->
	<div class="container" id="footerFN">
		<div class="safe_icons">
			<img src="${commonResourcePath}/images/logo_norton.png"> <img
				src="${commonResourcePath}/images/logo_sosa.png"> <img
				src="${commonResourcePath}/images/logo_dp.png">
		</div>

		<ul>
			<li><a href="#">品牌簡介</a></li>
			<li><a href="#">全台門市</a></li>
			<li><a href="#">線上型錄</a></li>
			<li><a href="#">異業合作</a></li>
		</ul>

		<ul>
			<li><a href="#">客服中心</a></li>
			<li><a href="#">交易安全</a></li>
			<li><a href="#">隱私權保護</a></li>
		</ul>

		<div class="co_icons">

			<div id="fb-root"></div>
			<script>
				(function(d, s, id) {
					var js, fjs = d.getElementsByTagName(s)[0];
					if (d.getElementById(id))
						return;
					js = d.createElement(s);
					js.id = id;
					js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.4&appId=199817436730919";
					fjs.parentNode.insertBefore(js, fjs);
				}(document, 'script', 'facebook-jssdk'));
			</script>

			<div class="fb-like" data-layout="button_count"
				data-href="https://www.facebook.com/HOLA.taiwan"></div>

			<section>
				<a href="#"><img src="${commonResourcePath}/images/icon_fb.png"></a>
				<a href="#"><img src="${commonResourcePath}/images/icon_u2b.png"></a>
				<a href="#"><img
					src="${commonResourcePath}/images/icon_plurk.png"></a> <a
					href="#"><img src="${commonResourcePath}/images/icon_dp.png"></a>
			</section>
			<hr>
			<section>
				<a href="#"><img
					src="${commonResourcePath}/images/icon_hola.png"></a>
			</section>

		</div>

	</div>


	<!--footer-->
	<footer class="text-center">
		<section>
			<p>特力屋股份有限公司 版權所有 © Copyright 2015 Test Rite Retail Co., LTD. All
				Rights Reserved</p>
		</section>
	</footer>
	<!--footerEnd->
<!-- JS -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- InstanceBeginEditable name="JS" -->
	<script>
		$(function() {
		})
	</script>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd -->
	</html>



</template:page>