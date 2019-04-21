<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="app://pub.form" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%> 
	<script type="text/javascript">
		$(function() { 
			
			layui.use(['form', 'laydate'], function() {
				var form = layui.form;
				var laydate = layui.laydate;
				laydate.render({
				    elem: '#createTime',
				    format: 'yyyy-MM-dd HH:mm:ss'
				}); 
			 
			}); 
			 
		});
	</script>
</head>
<body>
	<form:form action="operate.do?op=save" modelAttribute="TAlarm" method="post" cssClass="layui-form layui-form-pane">
		<div style="display: none"><form:input  path="id" cssClass="layui-input" /></div>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item">
						<label class="layui-form-label">所属设备</label>
						<div class="layui-input-block">
					         <form:select path="deviceId" items="${refData.devices}" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">所属项目</label>
						<div class="layui-input-block">
					         <form:select path="projectId" items="${refData.projects}" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">报警时间</label>
						<div class="layui-input-inline">
							<form:input path="createTime" cssClass="layui-input" pattern="yyyy-MM-dd HH:mm:ss" /> 
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">报警数值</label>
						<div class="layui-input-block">
							<form:input path="num" cssClass="layui-input" /> 
						</div>
					</div>  
					<div class="layui-form-item">
						<label class="layui-form-label">报警内容</label>
						<div class="layui-input-block">
							<form:input path="content" cssClass="layui-input" /> 
						</div>
					</div> 
					<div class="layui-form-item">
						<label class="layui-form-label">处理状态</label>
						<div class="layui-input-block">
					         <form:select path="status" items="${refData.statuses}" />
						</div>
					</div> 
				</blockquote>
		</div>
		<div class="submit_group">  
		    <button class="layui-btn"><i class="layui-icon">&#xe605;</i>保存</button>
			<a class="butx" onclick="gotoUrl('query.html')"><i class="layui-icon">&#xe65a;</i>返回</a> 
		</div>
	</form:form> 
</body>
</html>