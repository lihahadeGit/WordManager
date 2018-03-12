<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
 if (request.getSession(false) != null && request.getSession(false).getAttribute("username") != null)
  {
    %><jsp:forward page="index.jsp" /><%
  }
%>
<!doctype>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录-词库</title>
		<link rel="stylesheet" type="text/css" href="public/style/login.css">
		<link rel="stylesheet" type="text/css" href="public/style/baseContainer.css">
		<script src="public/JS/myMainAPI.js"></script>
	</head>
	<body>
		<header class="header-container">
			<img src="public/img/logo5.png" class="logo">
		</header>
		<div id="main-container">
			<div class="form-container">
				<div class="form-header"><label>登录到 词库</label></div>
				<div class="form-body">
					<label class="username">你的用户名</label>
					<input type="text" name="username" id="username" class="form-control input-block">
					<label>你的密码</label>
					<input type="password" name="password" id="password" class="form-control input-block">
					<input type="submit" id="loginBtn" class="btn-block" value="登     录">
				</div>
				<div class="form-res">
					<p class="create-account">
						没有账号？
						<a href="register.jsp">new一个！</a>
					</p>
				</div>
			</div>
		</div>
		<footer class="footer-container">
			<p>
				Copyright © 2017 Trans-Manager ALL RIGHT RESERVED
			</p>
		</footer>
	</body>
	<script>
		installHelper(window,'$');
		var validate = {
			user: 'unchecked',
			ps: 'unchecked'
		};
		var tip = {
			tip: '',
			color: 'rgb(193,33,39)',
			border: '1px solid rgb(20,199,0)',
			padding: '5px 20px 5px 20px',
			last: 1000,
			die: 500,
			top: parseInt(window.scrollY+2*window.innerHeight/3+70)+'px',
			left: parseInt(window.scrollX+window.innerWidth/2 - 100)+'px'
		}
		var user = $('#username').on('blur',function(){
			if(this.value.length){
				var value = this.value;
				$.getAjax('Login',{
					username: this.value
				},function(data){
					if(data != '1'){
						tip.tip = '用户名不存在';
						$.tip(tip);
						$('#username').setStyle('borderColor','red');						
						validate.user = 'notfound';
					}else if(data === '1'){
						$('#username').setStyle('borderColor','#ddd');
						validate.user = 'pass';
					}
				});
			}else{
				tip.tip = '用户名不能为空';
				$.tip(tip);
				$(this).setStyle('borderColor','red');
				validate.user = 'empty';
			}
		});
		var ps = $('#password').on('blur',function(){
			if(this.value.length){
				$('#password').setStyle('borderColor','#ddd');
				validate.ps = 'pass';
			}else{
				tip.tip = '密码不能为空'
				$.tip(tip);
				$('#password').setStyle('borderColor','red');
				validate.ps = 'empty';
			}
		});
		$('#loginBtn').on('click',function(){
			if(!user.getAttr('value').length){
				tip.tip = '用户名不能为空'
				$.tip(tip);
				return;
			}
			if(!ps.getAttr('value').length){
				tip.tip = '密码不能为空'
				$.tip(tip);
				return;
			}
			if(validate.user === 'notfound'){
				tip.tip = '用户名不存在';
				$.tip(tip);
				return;
			}
			$.postAjax('Login',{
				username: user.getAttr('value'),
				password: ps.getAttr('value')
			},function(data){
				if(data === '1' || data === '3'){
					window.location.href = 'index.jsp';
				}else if(data === '0'){
					tip.tip = '用户名或密码错误';
					$.tip(tip);
				}else if(data === '-1'){
					tip.tip = '服务器在开小差';
					$.tip(tip);
				}
			});
		});
	</script>
</html>