package ru.lanit.steel.bpm.listener;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import ru.lanit.steel.dao.BpmDiagnosticData;
import ru.lanit.steel.dao.BpmDiagnosticQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Slf4j
public class LanitExecutionListener implements ExecutionListener {

    private long startTimeMS = 0;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        long currentTimeMS = System.currentTimeMillis();
        ProcessEngine processEngine = execution.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();

        String taskId = execution.getId();
        BpmDiagnosticData d = new BpmDiagnosticData();
        d.setBpmProcessId(execution.getProcessInstanceId());
        d.setBpmProcessName(processEngine.getName());

        d.setBpmTaskDesc("[ID:  " + execution.getCurrentActivityId() + " ] " + execution.getCurrentActivityName());
        d.setBpmTaskId(execution.getCurrentActivityId());
        DateFormat df = new SimpleDateFormat("HH:mm:ss");

        String eventName = execution.getEventName();
        switch (eventName) {
            case "start":
                startTimeMS = currentTimeMS;
                d.setBpmWorkStatus("Запущена задача");
                break;

            case "end":
                long deltaMS = currentTimeMS - startTimeMS;
                d.setBpmWorkStatus("Завершена задача, затрачено = " + deltaMS + " (миллисекунд)");
                break;

            case "take":
                d.setBpmWorkStatus("В процессе выполнения (взята в работу если это UserTask)");
                break;

            default:
                d.setBpmWorkStatus("Статус не распознан: '" + eventName + "'");
                break;
        }


        BpmDiagnosticQuery.getInstance().addDiagnostic(d);
    }
}
