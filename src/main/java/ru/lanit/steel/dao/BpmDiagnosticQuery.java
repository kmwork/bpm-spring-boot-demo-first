package ru.lanit.steel.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BpmDiagnosticQuery {

    private static final String PREFIX_LOG = "[Kostya:BpmDiagnosticQuery] ";


    @Getter
    private static final BpmDiagnosticQuery instance = new BpmDiagnosticQuery();

    private BpmDiagnosticQuery() {
        log.info(PREFIX_LOG + ": init");
    }


    @Getter
    private final LinkedBlockingDeque<BpmDiagnosticData> blockingDeque = new LinkedBlockingDeque(1024);

    public void addDiagnostic(BpmDiagnosticData val) {
        log.info(PREFIX_LOG + "[******] add diagnostic to user: val = " + val);
        blockingDeque.push(val);
    }

    public BpmDiagnosticData readDiagnostic() throws InterruptedException {
        log.info(PREFIX_LOG + "[======] stating for read diagnostic");
        BpmDiagnosticData val = blockingDeque.pollFirst(5, TimeUnit.MINUTES);
        log.info(PREFIX_LOG + "[======]: success for read diagnostic: " + val);
        return val;
    }
}