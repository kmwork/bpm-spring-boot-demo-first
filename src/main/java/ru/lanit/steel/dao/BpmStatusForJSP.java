package ru.lanit.steel.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.lanit.steel.utils.TypeException;

@AllArgsConstructor
@ToString
public class BpmStatusForJSP {
    @Getter
    private final String messageId = "TimeID" + System.nanoTime();

    @Getter
    @Setter
    private TypeException typeException;
    @Getter
    @Setter
    private String descForUser;
}
