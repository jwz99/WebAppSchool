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
	<form action='StudentServlet'>
	<div id = 'lewa'>
	<h1>Edytuj swoje dane</h1>
		<p>Imię</p>
		<p>
			<input type='text' name='imieStudent' id='textBox'>
		</p>
		<p>Nazwisko</p>
		<p>
			<input type='text' name='nazwiskoStudent' id='textBox'>
		</p>
		<p>Login</p>
		<p>
			<input type='text' name='loginStudent' id='textBox'>
		</p>
		<input type='submit' name='Dodaj' id='button' value='Edytuj'>
		<p>${error}</p>
		</div>
	</form>
</body>
</html>