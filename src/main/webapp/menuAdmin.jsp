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
			<a href = 'RegisterServlet'>Stwórz konto nauczyciela</a>|<a href = 'TeacherServlet'>Nauczyciele</a>|<a href = 'StudentServlet'>Uczniowie</a>|<a href = 'SubjectServlet'>Przedmioty</a><input type='submit'
				value='Wyloguj się' name='wylogowanie' id='button'>
		</div>
	</form>
</body>
</html>