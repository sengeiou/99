<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/common/meta.jsp"%>
</head>
<body class="childrenBody">
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" class="main"
		style='width:80%;height:800px;float:center;margin-right:0;padding-right:0;padding-left:220px;padding-top:20px;border-right-width:0'></div>
	<!-- ECharts单文件引入 -->
	<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var myChart = echarts.init(document.getElementById('main'));
		var option = {
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '2019-02-21', '2019-02-22', '2019-02-23', '2019-02-24', '2019-02-25' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataZoom : {
						show : true
					},
					dataView : {
						show : true
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar', 'stack', 'tiled' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 100
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : false,
					data : function() {
						var list = [];
						for (var i = 0; i <= 24; i++) {
							list.push(i+ '-00');
						}
						return list;
					}()
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					name : '2019-02-21',
					type : 'line',
					data : function() {
						var list = [];
						for (var i = 0; i <= 24; i++) {
							list.push(Math.round(Math.random() * 30));
						}
						return list;
					}()
				},
				{
					name : '2019-02-22',
					type : 'line',
					data : function() {
						var list = [];
						for (var i = 1; i <= 24; i++) {
							list.push(Math.round(Math.random() * 10));
						}
						return list;
					}()
				},
				{
					name : '2019-02-23',
					type : 'line',
					data : function() {
						var list = [];
						for (var i = 1; i <= 24; i++) {
							list.push(Math.round(Math.random() * 10));
						}
						return list;
					}()
				},
				{
					name : '2019-02-24',
					type : 'line',
					data : function() {
						var list = [];
						for (var i = 1; i <= 24; i++) {
							list.push(Math.round(Math.random() * 10));
						}
						return list;
					}()
				},
				{
					name : '2019-02-25',
					type : 'line',
					data : function() {
						var list = [];
						for (var i = 1; i <= 24; i++) {
							list.push(Math.round(Math.random() * 10));
						}
						return list;
					}()
				}
			]
		};
	
	
		myChart.setOption(option);
	</script>
</body>