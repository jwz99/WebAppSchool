<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/menuTeacher.jsp" />
	<p></p>
	<form action='TeacherServlet'>
	<div id = 'lewa'>
	<h1>Edytuj swoje dane</h1>
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
			<input type='text' name='imieTeacher' id='imie'>
		</p>
		<p>Nazwisko</p>
		<p>
			<input type='text' name='nazwiskoTeacher' id='nazwisko'>
		</p>
		<p>Login</p>
		<p>
			<input type='text' name='loginTeacher' id='login'>
		</p>
		<input type='submit' name='Zmien' id='button' value='Zmień'>
		<p>${error}</p>
		</div>
	</form>
</body>
</html>