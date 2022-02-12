<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div id='lewa'>
		<b id='title'>O nas</b>
		<p>Od lat z powodzeniem pomagamy dzieciom i młodzieży w każdym
			wieku z przedmiotami szkolnymi, poszerzami ich wiedzę oraz zarażamy
			pasją. Udzielamy korepetycji z matematyki, programowania, chemii i
			fizyki. Nasze zajęcia są dostępne dla każdego. Program nauczania jest
			wybierany pod ucznia, przez co możemy zrealizować potrzeby każdeo
			niezależnie od poziomu i stanu wiedzy. Organizujemy tylko zajęcia
			indywidualne. Zajęcia odbywają się od poniedziałku do piątku w
			godzinach od 9 do 18.
		<p>Zapraszamy uczniów podstawówek, gimnazjów i szkół średnich na
			wyjątkowe zajęcia edukacyjne. Oferujemy:</p>
		<p>• indywidualne podejście do każdego ucznia</p>
		<p>• stały kontakt z rodzicem oraz miesięczne raporty z postępów
			ucznia</p>
		<p>• autorskie materiały przygotowane przez specjalistów</p>
		<p>• komfortowe warunki nauki z doświadczonymi i energicznymi
			nauczycielami</p>
	</div>
	<div></div>
	<div id='prawa'>
		<form action='LoginServlet'>
			<div id='error'>${error}</div>
			<div id='logowanie'>
				Login <input type='text' id='textBox' name='login'> Hasło
				<input type='password' id='textBox' name='haslo'>
				<p>
					<input type='submit' value='Zaloguj się' id='button'>
				</p>
			</div>
		</form>
		<div id='rejestracja'>
			<a href='RegisterStudent'>Zarejestruj się</a>
		</div>
	</div>
</body>
</html>