package ru.lanit.steel.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RuntimeService;
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
import ru.lanit.steel.ui.UserConst;

@Controller
@Slf4j
@RequestMapping(AppConst.APP_REST_PREFIX_URL)
public class AskBpmPageRest {

    private static final String PREFIX_LOG = "[Latin:AskBpmPageRest] ";

    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping(value = "/askBpmPagePost", method = RequestMethod.POST)
    public ModelAndView formPost(@ModelAttribute("steelBpmData") ru.lanit.steel.dao.SteelBpmData form,
                                 BindingResult result) throws InterruptedException {
        log.info(PREFIX_LOG + "formPost: steelBpmData = " + form);


        String model = form.getSteelModelName();
        String percent = form.getSteelPercentValue();


        model = StringUtils.isEmpty(model) ? UserConst.USER_EMPTY_VALUE : model.toUpperCase().trim();
        percent = StringUtils.isEmpty(percent) ? UserConst.USER_EMPTY_VALUE : percent.toUpperCase().trim();


        runtimeService.createMessageCorrelation("MessageNameSteelPercentValue01")
                .setVariable(BpmConst.MESSAGE_PARAM_SteelPercentValue, percent)
                .setVariable(BpmConst.MESSAGE_PARAM_SteelModelName, model)
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
        log.info(PREFIX_LOG + "formPost: end");
        return mv;
    }

    @RequestMapping(value = "/askBpmPageGet", method = RequestMethod.GET)
    public ModelAndView formGet() throws InterruptedException {
        log.info(PREFIX_LOG + "formGet");
        ModelAndView mv = new ModelAndView();
        ru.lanit.steel.dao.SteelBpmData model = new ru.lanit.steel.dao.SteelBpmData();
        mv.addObject("steelBpmData", model);
        mv.setViewName("form_page");
        log.info(PREFIX_LOG + "formPost: end");
        return mv;
    }


}
