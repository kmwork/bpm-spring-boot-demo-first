# bpm-spring-boot-demo
Пробная версия на Камунда BPM

# технологии
Camunda BPM Engine
Spring Boot (Core + MVC)
JSP

# кратное описание
### модель процесса тут
    <this project >/src/main/resources/bpmn/process_json_k2.bpmn
# модель сама запускается так задумано  

## как пользоваться
### ввод формы (это REST-GET-запрос на сервис REST класса AskBpmPageRest [package ru.lanit.steel.controllers] )
    `http://localhost:8080/lanit-bpm-app/askBpmPageGet`
    
### форма диагностики (это REST- BpmDiagnosticRest [package ru.lanit.steel.controllers])
    'http://localhost:8080/lanit-bpm-app/bpmDiagnosticGet'    


    