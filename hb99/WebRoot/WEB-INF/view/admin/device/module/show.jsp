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
				    elem: '#sdate',
				    format: 'yyyy-MM-dd'
				}); 
				laydate.render({
				    elem: '#edate',
				    format: 'yyyy-MM-dd'
				}); 
				  
			})
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="TDeviceModule" method="post" showErrors="true" cssClass="layui-form layui-form-pane"> 
	    <form:hidden path="id"/>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item"> 
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">MAC地址:</label>
								<div class="layui-input-block">
							       <form:input path="mac" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">IP地址</label>
								<div class="layui-input-block">
								    <form:input path="ip" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div>   
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">SIM卡编号</label>
								<div class="layui-input-block">
								    <form:input path="sim" cssClass="layui-input" /> 
								</div>
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">是否激活</label>
								<div class="layui-input-block">
									<form:checkbox path="status" value="1" title="是"
										uncheckedValue="0" lay-skin="primary" />
								</div>
							</div>
						</div> 
					</div>   
					<div class="layui-form-item">  
						<div class="layui-col-md8">
							<div class="layui-form-item">
								<label class="layui-form-label">有效期</label> 
						        <div class="layui-input-inline" style="width: 200px;">
								    <form:input path="sdate" cssClass="layui-input" placeholder="yyyy-MM-dd"/> 
						        </div>
						        <div class="layui-form-mid">-</div>
						        <div class="layui-input-inline" style="width: 200px;">
								    <form:input path="edate" cssClass="layui-input" placeholder="yyyy-MM-dd"/> 
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
</html>