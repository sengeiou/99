<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>   
	<script type="text/javascript" src="${home}/js/ajax_funcs.js"></script>
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
	<form:form action="show.html?op=save" modelAttribute="TDeviceType" method="post" cssClass="layui-form layui-form-pane"> 
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
				<div class="layui-form-item">
					<label class="layui-form-label">名称</label>
					<div class="layui-input-block">
						<form:input path="name" cssClass="layui-input" />
					</div>
				</div>   
				<div class="layui-form-item">
					<label class="layui-form-label">检测仪</label>
					<div class="layui-input-block">
						<form:checkbox path="type" value="1" title="是" uncheckedValue="0" lay-skin="primary" />
					</div>
				</div> 
			    <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">说明</label>
				    <div class="layui-input-block"> 
				      <form:textarea path="remark" cssClass="layui-textarea"/>
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
			</blockquote>
		</div>     
		<div class="submit_group"> 
		    <button class="layui-btn"><i class="layui-icon">&#xe605;</i>保存</button>
			<a class="butx" onclick="gotoUrl('query.html')"><i class="layui-icon">&#xe65a;</i>返回</a> 
		</div>
	</form:form> 
</body>  
</html>