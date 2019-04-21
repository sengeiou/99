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
			 
			});   
		});
	</script>
</head>
<body>
	<form:form action="operate.do?op=save" modelAttribute="TOrg" method="post" cssClass="layui-form layui-form-pane"> 
		<form:hidden path="id"/>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item">
						<label class="layui-form-label">机构名称</label>
						<div class="layui-input-block">
							<form:input path="name" cssClass="layui-input" /> 
						</div>
					</div>  
					<div class="layui-form-item">
						<label class="layui-form-label">机构描述</label>
						<div class="layui-input-block">
							<form:textarea path="remark" cssClass="layui-textarea" /> 
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