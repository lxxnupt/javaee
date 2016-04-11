<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic.css" />
<link rel="stylesheet" type="text/css" href="../css/findreplay.css" />
<link rel="stylesheet" type="text/css" href="../css/welcome.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回帖</title>
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
			<p class="suojin">帖子主题：${louzhu.title}</p>
			<table  border="1">
			
			<c:if test="${page.currentPage==1}">
					<tr>
					<td width="60">
					<p>${louzhu.account}</p>
					<p>楼主</p>
					<p>${louzhu.date}</p>
					</td>
					<td width="500">${louzhu.louzhucontent}</td>
					</tr>
			</c:if>
			<c:set var="replaynumber" value="${fn:length(replays)}"/>
	  		<c:if test="${replaynumber!=0}">
			<c:forEach var="i"  begin="0"  end="${replaynumber-1}">
					<tr>
						<td width="60">
						<p>${authors[i].account}</p>
						<p>${replays[i].floor }楼</p>
						<p>${replays[i].dateCreated}</p>
						<p><a href="javascript:if(confirm('确实要删除该内容吗?'))location='deletereplay.do?replayid=${replays[i].id}&currentPage=${page.currentPage}&replaynumber=${replaynumber}&postid=${id}'">删除</a></p>
						</td>
						<td width="500">${replays[i].content}</td>
					</tr>
			</c:forEach>
			</c:if>
			</table>
		<form name="huitie" action="addreplay.do"  method="post">
				<p class="suojin"><textarea class="area" name="content" >（请输入具体内容）</textarea></p>
				<input type="hidden"  name="postid"  value="${louzhu.postid}">
				<input type="hidden"  name="authorid"  value="${person.id}">
				<input type="hidden"  name="currentPage"  value="${page.pageCount}">
				<input type="hidden"  name="replaynumber"  value="${replaynumber}">
				<p class="suojin"><input  id="submit"  type="submit" value="回帖"/></p>
		</form>
					<p class="fenye">第${page.currentPage }页&nbsp;&nbsp;共${page.pageCount}页</p>
						
			<c:if test="${page.biaozhi=='first'}">
			<p class="fenye"><a href="findreplay.do?id=${id}&currentPage=1">第一页</a>&nbsp;&nbsp;
										上一页&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.currentPage+1}">下一页</a>&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>

			<c:if test="${page.biaozhi=='middle'}">
			<p class="fenye"><a href="findreplay.do?id=${id}&currentPage=1">第一页</a>&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.currentPage-1}">上一页</a>&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.currentPage+1}">下一页</a>&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>
			
			<c:if test="${page.biaozhi=='last'}">
			<p class="fenye"><a href="findreplay.do?id=${id}&currentPage=1">第一页</a>&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.currentPage-1}">上一页</a>&nbsp;&nbsp;
										下一页&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>
			
			<c:if test="${page.biaozhi=='onlyone'}">
			<p class="fenye"><a href="findreplay.do?id=${id}&currentPage=1">第一页&nbsp;&nbsp;</a>
										上一页&nbsp;&nbsp;
										下一页&nbsp;&nbsp;
										<a href="findreplay.do?id=${id}&currentPage=${page.pageCount}">最后一页</a></p>
			</c:if>			
		</div>
		
		<div id="copyright">
		<p>版权所有，仅供学习</p>
		<p>陆徐行  2015.07 南京</p>
		</div>
	</body>
</html>
