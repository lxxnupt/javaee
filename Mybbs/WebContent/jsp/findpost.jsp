<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/boardEntrance.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>板块入口</title>
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

			<c:forEach var="i"  begin="0"  end="${boardnumber-1}">
			<c:if test="${allboards[i].id==id}">
			<p class="description">这里是${allboards[i].name}</p>
			<p class="description">板块简介：${allboards[i].description}</p>
			</c:if>
			</c:forEach>

			<table>
			<tr>
				<td>主题</td>
				<td>发帖人</td>
				<td>发帖时间</td>
				<td>点击/回复</td>
				<td>最后回帖人</td>
				<td>最后回帖时间</td>
				<td colspan="2">操作</td>
			</tr>
			
			<c:set var="postnumber" value="${fn:length(posts)}"/>
	  		<c:if test="${postnumber!=0}">
			<c:forEach var="i"  begin="0"  end="${postnumber-1}">
			
			<tr>
				<td><a class="blue" href="addposthit.do?id=${posts[i].id}&currentPage=1">${posts[i].title}</a></td>
				<td>${authors[i].account}</td>
				<td>${posts[i].dateCreated}</td>
				<td>${posts[i].hit}/${posts[i].replayCount}</td>
				<td>${authorslast[i].account }</td>
				<td>${posts[i].dateLastReplied}</td>
				<td class="orange"><a href="javascript:if(confirm('确实要删除该内容吗?'))location='deletepost.do?id=${posts[i].id}&currentPage=${page.currentPage}&postnumber=${postnumber}'">删除</a></td>
				<td class="orange"><a href="">置顶</a></td>
			</tr>
			
			</c:forEach>
			</c:if>
			</table>
			<p id="addpost"><a href="toaddpost.do?id=${id}">我要发帖</a></p>
			
			<p class="fenye">第${page.currentPage }页&nbsp;&nbsp;共${page.pageCount}页</p>
						
			<c:if test="${page.biaozhi=='first'}">
			<p class="fenye"><a href="findpost.do?id=${id}&currentPage=1">第一页</a>&nbsp;&nbsp;
										上一页&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.currentPage+1}">下一页</a>&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>

			<c:if test="${page.biaozhi=='middle'}">
			<p class="fenye"><a href="findpost.do?id=${id}&currentPage=1">第一页</a>&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.currentPage-1}">上一页</a>&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.currentPage+1}">下一页</a>&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>
			
			<c:if test="${page.biaozhi=='last'}">
			<p class="fenye"><a href="findpost.do?id=${id}&currentPage=1">第一页</a>&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.currentPage-1}">上一页</a>&nbsp;&nbsp;
										下一页&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>
			
			<c:if test="${page.biaozhi=='onlyone'}">
			<p class="fenye"><a href="findpost.do?id=${id}&currentPage=1">第一页&nbsp;&nbsp;</a>
										上一页&nbsp;&nbsp;
										下一页&nbsp;&nbsp;
										<a href="findpost.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>			
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
	
