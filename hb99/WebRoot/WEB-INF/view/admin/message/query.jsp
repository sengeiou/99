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
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="message" method="get">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
					<form:select path="status" items="${refData.statuses}" nullItem="--请选择状态--" lay-filter="status"/>
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="type" items="${refData.types}" nullItem="--请选择类型--" lay-filter="type"/>
			    </div> 
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button> 
			    <a class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>新增</a>  
			</div> 
	    </form:form>  
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <thead>
				<tr class="th_center"> 
					<th width="40px;">序号</th>
					<th>发送人</th>
					<th>接收人</th>
					<th>发送时间</th> 
					<th>状态</th> 
					<th>消息类型</th>
					<th>相关设备</th>
					<th>内容</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content tabList"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${row.sendUserId}</td>  
			    	<td>${row.receiveUserId}</td>   
			    	<td><fmt:formatDate value="${row.preDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    	<td>${funcs:translate(row.status, refData.statuses)}</td>
			    	<td>${funcs:translate(row.type, refData.types)}</td>
			    	<td>${row.deviceId}</td>   	 
			    	<td>${row.content}</td>   	 
			    	<td> 
						<a class="layui-btn layui-btn-xs layui-btn-warm" onclick="gotoUrl('show.html?id=${row.id}&readonly=true')"><i class="layui-icon">&#xe642;</i>详情</a> 
			        </td>
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div>
	
	
</body>
</html>
