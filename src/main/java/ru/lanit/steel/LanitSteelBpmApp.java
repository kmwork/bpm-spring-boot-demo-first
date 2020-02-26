package ru.lanit.steel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.camunda.bpm.application.ProcessApplicationInterface;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.lanit.steel.beans.Showcase;
import ru.lanit.steel.config.AppConst;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
@EnableProcessApplication("lanitSteelBpmApp")
public class LanitSteelBpmApp implements CommandLineRunner {

    boolean processApplicationStopped;

    public static void main(final String... args) throws Exception {

        System.setProperty("spring.profiles.active", AppConst.TESTING_DB_H2_PROFILE);
        System.setProperty("java.awt.headless", "false");
        System.setProperty("java.awt.headless", "false");
        System.setProperty("file.encoding", "UTF8");
        SpringApplication.run(LanitSteelBpmApp.class, args);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobExecutor jobExecutor;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private Showcase showcase;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ProcessApplicationInterface application;


    @Value("${org.camunda.bpm.spring.boot.starter.example.simple.SimpleApplication.exitWhenFinished:true}")
    private boolean exitWhenFinished;

    @PostConstruct
    public void init() {
        logger.info("[LANIT:APP] init");
    }

    @EventListener
    public void onPostDeploy(PostDeployEvent event) {
        logger.info("postDeploy: {}", event);
    }

    @EventListener
    public void onPreUndeploy(PreUndeployEvent event) {
        logger.info("preUndeploy: {}", event);
        processApplicationStopped = true;
    }


    @Scheduled(fixedDelay = 1500L)
    public void exitApplicationWhenProcessIsFinished() {
        Assert.isTrue(!((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration()).isDbMetricsReporterActivate(),
                "Metrics reporter should be deactivated");

        String processInstanceId = showcase.getProcessInstanceId();

        if (processInstanceId == null) {
            logger.info("processInstance not yet started!");
            return;
        }

//    if (isProcessInstanceFinished()) {
//      logger.info("processinstance ended!");
//
//      if (exitWhenFinished) {
//        jobExecutor.shutdown();
//        SpringApplication.exit(context, () -> 0);
//      }
//      return;
//    }
        //logger.info("processInstance not yet ended!");
    }

    public boolean isProcessInstanceFinished() {
        final HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(showcase.getProcessInstanceId()).singleResult();

        return historicProcessInstance != null && historicProcessInstance.getEndTime() != null;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("CommandLineRunner#run() - {}", ToStringBuilder.reflectionToString(application, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
