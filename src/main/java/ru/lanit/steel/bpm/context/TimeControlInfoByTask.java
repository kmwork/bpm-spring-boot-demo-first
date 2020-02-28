package ru.lanit.steel.bpm.context;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static ru.lanit.steel.config.AppConst.LOG_PREFIX_APP_WARN;

@Slf4j
public class TimeControlInfoByTask {
    private static final String PREFIX_LOG = "[TimeControlInfoByTask] ";
    public final static long INVALID_MS = -1;
    @Getter
    private long startTimeMS = INVALID_MS;

    @Getter
    private long deltaTimeMS = INVALID_MS;

    private final String taskId;

    public TimeControlInfoByTask(String taskId) {
        this.taskId = taskId;
    }

    public void startTimerForTask() {
        startTimeMS = System.currentTimeMillis();
        deltaTimeMS = INVALID_MS;
    }

    public void finishTimerForTask() {
        if (startTimeMS != INVALID_MS)
            deltaTimeMS = System.currentTimeMillis() - startTimeMS;
        else {
            deltaTimeMS = INVALID_MS;
            log.warn(PREFIX_LOG + LOG_PREFIX_APP_WARN + "Не было старта по задаче taskId = " + taskId);
        }
    }
}
