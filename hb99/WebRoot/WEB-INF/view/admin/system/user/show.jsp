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
			
			layui.use(['form'], function() {  
				var form = layui.form; 
				var layer = layui.layer; 
				
				form.on('select(province)', function(data) { 
					var url = '${home}/common/area/listSubAreas'; 
					var data = {op: 'listSubAreas', pid: data.value}; 
					fillCombo($('#user\\.cityId'), url, data, '--请选择--', function() {
						form.render();
					});   
				    $('#user\\.areaId').empty();
				    $('#user\\.areaId').append("<option value=''>--请选择--</option>");
				});
				
				form.on('select(city)', function(data) { 
					var url = '${home}/common/area/listSubAreas';
					var data = {op: 'listSubAreas', pid: data.value};
					fillCombo($('#user\\.areaId'), url, data, '--请选择--', function() {
						form.render();
					});
				});  
			});
		});
	</script>
</head>
<body>
	<form:form action="show.html?op=save" modelAttribute="userData"
		method="post" showErrors="true" cssClass="layui-form layui-form-pane">
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
				<div class="layui-form-item">
					<label class="layui-form-label">帐号</label>
					<div class="layui-col-md4">
						<form:input path="user.account" cssClass="layui-input" />
					</div>
					<label class="layui-form-label">密码</label>
					<div class="layui-col-md4">
						<form:password path="user.password" cssClass="layui-input" />
						<c:if test="${isEdit}">
							<em style="color: red;">密码不填，代表不修改密码</em>
						</c:if>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-col-md4">
						<form:input path="user.name" cssClass="layui-input" />
					</div>
					<label class="layui-form-label">手机号码</label>
					<div class="layui-col-md4">
						<form:input path="user.mobile" cssClass="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">管理员</label>
					<div class="layui-col-md4">
						<form:checkbox path="user.isManager" value="1" title="是"
							uncheckedValue="0" lay-skin="primary" />
					</div>
					<label class="layui-form-label">是否激活</label>
					<div class="layui-col-md4">
						<form:checkbox path="user.status" value="1" title="是"
							uncheckedValue="0" lay-skin="primary" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">微信号</label>
					<div class="layui-col-md4">
						<form:input path="user.weixin" cssClass="layui-input" />
					</div>
					<label class="layui-form-label">电子邮箱</label>
					<div class="layui-col-md4">
						<form:input path="user.email" cssClass="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所在部门</label>
					<div class="layui-col-md4">
						<form:input path="user.dept" cssClass="layui-input" />
					</div>
					<label class="layui-form-label">职位</label>
					<div class="layui-col-md4">
						<form:input path="user.job" cssClass="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地区:</label>
					<div class="layui-input-block">
						<div class="layui-input-inline">
							<form:select path="user.provinceId" items="${refData.provinces}"
								nullItem="--请选择省份--" lay-filter="province" />
						</div>
						<div class="layui-input-inline">
							<form:select path="user.cityId" items="${refData.cities}"
								nullItem="--请选择城市--" lay-filter="city" />
						</div>
						<div class="layui-input-inline">
							<form:select path="user.areaId" items="${refData.areas}"
								nullItem="--请选择区域--" lay-filter="area" />
						</div>
						<div class="layui-input-inline">
							<form:input path="user.address" cssClass="layui-input" />
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">机构类型:</label>
					<div class="layui-input-block">
						<div class="layui-input-inline">
							<form:select path="user.type" items="${refData.types}" nullItem="--请选择机构类型--" />
						</div> 
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">角色权限</label>
					<div class="layui-input-block">
						<form:checkboxes path="roleIds" items="${refData.roles}"
							delimiter="&nbsp;&nbsp;" lay-skin="primary" />
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