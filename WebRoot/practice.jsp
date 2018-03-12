<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
 if (session == null)
  {
    %><jsp:forward page="login.jsp" /><%
  }
%>
<!doctype>
<html>
	<head>
		<meta charset="utf-8">
		<title>-词库</title>
		<link rel="stylesheet" type="text/css" href="public/style/baseCommon.css">
		<link rel="stylesheet" type="text/css" href="public/style/practice.css">
		<script src="public/JS/myMainAPI.js"></script>
	</head>
	<body>
		<header class="header-container">
			<img src="public/img/logo5.png" class="logo">
			<div class="head-tab">
				<a href="index.jsp" class="tab-item">首页</a>
				<a href="viewAccordingToTime.jsp" class="tab-item">词库</a>
				<a href="practice.jsp" class="tab-item">GO！</a>
			</div>
			<span class="title">
				词 库
			</span>					
			<div class="user-status">
				<span class="user">FreePoiX</span>
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
			<input type="hidden" value="<%= request.getParameter("glossaryname") %>" id="glossary-name">
			<div class="content">
				<div class="practice-container">
					<div class="practice-status"></div>
					<div id="last" class="card default-last"></div>
					<div id="now" class="card default-word"></div>
					<div id="next" class="card default-next"></div>
				</div>
				<div class="mint-blue-container"></div>
				<div style="height:520px;margin-bottom: 15px;"></div>
			</div>
			<footer class="footer-container">
				<p>
					Copyright © 2017 Trans-Manager ALL RIGHT RESERVED
				</p>
			</footer>
		</div>
	</body>
	<script>
		installHelper(window,'$');
		$.log(true);

		var strangeWords = [],
			holdWords = [],
			alreadyWords = [],
			backWords = [],
			remainWords = [];
		var strangeCursor = 0,
			holdCursor = 0;
		var maxWordsOneTurn = 8,
			overrideWordsNum = 1;
		var baseType;
		var data = [];
		function sort(){
			var o = Object.create(null);
			var oConnect = Object.create(null);
			var oIndex = Object.create(null);
			var prop;	
			for(var i=0,len=data.length;i<len;i++){
				prop = 'a'+data[i].wordid;
				o[prop] = 1;
				oConnect[prop] = data[i].next;
				oIndex[prop] = i;
			}
			for(var i=0,len=data.length;i<len;i++){
					o['a'+data[i].next] = 0;
			}
			for(var prop in o){
				if(o[prop]){
					if(data[oIndex[prop]].type == 1)
						var tempArray = strangeWords;
					else if(data[oIndex[prop]].type == 2)
						var tempArray = holdWords;
					else if(data[oIndex[prop]].type == 3)
						var tempArray = alreadyWords;
					do{
						var item = data[oIndex[prop]];
						tempArray.push({
							id: parseInt(item.wordid),
							next: parseInt(item.next),
							type: parseInt(item.type),
							en: item.text_English,
							zh: item.text_Chinese,
							usPronounce: item.alphabetUS,
							ukPronounce: item.alphabetUK
						});
						prop = 'a'+oConnect[prop];
					}while(typeof oConnect[prop] != 'undefined');
				}
			}
		}
		function init(){
			if(strangeWords.length == 0 && holdWords.length == 0){
				$('#now').setAttr('innerText','done!').setStyle(['line-height','text-align','font-size'],['100%','center','30px']);
				return;
			}
			$('#now').append($.create('<div id="word" style="width:100%;height:90px;line-height:90px;text-align:center;font-size:30px;margin-top:20px;"></div>'))
			.append($.create('<div style="width:100%;height:39px;border-bottom:1px solid rgb(216,222,226);line-height:40px"></div>').append($.create('<label id="usPronounce" style="display:block;float:left;">美：</label>')).append($.create('<label id="ukPronounce" style="display:block;float:right;">英：</label>')))
			.append($.create('<div id="zhs" style="width:100%;height:149px;border-bottom:1px solid rgb(216,222,226);text-align:center"></div>'))
			.append($.create('<div id="seeZh" style="background:white;width:100%;height:149px;border-bottom:1px solid rgb(216,222,226);display:none;font-size:30px;line-height:149px;text-align:center;">???</div>'))
			.append($.create('<div style="width:100%;height:64px;"><div id="forget" style="float:left;height:100%;width:49%;border-right:1px solid rgb(216,222,226);display:none;font-size:20px;line-height:64px;text-align:center;">忘了</div><div id="done" style="float:right;height:100%;width:50%;font-size:20px;text-align:center;line-height:64px;">记住了</div></div>'));
			baseType = strangeWords.length?strangeWords[0].type:(holdWords.length?holdWords[0].type:alreadyWords[0].type);
			backWords.push(strangeWords.length?strangeWords[0]:holdWords[0]);
			$('#seeZh').on('click',function(){
				change(parseInt($('#word').getOriAttr('index')),2,1);
				$(this).setStyle('display','none');
				$('#zhs').setStyle('display','block');
				$('#forget').setStyle('display','none');
			});
			$('#forget').on('click',function(){
				$('#word').setOriAttr('type','1')
				change(parseInt($('#word').getOriAttr('index')),2,1);
				$('#seeZh').setStyle('display','none');
				$('#zhs').setStyle('display','block');
				$(this).setStyle('display','none');
			});
			$('#done').on('click',function(){
				change(parseInt($('#word').getOriAttr('index')),parseInt($('#word').getOriAttr('type')),parseInt($('#word').getOriAttr('type'))+1);
				next();
			});	
			setData(strangeWords.length?strangeWords[0]:holdWords[0],strangeWords.length>1?strangeWords[1]:(holdWords.length?holdWords[0]:null));			
		}
		function setData(now,next,last){
			$('#word').setAttr('innerText',now.en).setOriAttr(['index','type'],[now.id,now.type]);
			$('#usPronounce').setAttr('innerText',now.usPronounce);
			$('#ukPronounce').setAttr('innerText',now.ukPronounce);
			var zhContainer = $('#zhs').clean();
			var zhs = now.zh.split('~');
			for(var i=0 ; i<zhs.length;i++){
				zhContainer.append($.create('label',{'innerText':zhs[i]})).append($.create('br'));
			}
			if(now.type == '1' || now.type == '3'){
				$('#zhs').setStyle('display','block');
				$('#seeZh').setStyle('display','none');
				$('#forget').setStyle('display','none');
			}else{
				$('#zhs').setStyle('display','none');
				$('#seeZh').setStyle('display','block');
				$('#forget').setStyle('display','block');
			}
			$('#last').setAttr('innerText',last==null?'?':last.en);
			$('#next').setAttr('innerText',next==null?'?':next.en);
		}
		function change(id,from,to){
			var queue = from===1?strangeWords:(from===2?holdWords:alreadyWords);
			var queueTo = to===1?strangeWords:(to===2?holdWords:alreadyWords);
			for(var i=0;i<queue.length;i++){
				if(queue[i].id == id)
					break;
			}
			var item = queue.splice(i,1)[0];
			try{ 
				$.getAjax('RememberAndForget',{
					wordId: item.id,
					newType: to,
					glossaryName: $('#glossary-name').getAttr('value')
				},function(msg){
					if(msg == 1){
						console.log('modify successfully.');
					}else{
						console.log('modify failly.');
					}
				});
			}catch(e){
				console.log(e);
			}
			item.type = to;
			item.next = null;
			queueTo.push(item);
			queue[queue.length-1].next = item.id;
			if(from === 1){
				strangeCursor -= 1;
			}else if(from===2){
				holdCursor -= 1;
			}
		}
		function next(){
			if(remainWords.length){
				var now = remainWords.shift();
				var next;
				if(remainWords.length){
					next = remainWords[0];
				}else if(strangeWords.length == 0 && holdWords.length == 0){
					next = null;
				}else if(strangeWords.length != 0 && holdWords.length == 0){
					next = strangeWords[(strangeCursor+1)%strangeWords.length];
				}else if(strangeWords.length == 0 && holdWords.length != 0){
					next = holdWords[(holdCursor+1)%holdWords.length];
				}else{
					if(overrideWordsNum>=maxWordsOneTurn){
						next = (holdCursor == holdCursor.length-1)?strangeWords[(baseType=='1'?strangeCursor+1:strangeCursor)/strangeWords.length]:holdWords[holdCursor];
					}else{
						next = overrideWordsNum+1==maxWordsOneTurn ? holdWords[0]:strangeWords[(strangeCursor+1)%strangeWords.length];
					}
				}
				setData(now,next,backWords[backWords.length-1]);
				backWords.push(now);
				return;
			}
			if(strangeWords.length == 0 && holdWords.length == 0){
				$('#now').setAttr('innerText','done!').setStyle(['line-height','text-align','font-size'],['100%','center','30px']);
			}else if(strangeWords.length != 0 && holdWords.length == 0){				
				if(baseType=='1'){					
					strangeCursor = (strangeCursor+1)%strangeWords.length;
					setData(strangeWords[strangeCursor],strangeWords[(strangeCursor+1)%strangeWords.length],backWords[backWords.length-1]);
					backWords.push(strangeWords[strangeCursor]);
				}else{
					setData(strangeWords[strangeCursor],strangeWords[(strangeCursor+1)%strangeWords.length],backWords[backWords.length-1]);
					backWords.push(strangeWords[strangeCursor]);
					strangeCursor = (strangeCursor+1)%strangeWords.length;
				}
				overrideWordsNum += 1;
			}else if(strangeWords.length == 0 && holdWords.length != 0){
				if(baseType=='2'){
					holdCursor = (holdCursor+1)%holdWords.length;
					setData(holdWords[holdCursor],holdWords[(holdCursor+1)%holdWords.length],backWords[backWords.length-1]);
					backWords.push(holdWords[holdCursor]);
				}else{
					setData(holdWords[holdCursor],holdWords[(holdCursor+1)%holdWords.length],backWords[backWords.length-1]);
					backWords.push(holdWords[holdCursor]);
					holdCursor = (holdCursor+1)%holdWords.length;
				}
			}else{
				if(overrideWordsNum>=maxWordsOneTurn){
					if(baseType=='2'){
						holdCursor += 1;
						setData(holdWords[holdCursor],(holdCursor == holdWords.length-1)?strangeWords[(baseType=='1'?strangeCursor+1:strangeCursor)%strangeWords.length]:holdWords[holdCursor+1],backWords[backWords.length-1]);
						backWords.push(holdWords[holdCursor]);
					}else{
						setData(holdWords[holdCursor],(holdCursor == holdWords.length-1)?strangeWords[(baseType=='1'?strangeCursor+1:strangeCursor)%strangeWords.length]:holdWords[holdCursor+1],backWords[backWords.length-1]);
						backWords.push(holdWords[holdCursor]);
						holdCursor += 1;
					}
					if(holdCursor == holdWords.length){
						if(strangeCursor < strangeWords.length){
							overrideWordsNum = 0;
						}
						holdCursor = 0;
					}
				}else{
					if(baseType=='1'){					
						strangeCursor = (strangeCursor+1)%strangeWords.length;
						setData(strangeWords[strangeCursor],overrideWordsNum+1==maxWordsOneTurn?holdWords[0]:strangeWords[(strangeCursor+1)%strangeWords.length],backWords[backWords.length-1]);
						backWords.push(strangeWords[strangeCursor]);
					}else{
						setData(strangeWords[strangeCursor],overrideWordsNum+1==maxWordsOneTurn?holdWords[0]:strangeWords[(strangeCursor+1)%strangeWords.length],backWords[backWords.length-1]);
						backWords.push(strangeWords[strangeCursor]);
						strangeCursor = (strangeCursor+1)%strangeWords.length;						
					}
					overrideWordsNum += 1;			
				}
			}			
		}
		function last(){
			if(backWords.length < 2){
				return;
			}
			var next = backWords.pop();
			var now = backWords[backWords.length-1];
			var last = backWords.length>1?backWords[backWords.length-2]:null;
			remainWords.unshift(next);
			setData(now,next,last);
		}
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
					window.location.assign('login.html');
				}
			});
		});
		$.getAjax('GetInitGlossaryData',{
			glossaryName: $('#glossary-name').getAttr('value')
		},function(msg){
			msg = JSON.parse(msg);
			if(msg.type != 1)
				return
			data = msg.words;
			sort();
			init();
			$('#last').on('click',function(){
				last();
			});
			$('#next').on('click',function(){
				next();
			});
		});
	</script>
</html>






