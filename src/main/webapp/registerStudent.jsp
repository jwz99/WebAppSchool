<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
<a href = "index.jsp">Wróć do strony głównej</a>
	<form action='RegisterStudent'>
	<div id = 'lewa'>
	<h1>Zarejestruj się</h1>
		<p>Imię</p>
		<p>
			<input type='text' name='imieStudent' id='textBox'>
		</p>
		<p>Nazwisko</p>
		<p>
			<input type='text' name='nazwiskoStudent' id='textBox'>
		</p>
		<p>Login</p>
		<p>
			<input type='text' name='loginStudent' id='textBox'>
		</p>
		<p>Hasło</p>
		<p>
			<input type='password' name='hasloStudent' id='textBox'>
		</p>
		<p>Powtórz hasło</p>
		<p>
			<input type='password' name='powtorzHasloStudent' id='textBox'>
		</p>
		<input type='submit' name='Dodaj' value='Zarejestruj' id = 'button'>
		<p>${error}</p>
		</div>
	</form>
</body>
</html>