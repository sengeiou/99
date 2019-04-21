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
	<script type="text/javascript" src="${home}/component/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${home}/component/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${home}/component/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		$(function() { 
			
			layui.use(['form', 'laydate'], function() {
				var form = layui.form;
				var laydate = layui.laydate;
				laydate.render({
				    elem: '#deadline',
				    format: 'yyyy-MM-dd'
				}); 
			 
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
		
			var ue = UE.getEditor('contents', ueConfig);
			 
		});
	</script>
</head>
<body>
	<form:form action="operate.do?op=save" modelAttribute="sysNotice" method="post" cssClass="layui-form layui-form-pane">
		<div style="display: none"><form:input  path="id" cssClass="layui-input" /></div>
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
						<label class="layui-form-label">是否置顶</label>
						<div class="layui-input-block">
							<form:checkbox path="isTop" value="1" title="是"  uncheckedValue="0" lay-skin="primary" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">截止时间</label>
						<div class="layui-input-inline">
							<form:input path="deadline" cssClass="layui-input" /> 
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">发布内容</label>
						<div class="layui-input-block">
							<form:textarea path="contents" /> 
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