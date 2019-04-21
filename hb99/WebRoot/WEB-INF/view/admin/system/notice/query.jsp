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
				var laydate = layui.laydate;
				
				laydate.render({
				    elem: '#deadline'
				}); 
			});
		});
	</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="sysNotice" method="get">
			<div class="layui-inline"> 
			    <div class="layui-input-inline"> 
			    	<form:input path="title" cssClass="layui-input search_input" placeholder="标题" />
			    </div>
			    <div class="layui-input-inline"> 
			    	<form:input path="deadline" cssClass="layui-input" placeholder="截止时间" format="yyyy-MM-dd" />
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
					<th width="40px">序号</th>
					<th>发布日期</th>
					<th>截止日期</th>
					<th>标题</th> 
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			    	<td>${row.deadline}</td>
			    	<td>${row.title}</td>   	 
			    	<td> 
						<a class="layui-btn layui-btn-xs layui-btn-warm" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a> 
			        </td>
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div>
</body>
</html>
