<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<form action='LogoutServlet'>
		<div id='menu'>
			<a href='ServletLesson'>Lekcje</a> | <a href='StudentServlet'>Moi Uczniowie</a>
			| <a href='LoginServlet'>Moje Dane</a><input type='submit'
				value='Wyloguj się' name='wylogowanie' id='button'>
		</div>
	</form>
</body>
</html>