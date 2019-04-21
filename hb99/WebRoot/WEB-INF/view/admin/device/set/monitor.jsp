<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${home }/css/device.css">
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=trLEKMVBCc6MKGemHlUXdyy2&s=1"></script>
</head>
<body class="unset"> 
    <div class="main">
        <div class="device">
            <h1>视频监控1#</h1>
            <p>拖挂式设备</p>
            <div><img src="${home }/images/gdpwj.png" /></div>
        </div>
        <div class="deviceDetail">
            <p><span>风机电压</span><span>0.0V</span></p>
            <p><span>风机电流</span><span>0.0A</span></p>
            <p><span>风机频率</span><span>0.00Hz</span></p>
            <p><span>上仰</span><span class="normal">正常</span></p>
            <p><span>下俯</span><span class="normal">正常</span></p>
            <p><span>左转</span><span class="normal">正常</span></p>
            <p><span>右转</span><span class="normal">正常</span></p>
            <p><span>风机</span><img src="${home }/images/fengshan.png" /></p>
            <p><span>水泵</span><img src="${home }/images/fengshan.png" /></p>
            <p><span>AQI</span><span>48</span></p>
            <p><span>PM2.5</span><span>0.0ug/m²</span></p>
            <p><span>PM10</span><span>0.0ug/m²</span></p>
            <p><span>TSP</span><span>0.0ug/m²</span></p>
            <p><span>温度</span><span>0.0℃</span></p>
            <p><span>湿度</span><span>0.0%</span></p>
        </div>
        <div id="mapContainer"  class="changeStyle"></div>
    </div>
    <script src="${home }/js/baiduMap.js"></script>
</body>
</html>