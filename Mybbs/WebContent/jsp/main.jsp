<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import = "bbs.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功后</title>
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
				
		<c:if test="${person.identity=='普通' }">
		<div id="common">
			<p>普通用户界面</p>
			<table>
			<tr>
				<td>板块名称</td>
				<td>版主</td>
				<td>建立时间</td>
			</tr>
			<tr>
				<td>情感天地</td>
				<td>AAA</td>
				<td>15-07-09 18:30</td>
			</tr>
			</table>
			</div>
		</c:if>
		
			<c:if test="${person.identity=='管理' }">
			<div id="guanliyuan">
			<p>管理员界面</p>
			<table>
			<tr>
				<td>板块名称</td>
				<td>版主</td>
				<td>建立时间</td>
				<td colspan="2">编辑</td>
			</tr>
			<tr>
				<td>情感天地</td>
				<td>AAA</td>
				<td>15-07-09 18:30</td>
				<td class="orange"><a href="">设置管理员</a></td>
				<td class="orange"><a href="">修改</a></td>
			</tr>
			</table>
			</div>
			</c:if>
			
			<c:if test="${person.identity=='开发' }">
			<div id="kaifarenyuan">
			<p>开发人员界面</p>
			<table>
			<tr>
				<td>板块名称</td>
				<td>版主</td>
				<td>建立时间</td>
				<td colspan="4">编辑</td>
			</tr>
			<c:if test="${boardnumber!=0}">
			<c:forEach var="i" begin="0" end="${boardnumber-1}">
			<tr>
				<td>${allboards[i].name}</td>
				<td>AAA</td>
				<td>${allboards[i].dateCreated}</td>
				<td class="orange"><a href="javascript:if(confirm('确实要删除该内容吗?'))location='../board/deleteboard.do?id=${allboards[i].id}'">删除</a></td>
				<td class="orange"><a href="../board/toupdateboard.do?oldname=${allboards[i].name}">修改</a></td>
				<td class="orange"><a href="tosetmanager.do?oldname=${allboards[i].name}&bid=${allboards[i].id}">设置管理员</a></td>
				<td class="orange"><a href="todeletemanager.do?oldname=${allboards[i].name}&bid=${allboards[i].id}">取消管理员</a></td>								
			</tr>
			<!--  "../board/deleteboard.do?id=${allboards[i].id}"-->
			</c:forEach>
			</c:if>		
			
			</table>
				<p><a href="../board/toaddboard.do">新增板块</a></p>
			<!--  	<p>$message}</p>-->
			</div>
			</c:if>
			
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
	
