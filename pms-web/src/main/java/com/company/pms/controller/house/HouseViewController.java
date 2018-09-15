package com.company.pms.controller.house;

import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsservice.house.HouseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("view/house")
public class HouseViewController {

    private final Logger logger = LoggerFactory.getLogger(HouseViewController.class);

    @Value("${page_size}")
    private Integer pageSize;

    @Autowired
    private HouseManager houseManager;

    @GetMapping("generator")
    public ModelAndView generator(ModelAndView modelAndView) {
        modelAndView.addObject("entityName", "House -- 房屋");
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @GetMapping("index")
    public ModelAndView index(House house, ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return  modelAndView;
    }

    @GetMapping("house")
    public ModelAndView house(House house, ModelAndView modelAndView){
        house.setState(false);
        modelAndView.addObject("communities", this.houseManager.getCommunities());
        modelAndView.addObject("types", this.houseManager.getHouseTypes());
        modelAndView.addObject("datas", this.houseManager.findAll(0, pageSize, house));
        modelAndView.setViewName("house");
        return  modelAndView;
    }

    @GetMapping("choose")
    public ModelAndView choose(House house, ModelAndView modelAndView){
        house.setState(false);
        modelAndView.addObject("communities", this.houseManager.getCommunities());
        modelAndView.setViewName("choose_house");
        return  modelAndView;
    }

}