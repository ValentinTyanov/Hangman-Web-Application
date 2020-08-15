<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Game Over</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/game_end.css">
</head>
<body>

<div class="title">
		<h1> Game Over </h1>
	</div>

<form class="rankingType" method="POST" action="/ranking"> <!-- should send you to /games/ranking  *not sure -->
Top 10 All-time <input type="checkbox" name="choice" value="all-time" onchange="this.form.submit()">
Top 10 Last 30 Days <input type="checkbox" name="choice" value="lastMonth" onchange="this.form.submit()">      
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
			<input type="submit" value="Start New Game"/>
		</form>
	</div>
</body>
</html>