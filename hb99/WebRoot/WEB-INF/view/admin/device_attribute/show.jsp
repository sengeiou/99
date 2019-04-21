<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>   
    <link rel="stylesheet" href="${home}/css/myImageUpload.css" />
	<link rel="stylesheet" href="${home}/component/lightbox2/css/lightbox.min.css" /> 
	<script type="text/javascript" src="${home}/js/myImageUpload.js"></script>
	<script type="text/javascript" src="${home}/component/lightbox2/js/lightbox.min.js"></script> 
	<link rel="stylesheet" href="${home}/css/loginDialog.css" /> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=d2dVBeSkIh7LQDrrAKhHdjNTGmQH3Gsa"></script>  
	<script type="text/javascript">
		$(function() { 
			${fn:substringBefore(jsValidator, '});')}
				,ignore: '',
				submitHandler: function(form) {
					$(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
					form.submit();
				}
			});
			
			layui.use(['form', 'laydate'], function() {
				var form = layui.form;
				var laydate = layui.laydate;  
				
			}) 
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="deviceAttribute" method="post" showErrors="true" cssClass="layui-form layui-form-pane"> 
	    <form:hidden path="id"/>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item">
						<div class="layui-col-md4">
							<label class="layui-form-label">设备类型:</label>
							<div class="layui-input-block">
								<form:select path="deviceTypeId" items="${refData.deviceTypes}" nullItem="设备类型"  lay-verify="required" />
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">
						<div class="layui-col-md4">
								<label class="layui-form-label">数据名称:</label>
							<div class="layui-input-block">
							       <form:input path="name" cssClass="layui-input" /> 
							</div>
						</div>
					</div> 
					<div class="layui-form-item">
						<div class="layui-col-md4">
							<label class="layui-form-label">数据代码</label>
							<div class="layui-input-block">
								<form:input path="attributeCode" cssClass="layui-input" /> 
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">
						<label class="layui-form-label">阈值:</label>
						<div class="layui-input-block">
							<div class="layui-input-inline">
								<form:input path="thresholdStart" cssClass="layui-input" placeholder="请输入起始值"/>
							</div>
							<div class="layui-form-mid layui-word-aux">-</div>
							<div class="layui-input-inline">
								<form:input path="thresholdStop" cssClass="layui-input" placeholder="请输入结束值"/>
							</div>
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