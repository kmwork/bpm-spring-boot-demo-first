package ru.lanit.kostya.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class StatusData {


    private static final String PREFIX_LOG = "[Kostya:LOCKER] ";
    @Setter
    @Getter
    protected String bpmStatusResult;

    @Setter
    @Getter
    protected KostyaBpmData kostyaBpmDataFromUser;
    @Getter
    private static StatusData instance = new StatusData();
    private AtomicBoolean engineLock = new AtomicBoolean(false);
    private AtomicBoolean userLock = new AtomicBoolean(false);

    public void doEngineUnlock() {
        log.info(PREFIX_LOG + "[====]: engine unlock");
        engineLock.set(false);
    }

    public void doUserUnlock() {
        log.info(PREFIX_LOG + "[----]: user unlock");
        userLock.set(false);
    }


    public void doEngineWait() throws InterruptedException {
        engineLock.set(true);
        do {
            log.info(PREFIX_LOG + "[++++]: wait engine....");
            Thread.sleep(1000);
        } while (engineLock.get());

    }


    public void doUserWait() throws InterruptedException {
        userLock.set(true);
        do {
            log.info(PREFIX_LOG + "[++++]: wait user....");
            Thread.sleep(1000);
        } while (userLock.get());

    }
}
