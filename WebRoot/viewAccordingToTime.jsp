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
		<!-- <base href="<%=basePath%>"> -->
		<title>时间轴浏览-词库</title>
		<link rel="stylesheet" type="text/css" href="public/style/baseCommon.css">
		<link rel="stylesheet" type="text/css" href="public/style/viewAccordingToTime.css">
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
			<div class="content">
				<div class="words-container">
					<div class="words-status"></div>
					<div class="words-show">
						<div class="time">
							<label class="words-item line1 words-time"></label>
							<label class="words-item line2 words-time"></label>
							<label class="words-item line3 words-time"></label>
							<label class="words-item line4 words-time"></label>
							<label class="words-item line5 words-time"></label>
							<label class="words-item line6 words-time"></label>
							<label class="words-item line7 words-time"></label>
							<label class="words-item line8 words-time"></label>
							<label class="words-item line9 words-time"></label>
							<label class="words-item line10 words-time"></label>
						</div>
						<div class="words-line">
							<div class="words-item line1 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line2 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line3 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line4 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line5 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line6 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line7 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line8 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line9 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认"><br>
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>
							<div class="words-item line10 words">
								<div class="status-en-container">
									<label class="status status-en" type="en"></label>
									<input type="text" class="status-input status-en-input">
								</div>
								<div class="status-zh-container">
									<label class="status status-zh" type="zh">
										
									</label>
									<input type="text" class="status-input status-zh-input">
								</div>
								<div class="status-usPronounce-container">
									<label class="status status-usPronounce" type="usPronounce">
									</label>
									<input type="text" class="status-input status-usPronounce-input">
								</div>
								<div class="status-ukPronounce-container">
									<label class="status status-ukPronounce" type="ukPronounce">
									</label>
									<input type="text" class="status-input status-ukPronounce-input">
								</div>
								<div class="status-glossary-container">
									<img src="public/img/add.png" class="add-img">
									<div class="addTO-glossary">
										<input type="text" class="glossary-name">
										<input type="button" class="confirm" value="确认">
									</div>
								</div>
								<div class="status-delete-container">
									<input type="button" value="删除" class="delete">
								</div>
							</div>	
						</div>
						<div class="adjust-container">
							<div class="up-adjust">
								<input type="text" id="up-step" value="3" class="step-input">
							</div>
							<div class="time-adjust">
								<div class="year-adjust item-adjust-container" id="year">
									<a href="#" class="adjust" id="year-adjust-down" operation="year-adjust-input down">-</a>
									<input type="text" class="adjust-input" value="2017" id="year-adjust-input">
									<a href="#" class="adjust" id="year-adjust-up" operation="year-adjust-input up">+</a>
								</div>
								<div class="month-adjust item-adjust-container" id="month">
									<a href="#" class="adjust" id="month-adjust-down" operation="month-adjust-input down">-</a>
									<input type="text" class="adjust-input" value="1" id="month-adjust-input">
									<a href="#" class="adjust" id="month-adjust-up" operation="month-adjust-input up">+</a>
								</div>
								<div class="day-adjust item-adjust-container" id="day">
									<a href="#" class="adjust" id="day-adjust-down" operation="day-adjust-input down">-</a>
									<input type="text" class="adjust-input" value="1" id="day-adjust-input">
									<a href="#" class="adjust" id="day-adjust-up" operation="day-adjust-input up">+</a>
								</div>
							</div>
							<div class="down-adjust">
								<input type="text" id="down-step" value="3" class="step-input">
							</div>	
						</div>
					</div>
					<div class="words-bottom"></div>
				</div> 
				<div style="height:560px;width:0px;margin-bottom: 15px;"></div>
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
		var downStepInput = $('#down-step');
		var upStepInput = $('#up-step');
		var wordsItem = $('.words-item');
		var downStep = parseInt(downStepInput.getAttr('value'));
		var upStep =  parseInt(upStepInput.getAttr('value'));
		
		var LINES = $('.words').element.length-1,cursor = 0,targetCursor = 0;
		var runningTag = false;
		var data = [];
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
		function load(lineIndex,dataIndex,num){
			for(var i=dataIndex;i<dataIndex+num;i++){				
				$('.status-en').setAttr('innerText',data[i].en,lineIndex).setOriAttr('type','textenglish',lineIndex);
				$('.status-zh').setAttr('innerText',data[i].zh,lineIndex).setOriAttr('type','textchinese',lineIndex);
				$('.status-usPronounce').setAttr('innerText',data[i].usPronounce,lineIndex).setOriAttr('type','alphabetUS',lineIndex);				
				$('.status-ukPronounce').setAttr('innerText',data[i].ukPronounce,lineIndex).setOriAttr('type','alphabetUK',lineIndex);
				$('.words').setOriAttr('index',data[i].id,lineIndex);
				$('.words-time').setAttr('innerText',data[i].time,lineIndex);
				lineIndex = (lineIndex+1)%10;
			}
		}
		function clear(DomIndex,num){
			for(var i=0;i<num;i++){				
				$('.status-en').setAttr('innerText','',DomIndex).setOriAttr('type','',DomIndex);
				$('.status-zh').setAttr('innerText','',DomIndex).setOriAttr('type','',DomIndex);
				$('.status-usPronounce').setAttr('innerText','',DomIndex).setOriAttr('type','',DomIndex);
				$('.status-ukPronounce').setAttr('innerText','',DomIndex).setOriAttr('type','',DomIndex);
				$('.words').setOriAttr('index','',DomIndex);
				$('.words-time').setAttr('innerText','',DomIndex);
				DomIndex = (DomIndex+1)%(LINES+1);
			}
		}
		function move(baseY){//
			runningTag = true;
			if(targetCursor == cursor){
				runningTag = false;
				return;
			}
			var tempBaseY = [];
			var tempCursor = cursor;
			if(targetCursor > cursor){
				if(data.length-1<cursor+LINES){
					runningTag = false;
					return;
				}
				for(var i=0;i<baseY.length;i++){
					if(baseY[i] == -50){
						wordsItem.setStyle('top','450px',i);
						load(i%10,cursor+LINES,1);

						baseY[i] = 450;
					}else if(baseY[i] == 450){
						load(i%10,cursor+LINES,1);
					}
					tempBaseY.push(baseY[i]-50);
				}
				cursor += 1;
			}
			else{
				if(0>cursor-1){
					runningTag = false;
					return;
				}
				for(var i=0;i<baseY.length;i++){
					if(baseY[i] == 450){
						wordsItem.setStyle('top','-50px',i);
						load(i%10,cursor-1,1);
						baseY[i] = -50;
					}else if(baseY[i] == -50){						
						load(i%10,cursor-1,1);					
					}
					tempBaseY.push(baseY[i]+50);
				}
				cursor -= 1;
			}			
			$.move(wordsItem,{
				baseY:baseY,
				moveY:targetCursor>tempCursor?-50:50,
				fn:move,
				last: 300
			},tempBaseY);
		}
		function getBaseY(){
			var baseY = new Array(20);
			for(var i=0,len=$('.words-item').element.length;i<len;i++){				
				var topStr = wordsItem.getStyle('top',i);
				baseY[i] = topStr == ''? (parseInt(i%(LINES+1))*50):(new Number(topStr.substring(0,topStr.indexOf('px')==-1?top.length:topStr.indexOf('px')))).valueOf();
			}
			return baseY;
		}
		$.getAjax('GetWordsInit',{
			browseWay: 'browseByDate'
		},function(msg){
			msg = JSON.parse(msg);
			if(msg.type=='1'){
				var tempData = msg.words;
				for(var i=0;i<tempData.length;i++){
					data.push({
						en: tempData[i].text_English,
						zh: tempData[i].text_Chinese,
						id: parseInt(tempData[i].wordid),
						usPronounce: tempData[i].alphabetUS,
						ukPronounce: tempData[i].alphabetUK,
						time: tempData[i].dateStr.split(' ')[0]
					});
				}
				delete tempData;
				load(0,0,data.length>=LINES?LINES:data.length);
			}
		});
		$('.status').on('click',function(){
			var status = $('.status');
			for(var i=0,len=status.element.length;i<len;i++){
				if(status.element[i] === this){
					var that = this;
					$('.status-input').on('blur',function(){
						this.style.display = 'none';
						var it = this;
						$.getAjax('EditWord',{
							wordId: $(that.parentNode.parentNode).getOriAttr('index'),
							type: $(that).getOriAttr('type'),
							value: this.value
						},function(msg){
							if(msg === '1'){
								$(that).setAttr('innerText',it.value);								
							}
						});
					},{index:i});					
					$('.status-input').setAttr('value',status.getAttr('innerText',i),i).setStyle('display','block',i)
					break;
				}
			}
		});
		$('.delete').on('click',function(){
			for(var i=0;i<data.length;i++){
				if(data[i].id == $(this.parentNode.parentNode).getOriAttr('index'))
					break;				
			}
			var that = this;
			var index = cursor;
			$.getAjax('DeleteWord',{
				wordId: data[i].id
			},function(msg){
				console.log(msg);
				if(msg === '1'){
					data.splice(i,1);
					for(var j=0,len=$('.delete').element.length;j<len;j++){
						if($('.delete').element[j] === that)
							break;
					}
					clear(j,data.length>= LINES-1?9-i+index:data.length-j+1);
					load(j,i,data.length>=LINES?9-i+index:data.length-j);
				}
			});
			
			
		});
		$('.adjust').on('click',function(e){//修改日期
			e.preventDefault();
			var action = this.attributes.operation.value.split(' ');
			var target = $('#'+action[0]);
			if(action[1] === 'down'){
				target.setAttr('value',target.getAttr('value')-1);
			}else{
				target.setAttr('value',parseInt(target.getAttr('value'))+1);
			}
		});
		$('.item-adjust-container').on('click',function(e){//阻止按日期跳转
			e.stopPropagation();
		});
		//按日期跳转
		$('.time-adjust').on('click',function(){
			var tartgetTime = $('#year-adjust-input').getAttr('value')+'-'+($('#month-adjust-input').getAttr('value').length==1?'0':'')+$('#month-adjust-input').getAttr('value')+'-'+($('#day-adjust-input').getAttr('value').length==1?'0':'')+$('#day-adjust-input').getAttr('value');	
			$.getAjax('GetWordsByDate',{
				date: tartgetTime
			},function(msg){
				msg = JSON.parse(msg);
				if(msg.type=='1'){
					var tempData = msg.words;
					data = [];
					for(var i=0;i<tempData.length;i++){
						data.push({
							en: tempData[i].text_English,
							zh: tempData[i].text_Chinese,
							id: parseInt(tempData[i].wordid),
							usPronounce: tempData[i].alphabetUS,
							ukPronounce: tempData[i].alphabetUK,
							time: tempData[i].dateStr.split(' ')[0]
						});
					}
					delete tempData;
					cursor = 0,tempCursor=0;
					clear(0,LINES);
					wordsItem.setStyle('top',[0,50,100,150,200,250,300,350,400,450,0,50,100,150,200,250,300,350,400,450])
					load(0,0,data.length>=LINES?LINES:data.length);
				}
			})
		});

		$('.step-input').on('click',function(e){//阻止向上或向下移动
			e.stopPropagation();			
		});
		downStepInput.on('blur',function(){//修改down step
			downStep = this.value;
		});
		upStepInput.on('blur',function(){//修改up step
			upStep = this.value;
		});

		$('.up-adjust').on('click',function(){//向上移动?step
			targetCursor -= upStep;
			if(cursor >= upStep){
				if(runningTag){
					return;
				}
				move(getBaseY());
			}else{
				if(cursor < upStep && cursor > 0){
					if(!runningTag)
						move(getBaseY());
				}
				if(data.length == 0){
					return;
				}
				$.getAjax('GetWordsByStep',{
					wordid: data[0].id,
					step: upStep,
					type: 'new'
				},function(tempData){
					var tempData = JSON.parse(tempData).words;
					for(var i=0,len=tempData.length;i<len;i++){
						var tempItem = tempData.shift();						
						data.unshift({
							en: tempItem.text_English,
							zh: tempItem.text_Chinese,
							id: parseInt(tempItem.wordid),
							usPronounce: tempItem.alphabetUS,
							ukPronounce: tempItem.alphabetUK,
							time: tempItem.dateStr.split(' ')[0]
						});
						delete tempItem;
					}
					if(runningTag){
						return;
					}
					if(targetCursor<cursor){
						move(getBaseY());
					}
				});
			}
		});
		
		$('.down-adjust').on('click',function(){//向下移动?step			
			targetCursor += downStep;
			if(data.length>= LINES+cursor+downStep){				
				if(runningTag){
					return;
				}
				move(getBaseY());
			}else{
				if(data.length>LINES+cursor && data.length < LINES+cursor+downStep){				
					if(!runningTag)
						move(getBaseY());
				}							
				if(data.length == 0){
					return;
				}
				$.getAjax('GetWordsByStep',{
					wordid: data[data.length-1].id,
					step: downStep,
					type: 'old'
				},function(tempData){
					var tempData = JSON.parse(tempData).words;
					for(var i=0,len=tempData.length;i<len;i++){
						var tempItem = tempData.shift();						
						data.push({
							en: tempItem.text_English,
							zh: tempItem.text_Chinese,
							id: parseInt(tempItem.wordid),
							usPronounce: tempItem.alphabetUS,
							ukPronounce: tempItem.alphabetUK,
							time: tempItem.dateStr.split(' ')[0]
						});
						delete tempItem;
					}
					if(runningTag){
						return;
					}
					if(targetCursor>cursor){
						move(getBaseY());
					}
				});			
			}
		});
		$('.add-img').on('click',function(){
			for(var i=0,len=$('.add-img').element.length;i<len;i++){
				if($('.add-img').element[i] == this){
					break;
				}
			}
			var index = i;
			var that = this;
			var id = $(this.parentNode.parentNode).getOriAttr('index');
			$.getAjax('AddWordClick',{
				wordid: $(this.parentNode.parentNode).getOriAttr('index')
			},function(msg){				
				msg = JSON.parse(msg);
				if(msg.type === 1){
					var glossaryNames = msg.glossary.split(',');
					var addTOGlossary = $('.addTO-glossary');
					for(var i=0;i<glossaryNames.length;i++){
						addTOGlossary.append($.create('<label class="glossary'+id+'"> '+glossaryNames[i]+' </label>'));
					}
					console.log(id);
					$('.glossary'+id).on('click',function(){
						$.getAjax('InsertWordToGlossary',{
							wordId: $(this.parentNode.parentNode.parentNode).getOriAttr('index'),
							glossaryName: $(this).getAttr('innerText')						
						},function(msg){
							console.log('nice');
						});
						$('.glossary'+id).removeSelf({clean:true});
						$('.addTO-glossary').setStyle('display','none',index);
						$('.glossary-name').setAttr('value','',index);
					});
				}
			});
			$('.addTO-glossary').setStyle('display','block',index);
			$('.confirm').on('click',function(){
				var it = this;
				$('.addTO-glossary').setStyle('display','none',index);
				$('.glossary'+id).removeSelf({clean:true});
				if($('.glossary-name').getAttr('value',index) == '')
					return;
				$.getAjax('CreateGlossary',{
					glossaryName: $('.glossary-name').getAttr('value',index)
				},function(msg){
					if(msg === '1'){
						$.getAjax('InsertWordToGlossary',{
							wordId: $(it.parentNode.parentNode.parentNode).getOriAttr('index'),
							glossaryName: $('.glossary-name').getAttr('value',index)					
						},function(msg){
							if(msg == 1)
								console.log('nice');
							else{
								console.log('no');
							}
						});
					}
				});
				$('.glossary-name').setAttr('value','',index);
			},{index:index});
		});
	</script>
</html>