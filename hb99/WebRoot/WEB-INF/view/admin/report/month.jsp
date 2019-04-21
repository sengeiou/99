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
			title : {
				text : '用户月度用气量'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '用气量' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
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
			xAxis : [
				{
					type : 'category',
					data : [ '2018-01', '2018-02', '2018-03', '2018-04', '2018-05', '2018-06', 
					         '2018-07', '2018-08', '2018-09', '2018-10', '2018-11', '2018-12' ]
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					name : '用气量',
					type : 'bar',
					data : [ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3 ],
					markPoint : {
						data : [
							{
								type : 'max',
								name : '最大值'
							},
							{
								type : 'min',
								name : '最小值'
							}
						]
					},
					markLine : {
						data : [
							{
								type : 'average',
								name : '平均值'
							}
						]
					}
				} 
			]
		};
	
	
	
		myChart.setOption(option);
	</script>
</body>