package ru.lanit.steel.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@Slf4j
public class ValueParser {
    private static final String PREFIX_LOG = "[Latin:UserParser] ";

    public static int parseInt(String strValue, String userNameField) throws AppException {
        log.debug(PREFIX_LOG + ": parse as Int for Field [" + userNameField + "] = " + strValue);
        String args = userNameField + strValue;
        if (StringUtils.isEmpty(strValue)) {
            throw new AppException(TypeException.INVALID_USER_INPUT_DATA, "пустое значение", args, null);
        }

        try {
            int value = Integer.parseInt(strValue.toString().trim());
            log.debug(PREFIX_LOG + ": success parsing: [" + userNameField + "] = " + value);
            return value;
        } catch (NumberFormatException ex) {
            throw new AppException(TypeException.INVALID_USER_INPUT_DATA, "не верное целое число", args, null);
        }

    }

    public static void checkValidValue(String strValue, String userNameField, Set<String> validValues) throws AppException {
        log.debug(PREFIX_LOG + ": validation  as Field [" + userNameField + "] = " + strValue);
        String args = userNameField + strValue;
        if (StringUtils.isEmpty(strValue)) {
            throw new AppException(TypeException.INVALID_USER_INPUT_DATA, "пустое значение", args, null);
        }

        String key = strValue.trim().toUpperCase();
        if (validValues.contains(key))
            log.debug(PREFIX_LOG + ": success validation  : [" + strValue + "] in " + validValues.toString());
        else
            throw new AppException(TypeException.INVALID_USER_INPUT_DATA, "знание не попадает в разрешенные значения: " + validValues.toString(), args, null);
    }
}
