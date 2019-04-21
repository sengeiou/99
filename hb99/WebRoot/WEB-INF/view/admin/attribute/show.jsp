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
			var $valDiv = $('#valDiv');
			
			function typeHandler(value) {
				$valDiv.hide(); 
				if (value == 0) { 
					$valDiv.show();
				} else { 
					$valDiv.hide();
				} 
			}
            
            ${fn:substringBefore(jsValidator, '});')}
                ,ignore: '',
                submitHandler: function(form) {
                    $(form).find('button').prop("disabled", true).addClass('layui-btn-disabled');
                    form.submit();
                }
            });
            
           layui.use(['form'], function() {
	              var form = layui.form;
				  form.on('select(type)', function(data) {
					 $('#type').trigger("click"); // 触发validate验证
					 typeHandler(data.value);
				  }); 
                  
            });
            
			typeHandler($('#type').val());
       });
</script> 
</head>
<body>
	<form:form action="operate.do?op=save" modelAttribute="TMaintainAttribute"
		method="post" showErrors="true" cssClass="layui-form">
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
				<div class="layui-form-item">
					<div class="layui-col-md4">
						<label class="layui-form-label">属性值名称 :</label>
						<div class="layui-input-block">
							<form:input path="name" cssClass="layui-input" />
						</div>
					</div>
				</div> 
				<div class="layui-form-item">
					<div class="layui-col-md4">
						<label class="layui-form-label">参数值类型:</label>
						<div class="layui-input-block">
							<form:select path="type" items="${refData.types}" lay-verify="required" lay-filter="type"/>
						</div>
					</div> 
				</div>   
				<div class="layui-form-item" id="valDiv">
					<label class="layui-form-label">正常值范围 :</label>
					<div class="layui-col-md2">
						<form:input path="normalStartVal" cssClass="layui-input" placeholder="最小值" />
					</div>
					<div class="layui-col-md2">
						<form:input path="normalEndVal" cssClass="layui-input" cssStyle="margin-left: -80px;" placeholder="最大值" />
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