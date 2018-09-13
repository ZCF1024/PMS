package com.company.pms.controller.charge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/charge")
public class ChargeViewController {

    private final Logger logger = LoggerFactory.getLogger(ChargeViewController.class);

    @GetMapping("generator")
    public ModelAndView generator(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "Charge -- 收费");
        modelAndView.setViewName("test");
        return modelAndView;
    }

}