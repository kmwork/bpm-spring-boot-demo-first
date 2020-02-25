<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ланит Демка для BPM Camunda - Ответ севиса</title>
</head>
<body class="security-app">

<div class="lc-block">

    <h1>**** Финищ ****</h1>
    <p> ID сообщенения: "${timeID}"</p>
    <p> Признак ошибки: "${error}"</p>
    <p> Статус исполнения BPM модели: "${steelStatus}"</p>


</div>

</body>
</html>
