package ru.lanit.steel.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@Profile(AppConst.TESTING_DB_H2_PROFILE)
@ConfigurationProperties(prefix = "lanit.global")
//@Import(H2DatabaseInitConfig.class)
@Slf4j
public class LanitSpringConfig implements WebMvcConfigurer {


    @Setter
    @Getter
    protected String appVersion;

    public static LanitSpringConfig getInstance() {
        return instance;
    }

    private static LanitSpringConfig instance;

    @PostConstruct
    protected void init() {
        instance = this;
        log.info(AppConst.LOG_PREFIX_APP + "appVersion = " + appVersion);
    }

}
