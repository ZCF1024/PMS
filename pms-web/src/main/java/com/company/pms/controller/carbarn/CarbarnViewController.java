package com.company.pms.controller.carbarn;

import com.company.pms.pmsservice.carbarn.CarbarnManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/carbarn")
public class CarbarnViewController {

    private final Logger logger = LoggerFactory.getLogger(CarbarnViewController.class);

    private CarbarnManager carbarnManager;

    @GetMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "Carbar -- 车库");
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @Autowired
    public void setCarbarnManager(CarbarnManager carbarnManager) {
        this.carbarnManager = carbarnManager;
    }
}