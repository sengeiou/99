<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>  
	<link rel="stylesheet" href="${home}/css/user.css" />
	<script type="text/javascript" src="${home}/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${home}/js/jquery.validate.ex.js"></script>
	<script type="text/javascript" src="${home}/js/ajax_funcs.js"></script>
	<script type="text/javascript" src="${home}/js/public.js"></script>
	<script type="text/javascript">
		$(function() {
			${fn:substringBefore(jsValidator, '});')}
				,ignore: '',
				submitHandler: function(form) {
					$(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
					form.submit();
				}
			});
			
			layui.use(['form', 'layer', 'upload'], function() {
				var form = layui.form;
				var upload = layui.upload;
				var layer = layui.layer; 
				
				form.on('select(province)', function(data) {
					$('#user\\.provinceId').trigger("click"); // 触发validate验证
					var url = '${home}/common/area/listSubAreas'; 
					var data = {op: 'listSubAreas', pid: data.value}; 
					fillCombo($('#user\\.cityId'), url, data, '--请选择--', function() {
						form.render();
					});   
				    $('#user\\.areaId').empty();
				    $('#user\\.areaId').append("<option value=''>--请选择--</option>");
				});
				
				form.on('select(city)', function(data) {
					$('#user\\.cityId').trigger("click"); // 触发validate验证
					var url = '${home}/common/area/listSubAreas';
					var data = {op: 'listSubAreas', pid: data.value};
					fillCombo($('#user\\.areaId'), url, data, '--请选择--', function() {
						form.render();
					});
				});
				
				form.on('select(area)', function(data) {
					$('#user\\.areaId').trigger("click"); // 触发validate验证
				});
				
	            upload.render({
	                elem: '#test10',
	                url: '${home}/common/image/ajax_upload',
	                accept: 'images',
	                acceptMime: 'image/*',
	                size: 2048,
	                before: function(obj) {
	                	layer.load(2); 
	                },
				    done: function(res){
				        layer.closeAll('loading');  
				        if (res.code == 200) {
			                $('#fileId').val(res.id);   
	                        var img = '<img style="width" src="' + res.src + '" class="layui-circle" id="userFace">';
	                        $('#test10').html(img); 
						    layer.msg('图片上传成功');
	                    } else {
	                        layer.msg(res.msg, {icon: 5, time: 3000, anim: 6});
	                    }
				    },
				    error: function(){ 
	                	layer.msg('上传出错', {icon: 5, time: 3000, anim: 6});
				    }  
	            });
			}); 
		});
	</script>
</head>
<body>
	<form:form action="${home}/admin/system/user/update" modelAttribute="userData" method="post" showErrors="true" cssClass="layui-form" enctype="multipart/form-data">
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
		    	<input type="hidden" id="fileId" name="fileId" value="${fileId}" />
				<div class="user_left">
					<div class="layui-form-item">
					    <label class="layui-form-label">账号</label>
					    <div class="layui-input-block"> 
							<form:input path="user.account" cssClass="layui-input layui-disabled"/>
					    </div>
					</div>  
					<div class="layui-form-item">
					    <label class="layui-form-label">姓名</label>
					    <div class="layui-input-block"> 
							<form:input path="user.name" cssClass="layui-input"/>
					    </div>
					</div> 
					<div class="layui-form-item">
					    <label class="layui-form-label">手机号码</label>
					    <div class="layui-input-block"> 
							<form:input path="user.mobile" cssClass="layui-input"/>
					    </div>
					</div> 
					<div class="layui-form-item">
						<label class="layui-form-label">地区:</label>
						<div class="layui-input-inline">
							<form:select path="user.provinceId" items="${refData.provinces}" nullItem="--请选择--" lay-filter="province" />
						</div>
						<div class="layui-input-inline">
							<form:select path="user.cityId" items="${refData.cities}" nullItem="--请选择--" lay-filter="city" />
						</div>
						<div class="layui-input-inline">
							<form:select path="user.areaId" items="${refData.districts}" nullItem="--请选择--" lay-filter="area" />
						</div>
					</div>  
					<div class="layui-form-item">
					    <label class="layui-form-label">邮箱</label>
					    <div class="layui-input-block"> 
							<form:input path="user.email" cssClass="layui-input"/>
					    </div>
					</div> 
				</div>
				<div class="user_right">
					<div class="layui-upload-drag" id="test10" style="width: 200px; height: 200px;"> 
						<c:choose>
					    	<c:when test="${not empty url}"><img style="width" src="${url}" class="layui-circle" id="userFace"></c:when>
							<c:otherwise>
								<div style="margin-top: 25px;">
									<i class="layui-icon" style="font-size: 100px;">&#xe681;</i>
									<p style="color: #999;">点击上传，或将文件拖拽到此处</p>
								</div> 
					      </c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="layui-form-item" style="margin-left: 5%;">
					<div class="layui-input-block">
				    	<button class="layui-btn">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				    </div>
				</div>
			</blockquote>
	</form:form> 
</body>
</html>