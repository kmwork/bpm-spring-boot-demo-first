package ru.lanit.steel.utils;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.lanit.steel.config.AppConst;

/**
 * Главный exception приложения для перекидования наружу
 */
@Slf4j
@ToString
@Getter
public class AppException extends Exception {
    private static final String PREFIX = AppConst.LOG_PREFIX_APP + " [SBER-APP:ERROR] ";
    private final String strArgs;
    private final String msg;
    private final TypeException type;

    public AppException(TypeException type, String msg, String strArgs, Exception ex) {
        super("Type: " + type + ",\n msg=" + msg + "\n strArgs = " + strArgs, ex);
        this.type = type;
        this.msg = msg;
        this.strArgs = strArgs;
    }
}
