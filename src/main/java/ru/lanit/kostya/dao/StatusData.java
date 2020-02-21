package ru.lanit.kostya.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class StatusData {


    private static final String PREFIX_LOG = "[Kostya:LOCKER] ";
    @Setter
    @Getter
    protected String bpmStatusResult;

    @Getter
    private static StatusData instance = new StatusData();
    private ReentrantLock engineLock = new ReentrantLock();

    public void doEngineUnlock() {
        log.info(PREFIX_LOG + "[====]: engine unlock");
        engineLock.unlock();
    }

    public void doEngineLock() throws InterruptedException {
        log.info(PREFIX_LOG + "[++++]: engine lock");
        engineLock.lock();

    }
}
