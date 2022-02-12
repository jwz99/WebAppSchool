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
<form action = 'SubjectServlet'>
<div id = 'lewa'>
<h1>Dodaj przedmiot</h1>
<p>Wpisz nazwę przedmiotu</p>
<input type = 'text' name = 'przedmiot' id = 'textBox'>
<p><input type = 'submit' value = 'Dodaj' id = 'button'></p>
</form>
<p></p>
<form action = 'SubjectServlet'>
<h1>Usuń przedmiot</h1>
<p>Wybierz przedmiot</p>
	<select name='przedmiotD' id='przedmiot'>
		<c:forEach var="el" items="${ls}">
			<option>${el.name}</option>
		</c:forEach>
	</select><input type = 'submit' name='zmien' id='button' value='Usuń'>
	</div>
	</form>
</body>
</html>