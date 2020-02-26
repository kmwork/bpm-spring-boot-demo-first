package ru.lanit.steel.bpm.listener;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import ru.lanit.steel.dao.BpmDiagnosticData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Slf4j
public class TaskAssignmentListener implements TaskListener {

    public void notify(DelegateTask t) {
        ProcessEngine processEngine = t.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();

        String taskId = t.getId();
        BpmDiagnosticData d = new BpmDiagnosticData();
        d.setBpmProcessId(t.getProcessInstanceId());
        d.setBpmProcessName(processEngine.getName());
        d.setBpmTaskDesc("[ " + t.getName() + " ] " + t.getDescription());
        d.setBpmTaskId(t.getId());
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        d.setBpmWorkStatus("Запущен во время " + df.format(t.getCreateTime()));


    }
}
