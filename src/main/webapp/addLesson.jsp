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
<jsp:include page="/menuStudent.jsp" />
<p></p>
<form action = 'LessonServlet' >
<div id = 'lewa'>
<h1>Zapisz się na lekcje</h1>
	<p>Wybierz przedmiot</p>
		<select name='przedmiot' id='przedmiot'>
			<c:forEach var="el" items="${ls}">
				<option>${el.name}</option>
			</c:forEach>
		</select>
		<input type = 'submit' name='zmien' id='button' value='Zatwierdz'>
	</form>
	<form action = 'LessonServlet'>
	<p>Wybierz nauczyciela</p>
<select name = 'lekcje' id = 'lekcje'>
		<c:forEach var="el" items="${ll}">
			<option>${el.teacher.name} ${el.teacher.lastname} ${el.hour} ${el.date}</option>
		</c:forEach>
	</select>
	<input type = 'submit' name='zmien' id='button' value='Zatwierdz'>
	</div>
	</form>
</body>
</html>