package ru.lanit.kostya.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class BpmStatusForJSP {
    @Getter
    private final String messageId = "TimeID" + System.currentTimeMillis();

    @Getter
    @Setter
    private boolean isError;
    @Getter
    @Setter
    private String desc;
}
