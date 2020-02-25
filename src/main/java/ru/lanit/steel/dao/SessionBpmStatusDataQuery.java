package ru.lanit.steel.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SessionBpmStatusDataQuery {

    private static final String PREFIX_LOG = "[Kostya:LOCKER] ";


    @Getter
    private static final SessionBpmStatusDataQuery instance = new SessionBpmStatusDataQuery();

    private SessionBpmStatusDataQuery() {
        log.info("[KOSTYA]-SessionBpmStatusDataQuery: init");
    }


    @Getter
    private final LinkedBlockingDeque<BpmStatusForJSP> blockingDeque = new LinkedBlockingDeque(10);

    public void addMessage(String serviceTaskName, BpmStatusForJSP val) {
        log.info(PREFIX_LOG + "[++++][Task: " + serviceTaskName + "] add message to user: val = " + val);
        blockingDeque.push(val);
    }

    public BpmStatusForJSP readMessage(String serviceTaskName) throws InterruptedException {
        log.info(PREFIX_LOG + "[----][Task: " + serviceTaskName + "] stating for read message");
        BpmStatusForJSP val = blockingDeque.pollLast(5, TimeUnit.MINUTES);
        log.info(PREFIX_LOG + "[----]: success for read message: " + val);
        return val;
    }
}
