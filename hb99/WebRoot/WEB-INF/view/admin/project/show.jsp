<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>   
    <link rel="stylesheet" href="${home}/css/myImageUpload.css" />
	<link rel="stylesheet" href="${home}/component/lightbox2/css/lightbox.min.css" /> 
	<script type="text/javascript" src="${home}/js/myImageUpload.js"></script>
	<script type="text/javascript" src="${home}/component/lightbox2/js/lightbox.min.js"></script> 
	<link rel="stylesheet" href="${home}/css/loginDialog.css" /> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=d2dVBeSkIh7LQDrrAKhHdjNTGmQH3Gsa"></script>  
	<script type="text/javascript">
		$(function() { 
			${fn:substringBefore(jsValidator, '});')}
				,ignore: '',
				submitHandler: function(form) {
					$(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
					form.submit();
				}
			});
			
			layui.use(['form', 'laydate'], function() {
				var form = layui.form;
				var laydate = layui.laydate;  
				
				form.on('select(province)', function(data) {
					$('#provinceId').trigger("click"); // 触发validate验证
					var url = '${home}/common/area/listSubAreas'; 
					var data = {op: 'listSubAreas', pid: data.value}; 
					fillCombo($('#cityId'), url, data, '--请选择--', function() {
						form.render();
					});   
				    $('#areaId').empty();
				    $('#areaId').append("<option value=''>--请选择--</option>");
				});
				
				form.on('select(city)', function(data) {
					$('#cityId').trigger("click"); // 触发validate验证
					var url = '${home}/common/area/listSubAreas';
					var data = {op: 'listSubAreas', pid: data.value};
					fillCombo($('#areaId'), url, data, '--请选择--', function() {
						form.render();
					});
				}); 
				 
				// 查询经纬度地图
				// 弹出
				$("#example").hover(function () {
					$(this).stop().animate({opacity: '1'}, 600);
				}, function () {
					$(this).stop().animate({opacity: '0.6'}, 1000);
				}).on('click', function () {
					$("body").append("<div id='mask'></div>");
					$("#mask").addClass("mask").fadeIn("slow");
					$("#LoginBox").fadeIn("slow");
				});
		        // 在指定容器创建地图实例并设置最大最小缩放级别
		        var map = new BMap.Map("allmap", {
		            minZoom: 5,
		            maxZoom: 19
		        });
		        // 初始化地图，设置中心点和显示级别
		        map.centerAndZoom(new BMap.Point(${not empty TProject.gpsX?TProject.gpsX:121.36564}, ${not empty TProject.gpsY?TProject.gpsY:31.22611}), 15);
				var geolocation = new BMap.Geolocation();
				geolocation.getCurrentPosition(function(r) {
					if (this.getStatus() == BMAP_STATUS_SUCCESS) {
						var mk = new BMap.Marker(r.point);
						map.addOverlay(mk);
						map.panTo(r.point);
					}        
				}, {enableHighAccuracy: true});
		        // 开启鼠标滚轮缩放功能，仅对PC上有效
		        map.enableScrollWheelZoom(true);
		        // 将控件（平移缩放控件）添加到地图上
		        map.addControl(new BMap.NavigationControl());
		        //不为查看的时候 为地图增加点击事件，为input赋值
		        map.addEventListener("click", function(e) {
			         document.getElementById('lat').value = e.point.lat;
			         document.getElementById('lng').value = e.point.lng;
					 var allOverlay = map.getOverlays();
					 for (var i = 0; i < allOverlay.length; i++) {
						   map.removeOverlay(allOverlay[i]);
					 }	
			         var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat)); // 创建标注 
			         map.addOverlay(marker); 
			    });	  
		        // 创建位置检索、周边检索和范围检索
		        var local = new BMap.LocalSearch(map, {
		            renderOptions: {
		                map: map
		            }
		        });
		        // 发起检索
		        $("#mapSearch").on("click", function() {
		            var city = $("#cityName").val();
		            if (city != "") {
		                local.search(city);
		            }
		        });
		        // 设置经纬度
		        $("#setGps").on("click", function() {
		            $("#gpsX").val($("#lng").val());
					$("#gpsY").val($("#lat").val()); 
					$(".close_btn").trigger("click");
		        });
				// 关闭
				$(".close_btn").hover(function () {$(this).css({color: "black"})}, function () {$(this).css({color: "#999"})}).on("click", function () {
					$("#LoginBox").fadeOut("fast");
					$("#mask").css({display: "none"});
				}); 
				
			}) 
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="TProject" method="post" showErrors="true" cssClass="layui-form layui-form-pane" enctype="multipart/form-data"> 
    	<input type="hidden" id="fileId" name="fileId" value="${fileId}" />  
	    <form:hidden path="id"/>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item"> 
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">项目名称:</label>
								<div class="layui-input-block">
							       <form:input path="name" cssClass="layui-input" /> 
								</div>
							</div>
						</div>
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">负责人</label>
								<div class="layui-input-block">
								<form:input path="perLiable" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">
						<label class="layui-form-label">地区:</label>
						<div class="layui-input-block">
							<div class="layui-input-inline">
								<form:select path="provinceId" items="${refData.provinces}" nullItem="--请选择省份--" lay-filter="province"/>
							</div>
							<div class="layui-input-inline">
								<form:select path="cityId" items="${refData.cities}" nullItem="--请选择城市--" lay-filter="city"/>
							</div>
							<div class="layui-input-inline">
								<form:select path="areaId" items="${refData.areas}" nullItem="--请选择区域--" lay-filter="area"/>
							</div>
							<div class="layui-input-inline">
							    <form:input path="address" cssClass="layui-input" /> 
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">经度:</label>
								<div class="layui-input-block">
									<form:input path="gpsX" cssClass="layui-input" />
								</div>
							</div>
						</div>
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">纬度:</label>
								<div class="layui-input-block">
									<form:input path="gpsY" cssClass="layui-input" />
								</div>
							</div>
						</div> 
						<div class="layui-col-md4">
							<div class="layui-input-block">
								<input type="button" class="layui-btn layui-btn-normal" value="查询经纬度" id="example" />
							</div>
						</div>
					</div>   
					<div class="layui-form-item">
						<div class="layui-col-md4">
							<label class="layui-form-label">所属行业:</label>
							<div class="layui-input-block">
								<form:select path="industryId" items="${refData.sysIndustrys}" nullItem="所属行业"  lay-verify="required" />
							</div>
						</div> 
						<div class="layui-col-md4">
							<label class="layui-form-label">属性:</label>
							<div class="layui-input-block">
								<form:select path="type"  items="${refData.types}" nullItem="请选择属性"  />
							</div>
						</div> 
					</div>  
					<div class="layui-form-item"> 
						<div class="layui-col-md8">
							<div class="layui-form-item">
								<label class="layui-form-label">项目介绍:</label>
								<div class="layui-input-block">
							       <form:textarea path="introduce" cssClass="layui-textarea" /> 
								</div>
							</div>
						</div> 
					</div>  	
					<div class="layui-form-item">
						<label class="layui-form-label">webLogo</label>
						<div class="layui-input-block">
	                       <div class="layui-upload">
							   <button type="button" class="layui-btn" id="test1">上传图片</button>
							   <div class="layui-upload-list">
							     <img class="layui-upload-img" id="demo1" style="width:80%;" <c:if test="${not empty url}">src="${url }"</c:if>>
							     <p id="demoText"></p>
							   </div>
						   </div>
						</div> 
					</div>	
				</blockquote>
			</div>
		<div class="submit_group">
		    <button class="layui-btn"><i class="layui-icon">&#xe605;</i>保存</button>
			<a class="butx" onclick="gotoUrl('query.html')"><i class="layui-icon">&#xe65a;</i>返回</a> 
		</div>
	</form:form> 
	<div id="LoginBox" style="display: none">
		<div class="dialogHead">
	    </div> 
		<!-- 地图盒子 -->
	    <div id="allmap"  style="width: 100%; height: 100%; border: 1px solid gray"></div>
	    <!-- 搜索显示框 -->
	    <div id="r-result">
	     	地址: 
	   		<input id="cityName" type="text" style="width: 200px;" />
	        <a id="mapSearch" href="javascript:void(0);" class="layui-btn layui-btn-sm">搜索</a> 经度:
	        <input type="text" id="lng" disabled="disabled" /> 纬度：
	        <input type="text" id="lat" disabled="disabled" />
	        <a id="setGps" href="javascript:void(0);" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger">提交</a>
	        <a href="javascript:void(0)" title="关闭窗口" class="close_btn">×</a>
	    </div>
	</div>   
<script>
layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
   
  var uploadInst = upload.render({
    elem: '#test1'
	,url: '${home}/admin/common/functions/ajax_upload'
	,accept: 'images'
	,acceptMime: 'image/*'
    ,before: function(obj){ 
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(json, index, upload) { 
	   if (json['code'] == 0) {
	     var data = json['data'];
	     $('#headId').val(data.id);  
	     layer.msg(json['msg'], {icon: 1, time: 3000, anim: 0});
	   } else {
	     layer.msg(json['msg'], {icon: 5, time: 3000, anim: 6});
	   } 
    }
    ,error: function(index, upload){ 
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  }); 
  
});
</script>
</body>  
</html>