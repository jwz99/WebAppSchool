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
	<form action='RegisterServlet'>
<div id='lewa'>
<h1>Zarejestruj nowego nauczyciela</h1>
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
		<p>Hasło</p>
		<p>
			<input type='password' name='hasloTeacher' id='textBox'>
		</p>
		<p>Powtórz hasło</p>
		<p>
			<input type='password' name='powtorzHasloTeacher' id='textBox'>
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
		<input type='submit' name='Dodaj' id='button' value='Dodaj'>
		<p>${error}</p>
		</div>
	</form>
	
</body>
</html>