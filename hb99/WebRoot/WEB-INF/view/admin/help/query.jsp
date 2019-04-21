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
		<form:form action="${home}/admin/help/query.html" modelAttribute="help" method="get" id="frmMain" cssClass="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<form:input path="title" cssClass="layui-input search_input" placeholder="请输入名称" />
				</div>
				<div class="layui-input-inline">
					<form:select path="type" items="${refData.types}" nullItem="--请选择类型--" lay-filter="type" />
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
					<th>创建时间</th>
					<th>最近修改时间</th>
					<th>类型</th>
					<th>内容</th>
					<th width="70px;">操作</th>
				</tr>
			</thead>
			<tbody class="news_content">
				<c:forEach items="${pagehelper.list}" var="row" varStatus="s">
					<tr>
						<td>${s.index+1}</td>
						<td>${row.title }</td>
						<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${row.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${funcs:translate(row.type, refData.types)}</td>
						<td><a class="layui-btn layui-btn-normal layui-btn-xs preview" data-content='${ row.content}'>预览</a></td>
						<td>
						  <a class="layui-btn layui-btn-xs" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp" />
	</div>
	<script type="text/javascript">
		$(function() {
			
			layui.use([ 'form' ], function() {
				var form = layui.form;
	
				$('.preview').on('click', function() {
						var content = $(this).data("content");
						layer.open({
						  title: '内容',
						  closeBtn:2,
						  area: ['500px', '500px'],
						  content: content
						});
				});
	
			})
		});
	</script>
</body>
</html>
