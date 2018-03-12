<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype>
<html>
	<head>
		<meta charset="utf-8">
		<title>注册-词库</title>
		<link rel="stylesheet" type="text/css" href="public/style/baseContainer.css">
		<link rel="stylesheet" type="text/css" href="public/style/register.css">
		<script src="public/JS/myMainAPI.js"></script>
	</head>
	<body>
		<header class="header-container">
			<img src="public/img/logo5.png" class="logo">
		</header>
		<div id="main-container">
			<div class="form-container">
				<div class="form-header"><label>注册 词库</label></div>
				<div class="form-body">
					<label class="username">用户名</label>
					<input type="text" name="username" id="username" class="form-control input-block">
					<label class="input-tip">(由1到10位数字或字母组成)</label>
					<label>密码</label>
					<input type="password" name="password" id="password" class="form-control input-block">					
					<label class="input-tip">(由6到15位数字、字母或常用符号组成)</label>
					<label>确认密码</label>
					<input type="password" name="rePassword" id="confirm" class="form-control input-block">
					<input type="submit" id="registerBtn" class="btn-block" value="注     册">
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
			ps: 'unchecked',
			rePS: 'unchecked'
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
				$.getAjax('Register',{
					username
				},function(data){
					if(data === '1'){
						tip.tip = '用户名已经被注册';
						$.tip(tip);
						$('#username').setStyle('borderColor','red');						
						validate.user = 'repeat';
					}else if(data === '0'){
						$('#username').setStyle('borderColor','#ddd');
						validate.user = 'pass';
					}else if(data === '-1'){
						tip.tip = '服务器开了小差';
						$.tip(tip);
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
		var rePs = $('#confirm').on('blur',function(){
			if(this.value.length){
				if(this.value === ps.getAttr('value')){
					$('#rePassword').setStyle('borderColor','#ddd');
					validate.rePs = 'pass';
				}else{
					tip.tip = '密码不一致'
					$.tip(tip);
					$('#rePassword').setStyle('borderColor','red');
					validate.rePs = 'different';
				}
			}else{
				tip.tip = '请确认密码'
				$.tip(tip);
				$('#rePassword').setStyle('borderColor','red');
				validate.rePs = 'empty';
			}
		});
		$('#registerBtn').on('click',function(){
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
			if(!rePs.getAttr('value').length){
				tip.tip = '请确认密码'
				$.tip(tip);
				return;
			}
			if(ps.getAttr('value') != rePs.getAttr('value')){
				tip.tip = '密码不一致'
				$.tip(tip);
				return;
			}
			if(validate.user === 'notfound'){
				tip.tip = '用户名已经被注册';
				$.tip(tip);
				return;
			}
			$.postAjax('Register',{
				username: user.getAttr('value'),
				password: ps.getAttr('value')
			},function(data){
				if(data === '1'){
					tip.tip = '注册成功----->跳转首页';
					$.tip(tip);
					setTimeout("window.location.href = 'index.jsp'",1500);
				}else if(data === '-1'){
					tip.tip = '用户名已经被注册';
					$.tip(tip);
					validate.user = 'repeat';
				}
			});
		});
	</script>
</html>