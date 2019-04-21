<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>九九环保粉尘综合治理智能平台</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${home}/layui/css/layui.css" />
<link rel="stylesheet" href="//at.alicdn.com/t/font_eolqem241z66flxr.css" />
<link rel="stylesheet" href="${home}/css/main.css" />
<link rel="stylesheet" href="${home}/css/css.css" />
<style type="text/css">
	body, html {width: 100%; height: 100%; margin: 0; font-family: "微软雅黑";}
	#allmap {width: 100%; height: 850px;}
	button {outline: none;}
	.admin-header-lock-input {margin: 0 10px 0 27px;}
	.ps {overflow-y: auto!important;}
	#projectSubListDiv p, #projectSubListDiv .ant-col-18 {overflow: hidden; white-space: nowrap; text-overflow: ellipsis;}
</style>
<script type="text/javascript" src="${home}/js/jquery.js"></script>
<script type="text/javascript" src="${home}/js/jqueryex.js"></script>
<script type="text/javascript" src="${home}/js/public.js"></script>
<script type="text/javascript" src="${home}/js/underscore.js"></script>
<script type="text/javascript" src="${home}/layui/layui.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=92b6dd786e4fd39c19911324efeaa18b"></script>
</head>
<body class="main_body">
  <div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main">
				<a href="#" class="logo">九九环保粉尘综合治理智能平台</a> 
			    <ul class="layui-nav top_menu">
			    	<li class="layui-nav-item" mobile>
			    		<a href="javascript:;" data-url="${home}/admin/system/user/change_password.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>设置</cite></a>
			    	</li>
			    	<li class="layui-nav-item" mobile>
			    		<a href="javascript:;"><i class="iconfont icon-loginout"></i> 退出</a>
			    	</li>
			    	<c:if test="${not empty ADMIN_SESSION_DATA.user}">
						<li class="layui-nav-item lockcms" pc>
							<a href="javascript:;"><i class="iconfont icon-lock1"></i><cite>锁屏</cite></a>
						</li>
			    	</c:if>
					<li class="layui-nav-item" pc>
						<a href="javascript:;">
							<c:choose>
								<c:when test="${not empty ADMIN_SESSION_DATA.imgUrl}">
									<img src="${ADMIN_SESSION_DATA.imgUrl}" class="layui-circle" width="35" height="35"/>
								</c:when>
								<c:otherwise>
									<img src="${home}/images/otc.png" class="layui-circle" width="35" height="35"/>
								</c:otherwise>
						    </c:choose>
							<cite>${ADMIN_SESSION_DATA.user.name}</cite>
						</a>
						<dl class="layui-nav-child"> 
							<dd><a href="login.html"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 锁屏 -->
	<div class="admin-header-lock" id="lock-box" style="display: none;">
		<div class="admin-header-lock-img">
			<c:choose>
				<c:when test="${not empty ADMIN_SESSION_DATA.imgUrl}">
				   <img src="${ADMIN_SESSION_DATA.imgUrl}" class="layui-circle" width="35" height="35"/>
				</c:when>
				<c:otherwise>
					<img src="${home}/images/otc.png" class="layui-circle" width="35" height="35"/>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="admin-header-lock-name" id="lockUserName">${ADMIN_SESSION_DATA.user.name}</div>
		<div class="input_btn">
			<input type="password" class="admin-header-lock-input layui-input" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />
			<button class="layui-btn" id="unlock">解锁</button>
		</div>
	</div>
	<!-- 移动导航 -->
    <div class="ant-layout" id="layout">
      <div class="ant-layout-content">
           <div style="position: relative;">
            <div id="allmap"></div>
            <div class="gis-search" style="height: 813px; width: 400px; position: absolute; top: 0px; right: 0px;">
              <span class="ant-input-search ant-input-affix-wrapper"><input type="text" id="projectSubSearch" placeholder="请输入项目名称搜索" class="ant-input ant-input-lg" /><span class="ant-input-suffix"><i class="anticon layui-icon">&#xe615;</i></span></span>
              <div class="ps ps--theme_default ps--active-y" id="projectSubListDiv" style="position: relative; margin-top: 15px; height: 713px;">
              
              </div>
            </div>
         </div>
      </div>
      <div class="footer ant-layout-footer" style="padding: 0px;">
       <div style="text-align: center;">
         copyright @2018 湖南欧特云科技
       </div>
      </div>
    </div>
</body>
<script type="text/javascript">
	window.onload = function() {		
		_.templateSettings = {
				interpolate: /\<\@\=(.+?)\@\>/gim,
			    evaluate: /\<\@(.+?)\@\>/gim,
			    escape: /\<\@\-(.+?)\@\>/gim
		};
	    
	   var skyconsWeather;
		layui.use(['form', 'layer'], function() {
			var form = layui.form(),
				layer = layui.layer;
		
			//锁屏
			function lockPage() {
				layer.open({
					title : false,
					type : 1,
					content : $("#lock-box"),
					closeBtn : 0,
					shade : 0.9
				})
			}
			
			$(".lockcms").on("click", function() {
				window.sessionStorage.setItem("lockcms", true);
				lockPage();
			});
			
			// 判断是否显示锁屏
			if (window.sessionStorage.getItem("lockcms") == "true") {
				lockPage();
			}
			
			// 解锁
			$("#unlock").on("click", function() {
				if ($(this).siblings(".admin-header-lock-input").val() == '') {
					layer.msg("请输入解锁密码！");
				} else {
					var data = {op: 'validatePwd', pwd: $(this).siblings(".admin-header-lock-input").val()}; 
					var url = '${home}/admin/system/user/functions.json'; 
					var b = false;	
					syncAjax(function() {
						$.getJSON(url, data, function(json) {										
							b = json;														
						})
					});	 
					if (b) {
						window.sessionStorage.setItem("lockcms",false);
						$(this).siblings(".admin-header-lock-input").val('');
						layer.closeAll("page");
					} else {
						layer.msg("密码错误,请重新输入!");
					}
				}
			});
			
			// 子项目信息若显示不完整，点击显示完整内容
			$('#projectSubListDiv').on('click', 'p, .ant-col-18', function() {
				if (this.scrollWidth > this.clientWidth) {
					layer.open({
					  type: 4,
					  shadeClose: true,
					  content: [$(this).html(), this],
					  tips: [1, '#009688']
					});
				}
			});
		
			 // 百度地图API功能
			 map = new BMap.Map("allmap");
			 map.setMapStyle({style: 'dark'});
			 map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
			 map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
			 map.centerAndZoom(new BMap.Point(110.404, 35.915), 5);
			 var opts = {
			  width: 400,     // 信息窗口宽度
			  height: 210,     // 信息窗口高度
			  //title : "信息窗口ertertert" , // 信息窗口标题
			  enableMessage: true//设置允许信息窗发送短息
			 };
			 
			 function addClickHandler(content, marker) {
			  marker.addEventListener("click", function(e) {
			     openInfo(content, e);
			  	}
			  );
			 }
			 
			 function openInfo(content, e) {
			  var p = e.target;
			  var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			  var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
			  map.openInfoWindow(infoWindow, point); //开启信息窗口
			 }
			 
			 var jsonData = ${jsonData}; 
			 // 载入地图锚点数据
			 function loadMapPoint(data) {
				 map.clearOverlays();
				 for (var i = 0; i < data.length; i++) {
					 if (!data[i].gpsX || !data[i].gpsY) {
						 continue;
					 }
					 var marker = new BMap.Marker(new BMap.Point(data[i].gpsX, data[i].gpsY)); // 创建标注
					 var url = "app/selected_project.html?projectId=" + data[i].id ;
					 var content = "<h4 style='margin:0 0 10px 0;padding-bottom:0.6em;border-bottom: 1px solid #8E8F91;color: #212121;font-size: 16px;font-weight: bold;'>"+ data[i].name + "</h4>" +
				    	"<p style='margin:0;line-height:24px;font-size:15px;width: 75%;float: left'>设备总数:4</p></br>" +
				    	"<p style='margin:0;line-height:24px;font-size:15px;width: 75%;float: left'>在线设备:3</p></br>" +
				    	"<p style='margin:0;line-height:24px;font-size:15px;width: 75%;float: left'>固定式设备:2</p></br>" +
				    	"<p style='margin:0;line-height:24px;font-size:15px;width: 75%;float: left'>空气质量检测仪:1</p></br>" +
				    	"<button style='border:none;text-decoration: none;float: right;background: #F0AF24;padding: 0.5rem 0.8rem;cursor: pointer;border-radius: 8px;color: #fff;margin-top: 1.5rem;'><a href='javascript:;' onclick='gotoUrl(\"" + url + "\")' style='color: #fff;'>管理项目</a></button>";
				  	map.addOverlay(marker); // 将标注添加到地图中
				 	addClickHandler(content, marker);
				 }
			 }
			 var $projectSubListDiv = $('#projectSubListDiv');
			 var projectSubTemplate = _.template($("#projectSubTemplate").html());
			 // 载入子项目列表数据
			 function loadProjectSubList(data) {
				 $projectSubListDiv.empty();
				 for (var i = 0; i < data.length; i++) {
					 $projectSubListDiv.append(projectSubTemplate({data: data[i]}));
				 }
			 }
			 
			 loadMapPoint(jsonData);
			 loadProjectSubList(jsonData);
			 
			 $('#projectSubSearch').keydown(function(e) {
				 if (e.keyCode == 13) { // 按下回车
					 $.post('${home}/admin/app/functions.json?fn=getProjectSubList', {projectSubName: this.value}, function(json) {
		                 if (json['isok']) {
		                	 loadMapPoint(json.data);
		                	 loadProjectSubList(json.data);
		                 } else {
		                	 layer.msg('搜索失败\n' + json['errorMsg']);
		                 }
					 });
				 }
			 });
		});		
	};
</script>
<script id="projectSubTemplate" type="text/template">
	<div class="gis-search-item">
    	<div class="ant-row" style="height: 50px; line-height: 50px;">
			<div class="ant-col-18">
				项目名称：<@-data.name@>
			</div>
			<div class="ant-col-6">
				<button type="button" class="ant-btn ant-btn-primary ant-btn-sm" onclick="gotoUrl('app/selected_project.html?projectId=<@-data.id@>')"><span>项目管理&gt;</span></button>
			</div>
		</div>
		<div class="ant-row">
			<div class="ant-col-24">
				<p class="desc">设备总数: <@-data.name@></p>
			</div>
		</div>
		<div class="ant-row">
			<div class="ant-col-24">
				<p class="desc">在线设备: <@-data.name@></p>
			</div>
			<div class="ant-col-24">
				<p class="desc">固定式设备: <@-data.name@></p>
			</div>
			<div class="ant-col-24">
				<p class="desc">空气质量检测仪: <@-data.name@></p>
			</div>
		</div>
	</div>
</script>
</html>