package ru.lanit.steel.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class BpmDiagnosticData {
    @Getter
    private final String messageId = "DiagnosticID" + System.nanoTime();

    @Getter
    @Setter
    private String bpmTaskId;

    @Getter
    @Setter
    private String bpmTaskDesc;


    @Getter
    @Setter
    private String bpmProcessId;


    @Getter
    @Setter
    private String bpmProcessName;


    @Getter
    @Setter
    private String bpmWorkStatus;

}
