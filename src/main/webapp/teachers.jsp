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
	<form action = 'TeacherServlet'>
	<div id = 'lewa'>
	<h1>Nauczyciele</h1>
			<table>
				<tr>
					<td>Imię</td>
					<td>Nazwisko</td>
					<td>Login</td>
					<td>Hasło</td>
					<td>Przedmiot</td>
				</tr>
				<c:forEach var="el" items="${l_t}">
					<tr>
						<td>${el.getName()}</td>
						<td>${el.getLastname()}</td>
						<td>${el.getLogin()}</td>
						<td>${el.getPassword()}</td>
						<td>${el.getSubject().getName()}</td>
					</tr>
				</c:forEach>
			</table>
	</form>
	<form action='TeacherServlet'>
	<h1>Edytuj dane nauczycieli</h1>
	<p>Wybierz nauczyciela</p>
		<p>
			<select name='nauczyciel' id='nauczyciel'>
				<c:forEach var="el" items="${l_t}">
					<option>${el.getName()} ${el.getLastname()}</option>
				</c:forEach>
			</select>
		</p>
		<p>Przedmiot</p>
		<p>
			<select name='przedmiot' id='przedmiot'>
				<option></option>
				<c:forEach var="el" items="${ls}">
					<option>${el.name}</option>
				</c:forEach>
			</select>
		</p>
		<p>Imię</p>
		<p>
			<input type='text' name='imieTeacher' id='textBox'>
		</p>
		<p>Nazwisko</p>
		<p>
			<input type='text' name='nazwiskoTeacher' id='textBox'>
		</p>
		<p>Login</p>
		<p>
			<input type='text' name='loginTeacher' id='textBox'>
		</p>
		<input type='submit' name='Zmien' id='button' value='Zmień'>
		<p>${error}</p>
		</div>
	</form>
	<p></p>
	<form action = 'TeacherServlet'>
<div id = 'lewa'>
	<h1>Usuń nauczyciela</h1>
<p>Wybierz nauczyciela</p>
	<select name='nauczyciel' id='nauczyciel'>
		<c:forEach var="el" items="${l_t}">
			<option>${el.name} ${el.lastname}</option>
		</c:forEach>
	</select><input type = 'submit' name='zmien' id='button' value='Usuń'>
	</div>
	</form>
</body>
</html>