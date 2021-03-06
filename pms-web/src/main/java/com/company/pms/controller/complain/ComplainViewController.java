package com.company.pms.controller.complain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/complain")
public class ComplainViewController {

    private final Logger logger = LoggerFactory.getLogger(ComplainViewController.class);

    @GetMapping("generator")
    public ModelAndView generator(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "Complain -- 投诉");
        modelAndView.setViewName("test");
        return modelAndView;
    }

}