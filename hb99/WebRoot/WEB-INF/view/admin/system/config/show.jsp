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
			${fn:substringBefore(jsValidator, '});')}
				,ignore: '',
				submitHandler: function(form) {
					$(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
					form.submit();
				}
			});
			
			layui.use(['form'], function() {
				var form = layui.form; 
			});
			 
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="sysConfig" method="post" cssClass="layui-form layui-form-pane"> 
		<div class="p15">  
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item">
						<label class="layui-form-label">key</label>
						<div class="layui-input-block">
							<form:input path="configKey" cssClass="layui-input" /> 
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">值</label>
						<div class="layui-input-block"> 
							<form:input path="configValue" cssClass="layui-input"/>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">说明</label>
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