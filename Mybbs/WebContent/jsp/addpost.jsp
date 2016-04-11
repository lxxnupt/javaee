<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/addpost.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发帖</title>
</head>
<html>
	<body>
		<div id="head"></div>
		<div id="left">
		<ul>
			<c:set var="boardnumber" value="${fn:length(allboards)}"/>
			<c:forEach var="i" begin="0" end="${boardnumber-1}">
			<li><a href="../post/findpost.do?id=${allboards[i].id}&currentPage=1">${allboards[i].name}</a></li>
			</c:forEach>
						<li><a href="/Mybbs/user/back.do">板块概况</a></li>	
			</ul>
		</div>
	
		<div id="right">
		<ul id ="welcome">
			<li>欢迎你，${person.account}！</li>
				<li><a href="../user/index.do">|注销</a></li>
			<li><a href="../user/userinfo.do" >|查看资料</a></li>
		</ul>

			<c:forEach var="i"  begin="0"  end="${boardnumber-1}">
			<c:if test="${allboards[i].id==id}">
			<p class="description">这里是${allboards[i].name}</p>
			<p class="description">板块简介：${allboards[i].description}</p>
			</c:if>
			</c:forEach>
			
		<form name="fatie" action="${pageContext.request.contextPath}/post/addpost.do" method="post" >
				<p>主题(15字以内)：<input id="title"  type="text"  name="title"/></p>
				<p><textarea class="area" name="content" >（请输入具体内容）</textarea></p>
				<p><input type="hidden" name="bid"  value="${id}"></p>
				<p><input type="hidden" name="pid"  value="${person.id}"></p>
				<p><input  id="submit"  type="submit" value="发帖"/></p>
		</form>
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
