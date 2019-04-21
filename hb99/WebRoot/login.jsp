<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${home}/css/login.css">
    <script src="${home}/js/carousel.js"></script>
    <title>大气污染源管控治一体化智能平台</title>
</head>
<body>
    <header>
        <img src="${home}/images/logo.png" />
    </header>
    <main>
        <div class="carouselWrap">
			<div class="moveBox">
				<ul>
					<li><img src="${home}/images/0_0pic.png" /></li>
					<li><img src="${home}/images/0_1pic.png" /></li>
					<li><img src="${home}/images/0_2pic.png" /></li>
					<li><img src="${home}/images/0_3pic.png" /></li> 
					<li><img src="${home}/images/0_4pic.png" /></li> 
					<li><img src="${home}/images/0_5pic.png" /></li> 
					<li><img src="${home}/images/0_6pic.png" /></li> 
				</ul>
			</div> 
			<div class="circle">
				<a href="# " class="moveCircle"></a>
				<a href="# "></a>
				<a href="# "></a>
				<a href="# "></a>
                <a href="# "></a> 
                <a href="# "></a> 
                <a href="# "></a> 
			</div> 
        </div>
        <div class="loginWrap">
            <form action="login.html"  method="post">
	            <input type="hidden" name="doLogin" value="1"/>   
                <h3>登录</h3>
                <div><label for="code"><img src="${home}/images/user.png" /></label><input placeholder="用户名" type="text" id="code" name="code"></div>
                <div><label for="password"><img src="${home}/images/password.png" /></label><input placeholder="密码" type="password" id="password" name="password"></div> 
                <button type="submit">登录</button>
            </form>
        </div>
     
    </main>
    <article>
        <h1>公司简介</h1>
        <p>九九矿安环保从诞生的第一天开始，就以创造碧水蓝天为使命！在企业使命的指引下，并自主研 发及生产综合除尘、抑尘系列产品（远程射雾器、高压干雾抑尘器、可拆卸防风抑尘网组件、环保型抑尘剂等），多年来从事喷雾除尘技术的研究，在喷雾除尘行业里面取得了技术领先地位。</p>
    </article>
</body>
</html>