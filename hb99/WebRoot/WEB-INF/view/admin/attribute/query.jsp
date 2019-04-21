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
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="maintainAttribute" method="get">
			<div class="layui-inline"> 
			    <div class="layui-input-inline"> 
			    	<form:input path="name" cssClass="layui-input search_input" placeholder="属性值名称" />
			    </div> 
			    <div class="layui-input-inline"> 
			    	<form:select path="type" items="${refData.types}" nullItem="请选择参数类型"  lay-search="1"/>
			    </div>			    
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button>
			    <a class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>添加</a>
			</div> 
	    </form:form> 
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <thead>
				<tr class="th_center"> 
					<th style="width: 40px;">序号</th>
					<th>属性值名称</th> 
					<th>参数类型</th> 
					<th>创建时间</th> 
					<th>操作</th>
				</tr>
		    </thead>
		    <tbody class="news_content">
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>
			    	<td>${s.index+1}</td>
			    	<td>${row.name}</td> 
			    	<td>${funcs:translate(row.type, refData.types)}</td> 
					<td><fmt:formatDate value="${row.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> 
			    	<td class="td-status">  
				   		<a class="layui-btn layui-btn-xs " onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a> 
			        </td>
			    </tr>
			</c:forEach> 
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div> 
</body>
</html>
