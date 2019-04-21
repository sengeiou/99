<%@ page contentType="text/html;charset=utf-8" language="java"%>
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
				
			});
		});
	</script>
</head>
<body>
	<form:form action="change_password.html?op=changePassword" modelAttribute="changePasswordData" method="post" showErrors="true" cssClass="layui-form mt20">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-inline"> 
			    	<input type="text" value="${ADMIN_SESSION_DATA.user.name}" disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label required">原密码</label>
			<div class="layui-input-inline">
				<form:password path="oldPassword" cssClass="layui-input pwd" /> 
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
				<form:password path="newPassword" cssClass="layui-input pwd" /> 
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
				<form:password path="confirmPassword" cssClass="layui-input pwd" /> 
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button id="buttonId" class="layui-btn" lay-filter="changePwd"  lay-submit="" >立即修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form:form>
	<script>
        layui.use(['form', 'layedit', 'laydate', 'upload'], function(){
            var form = layui.form,  
				layer = layui.layer;  
            //监听提交
            form.on('submit(changePwd)', function (data) {
	            if($("#oldPassword").val()==''){ 
                    layer.msg('原密码不能为空');
                    return false;
	            } 
	            if($("#newPassword").val()==''){ 
                    layer.msg('新密码不能为空');
                    return false;
	            } 
	            if($("#confirmPassword").val()==''){ 
                    layer.msg('确认密码不能为空');
                    return false;
	            }
	            if($("#newPassword").val()!=$("#confirmPassword").val()){
                    layer.msg('新密码与确认密码不相符');
                    return false;
	            }
                var url = '${home}/admin/system/user/changePassword';
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {op: 'changePassword', oldPassword: $("#oldPassword").val(), newPassword: $("#newPassword").val()},
                    dataType: 'json',
                    success: function (data) {
                        if(data.code=200){ 
                            layer.msg("保存成功",{icon: 5,time:3000,anim: 6}); 
			                $("#buttonId").addClass("layui-disabled");
			                $("#buttonId").attr("disabled", "disabled");
                        } else {
                            layer.msg(data.msg,{icon: 5,time:3000,anim: 6}); 
                        }
                    },
                    error: function (data) {

                    }
                }); 
                return false;
            }); 

        });
	</script>
</body>
</html>