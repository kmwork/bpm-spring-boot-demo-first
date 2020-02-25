package ru.lanit.steel.controllers;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.steel.bpm.config.BpmConst;
import ru.lanit.steel.config.AppConst;
import ru.lanit.steel.dao.BpmStatusForJSP;
import ru.lanit.steel.dao.SessionBpmStatusDataQuery;

@Controller
@Slf4j
@RequestMapping(AppConst.APP_REST_PREFIX_URL)
public class askBpmPageRest {


    @Autowired
    private ProcessEngine camunda;

    @RequestMapping(value = "/askBpmPagePost", method = RequestMethod.POST)
    public ModelAndView formPost(@ModelAttribute("steelBpmData") ru.lanit.steel.dao.SteelBpmData form,
                                 BindingResult result) throws InterruptedException {
        log.info("[Kostya-RestController] steelBpmData = " + form);


        camunda.getRuntimeService().createMessageCorrelation("MessageNameSteelPercentValue01")
                .setVariable(BpmConst.MESSAGE_PARAM_SteelPercentValue, form.getSteelPercentValue())
                .setVariable(BpmConst.MESSAGE_PARAM_SteelModelName, form.getSteelModelName())
                .correlate();
        SessionBpmStatusDataQuery query = SessionBpmStatusDataQuery.getInstance();
        BpmStatusForJSP mes = query.readMessage("formPost");
        ModelAndView mv = new ModelAndView();

        mv.addObject("bpmStatus", mes.getDescForUser());
        mv.addObject("timeID", mes.getMessageId());
        mv.addObject("codeError", mes.getTypeException().getCodeError());
        mv.addObject("errorDesc", mes.getTypeException().getDescError());
        mv.addObject("errorTechnicalDesc", mes.getErrorTechnicalDesc());
        mv.setViewName("response_page");
        return mv;
    }

    @RequestMapping(value = "/askBpmPageGet", method = RequestMethod.GET)
    public ModelAndView formGet() throws InterruptedException {
        ModelAndView mv = new ModelAndView();
        ru.lanit.steel.dao.SteelBpmData model = new ru.lanit.steel.dao.SteelBpmData();
        mv.addObject("steelBpmData", model);
        mv.setViewName("form_page");
        return mv;
    }


}
