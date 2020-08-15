<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Menu</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
	<div class="title">
		<h1> Hangman </h1>
	</div>
	
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

<form class="rankingType" method="POST" action="/ranking">
Top 10 All-time <input type="checkbox" name="choice" value="all-time" onchange="this.form.submit()">
Top 10 Last 30 Days <input type="checkbox" name="choice" value="lastMonth" onchange="this.form.submit()">      
</form>
	  
	<div class="bottom">
	<p> Alias: </p> 
		<form:form class="startGame" action="games" modelAttribute="ranking" method="POST">
			<form:input class="textField" maxlength="15" path="alias"/>
			<br>
			<br>
			<input type="submit" value="Start New Game"/>
		</form:form>
	</div>
</body>
</html>