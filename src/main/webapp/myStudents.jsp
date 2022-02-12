<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Uczniowie</title>
</head>
<body>
	<jsp:include page="/menuTeacher.jsp" />
	<p></p>
	<form action='StudentServlet'>
		<div id='lewa'>
			<h1>Twoi uczniowie</h1>
			<table>
				<tr>
					<td>Imię</td>
					<td>Nazwisko</td>
				</tr>
				<c:forEach var="el" items="${ls}">
					<tr>
						<td>${el.getName()}</td>
						<td>${el.getLastname()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>