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
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="sysConfig" method="get">
			<div class="layui-inline"> 
			    <div class="layui-input-inline"> 
			    	<form:input path="configKey" cssClass="layui-input search_input" placeholder="key" />
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
					<th>key</th>
					<th>值</th> 
					<th>说明</th> 
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${row.configKey}</td>
			    	<td>${row.configValue}</td> 
					<td>${row.remark}</td>    	 
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
