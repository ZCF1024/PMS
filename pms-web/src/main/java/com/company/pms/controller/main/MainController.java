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
import java.util.List;
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

    @GetMapping("export")
    public ModelAndView mexport(ModelAndView modelAndView) {
        modelAndView.setViewName("export");
        Map<String, List<String>> fields= new HashMap<>();
        fields.put("house", FieldUtils.getHouseFieldNames());
        fields.put("employee", FieldUtils.getEmployeeFieldNames());
        fields.put("complain", FieldUtils.getComplainFieldNames());
        fields.put("carbarn", FieldUtils.getCarbarnFieldNames());
        fields.put("device", FieldUtils.getDeviceFieldNames());
        fields.put("customer", FieldUtils.getCustomerFieldNames());
        fields.put("charge", FieldUtils.getChargeFieldNames());
        modelAndView.addObject("fields", fields);
        return modelAndView;
    }

    @GetMapping("import")
    public ModelAndView dimport(ModelAndView modelAndView) {
        modelAndView.setViewName("import");
        return modelAndView;
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}