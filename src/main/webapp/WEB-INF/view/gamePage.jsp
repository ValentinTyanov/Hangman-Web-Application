<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/gamePage.css">
<title>Hangman</title>
</head>
<body>
	<div class="title">
		<p class="gameTitle"> Hangman </p>
	</div>
			<br/>
			<br/>
		<p> Guess the word! </p>
			<br/>
		<div class="hiddenWord"> ${game.wordInProgress} </div>
		<br/>
		<br/>
		<p>Attempts left: ${game.attemptsLeft}</p>
		<br/>
		<div class="cheat">
			<form class="cheatButton" method="GET" action="../login/${game.id}">	
			<c:set var="revealButton" scope="request" value="${game.wordReveal}"/>		
				<button type="submit" class="btn btn-success">${revealButton ? game.word : 'Reveal Word'}</button>
			</form>
		</div>

		<div class="holder">
			<form class="buttons" method="POST">
				<c:set var="alphabet" value="${game.unusedLetters}"/>
				<c:forEach var="unusedLetter" items="${alphabet}">
					<input type="submit" name="letter" value="${unusedLetter.letter}"/>
				</c:forEach>
			</form>
		</div>
</body>
</html>