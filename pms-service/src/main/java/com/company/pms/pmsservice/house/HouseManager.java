package com.company.pms.pmsservice.house;

import com.company.pms.pmsbase.service.GenericManager;
import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsrepository.house.domain.HouseLocation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseManager extends GenericManager<House,Long> {

    Page<House> findAll(Integer page, Integer size, House house);

    List<String> getCommunities();

    List<Integer> getBuildingNumbers(String community);

    List<HouseLocation> getHouseLocations(String community, Integer buildingNumber);

    List<String> getHouseTypes();

    Integer deleteOne(Long id);

    Integer getMaxFloorNumber(String community, Integer buildingNumber);

}
