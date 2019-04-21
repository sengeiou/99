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
				laydate.render({
				    elem: '#produceTime'
				}); 
				laydate.render({
				    elem: '#factoryTime'
				}); 
				
			})
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="TDevice" method="post" showErrors="true" cssClass="layui-form layui-form-pane"> 
	    <form:hidden path="id"/>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item"> 
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">设备id:</label>
								<div class="layui-input-block">
							       <form:input path="deviceid" cssClass="layui-input" /> 
								</div>
							</div>
						</div>   
					</div> 
					<div class="layui-form-item"> 
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">设备名称:</label>
								<div class="layui-input-block">
							       <form:input path="name" cssClass="layui-input" /> 
								</div>
							</div>
						</div>  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">设备类型</label>
								<div class="layui-input-block">
								    <form:select path="typeId" items="${refData.types}" nullItem="--请选择设备类型--" lay-filter="typeId"/>
								</div>
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">属性</label>
								<div class="layui-input-block">
								    <form:select path="property" items="${refData.propertys}" nullItem="--请选择设属性--" lay-filter="property"/>
								</div>
							</div>
						</div>  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">采集地址</label>
								<div class="layui-input-block">
							       <form:input path="address" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div>   
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">采集周期</label>
								<div class="layui-input-block">
							       <form:input path="cycle" cssClass="layui-input" /> 
								</div>
							</div>
						</div>   
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">所属项目</label>
								<div class="layui-input-block">
								    <form:select path="projectId" items="${refData.projects}" nullItem="--请选择项目--" lay-filter="projectId"/>
								</div>
							</div>
						</div> 
					</div> 
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">所属模块</label>
								<div class="layui-input-block">
								    <form:select path="moduleId" items="${refData.deviceModules}" nullItem="--请选择模块--" lay-filter="moduleId"/>
								</div>
							</div>
						</div>  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">摄像头</label>
								<div class="layui-input-block">
									<form:checkbox path="isCamera" value="1" title="有"
										uncheckedValue="0" lay-skin="primary" />
								</div>
							</div>
						</div> 
					</div>    
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">设备IP</label>
								<div class="layui-input-block">
							       <form:input path="ip" cssClass="layui-input" /> 
								</div>
							</div>
						</div>  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">设备端口</label>
								<div class="layui-input-block">
							       <form:input path="port" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div> 
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">触屏IP</label>
								<div class="layui-input-block">
							       <form:input path="touchIp" cssClass="layui-input" /> 
								</div>
							</div>
						</div>  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">触屏端口</label>
								<div class="layui-input-block">
							       <form:input path="touchPort" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div>
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">生产日期</label>
								<div class="layui-input-block">
							       <form:input path="produceTime" cssClass="layui-input"  format="yyyy-MM-dd"/> 
								</div>
							</div>
						</div>  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">出厂日期</label>
								<div class="layui-input-block">
							       <form:input path="factoryTime" cssClass="layui-input"  format="yyyy-MM-dd"/> 
								</div>
							</div>
						</div> 
					</div>
					<div class="layui-form-item">
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">设备识别码</label>
								<div class="layui-input-block">
							       <form:input path="code" cssClass="layui-input" /> 
								</div>
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
	<script type="text/javascript">
	
		$(function() { 
			
			layui.use(['form', 'laydate'], function() {
				var form = layui.form,
					formSelects = layui.formSelects,
				 	laydate = layui.laydate; 
				 	 
			}); 
		}); 
   
	</script>
</html>