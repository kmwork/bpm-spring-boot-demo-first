package ru.lanit.kostya.controllers;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.kostya.dao.KostyaBpmData;
import ru.lanit.kostya.dao.SessionBpmStatusData;

@Controller
@Slf4j
@RequestMapping("/k2")
public class askBpmPageRest {



    @Autowired
    private ProcessEngine camunda;

    @RequestMapping(value = "/askBpmPagePost", method = RequestMethod.POST)
    public ModelAndView formPost(@ModelAttribute("kostyaBpmData") KostyaBpmData form,
                                 BindingResult result) throws InterruptedException {
        log.info("[Kostya-RestController] kostyaBpmData = " + form);


        camunda.getRuntimeService().createMessageCorrelation("MessageNameKostyaAge01")
                .setVariable("messageVar_KostyaAge", form.getKostyaAge())
                .setVariable("messageVar_NameUser", form.getName())
                .correlate();
        SessionBpmStatusData sessionBpmStatusData = SessionBpmStatusData.getInstance();
        sessionBpmStatusData.doEngineLock();

        ModelAndView mv = new ModelAndView();

        mv.addObject("kostyaStatus", sessionBpmStatusData.getBpmStatusResult());
        mv.setViewName("response_page");
        return mv;
    }

    @RequestMapping(value = "/askBpmPageGet", method = RequestMethod.GET)
    public ModelAndView formGet() throws InterruptedException {
        ModelAndView mv = new ModelAndView();
        KostyaBpmData model = new KostyaBpmData();
        mv.addObject("kostyaBpmData", model);
        mv.setViewName("form_page");
        return mv;
    }


}
