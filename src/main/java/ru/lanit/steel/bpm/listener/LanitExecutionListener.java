package ru.lanit.steel.bpm.listener;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import ru.lanit.steel.bpm.context.LanitBpmContext;
import ru.lanit.steel.bpm.context.TimeControlInfoByTask;
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

        LanitBpmContext bpmContext = LanitBpmContext.getInstance();
        String eventName = execution.getEventName();
        switch (eventName) {
            case "start":
                bpmContext.startTimer(taskId);
                d.setBpmWorkStatus("Запущена задача");
                break;

            case "end":
                TimeControlInfoByTask timer = bpmContext.finishTimerForTask(taskId);
                String strDeltaMS = timer == null || timer.getDeltaTimeMS() == TimeControlInfoByTask.INVALID_MS ? "<Нет расчета времени>" : Long.toString(timer.getDeltaTimeMS()) + "(миллисекунд)";
                d.setBpmWorkStatus("Завершена задача, затрачено = " + strDeltaMS);
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
