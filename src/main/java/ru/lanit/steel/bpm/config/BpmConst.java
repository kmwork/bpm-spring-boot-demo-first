package ru.lanit.steel.bpm.config;

import java.util.HashSet;
import java.util.Set;

public class BpmConst {
    public static String MESSAGE_PARAM_SteelPercentValue = "latin_SteelPercentValue";
    public static String MESSAGE_PARAM_SteelModelName = "latin_SteelModelName";

    public static String MESSAGE_PARAM_SteelPercentValue_FOR_USER = "Процент марки стали";
    public static String MESSAGE_PARAM_SteelModelName_FOR_USER = "Наименование марки стали";

    public static final String PREFIX_TASK_LOG = "[Lanit:TASK] ";
    public static final String MESSAGE_TASK_STEEL_ERROR = "Сорри, но сталь будет не достаточно прочной";
    public static final String MESSAGE_TASK_STEEL_OK = "Будет хорошая сталь";

    public static final String JSON_BPM_ERROR_VALUE = "BPM:Value:ERROR";
    public static final String JSON_ERROR_CODE_SUCCESS_VALUE = "BPM:Value:Success";

    public static final String JSON_STEEL_VALUE_PARAM = "jsonSteelPercentValueBPM";
    public static final String JSON_ERROR_DESC_PARAM = "jsonErrorDescBPM";
    public static final String JSON_ERROR_CODE_PARAM = "jsonErrorCodeBPM";
    public static final String JSON_STEEL_MODEL_PARAM = "jsonSteelModelName";


    public static final Set<String> VALID_STEEL_SET = new HashSet<>();

    static {
        VALID_STEEL_SET.add("A1".toLowerCase());
        VALID_STEEL_SET.add("B21".toLowerCase());
        VALID_STEEL_SET.add("C441".toLowerCase());
    }

}
