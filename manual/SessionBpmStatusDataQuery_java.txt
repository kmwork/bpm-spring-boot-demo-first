package ru.lanit.kostya.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


public class SessionBpmStatusDataQuery {

    private final static long ID_OBJECT = System.nanoTime();
    private static final String PREFIX_LOG = "[Kostya:LOCKER] ";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static SessionBpmStatusDataQuery getInstance() {
        return instance;
    }

    private static final SessionBpmStatusDataQuery instance = new SessionBpmStatusDataQuery();


    public LinkedBlockingDeque<BpmStatusForJSP> getBlockingDeque() {
        return blockingDeque;
    }

    private final LinkedBlockingDeque<BpmStatusForJSP> blockingDeque = new LinkedBlockingDeque(10);

    public void addMessage(BpmStatusForJSP val) {
        logger.info(PREFIX_LOG + "[++++]: add message to user: val to ID_OBJECT = " + ID_OBJECT);
        blockingDeque.push(val);
    }

    public BpmStatusForJSP readMessage() throws InterruptedException {
        logger.info(PREFIX_LOG + "[----]: stating for read message from ID_OBJECT  = " + ID_OBJECT);
        BpmStatusForJSP val = blockingDeque.pollFirst(5, TimeUnit.MINUTES);
        logger.info(PREFIX_LOG + "[----]: success for read message: " + val);
        return val;
    }
}
