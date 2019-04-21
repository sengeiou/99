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
	    <form:form action="${home}/admin/device/module/query.html"  modelAttribute="deviceModule" method="get" id="frmMain" cssClass="layui-form">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
			    	<form:input path="mac" cssClass="layui-input search_input"  placeholder="请输入MAC地址" />
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="status" items="${refData.statuses}" nullItem="--请选择激活状态--" lay-filter="status"/>
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
					<th width="40px;">序号</th>
					<th>MAC地址</th> 
					<th>IP地址</th>  
					<th>SIM卡编号</th>
					<th>是否激活</th> 
					<th>有效期</th>  
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${row.mac }</td>  
			    	<td>${row.ip }</td>  
			    	<td>${row.sim }</td>   
			    	<td>${funcs:translate(row.status, refData.statuses)}</td>      
			    	<td><fmt:formatDate value="${row.sdate}" pattern="yyyy-MM-dd" /> / <fmt:formatDate value="${row.edate}" pattern="yyyy-MM-dd" /></td>   
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
