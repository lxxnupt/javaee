<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/index.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>论坛登录</title>
</head>
<html>
	<body>
		<div id="head"></div>
		<div id="left">
			<p>登录失败！</p>
		</div>
	
			<div id="login">
	
			<form name="login" action="${pageContext.request.contextPath}/user/login.do" method="post">
				<p>账号不存在或密码错误，请重新登录!</p>
				<p>账号：<input type="text" name="account"/></p>
				<p>密码：<input type="password" name="password"/></p>
				<p><input  id="submit"  type="submit" value="登录"/></p>
			</form>
			</div>
			
			<div id="register">
				<a href="register.do"><img src="../image/register.jpg"/></a>
			</div>
	
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
	
</html>