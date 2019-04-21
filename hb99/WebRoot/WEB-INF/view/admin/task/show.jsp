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
				    elem: '#maintainTask\\.sdate',
				    format: 'yyyy-MM-dd'
				}); 
				laydate.render({
				    elem: '#maintainTask\\.edate',
				    format: 'yyyy-MM-dd'
				}); 
				 
			})
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="maintainTaskData" method="post" showErrors="true" cssClass="layui-form layui-form-pane"> 
	    <form:hidden path="maintainTask.id"/>
		<div class="p15">
				<blockquote class="layui-elem-quote title">基本信息</blockquote>
				<blockquote class="layui-elem-quote layui-quote-nm"> 
					<div class="layui-form-item"> 
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">任务名称:</label>
								<div class="layui-input-block">
							       <form:input path="maintainTask.name" cssClass="layui-input" /> 
								</div>
							</div>
						</div>   
					</div>  
					<div class="layui-form-item">   
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">所属设备</label>
								<div class="layui-input-block">
								    <form:select path="maintainTask.deviceId" items="${refData.devices}" nullItem="--请选择设备--" lay-filter="deviceId"/>
								</div>
							</div>
						</div> 
					</div>  
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">所属项目</label>
								<div class="layui-input-block">
								    <form:select path="maintainTask.projectId" items="${refData.projects}" nullItem="--请选择设属性--" lay-filter="projectId"/>
								</div>
							</div>
						</div>  
					</div> 
					<div class="layui-form-item">  
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">开始时间</label>
								<div class="layui-input-block">
							       <form:input path="maintainTask.sdate" cssClass="layui-input"  format="yyyy-MM-dd" /> 
								</div>
							</div>
						</div> 
						<div class="layui-col-md4">
							<div class="layui-form-item">
								<label class="layui-form-label">结束时间</label>
								<div class="layui-input-block">
							       <form:input path="maintainTask.edate" cssClass="layui-input"  format="yyyy-MM-dd" /> 
								</div>
							</div>
						</div>   
					</div> 
					<div class="layui-form-item">
						<div class="layui-col-md10">
							<label class="layui-form-label"><span style="color: #FF0000">*</span>相关属性:</label>
							<div class="layui-input-block" id="deviceAttributeDiv">
								<c:forEach items="${refData.maintainAttributes}" var="row" varStatus="s">
									<c:set var="isChecked" value="0" />
									<c:forEach items="${maintainTaskData.maintainAttributeList}" var="attrId">
										<c:if test="${attrId == row.intId}">
											<c:set var="isChecked" value="1" />
										</c:if>
									</c:forEach>
									<input type="checkbox" name="maintainAttributeList" title="${row.text}" value="${row.intId}" class="canOperate" ${isChecked == 1?'checked="true"' : ''}/>
								</c:forEach>
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
	<script type="text/javascript">
		$(function() {
			${fn:substringBefore(jsValidator, '});')}
				,ignore: '',
				submitHandler: function(form) {
					$(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
					if(!$("input[name='maintainAttributeList']:checked").length > 0){
						layer.msg("请至少选择一个相关属性");
						$(form).find('button').prop("disabled", false).removeClass('layui-btn-disabled');
						return false;
					}
					form.submit();
				}
			});
			
			layui.use(['form'], function() {
				var form = layui.form;   
			 
			});
			


		});
	</script>
</body>  
</html>