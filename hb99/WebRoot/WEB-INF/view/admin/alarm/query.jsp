<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>  
	<script type="text/javascript">
		$(function() { 
			layui.use(['form', 'layer', 'laydate'], function() {
				var form = layui.form;
				 
			});
		});
	</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="alarm" method="get">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
					<form:select path="status" items="${refData.statuses}" nullItem="--请选择状态--" lay-filter="status"/>
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="type" items="${refData.types}" nullItem="--请选择类型--" lay-filter="type"/>
			    </div> 
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button>  
			</div> 
	    </form:form>  
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <thead>
				<tr class="th_center"> 
					<th width="40px;">序号</th>
					<th>设备名称</th>
					<th>所属项目</th>
					<th>报警时间</th> 
					<th>报警类型</th> 
					<th>报警数值</th> 
					<th>状态</th> 
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${funcs:translate(row.deviceId, refData.devices)}</td> 
			    	<td>${funcs:translate(row.projectId, refData.projects)}</td>   
			    	<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    	<td>${funcs:translate(row.type, refData.types)}</td>
			    	<td>${row.num}</td>
			    	<td>${funcs:translate(row.status, refData.statuses)}</td> 
			    	<td> 
						<a class="layui-btn layui-btn-xs" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe615;</i>详情</a> 
			        </td>
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div>
</body>
</html>
