<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>取消管理员</title>
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
			<li><a href="index.do">|注销</a></li>
			<li><a href="userinfo.do" >|查看资料</a></li>
		</ul>
			<div id="kaifa">
			${bid}
			<p>下面的是${oldname }板块的管理员</p>
			<table>
			<tr>
				<td>账号</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:set var="managersnumber" value="${fn:length(managers)}"/>
			<c:if test="${managersnumber!=0}">
			<c:forEach var="i"  begin="0"  end="${managersnumber-1}">
			<tr>
				<td>${managers[i].account}</td>
				<td>管理员</td>
				<td class="orange"><a href="javascript:if(confirm('确实要取消这个人管理员的身份吗?'))location='../bp/deletemanager.do?bid=${bid}&pid=${managers[i].id}&oldname=${oldname }'">取消管理员</a></td>
			</tr>
			</c:forEach>
			</c:if>
			</table>
			<a href="back.do">返回</a>
			</div>
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
