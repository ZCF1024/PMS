package com.company.pms.controller.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/device")
public class DeviceViewController {

    private final Logger logger = LoggerFactory.getLogger(DeviceViewController.class);

    @GetMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "Device -- 设备");
        modelAndView.setViewName("test");
        return modelAndView;
    }

}