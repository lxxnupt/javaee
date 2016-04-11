<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<link rel="stylesheet" type="text/css" href="../css/userinformation.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户资料</title>
</head>
<html>
	<body>
		<div id="head"></div>
		<div id="left">
		<ul>
			<c:set var="boardnumber" value="${fn:length(allboards)}"/>
	  		<c:if test="${boardnumber!=0}">
			<c:forEach var="i"  begin="0"  end="${boardnumber-1}">
			<li><a href="../post/findpost.do?id=${allboards[i].id}&currentPage=1">${allboards[i].name}</a></li>
			</c:forEach>
			</c:if>
			<li><a href="/Mybbs/user/back.do">板块概况</a></li>
		</ul>
		</div>
	
		<div id="right">
			<ul id="welcome">
			<li>欢迎你，${person.account}！</li>
			<li><a href="index.do">|注销</a></li>
			<li><a href="back.do">|返回</a></li>
			</ul>
		
			<ul id="information">
			<li>账号:${person.account}</li>
			<li>姓名:${person.name}</li>
			<li>性别:${person.sex}</li>
			<li>生日:${person.birthday}</li>
			<li>电邮:${person.email}</li>
			<li>身份:${person.identity}</li>
			</ul>
			
		  	<p><a href="toupdateperson.do?person=${person}">更改个人信息</a></p>
		
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
	
