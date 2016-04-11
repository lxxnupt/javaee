<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/register.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户注册</title>
</head>
<html>
	<body>
		<div id="head"></div>
		<div id="left">
			<p>这是注册界面</p>
			<p>${message}</p>
		</div>
	
			<div id="right">
				<form action="${pageContext.request.contextPath}/user/addperson.do" method="post"> 
				<p>您是第一次来这里，请注册!</p>
				<p>账号(必填)：<input type="text" name="account"/></p>
				<p>密码(必填)：<input type="password" name="password"/></p>
				<p>确定密码  ：<input type="password" name="confirmpassword"/></p>		
				<p>姓&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;：<input type="text" name="name"/></p>
				<p>性&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;：<input type="text" name="sex"/></p>
				<p>电子邮件  ：<input type="text" name="email"/></p>
				<p>生&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;：<input type="text" name="birthday"/></p>
				<p><input  id="submit"  type="submit" value="注册"/></p>
			</form>
			
			</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
	</html>
	
