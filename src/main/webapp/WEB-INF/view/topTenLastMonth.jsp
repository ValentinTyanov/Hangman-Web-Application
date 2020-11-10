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
		<h1>Ranking</h1>
	</div>

<form class="rankingType" method="POST" action="/ranking">
Top 10 All-time <input type="checkbox" name="choice" value="all-time" onchange="this.form.submit()">    
</form>
 
 <div>
		<table class="table">
			<tr>
				<th>Alias</th>
				<th>Score</th> 
			</tr>
			<c:set var="topTenLastMonthMap" value="${rankingService.topTenLastMonth}"/>
			<c:forEach var="entry" items="${topTenLastMonthMap}">
				<tr>
					<td>${entry.key}</td>
					<td class="scoreCell">${entry.value.score}</td>
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