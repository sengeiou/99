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
				  
			  	$(".scan").on('click', function(){ 
		            layer.open({
		                type: 2, 
		                title: '告警图片记录',
		                fix: false,
		                shadeClose: true,
		                maxmin: true,
		                area: ['100%', '100%'],
		                content: '${home}/admin/pic/scan.html'
		            });
		        });
			});
		});
	</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="warnPic" method="get">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
			    	<form:input path="name" cssClass="layui-input search_input" placeholder="设备名称" />
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="projectId" items="${refData.projects}" nullItem="--请选择所属项目--" lay-filter="projectId"/>
			    </div>
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button>  
				<a class="layui-btn layui-btn-normal scan" onclick="gotoUrl('scan.html')"><i class="layui-icon">&#xe654;</i>浏览</a>  
			</div> 
	    </form:form>  
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <thead>
				<tr class="th_center"> 
					<th width="40px;">序号</th>
					<th>所属项目</th>
					<th>所属设备</th>
					<th>告警名称</th> 
					<th>告警时间</th> 
					<th>操作内容</th>  
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td> 
			    	<td>${funcs:translate(row.projectId, refData.projects)}</td>  
			    	<td>${funcs:translate(row.deviceId, refData.devices)}</td>  
			    	<td>${row.name}</td>   
			    	<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>  
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div>
</body>
</html>
