package ru.lanit.steel.controllers;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.steel.config.AppConst;
import ru.lanit.steel.dao.BpmDiagnosticData;
import ru.lanit.steel.dao.BpmDiagnosticQuery;

@Slf4j
@Controller
@RequestMapping(AppConst.APP_REST_PREFIX_URL)
public class BpmDiagnosticRest {
    private static final String PREFIX_LOG = "[Latin:BpmDiagnosticRest] ";
    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping(value = "/bpmDiagnosticGet", method = RequestMethod.GET)
    public ModelAndView formGet(BindingResult result) throws InterruptedException {
        log.info(PREFIX_LOG + "formGet: start");
        ModelAndView mv = new ModelAndView();
        BpmDiagnosticData mes = BpmDiagnosticQuery.getInstance().readDiagnostic();

        mv.addObject("bpmProcessId", mes.getBpmProcessId());
        mv.addObject("bpmProcessName", mes.getBpmProcessName());
        mv.addObject("messageId", mes.getMessageId());
        mv.addObject("bpmTaskId", mes.getBpmTaskId());
        mv.addObject("bpmTaskDesc", mes.getBpmTaskDesc());

        mv.addObject("bpmWorkStatus", mes.getBpmWorkStatus());

        mv.setViewName("diagnostic_page");

        log.info(PREFIX_LOG + "formGet: end");
        return mv;
    }


}
