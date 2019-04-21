<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="/common/meta.jsp"%>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<form:form action="${home}/admin/device_attribute/query.html" modelAttribute="deviceAttribute" method="get" id="frmMain" cssClass="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<form:input path="name" cssClass="layui-input search_input" placeholder="请输入名称" />
				</div>
				<div class="layui-input-inline">
					<form:input path="attributeCode" cssClass="layui-input search_input" placeholder="请输入编码" />
				</div>
				<div class="layui-input-inline">
					<form:select path="deviceTypeId" items="${refData.deviceTypes}" nullItem="--请选择类型--" lay-filter="deviceTypeId" />
				</div>

				<button type="submit" class="layui-btn search_btn">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
				<a id="add" class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>添加</a>
			</div>
		</form:form>
	</blockquote>
	<div class="layui-form news_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th width="40px;">序号</th>
					<th>名称</th>
					<th>编码</th>
					<th>类型</th>
					<th width="70px;">操作</th>
				</tr>
			</thead>
			<tbody class="news_content">
				<c:forEach items="${pagehelper.list}" var="row" varStatus="s">
					<tr>
						<td>${s.index+1}</td>
						<td>${row.name }</td>
						<td>${row.attributeCode}</td>
						<td>${funcs:translate(row.deviceTypeId, refData.deviceTypes)}</td>
						<td>
						  <a class="layui-btn layui-btn-xs" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp" />
	</div>
</body>
</html>
