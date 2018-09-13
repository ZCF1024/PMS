package com.company.pms.controller.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/customer")
public class CustomerViewController {

    private final Logger logger = LoggerFactory.getLogger(CustomerViewController.class);

    @GetMapping("generator")
    public ModelAndView generator(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "Customer -- 客户");
        modelAndView.setViewName("test");
        return modelAndView;
    }

}