<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>    
	<script type="text/javascript">
		$(function() { 
			layui.use(['layer', 'form'], function() {
 
			  	$(".scanVideo").on('click', function(){
			  		var id = $(this).data("id");
		            layer.open({
		                type: 2, 
		                title: '查看监控',
		                fix: false,
		                shadeClose: true,
		                maxmin: true,
		                area: ['100%', '100%'],
		                content: '${home}/admin/video/video.html?id='+id
		            });
		        });
			   
			});
		}); 
	</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	    <form:form cssClass="layui-form" action="query.html" modelAttribute="videoMonitor" method="get">
			<div class="layui-inline"> 
			    <div class="layui-input-inline"> 
			    	<form:input path="name" cssClass="layui-input search_input" placeholder="设备名称" />
			    </div>
			    <div class="layui-input-inline"> 
                    <form:input path="deviceSerial" cssClass="layui-input search_input" placeholder="序列号" />
                </div>
			    <div class="layui-input-inline"> 
					<form:select path="projectId" items="${refData.projects}" nullItem="--请选择所属项目--" lay-filter="projectId"/>
			    </div>
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button> 
			    <a id="add" class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>添加</a> 
			</div> 
	    </form:form> 
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <thead>
				<tr class="th_center"> 
					<th width="40px;">序号</th>
					<th>序列号</th>
					<th>设备名称</th>
					<th>设备所属项目</th> 
					<th>地址</th>
					<th>联系人</th>
					<th>联系电话</th>  
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${row.deviceSerial}</td> 
			    	<td>${row.name}</td>
			    	<td>${funcs:translate(row.projectId, refData.projects)}</td>
			    	<td>${row.address}</td> 
					<td>${row.contacts}</td> 
					<td>${row.tel}</td> 
					<td>  
                        <a class="layui-btn layui-btn-xs" href="javascript:;" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a> 
						<a class="layui-btn layui-btn-xs layui-btn-warm scanVideo" href="javascript:;"  data-id="${row.id}"><i class="layui-icon">&#xe6ed;</i> 监控</a> 
			        </td>
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div>
</body>
</html>
