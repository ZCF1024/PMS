package com.company.pms.pmsrepository.house;

import com.company.pms.pmsrepository.house.domain.House;
import com.company.pms.pmsrepository.house.repository.HouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseRepositoryTest {

    private HouseRepository houseRepository;

    @Test
    public void testGetHouseByCommunityAndBuilding(){
        List<House> houses = this.houseRepository.getHouseByCommunityAndBuildingNumberAndDeleted("合胜园小区", 1, false);
        System.out.println("size: =======" + houses.size() + "==========");
        for(House house : houses){
            Integer floor = ((House) house).getFloorNumber();
            System.out.println(floor);
        }
    }

    @Test
    public void testGetCommunities(){
        List<String> list = this.houseRepository.getCommunities();
        System.out.println(list.size() + " " + list.toString());
    }

    @Test
    public void testGetBuildingNumbers(){
        List<Integer> buildings = this.houseRepository.getBuildingNumbers("瑞景河畔小区");
        List<Integer> floors = this.houseRepository.getFloorNumbers("瑞景河畔小区",2);
        List<String> houseTypes = this.houseRepository.getHouseTypes();
        System.out.println(buildings.size() + " " + buildings.toString());
        System.out.println(floors.size() + " " + floors.toString());
        System.out.println(houseTypes.size() + " " + houseTypes.toString());
    }

    @Autowired
    public void setEmployeeRepository(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }
}