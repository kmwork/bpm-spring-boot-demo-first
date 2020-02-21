package ru.lanit.kostya.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SessionBpmStatusData {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PREFIX_LOG = "[Kostya:LOCKER] ";

    private static AtomicInteger counter = new AtomicInteger(0);

    public String getBpmStatusResult() {
        return bpmStatusResult;
    }

    public void setBpmStatusResult(String bpmStatusResult) {
        this.bpmStatusResult = bpmStatusResult;
    }

    private volatile String bpmStatusResult = "instance =  " + counter.get();

    public static SessionBpmStatusData getInstance() {
        return instance;
    }

    private static final SessionBpmStatusData instance = new SessionBpmStatusData();

    private SessionBpmStatusData() {
        int c = counter.addAndGet(1);
        if (c != 1)
            logger.error("[Kostya-error!!!!!] count = " + c);
    }

    private ReentrantLock engineLock = new ReentrantLock();

    public void doEngineUnlock() {
        logger.info(PREFIX_LOG + "[====]: engine unlock");
        engineLock.unlock();
    }

    public void doEngineLock() throws InterruptedException {
        logger.info(PREFIX_LOG + "[++++]: engine lock");
        engineLock.lock();

    }
}
