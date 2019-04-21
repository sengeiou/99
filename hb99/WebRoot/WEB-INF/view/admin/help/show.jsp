<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <%@ include file="/common/meta.jsp"%>  
   <script type="text/javascript" src="${home}/component/ueditor/ueditor.config.js"></script>
   <script type="text/javascript" src="${home}/component/ueditor/ueditor.all.min.js"></script>
   <script type="text/javascript" src="${home}/component/ueditor/lang/zh-cn/zh-cn.js"></script>
     
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
			
			var ueConfig = {
					toolbars: [
					    [
					        'anchor', 
					        'undo', 
					        'redo', 
					        'bold', 
					        'indent', 
					        'italic', 
					        'underline', 
					        'strikethrough', 
					        'subscript', 
					        'fontborder', 
					        'superscript',
					        'formatmatch', 
					        'source', 
					        'blockquote', 
					        'pasteplain', 
					        'selectall', 
					        'print', 
					        'preview', 
					        'horizontal', 
					        'removeformat', 
					        'time',
					        'date',
					        'unlink',
					        'insertrow', 
					        'insertcol', 
					        'mergeright',
					        'mergedown',
					        'deleterow',
					        'deletecol', 
					        'splittorows',
					        'splittocols', 
					        'splittocells',
					        'deletecaption',
					        'inserttitle',
					        'mergecells',
					        'deletetable',
					        'cleardoc', 
					        'insertparagraphbeforetable', 
					        'insertcode', 
					        'fontfamily', 
					        'fontsize',
					        'paragraph', 
					        'simpleupload',
					        'edittable',
					        'edittd',
					        'link', 
					        'emotion', 
					        'spechars',
					        'justifyleft', 
					        'justifyright', 
					        'justifycenter', 
					        'justifyjustify',
					        'forecolor', 
					        'touppercase', 
					        'tolowercase',
					        'backcolor', 
					        'insertorderedlist', 
					        'insertunorderedlist', 
					        'fullscreen',
					        'directionalityltr', 
					        'directionalityrtl', 
					        'rowspacingtop',
					        'rowspacingbottom',
					        'lineheight',
					        'pagebreak',
					        'insertframe',
					        'imagenone',
					        'imageleft',
					        'imageright',
					        'imagecenter',
					        'attachment',
					        'edittip ',
					        'customstyle',
					        'wordimage',
					        'autotypeset',
					        'background',
					        'template',
					        'inserttable',
					        'charts', 
					        'searchreplace',
					        'map',
					    ]
					],
					maximumWords: 2000,
					zIndex: 100,
					initialFrameWidth: 900,
					initialFrameHeight: 450,
					elementPathEnabled: false,
					enableContextMenu: false,
					enableAutoSave: false
				};
			
				var ue = UE.getEditor('content', ueConfig);
				 
		});
	</script>
</head>
<body> 
	<form:form action="operate.do" modelAttribute="help" method="post" cssClass="layui-form layui-form-pane"> 
	 <input  type="hidden" name="id" value="${help.id}"/>
		<div class="p15">
			<blockquote class="layui-elem-quote title">基本信息</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<form:input path="title" cssClass="layui-input" />
					</div>
				</div> 
				
				<div class="layui-form-item">
					<label class="layui-form-label">类型:</label>
					<div class="layui-input-block">
						<div class="layui-input-inline">
							<form:select path="type" items="${refData.types}" nullItem="--请选择类型--" />
						</div> 
					</div>
				</div>
				
			    <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">内容</label>
				    <div class="layui-input-block"> 
				      <form:textarea path="content" cssClass="layui-textarea"/>
				      
				    </div>
			    </div>
			</blockquote>
		</div>     
		<div class="submit_group"> 
			<button class="layui-btn">立即提交</button>
			<button type="button" class="layui-btn layui-btn-normal" onclick="gotoUrl('query.html')"><i class="layui-icon">&#xe65a;</i>返回</button> 
		</div>
	</form:form> 
</body>  
</html>