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
	<form action='ServletLesson'>
		<div id='lewa'>
			<h1>Twoje lekcje</h1>
			<table>
				<tr>
					<td>id lekcji</td>
					<td>Data</td>
					<td>Godzina</td>
					<td>Nauczyciel</td>
					<td>Przedmiot</td>
				</tr>
				<c:forEach var="el" items="${ll}">
					<tr>
						<td>${el.getIdl()}</td>
						<td>${el.getDate()}</td>
						<td>${el.getHour()}</td>
						<td>${el.getTeacher().getName()} ${el.getTeacher().getLastname()}</td>
						<td>${el.getTeacher().getSubject().getName()}</td>
					</tr>
				</c:forEach>
			</table>
	</form>
	<form action='ServletLesson'>
		<h1>Usuń lekcje</h1>
		<p>Wybierz id lekcji</p>
		<select name='lekcjaD' id='lekcje'>
			<c:forEach var="el" items="${ll}">
				<option>${el.idl}</option>
			</c:forEach>
		</select><input type='submit' name='zmien' id='button' value='Zatwierdz'>
		</div>
	</form>
</body>
</html>