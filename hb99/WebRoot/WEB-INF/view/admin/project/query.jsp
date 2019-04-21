<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript">
	$(function() {

		layui.use([ 'form' ], function() {
			var form = layui.form;

			form.on('select(province)', function(data) {
				$('#provinceId').trigger("click"); // 触发validate验证
				var url = '${home}/common/area/listSubAreas';
				var data = {
					op : 'listSubAreas',
					pid : data.value
				};
				fillCombo($('#cityId'), url, data, '--请选择--', function() {
					form.render();
				});
				$('#areaId').empty();
				$('#areaId').append("<option value=''>--请选择--</option>");
			});

			form.on('select(city)', function(data) {
				$('#cityId').trigger("click");
				var url = '${home}/common/area/listSubAreas';
				var data = {
					op : 'listSubAreas',
					pid : data.value
				};
				fillCombo($('#areaId'), url, data, '--请选择--', function() {
					form.render();
				});
			});

			$('#export').on('click', function() {
				var url = "${home}/admin/project/export?op=export&";
				window.location = url + $('#frmMain').serialize();
			});
 
			$(".setting").on('click', function() {
				var id = $(this).attr("data-id");
				layer.open({
					type : 2,
					title : '项目设备规划',
					fix : false,
					shadeClose : true,
					maxmin : true,
					area : [ '100%', '100%' ],
					content : '${home}/admin/project/device.html?projectId=' + id
				});
			});

		})
	});
</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<form:form action="${home}/admin/project/query.html" modelAttribute="project" method="get" id="frmMain" cssClass="layui-form"> 
			<div class="layui-inline">
				<div class="layui-input-inline">
					<form:input path="name" cssClass="layui-input search_input" placeholder="请输入项目名称" />
				</div>
				<div class="layui-input-inline">
					<form:select path="industryId" items="${refData.sysIndustrys}" nullItem="--请选择行业--" lay-filter="industryId" />
				</div>
				<div class="layui-input-inline">
					<form:select path="type" items="${refData.types}" nullItem="--请选择属性--" lay-filter="industryId" />
				</div>
				<div class="layui-input-inline">
					<form:select path="provinceId" items="${refData.provinces}" nullItem="--请选择省份--" lay-filter="province" />
				</div>
				<div class="layui-input-inline">
					<form:select path="cityId" items="${refData.citys}" nullItem="--请选择城市--" lay-filter="city" />
				</div>
				<div class="layui-input-inline">
					<form:select path="areaId" items="${refData.areas}" nullItem="--请选择区域--" lay-filter="area" />
				</div>
				<button type="submit" class="layui-btn search_btn">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
				<a id="add" class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>添加</a>
				<a class="layui-btn layui-btn-warm" href="javascript:;" id="export"><i class="layui-icon">&#xe601;</i>导出</a>
			</div>
		</form:form>
	</blockquote>
	<div class="layui-form news_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th width="40px;">序号</th> 
					<th>项目名称</th>
					<th>所属行业</th>
					<th>省份</th>
					<th>地址</th>
					<th>位置</th>
					<th>责任人</th>
					<th>属性</th>
					<th width="100px;">操作</th>
				</tr>
			</thead>
			<tbody class="news_content">
				<c:forEach items="${pagehelper.list}" var="row" varStatus="s">
					<tr>
						<td>${s.index+1}</td> 
						<td>${row.name }</td>
						<td>${funcs:translate(row.industryId, refData.sysIndustrys)}</td>
						<td>${funcs:translate(row.provinceId, refData.provinces)}</td>
						<td>${row.address }</td>
						<td>${row.gpsX },${row.gpsY }</td>
						<td>${row.perLiable }</td>
						<td>${funcs:translate(row.type, refData.types)}</td>
						<td>
						  <a class="layui-btn layui-btn-xs" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a> 
						  <a class="layui-btn layui-btn-xs layui-bg-orange setting" data-id="${row.id}"><i class="layui-icon">&#xe632;</i>设置</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp" />
	</div>
</body>
</html>
