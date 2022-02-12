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
	<jsp:include page="/menuStudent.jsp" />
	<p></p>
	<div id = 'lewa'>
	<h1>Witaj ${student}</h1>
	<p><b>Twoje dane</b></p>
	<table>
	<tr>
				<td>Imię</td>
				<td>Nazwisko</td>
				<td>Login</td>
			</tr>
			<tr>
				<td>${imie}</td>
				<td>${nazwisko}</td>
				<td>${login}</td>
				<td><a href = 'StudentServlet'>Edytuj</a>
			</tr>
		</table>
		</div>
</body>
</html>