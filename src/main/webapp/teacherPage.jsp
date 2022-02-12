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
	<div id = 'lewa'>
	<h1>Witaj ${teacher}</h1>
	<b>Twoje dane</b>
	<table>
	<tr>
				<td>Imię</td>
				<td>Nazwisko</td>
				<td>Login</td>
				<td>Przedmiot</td>
			</tr>
			<tr>
				<td>${imie}</td>
				<td>${nazwisko}</td>
				<td>${login}</td>
				<td>${przedmiot}</td>
				<td><a href = 'TeacherServlet'>Edytuj</a>
			</tr>
		</table>
		</div>
</body>
</html>