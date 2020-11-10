<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Ranking</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/ranking.css">
</head>
<body>
	<div class="title">
		<h1> Ranking </h1>
	</div>

<form class="rankingType" method="POST" action="/ranking">
Top 10 Last 30 Days <input type="checkbox" name="choice" value="lastMonth" onchange="this.form.submit()"/>      
</form>

<div>
		<table class="table">
			<tr>
				<th>Alias</th>
				<th>Score</th> 
			</tr>
			<c:set var="topTenRankings" value="${rankingService.topTen}"/>
			<c:forEach var="ranking" items="${topTenRankings}">
				<tr>
					<td>${ranking.alias}</td>
					<td class="scoreCell">${ranking.highScore}</td>
				</tr>
			</c:forEach>
		</table>
</div>

<div class="bottom">
		<form class="startGame" action="/">
			<input type="submit" value="Home"/>
		</form>
</div>
</body>
</html>