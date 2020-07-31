<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
	
	<br/>
	<br/>
	<br/>
<p> Word was: ${game.word}</p>

<div class="button">
		<form class="startGame" action="../games" method="POST">
			<input type="submit" value="Start New Game"/>
		</form>
	</div>
</body>
</html>