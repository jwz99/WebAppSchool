<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/menuAdmin.jsp" />
	<p></p>
	<form action = 'StudentServlet'>
	<div id = 'lewa'>
	<h1>Uczniowie</h1>
			<table>
				<tr>
					<td>Imię</td>
					<td>Nazwisko</td>
					<td>Login</td>
					<td>Hasło</td>
				</tr>
				<c:forEach var="el" items="${ls}">
					<tr>
						<td>${el.getName()}</td>
						<td>${el.getLastname()}</td>
						<td>${el.getLogin()}</td>
						<td>${el.getPassword()}</td>
					</tr>
				</c:forEach>
			</table>
	</form>
	<form action='StudentServlet'>
	<h1>Edytuj dane uczniów</h1>
		<p>Wybierz ucznia</p>
		<p>
			<select name='uczen' id='uczen'>
				<c:forEach var="el" items="${ls}">
					<option>${el.getName()} ${el.getLastname()}</option>
				</c:forEach>
			</select>
		</p>
		<p>Imię</p>
		<p>
			<input type='text' name='imieStudent' id = 'textBox'>
		</p>
		<p>Nazwisko</p>
		<p>
			<input type='text' name='nazwiskoStudent' id = 'textBox'>
		</p>
		<p>Login</p>
		<p>
			<input type='text' name='loginStudent' id = 'textBox'>
		</p>
		<p>Hasło</p>
		<p>
			<input type='text' name='hasloStudent' id = 'textBox'>
		</p>
		<input type='submit' name='zmien' id='button' value='Zmień'>
		</div>
	</form>
	<p></p>
	<form action = 'StudentServlet'>
	<div id = 'lewa'>
	<h1>Usuń ucznia</h1>
<p>Wybierz ucznia</p>
	<select name='uczen' id='uczen'>
		<c:forEach var="el" items="${ls}">
			<option>${el.name} ${el.lastname}</option>
		</c:forEach>
	</select><input type = 'submit' name='zmien' id='button' value='Usuń'>
	</div>
	</form>
</body>
</html>