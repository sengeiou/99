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
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="sysOperLog" method="get">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
					<form:select path="operType" items="${refData.operTypes}" nullItem="--请选择操作类型--" lay-filter="operType"/>
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
					<th width="70px;">账户</th>
					<th width="140px;">操作日期</th>
					<th width="70px;">操作类型</th> 
					<th>操作内容</th>  
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td> 
			    	<td>${funcs:translate(row.sysUserId, refData.sysUsers)}</td>  
			    	<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    	<td>${funcs:translate(row.operType, refData.operTypes)}</td>
			    	<td>${row.remark}</td>   
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div>
</body>
</html>
