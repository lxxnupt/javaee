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
<link rel="stylesheet" type="text/css" href="../css/userupdate.css" />
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
		
			<form action="${pageContext.request.contextPath}/user/updateperson.do" method="post"> 
				<p>账号(不可改)：${person.account}</p>
				<p>密码(必填)：<input type="password" name="password"/></p>
				<p>姓&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;：<input type="text" name="name"/></p>
				<p>性&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;：<input type="text" name="sex"/></p>
				<p>电子邮件  ：<input type="text" name="email"/></p>
				<p>生&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;：<input type="text" name="birthday"/></p>
				<p><input  id="submit"  type="submit" value="确认"/></p>
			</form>
	
		
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
	
