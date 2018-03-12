

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
 if (request.getSession(false) == null || request.getSession(false).getAttribute("username") == null){
	response.sendRedirect("login.jsp");      
}
%>
<!doctype>
<html>
	<head>
		<meta charset="utf-8">
		<title>首页-词库</title>
		<link rel="stylesheet" type="text/css" href="public/style/baseCommon.css">
		<link rel="stylesheet" type="text/css" href="public/style/index.css">
		<script src="public/JS/myMainAPI.js"></script>
	</head>
	<body>
		<header class="header-container">
			<img src="public/img/logo5.png" class="logo">
			<div class="head-tab">
				<a href="index.jsp" class="tab-item">首页</a>
				<a href="viewAccordingToTime.jsp" class="tab-item">词库</a>
				<a href="glossary.jsp" class="tab-item">GO！</a>
			</div>
			<span class="title">
				词 库
			</span>			
			<div class="user-status">
				<span class="user"><%= session.getAttribute("username") %></span>
				<a href="#" class="drop-down">
					<img src="public/img/down2.jpg" class="drop-down-img" id="drop-down">
				</a>
				<div id="drop-list-container" class="drop-list-container">
					<a class="drop-list" href="" id="modify">修改资料</a>
					<a class="drop-list" href="" id="logout">退出</a>
				</div>
			</div>	
		</header>
		<div class="hold-content"></div>
		<div class="wraper"></div>
		<div class="main-content">
			<div class="content"></div>
			<footer class="footer-container">
				<p>
					Copyright © 2017 Trans-Manager ALL RIGHT RESERVED
				</p>
			</footer>
		</div>
	</body>
	<script>
		installHelper(window,'$');
		var dropContainer = $('#drop-list-container');
		$('#drop-down').on('click',function(){
			if(dropContainer.sign){
				dropContainer.sign = false;
				dropContainer.setStyle('display','none');
			}else{
				dropContainer.sign = true;
				dropContainer.setStyle('display','block');
			}
		});
		$('#logout').on('click',function(){
			$.getAjax('Logout','',function(data){
				if(data == '1' || data == '0'){
					window.location.assign('login.jsp');
				}
			});
		});
	</script>
</html>