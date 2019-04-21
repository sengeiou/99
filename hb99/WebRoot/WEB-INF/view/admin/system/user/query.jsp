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
	    <form:form action="${home}/admin/system/user/query.html"  modelAttribute="sysUser" method="get" id="frmMain" cssClass="layui-form">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
			    	<form:input path="name" cssClass="layui-input search_input"  placeholder="请输入关键字" />
			    </div> 
				<div class="layui-input-inline">
					<form:select path="type" items="${refData.types}" nullItem="--请选择机构类型--" lay-filter="type" />
				</div>
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button> 
			    <a class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>新增</a>  
			</div>
	    </form:form> 
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table"> 
		    <thead>
				<tr> 
					<th width="40px">序号</th>
					<th>帐号</th>
					<th>所属机构类型</th> 
					<th>是否激活</th> 
					<th>联系人</th> 
					<th>联系电话</th> 
					<th>微信号</th> 
					<th>联系邮箱</th> 
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${row.account }</td> 
					<td>${funcs:translate(row.type, refData.types)}</td>
			    	<td>${funcs:translate(row.status, refData.statuses)}</td> 
			    	<td>${row.name }</td> 
			    	<td>${row.mobile }</td> 
			    	<td>${row.weixin }</td>  
			    	<td>${row.email }</td>  
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
