<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ланит Демка для BPM Camunda - Ответ сервиса</title>
</head>
<body class="security-app">
<div class="lc-block">
    <h1>**** Финиш ****</h1>
    <p> ID сообщенения: "${timeID}"</p>
    <p> Код ошибки: "${codeError}" (если 0, тогда все ок)</p>
    <p> Описание ошибки: "${errorDesc}"</p>
    <p> Техническое описание ошибки: "${errorTechnicalDesc}"</p>
    <p> Статус исполнения BPM модели: "${bpmStatus}"</p>
    <a href="/lanit-bpm-app/askBpmPageGet">В случае ошибки повторите ввод данных</a>
</div>
</body>
</html>
