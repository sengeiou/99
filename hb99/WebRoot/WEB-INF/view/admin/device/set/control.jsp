<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>  
<!DOCTYPE html>
<html> 
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${home }/css/device.css">
    <%@ include file="/common/meta.jsp"%>   
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=trLEKMVBCc6MKGemHlUXdyy2&s=1"></script>
</head> 
<body class="unset"> 
    <header>
        <select>
            <option>拖挂式设备</option>
        </select>
    </header>
    <div class="content">
        <div class="item1">
            <h3>环境参数</h3>
            <div>
                <p><span>AQI</span><span>48</span></p>
                <p><span>PM2.5</span><span>0.0ug/m²</span></p>
                <p><span>PM10</span><span>0.0ug/m²</span></p>
                <p><span>TSP</span><span>0.0ug/m²</span></p>
                <p><span>温度</span><span>0.0℃</span></p>
                <p><span>湿度</span><span>0.0%</span></p>
            </div>

        </div>
        <div class="item2">
            <h3>故障显示</h3>
            <div>
                <p><span>技术超市报警</span><span class="normal">正常</span></p>
                <p><span>自动限位故障</span><span class="normal">正常</span></p>
                <p><span>手动限位超时报警</span><span class="normal">正常</span></p>
                <p><span>风机过载</span><span class="normal">正常</span></p>
                <p><span>变频器故障</span><span class="normal">正常</span></p>
                <p><span>欠水报警停机</span><span class="normal">正常</span></p>
                <p><span>开机预警</span><span class="normal">正常</span></p>
            </div>

        </div>
        <div class="item3">
            <h1>${device.name}</h1>
            <p>拖挂式设备</p>
            <time>运行时间0天</time>
            <div><img src="${home }/images/gdpwj.png" /></div>
        </div>
        <div class="item4">
            <div class="switch">
            	<c:if test="${isOpen}">
	                <span data-index="1012" class="switch-span" >OFF</span><span data-index="1011" class="switch-span active">ON</span>
            	</c:if>
            	<c:if test="${!isOpen}">
	                <span data-index="1012" class="switch-span active" >OFF</span><span data-index="1011" class="switch-span">ON</span>
            	</c:if>            	
            </div>
            <div class="control">
                <div class="controlWrap">
                    <div data-index="1007" class="top operation"><img src="${home }/images/shang.png" /></div>
                    <div data-index="1006" class="right operation"><img src="${home }/images/you.png" /></div>
                    <div data-index="1005" class="left operation"><img src="${home }/images/zuo.png" /></div>
                    <div data-index="1008" class="bottom operation"><img src="${home }/images/xia.png" /></div>

                </div>
                <div class="turn">转动</div>
            </div>
            <div data-index="1015" class="reset operation">
                <div><img src="${home }/images/reset.png"></div>
            </div>
            <div data-index="1019" class="auto operation">
                <div><img src="${home }/images/auto.png"></div>
            </div>
        </div>
        <div class="item5">
            <h3>运行状态</h3>
            <div>
                <p><span>风机电压</span><span>0.0V</span></p>
                <p><span>风机电流</span><span>0.0A</span></p>
                <p><span>风机频率</span><span>0.00Hz</span></p>
                <p><span>上仰</span><span class="normal">正常</span></p>
                <p><span>下俯</span><span class="normal">正常</span></p>
                <p><span>左转</span><span class="normal">正常</span></p>
                <p><span>右转</span><span class="normal">正常</span></p>
                <p><span>风机</span><img src="${home }/images/fengshan.png" /></p>
                <p><span>水泵</span><img src="${home }/images/fengshan.png" /></p>
            </div>
        </div>
        <div id="mapContainer"></div>
    </div>
    <script src="${home }/js/baiduMap.js"></script>
	<script type="text/javascript">
		$(function() {
			layui.use(['form', 'layer'], function() {
				var deviceId = ${param.deviceId}
				$(".operation").on('click', function() {
					var index = $(this).attr("data-index");
	                var url = '${home}/admin/device/sendCommand';
	                layer.load(1)
	                $.ajax({
	                    type: 'POST',
	                    url: url,
	                    data: {op: 'sendCommand', index: index, commandValue: 1, deviceId:deviceId},
	                    dataType: 'json',
	                    success: function (data) {
	                        if(data.code == 200){ 
	                            layer.closeAll('loading');
	                        } else {
	                        	layer.closeAll('loading');
	                            layer.msg(data.msg,{icon: 5,time:3000,anim: 6}); 
	                        }
	                    },
	                    error: function (data) {
							layer.msg("异常"); 
							 layer.closeAll('loading');
	                    }
	                }); 
				});
				
				$(".switch-span").on('click', function() {
					var index = $(this).attr("data-index");
					var $this = $(this);
	                var url = '${home}/admin/device/sendCommand';
	                 layer.load(2)
	                $.ajax({
	                    type: 'POST',
	                    url: url,
	                    data: {op: 'sendCommand', index: index, commandValue: 1, deviceId:deviceId},
	                    dataType: 'json',
	                    success: function (data) {
	                        if(data.code == 200){
	                        	$(".active").removeClass("active");
	                        	$this.addClass("active");
	                            layer.closeAll('loading');
	                        } else {
	                        	layer.closeAll('loading');
	                            layer.msg(data.msg,{icon: 5,time:3000,anim: 6}); 
	                        }
	                    },
	                    error: function (data) {
	                    layer.closeAll('loading');
	                    	layer.msg("异常"); 
	                    }
	                }); 
				});

			});
		});
	</script>
</body>

</html>