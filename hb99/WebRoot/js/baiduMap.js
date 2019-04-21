//百度地图
baiduMap()
function baiduMap() {

	var map = new BMap.Map('mapContainer');
	// 创建地图实例
	var point = new BMap.Point(112.898404, 28.219707);
	// 创建点坐标
	map.centerAndZoom(point, 18);
	// 初始化地图， 设置中心点坐标和地图级别

    var marker = new BMap.Marker(point); // 创建标注
	map.addOverlay(marker);

}