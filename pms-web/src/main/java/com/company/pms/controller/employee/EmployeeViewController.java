package com.company.pms.controller.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/employee")
public class EmployeeViewController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeViewController.class);

    @GetMapping("index")
    public ModelAndView generator(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "Employee -- 员工");
        modelAndView.setViewName("test");
        return modelAndView;
    }

}