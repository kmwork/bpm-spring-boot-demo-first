package ru.lanit.kostya.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SessionBpmStatusDataQuery {

    private final static long ID_OBJECT = System.nanoTime();
    private static final String PREFIX_LOG = "[Kostya:LOCKER] ";


    @Getter
    private static final SessionBpmStatusDataQuery instance = new SessionBpmStatusDataQuery();

    private SessionBpmStatusDataQuery() {
        log.info("[KOSTYA]-SessionBpmStatusDataQuery: init");
    }


    @Getter
    private final LinkedBlockingDeque<BpmStatusForJSP> blockingDeque = new LinkedBlockingDeque(10);

    public void addMessage(BpmStatusForJSP val) {
        log.info(PREFIX_LOG + "[++++]: add message to user: val to ID_OBJECT = " + ID_OBJECT);
        blockingDeque.push(val);
    }

    public BpmStatusForJSP readMessage() throws InterruptedException {
        log.info(PREFIX_LOG + "[----]: stating for read message from ID_OBJECT  = " + ID_OBJECT);
        BpmStatusForJSP val = blockingDeque.pollFirst(5, TimeUnit.MINUTES);
        log.info(PREFIX_LOG + "[----]: success for read message: " + val);
        return val;
    }
}
