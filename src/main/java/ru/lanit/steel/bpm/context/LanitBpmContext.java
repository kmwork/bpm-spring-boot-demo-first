package ru.lanit.steel.bpm.context;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static ru.lanit.steel.config.AppConst.LOG_PREFIX_APP_WARN;

@ToString
@Slf4j
public class LanitBpmContext {

    private static final String PREFIX_LOG = "[LanitBpmContext] ";
    @Getter
    private static final LanitBpmContext instance = new LanitBpmContext();

    private final Map<String, TimeControlInfoByTask> timeByTask = new HashMap();

    private LanitBpmContext() {

    }

    public void startTimer(String taskId) {
        TimeControlInfoByTask timer = new TimeControlInfoByTask(taskId);
        timer.getStartTimeMS();
        if (timeByTask.containsKey(taskId)) {
            log.warn(PREFIX_LOG + LOG_PREFIX_APP_WARN + " Task [ID = " + taskId + "] ранее запускалась");
        }
        timeByTask.put(taskId, timer);
    }

    public TimeControlInfoByTask finishTimerForTask(String taskId) {
        TimeControlInfoByTask timer = timeByTask.get(taskId);
        if (timer == null) {
            log.warn(PREFIX_LOG + LOG_PREFIX_APP_WARN + "Task [ID = " + taskId + "] не запускалась ранее");
        } else
            timer.finishTimerForTask();

        return timer;
    }


}
