<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>   
	<script type="text/javascript">
		$(function() {
			layui.use(['form', 'layer'], function() { 
			  	$(".download").on('click', function(){ 
					var url = "${home}/admin/device/getQRUrlZip?op=getQRUrlZip&";
					window.location = url + $('#frmMain').serialize();	
			  	});
 
				$(".control").on('click', function() {
					var id = $(this).attr("data-id");
					layer.open({
						type : 2,
						title : '设备控制',
						fix : false,
						shadeClose : true,
						maxmin : false,
						area : [ '100%', '100%' ],
						content : '${home }/admin/device/set/control.html?deviceId=' + id
					});
				});
	 
				$(".monitor").on('click', function() {
					var id = $(this).attr("data-id");
					layer.open({
						type : 2,
						title : '设备监控',
						fix : false,
						shadeClose : true,
						maxmin : false,
						area : [ '100%', '100%' ],
						content : '${home }/admin/device/set/monitor.html?deviceId=' + id
					});
				});
	 
				$(".data").on('click', function() {
					var id = $(this).attr("data-id");
					layer.open({
						type : 2,
						title : '设备实时数据',
						fix : false,
						shadeClose : true,
						maxmin : false,
						area : [ '100%', '100%' ],
						content : '${home }/admin/device/data.html?deviceId=' + id
					});
				});
				
			});
		});
	</script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	    <form:form action="${home}/admin/device/query.html"  modelAttribute="device" method="get" id="frmMain" cssClass="layui-form">
			<div class="layui-inline">
			    <div class="layui-input-inline"> 
			    	<form:input path="name" cssClass="layui-input search_input"  placeholder="请输入设备名称" />
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="typeId" items="${refData.types}" nullItem="--请选择设备类型--" lay-filter="typeId"/>
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="property" items="${refData.propertys}" nullItem="--请选择属性--" lay-filter="property"/>
			    </div> 
			    <div class="layui-input-inline"> 
					<form:select path="projectId" items="${refData.projects}" nullItem="--请选择所属项目--" lay-filter="projectId"/>
			    </div>
			    <div class="layui-input-inline"> 
					<form:select path="isCamera" items="${refData.isCameras}" nullItem="--请选择是否有摄像头--" lay-filter="isCamera"/>
			    </div>
			    <button type="submit" class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</button> 
				<a id="add" class="layui-btn layui-btn-normal" onclick="gotoUrl('show.html')"><i class="layui-icon">&#xe654;</i>添加</a>  
			    <a class="layui-btn layui-btn-normal layui-bg-orange download" title="下载相关二维码" ><i class="layui-icon">&#xe601;</i>下载二维码</a>
			</div>
	    </form:form> 
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table"> 
		    <thead>
				<tr> 
					<th width="40px;">序号</th>
					<th>设备id</th> 
					<th>设备名称</th>  
					<th>设备类型</th>
					<th>属性</th>
					<th>所属项目</th>
					<th>所属模块</th>
					<th>摄像头</th>
					<th>生产日期</th>
					<th>出厂日期</th>
					<th width="190px;">操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content"> 
			<c:forEach items="${pagehelper.list}" var="row" varStatus="s"> 
				<tr>  
			    	<td>${s.index+1}</td>
			    	<td>${row.deviceid }</td>  
			    	<td>${row.name }</td>   
			    	<td>${funcs:translate(row.typeId, refData.types)}</td>  
			    	<td>${funcs:translate(row.property, refData.propertys)}</td> 
			    	<td>${funcs:translate(row.projectId, refData.projects)}</td>   
			    	<td>${funcs:translate(row.moduleId, refData.deviceModules)}</td>   
			    	<td>${funcs:translate(row.isCamera, refData.isCameras)}</td>  
			    	<td>${row.produceTime}</td>   
			    	<td>${row.factoryTime}</td>   
			    	<td>
					   <a class="layui-btn layui-btn-xs" onclick="gotoUrl('show.html?id=${row.id}')"><i class="layui-icon">&#xe642;</i>编辑</a>  
					   <c:if test="${row.type == 0 }">
					      <a class="layui-btn layui-btn-xs layui-bg-blue control" data-id="${row.id}"><i class="layui-icon">&#xe665;</i>控制</a>  
					   </c:if>
					   <c:if test="${row.type == 1 }">
					      <a class="layui-btn layui-btn-xs layui-bg-cyan monitor" data-id="${row.id}"><i class="layui-icon">&#xe857;</i>实时</a>  
					   </c:if>
					   <a class="layui-btn layui-btn-xs data" data-id="${row.id}" ><i class="layui-icon">&#xe642;</i>数据</a>  
			        </td>
			    </tr>
			</c:forEach>
		    </tbody>
		</table>
		<jsp:include page="/common/listview.pager.jsp"/>
	</div> 
</body>
</html>
