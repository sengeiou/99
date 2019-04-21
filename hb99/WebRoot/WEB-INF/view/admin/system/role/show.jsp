<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE HTML>
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
					var chks = $('form input[type="checkbox"]');
					
					form.on('checkbox(checkbox)', function(data) {
						checkboxClickHanlder(data.elem, chks, data.elem.checked);
					});
					
					function checkboxClickHanlder(chk, chks, checked) {
						onCheckChange(chk, chks, checked);
						var id = chk.id.substr(4);
						while (true) {
							var parentId = $('#chk_' + id).attr('class').substr('child_of_'.length);
							var $parent = $('#chk_' + parentId);
							if (!$parent.length) {
								break;
							}
							$parent[0].checked = true;
							id = parentId;
						}
						form.render();
					}
					
					function onCheckChange(chk, chks, checked) {
						var id = chk.id.substr(4);
						chks.filter('.child_of_' + id).each(function(n, e) {
							e.checked = checked;
							onCheckChange(e, chks, checked);
						});
					}
				});
			});
	</script> 
</head>
<body>
	<form:form action="${home}/admin/system/role/save" modelAttribute="roleData" method="post" cssClass="layui-form layui-form-pane">
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
				<div class="layui-form-item">
					<label class="layui-form-label">角色名称:</label>
					<div class="layui-input-inline">
						<form:input path="role.name" cssClass="layui-input" /> 
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">权限:</label>
					<div class="layui-input-block">
		     			<c:forEach items="${refData.moduleDatas}" var="data">
							<div> ${data.indent}
								<input type="checkbox" name="moduleIds" value="${data.module.id}" id="chk_${data.module.id}"  title="${data.module.name}" lay-skin="primary" <c:if test="${data.hasRel}">checked="checked"</c:if> class="child_of_${data.module.parentId}" lay-filter="checkbox" />
								
							</div>
						</c:forEach>
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