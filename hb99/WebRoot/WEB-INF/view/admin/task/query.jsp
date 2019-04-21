<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
	    <form:form action="${home}/admin/task/query.html"  modelAttribute="maintainTask" method="get" id="frmMain" cssClass="layui-form">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
			    	<form:input path="name" cssClass="layui-input search_input"  placeholder="请输入设备名称" />
			    </div>  
			    <div class="layui-input-inline"> 
					<form:select path="projectId" items="${refData.projects}" nullItem="--请选择所属项目--" lay-filter="projectId"/>
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="status" items="${refData.statuses}" nullItem="--请选择状态--" lay-filter="status"/>
			    </div> 
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button> 
				<a id="add" class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>添加</a>  
			</div>
	    </form:form> 
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table"> 
		    <thead>
				<tr> 
					<th width="40px">序号</th>
					<th>所属项目</th> 
					<th>任务名称</th>  
					<th>创建日期</th>
					<th>状态</th>
					<th>完成期限</th> 
					<th>完成时间</th> 
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${funcs:translate(row.projectId, refData.projects)}</td>   
			    	<td>${row.name }</td>  
			    	<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>  
			    	<td>${funcs:translate(row.status, refData.statuses)}</td>   
			    	<td>
			    	  <fmt:formatDate value="${row.sdate}" pattern="yyyy-MM-dd" /> /
			    	  <fmt:formatDate value="${row.edate}" pattern="yyyy-MM-dd" />
			    	</td>  
			    	<td>${row.recordTime}</td>   
			    	<td>
					   <a class="layui-btn layui-btn-xs" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a>  
			        </td>
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div> 
</body>
</html>
