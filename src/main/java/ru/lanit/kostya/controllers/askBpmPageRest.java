package ru.lanit.kostya.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.kostya.dao.KostyaBpmData;
import ru.lanit.kostya.dao.StatusData;

@Controller
@Slf4j
@RequestMapping("/k2")
public class askBpmPageRest {

    private StatusData statusData = StatusData.getInstance();

    @RequestMapping(value = "/askBpmPagePost", method = RequestMethod.POST)
    public ModelAndView formPost(@ModelAttribute("kostyaBpmData") KostyaBpmData form,
                                 BindingResult result) {
        log.info("[Kostya-RestController] kostyaBpmData = " + form);
        statusData.getKostyaBpmDataFromUser().setName(form.getName());
        statusData.getKostyaBpmDataFromUser().setName(form.getKostyaAge());
        ModelAndView mv = new ModelAndView();
        mv.addObject("kostyaStatus", "");
        mv.setViewName("response_page");
        statusData.doUserUnlock();
        return mv;
    }

    @RequestMapping(value = "/askBpmPageGet", method = RequestMethod.GET)
    public ModelAndView formGet() throws InterruptedException {
        statusData.doEngineWait();
        ModelAndView mv = new ModelAndView();
        KostyaBpmData model = new KostyaBpmData();
        statusData.setKostyaBpmDataFromUser(model);
        mv.addObject("kostyaBpmData", model);
        mv.setViewName("form_page");
        return mv;
    }


}
