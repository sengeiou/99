<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="funcs" uri="app://pub.functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>系统提示</title>  
<style type="text/css">
body{
	background:rgba(0,0,0,0.5);
}
.tcxt{
	width:540px;
	margin:0px auto;
	margin-top:160px;
}
.tcxt .tcxt_h{
	width:100%;
	height:60px;
	line-height:60px;
	background:#F3F3F3;
	border-top-left-radius:3px;
	border-top-right-radius:3px;
	-moz-border-top-left-radius:3px;
	-moz-border-top-right-radius:3px;
	-webkit-border-top-left-radius:3px;
	-webkit-border-top-right-radius:3px;
	border-bottom:1px solid #dedede;
	text-align:left;
}
.tcxt .tcxt_h span{
	display:block;
	font-size:22px;
	font-weight:bold;
	color:#434343;
	margin-left:28px;
}
.tcxt .tcxt_m{
	width:100%;
	height:170px;
	line-height:170px;
	background:#FFF;
	text-align:center;
	color:#434343;
	font-size:20px;
	border-bottom:1px solid #dedede;
	margin-top:-25px;
}
.tcxt .tcxt_f{
	width:100%;
	height:75px;
	text-align:center;
	background:#FFF;
	border-bottom-left-radius:3px;
	border-bottom-right-radius:3px;
	-moz-border-bottom-left-radius:3px;
	-moz-border-bottom-right-radius:3px;
	-webkit-border-bottom-left-radius:3px;
	-webkit-border-bottom-right-radius:3px;
}
.btn_bg{
	display:inline-block;
	*display:inline;
	zoom:1;
	width:126px;
	height:36px;
	text-align:center;
	line-height:36px;
	color:#fff;
	vertical-align:top;
	margin-top:16px;
	margin-right:5px; 
}
</style>
</head> 
<body> 
<div class="tcxt">
	<div class="tcxt_h">
    	<span>提示</span>
    </div>
    <div class="tcxt_m">
    	<c:if test="${not empty message}">
		<h2>${message}</h2> 
		</c:if>
    </div>
    <div class="tcxt_f">
        <c:if test="${isError}">
			<a href="javascript:void(0);" onclick="history.back();" style="background-color: #009688;" class="btn_bg btn_h">返回重试</a>
		</c:if>
		<c:if test="${not empty nextUrl}">
			<c:set var="url" value="${fn:startsWith(nextUrl, 'http:')? '': home}${nextUrl}"/>
			<a href="javascript:void(0);" onclick="returnUrl('${url}');" style="background-color: #009688;" class="btn_bg">确定</a>
		</c:if>
		<c:if test="${not empty close}">
			<a href="javascript:void(0);" onclick="closeCurrentWindow();"  style="background-color: #009688;" class="btn_bg">确定</a>
		</c:if> 
    </div>
</div> 
</body>
<script type="text/javascript" src="${home}/js/jquery.js"></script>
<script type="text/javascript">
	function returnUrl(url){
		location.href=url;
	} 
</script>
</html>