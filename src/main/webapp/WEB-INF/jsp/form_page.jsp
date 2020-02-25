<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Ланит Демо-приложение для BPM Camunda - Запрос ввода данных для сервиса</title>
</head>
<body class="security-app">
<div class="lc-block">
	<%--@elvariable id="steelBpmData" type="ru.lanit.steel.dao.SteelBpmData"--%>
	<form:form method="post" action="/k2/askBpmPagePost"
			   modelAttribute="steelBpmData">
		<h3>Плиз, введите данные марку стали для расчета мат. модели</h3>
		<table>
			<tr>
				<td>Код марки стали:</td>
				<td><form:input type="text" path="steelModelName"/></td>
			</tr>
			<tr>
				<td>Значение в процентах:</td>
				<td><form:input type="text" path="steelPercentValue"/></td>
			</tr>
		</table>
		<p>
			<input type="submit" value="Start"/>
		</p>
	</form:form>


</div>

</body>
</html>