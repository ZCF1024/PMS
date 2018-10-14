package com.company.pms.controller.main;

import com.company.pms.pmsbase.utils.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zcf
 * @Create 2018/7/25 11:45
 * @Desc
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @ResponseBody
    @GetMapping("main")
    public String main() {
        return "Main";
    }

    @GetMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("backup")
    public ModelAndView backup(ModelAndView modelAndView) {
        modelAndView.setViewName("backup");
        Map<String, String[][]> fields= new HashMap<String, String[][]>();
        fields.put("house", FieldUtils.HOUSE_FIELDS);
        fields.put("employee", FieldUtils.EMPLOYEE_FIELDS);
        fields.put("complain", FieldUtils.COMPLAIN_FIELDS);
        fields.put("carbarn", FieldUtils.CARBARN_FIELDS);
        fields.put("device", FieldUtils.DEVICE_FIELDS);
        fields.put("customer", FieldUtils.CUSTOMER_FIELDS);
        fields.put("charge", FieldUtils.CHARGE_FIELDS);
        modelAndView.addObject("fields", fields);
        return modelAndView;
    }

    @GetMapping("dimport")
    public ModelAndView dimport(ModelAndView modelAndView) {
        modelAndView.setViewName("import");
        return modelAndView;
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}