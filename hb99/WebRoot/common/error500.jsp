<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title> 
<style> 
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
} 
article, aside, details, figcaption, figure, 
footer, header, hgroup, menu, nav, section {
	display: block;
}
body {
	line-height: 1;
}
ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
/* add */
body {font-size:12px;font-family: arial,SimHei,"黑体";color:#333;}
img{border:0}
h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal;}
select,input{vertical-align:middle;padding:0;}
.clear { display:block !important; clear:both !important; float:none !important; margin:0 !important; padding:0 !important; height:0; line-height:0; font-size:0; overflow:hidden;}
.clearfix { zoom:1; }
.clearfix:after { content:""; display:block; clear:both; height:0; }
.spacer{clear:both; font-size:0; height:0; line-height:0;}
a{ text-decoration:none;cursor: pointer;outline:none;}

i,em{font-style: normal;}
.error_content {
	margin: 10% auto;
	width: 1000px;
	height: 600px;
	border: 1px solid #cccccc;
} 
.error_left {
	margin: 120px 0 0 50px;
	width: 330px;
	height: 345px;
	background: url(${home}/images/timg.png) no-repeat;
	background-size: 100%;
	float: left;
} 
.error_right {
	width: 450px;
	height: 590px;
	float: left;
} 
.error_detail {
	margin: 180px 0 0 120px;
	width: 400px;
	height: auto;
} 
.error_detail .error_p_title {
	font-size: 28px;
	color: #eb8531;
}

.error_detail .error_p_con {
	font-size: 14px;
	margin-top: 10px;
	line-height: 20px;
} 
.sp_con {
	margin-left: 128px;
	color: #1A4EC0;
	margin-top: 39px;
	position: absolute;
	font-size: 18px;
}  
</style>
</head>
<body>
	<div class="error_content">
		<div class="error_left">
			<span class="sp_con">赶紧修，大家等着呢。</span>
		</div>
		<div class="error_right">
			<div class="error_detail">
				<p class="error_p_title">哎呦~ 服务器居然累倒了!</p>
				<p class="error_p_con">●别急，工程师正在紧急处理，马上就好。</p>
				<p class="error_p_con">●您可致电0731-85123738,通知开发人员!</p>
				<p class="error_p_con">●我们的进步需要您的反馈,感谢您对我们的支持,请您耐心等待!</p>
			</div> 
		</div>
	</div>
</body>
</html>