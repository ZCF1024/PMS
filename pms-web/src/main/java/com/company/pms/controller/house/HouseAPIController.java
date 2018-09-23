package com.company.pms.controller.house;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsrepository.house.domain.HouseLocation;
import com.company.pms.pmsservice.house.HouseManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> getHouses(String community, Integer buildingNumber){
        Map<String, Object> result = new HashMap<>();
        result.put("max", this.houseManager.getMaxFloorNumber(community, buildingNumber));
        result.put("house", this.houseManager.getHouseLocations(community, buildingNumber));
        return result;
    }

    @PostMapping("building")
    public List<Integer> building(String community){
        return this.houseManager.getBuildingNumbers(community);
    }

    @PostMapping("search/{page}")
    public Page<House> search(@RequestBody House house, @PathVariable Integer page){
        return this.houseManager.findAll(page, pageSize, house);
    }

    @PostMapping("delete")
    public Integer updateDelete(Long id) throws IOException {
        return this.houseManager.deleteOne(id);
    }

    @Autowired
    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
        this.manager = this.houseManager;
    }
}
