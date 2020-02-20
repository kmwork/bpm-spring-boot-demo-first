<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Hello World!</title>
</head>
<body class="security-app">

<div class="lc-block">


	<%--@elvariable id="kostyaBpmData" type="ru.lanit.kostya.dao.KostyaBpmData"--%>
	<form:form method="post" action="/k2/askBpmPagePost"
			   modelAttribute="kostyaBpmData">
		<h3>introduce text here</h3>
		<table>
			<tr>
				<td>name:</td>
				<td><form:input type="text" path="name"/></td>
			</tr>
			<tr>
				<td>kostya-age:</td>
				<td><form:input type="text" path="kostyaAge"/></td>
			</tr>
		</table>
		<p>
			<input type="submit" value="Start"/>
		</p>
	</form:form>


</div>

</body>
</html>
