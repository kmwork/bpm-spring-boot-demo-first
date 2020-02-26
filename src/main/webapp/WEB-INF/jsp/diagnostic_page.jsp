<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ланит Демка для BPM Camunda - Диагностика версия сервиса</title>
</head>
<body class="security-app">
<div class="lc-block">
    <h1>**** Диагностика BPM ****</h1>
    <p> *** ID процесса: "${bpmProcessId}"</p>
    <p> *** Имя процесса: "${bpmProcessName}"</p>
    <p/>
    <p> ID сообщения: "${messageId}"</p>
    <p> ID задачи "${bpmTaskId}"</p>
    <p> Имя задачи: "${bpmTaskDesc}"</p>
    <p> *** Статус выполнения: "${bpmWorkStatus}"</p>
    <a href="/lanit-bpm-app/bpmDiagnosticGet">Обновить данные</a>
</div>
</body>
</html>
