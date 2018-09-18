package com.company.pms.controller.house;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsservice.house.HouseManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/house")
public class HouseAPIController extends GenericController<House, Long, HouseManager> {

    private final Logger logger = LoggerFactory.getLogger(HouseAPIController.class);

    private HouseManager houseManager;

    @Value("${page_size}")
    private Integer pageSize;

    @GetMapping("index")
    public String index() {
        return "House Index";
    }

    @PostMapping("gethousebybuilding")
    public List<House> getHouses(String community, Integer buildingNumber){
        return null;
    }

    @PostMapping("building")
    public List<Integer> building(String community){
        return this.houseManager.getBuildingNumbers(community);
    }

    @PostMapping("search/{page}")
    public Page<House> search(@RequestBody House house, @PathVariable Integer page){
        System.out.println(house);
        return this.houseManager.findAll(page, pageSize, house);
    }

    @Autowired
    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
        this.manager = this.houseManager;
    }
}
