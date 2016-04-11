<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/updateboard.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改板块</title>
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
		<ul id ="welcome">
			<li>欢迎你，${person.account}！</li>
			<li><a href="../user/index.do">|注销</a></li>
			<li><a href="../user/userinfo.do" >|查看资料</a></li>
		</ul>
		<form name="updateboard" action="${pageContext.request.contextPath}/board/updateboard.do" method="post" >
				<p class="suojin">修改板块名称（4个字）<input type="text"  name="name" value="${oldname}"></p>
				<p class="suojin">板块内容描述（30字以内）<textarea class="description" name="description" ></textarea></p>
				<p><input type="hidden"  name="oldname" value="${oldname}"></p>
				<p class="suojin"><input  id="submit"  type="submit" value="更改板块"/></p>
		</form>
		
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
