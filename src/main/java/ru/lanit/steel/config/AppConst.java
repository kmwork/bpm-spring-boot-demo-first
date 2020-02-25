package ru.lanit.steel.config;

import java.nio.charset.Charset;

/**
 * Главные константы приложения
 */
public interface AppConst {
    public static final String LOG_PREFIX_APP = "[Latin-BPM-APP: Kostya-Demo-V1] ";
    //профили спринга
    public static final String TESTING_DB_H2_PROFILE = "testing_db_h2";
    public static final String PRODUCTION_PROFILE = "production";

    public final static Charset SAFE_CHARSET_UTF8 = Charset.forName("UTF-8");


    public final static String KOSTYA_AGE = "500";

    public final static String APP_REST_PREFIX_URL = "/lanit-bpm-app";


}
