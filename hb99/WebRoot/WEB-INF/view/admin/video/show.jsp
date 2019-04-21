<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <%@ include file="/common/meta.jsp"%>    
    <link rel="stylesheet" href="${home}/css/loginDialog.css" /> 
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=92b6dd786e4fd39c19911324efeaa18b"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script> 
    <script type="text/javascript">
        $(function() {
            ${fn:substringBefore(jsValidator, '});')}
                ,ignore: '',
                submitHandler: function(form) {
                    $(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
                    form.submit();
                }
                
            });
            
            layui.use(['form'], function() {
                var form = layui.form;  
                
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
            map.centerAndZoom(new BMap.Point(121.36564, 31.22611), 15);
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
            // 为地图增加点击事件，为input赋值
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
        });
    </script>
</head>
<body>
    <form:form action="operate.do?op=save" modelAttribute="TVideoMonitor" method="post" showErrors="true" cssClass="layui-form"> 
        <div class="p15"> 
                <blockquote class="layui-elem-quote title">视频设备基本信息</blockquote>
                <blockquote class="layui-elem-quote layui-quote-nm"> 
                    <div class="layui-form-item">
                       <div class="layui-col-md4">
                            <label class="layui-form-label">设备名称:</label>
                            <div class="layui-input-block">
                                <form:input path="name" cssClass="layui-input" /> 
                            </div>
                       </div> 
                      <div class="layui-col-md4">
                           <label class="layui-form-label">所属主项目:</label>
                           <div class="layui-input-block">
                              <form:select path="projectId" items="${refData.projects}" lay-filter="projectLay" nullItem="--请选择--"  />
                           </div>
                      </div>               
                    </div>                  
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                        <label class="layui-form-label">序列号:</label>
                        <div class="layui-input-block">
                            <form:input path="deviceSerial" cssClass="layui-input" placeholder="设备机身上的序列号" /> 
                        </div>
                        </div>
                       <div class="layui-col-md4">
                            <label class="layui-form-label">设备验证码:</label>
                            <div class="layui-input-block">
                                <form:input path="verificationCode" cssClass="layui-input" placeholder="设备机身上的六位大写字母" /> 
                            </div>
                        </div>
                    </div>                                      
                    <div class="layui-form-item">
                       <div class="layui-col-md4">
                            <label class="layui-form-label">RTMP地址:</label>
                            <div class="layui-input-block">
                                <form:input path="rtmpUrl" cssClass="layui-input" placeholder="点击获取地址自动获取" readonly = "true" /> 
                            </div>
                        </div>
                        <div class="layui-col-md4">
	                        <label class="layui-form-label">HLS地址:</label>
	                        <div class="layui-input-block">
	                            <form:input path="hlsUrl" cssClass="layui-input" placeholder="点击获取地址自动获取" readonly = "true"/> 
	                        </div>
                        </div>                       
                    </div>   
                    <div class="layui-form-item">
                       <div class="layui-col-md4">
                            <label class="layui-form-label">ip:</label>
                            <div class="layui-input-block">
                                <form:input path="ip" cssClass="layui-input" /> 
                            </div>
                        </div>
                        <div class="layui-col-md4">
                        <label class="layui-form-label">端口:</label>
                        <div class="layui-input-block">
                            <form:input path="port" cssClass="layui-input" /> 
                        </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <div class="layui-form-item">
                                <label class="layui-form-label">设备帐号:</label>
                                <div class="layui-input-block">
                                    <form:input path="account" cssClass="layui-input" /> 
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md4">
                            <div class="layui-form-item">
                                <label class="layui-form-label">设备密码:</label>
                                <div class="layui-input-block">
                                    <form:input path="password" cssClass="layui-input" /> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <div class="layui-form-item">
                                <label class="layui-form-label">联系人:</label>
                                <div class="layui-input-block">
                                    <form:input path="contacts" cssClass="layui-input" /> 
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md4">
                            <div class="layui-form-item">
                                <label class="layui-form-label">联系电话:</label>
                                <div class="layui-input-block">
                                    <form:input path="tel" cssClass="layui-input" /> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                       <div class="layui-col-md6">
	                        <label class="layui-form-label">设备地址:</label>
	                        <div class="layui-input-block">
	                            <form:input path="address" cssClass="layui-input" /> 
	                        </div>
                       </div>  
                        <div class="layui-col-md2">
                            <div class="layui-input-block">
                                <input type="button" class="layui-btn layui-btn-normal" value="查询经纬度" id="example" />
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
                    </div>   
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <div class="layui-form-item">
                                <label class="layui-form-label">能否控制:</label>
                                <div class="layui-input-block">
									<form:checkbox path="control" value="1" title="支持"
										uncheckedValue="0" lay-skin="primary" />
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
</body>
</html>