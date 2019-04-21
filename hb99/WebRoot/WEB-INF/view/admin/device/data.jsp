<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${home}/css/news.css" />
<script type="text/javascript" src="${home}/js/jqueryex.js"></script>
<script type="text/javascript">
	$(function() {
		layui.use([ 'form', 'layer' ], function() {});
	});
</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search"></blockquote>
	<div class="layui-form news_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th width="40px;">序号</th>
					<th>指数</th>
					<th>参数值</th>
					<td>二进制</td>
					<td>十进制</td>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="news_content">
				<c:forEach items="${deviceUploads}" var="row" varStatus="s">
					<tr>
						<td>${s.index+1}</td>
						<td>${row.paramName }</td>
						<td>${row.paramValue }</td>
						<td><input type="text" name="number" class="layui-input input" value="${funcs:toBinaryString(row.paramValue)}"></td>
						<td class="td-value">${row.paramValue }</td>
						<td><a class="layui-btn layui-btn-xs layui-bg-red" onclick="send_order(this, ${row.deviceAttributeId})"><i class="layui-icon">&#xe601;</i>下发</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> 
	<script type="text/javascript">
		$(function(){
	        $(".input").focus(function(){ 
	           
	        }).blur(function(){  
	           $(this).parents("tr").find(".td-value").html(parseInt($(this).parents("tr").find(".input").val(),2).toString(10) );
	        });
	    })
		function send_order(obj, id) {
			layer.confirm('确认要下发指令吗？', function(index) {  
				var url = '${home}/admin/device/sendOrder?fn=sendOrder&deviceId=${param.deviceId}&attributeId=' + id +"&commandValue="+$(obj).parents("tr").find(".td-value").html();
				syncAjax(function() {
					$.getJSON(url, null, function(json) {
						if (json['code'] == '200') {
						   layer.msg('指令已下发!', { icon : 5, time : 2000 });
						} else {
						   layer.msg('操作失败\n' + json['errorMsg'], { icon : 5, time : 2000 });
						}
					});
				});
			});
		}
	</script>
</body>
</html>
