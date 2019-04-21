<%@ page contentType="text/html;charset=utf-8" language="java" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en" > 
<head>
<meta charset="UTF-8">
<title>404</title> 
<style> 
body {
	background: #2196F3;
} 
#notFound {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translateY(-50%) translateX(-50%) scale(1.2);
  width: 80%;
  height: auto;
} 
</style> 
</head> 
<body> 
<script src='${home }/js/pixi.js'></script>
<script src="${home }/js/404.js"></script> 
</body> 
</html>
