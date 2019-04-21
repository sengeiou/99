<%
	/*
	 应用背景：
	 由于一些js功能的实现依赖于一些第三方库，要引入该功能的话必须同时引入那些依赖库，
	 有的还需要引用配套的css文件，麻烦而易错。

	 此jsp主要用于解决此问题，即基于功能而非具体的js文件来声明脚本；
	 另外有一些通用功能的配套脚本也通过此jsp来实现。

	 使用说明：
	 在html的head中，包含此jsp，并将页面中需要的js功能通过参数传入，目前定义了如下功能：

	 operate   方便调用OperateAction的辅助函数
	 validate  支持jquery form验证
	 hints     支持form域后面的问号提示功能

	 参数传递方式只要功能名，不需要值，比如
	 <jsp:include page="meta.jsp?operate&validate&hints"/>

	 备注：
	 基础的js功能（jquery.js）不需要显式声明，会默认引入。
	 对于query.jsp来说，会默认引入opearte。
	 对于show.jsp来说，会默认引入validate。

	 */
%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<LINK rel="Bookmark" href="${home }/favicon.ico">
<LINK rel="Shortcut Icon" href="${home }/favicon.ico" />
<meta name="Keywords" content="九九智能平台">
<meta name="Description" content="九九智能平台">
<link rel="stylesheet" href="${home}/layui/css/layui.css" /> 
<link rel="stylesheet" href="${home}/css/common.css" />
<script type="text/javascript" src="${home}/layui/layui.js"></script> 
<script type="text/javascript" src="${home}/js/jquery.js"></script>
<script type="text/javascript" src="${home}/js/ajax_funcs.js"></script>
<script type="text/javascript" src="${home}/js/public.js"></script>
<%
	String pageName = request.getServletPath();
	boolean showPage = false;
	boolean queryPage = false;
	if (pageName.endsWith("query.jsp")) {
		queryPage = true;
	} else if (pageName.endsWith("show.jsp")) {
		showPage = true;
	} else if (pageName.endsWith("list.jsp")) {
		queryPage = true;
	}
	String params = "" + request.getAttribute("javax.servlet.include.query_string");
	boolean traits_operate = queryPage || params.contains("operate");
	boolean traits_validate = showPage || params.contains("validate");
%>
<%
	if (traits_operate) {
%>
<link rel="stylesheet" href="${home}/css/news.css" />
<script type="text/javascript" src="${home}/js/jqueryex.js"></script>
<script type="text/javascript" src="${home}/js/colResizable-1.6.min.js"></script>
<%
	}
%>
<%
	if (traits_validate) {
%>
<script type="text/javascript" src="${home}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${home}/js/jquery.validate.ex.js"></script> 
<%
	}
%>
<script type="text/javascript">
	$(function() {

		layui.use([ 'form' ], function() {

		});
	});
</script>
