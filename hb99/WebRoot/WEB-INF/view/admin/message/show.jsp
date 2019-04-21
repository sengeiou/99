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
   	<link type="text/css" rel="stylesheet" href="${home}/component/formSelects/formSelects-v4.css">
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
				var formSelects = layui.formSelects;
				var form = layui.form;
			});
		});
	</script>
</head>
<body>
	<form:form action="operate.do" modelAttribute="messageData" method="post" cssClass="layui-form layui-form-pane">
		<input type="hidden" name="id" value="${messageData.message.id }"/>
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm"> 
				<div class="layui-form-item">
					<label class="layui-form-label">接收人:</label>
      				<div class="layui-input-block">
	      				<div class="layui-col-md5">
	      					<form:select path="receiveUsers" xm-select="select1" items="${refData.allUserList}" nullItem="--请选择接收者--" /> 				
	    				</div>
      				</div>
    			</div>
    			<div class="layui-form-item">
					<label class="layui-form-label">消息类型:</label>
					<div class="layui-input-block">
						<div class="layui-input-inline">
							<form:select path="message.type" items="${refData.types}" nullItem="--请选择类型--" />
						</div> 
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">推送内容:</label>
					<div class="layui-input-block">
						<div class="layui-input-inline">
							<form:textarea path="message.content" rows="10" cols="60"/>
						</div> 
					</div>
				</div>  
			</blockquote>
		</div>
		<div class="submit_group">  
			<c:if test="${param.readonly != true}">
			    <button class="layui-btn"><i class="layui-icon">&#xe605;</i>保存</button>
			</c:if>
			<a class="butx" onclick="gotoUrl('query.html')"><i class="layui-icon">&#xe65a;</i>返回</a> 
		</div>
	</form:form>
	<script type="text/javascript" src="${home}/component/formSelects/formSelects-v4.js"></script>
</body>
</html>