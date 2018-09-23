package com.company.pms.pmsrepository.house;

import com.company.pms.pmsrepository.house.domain.HouseLocation;
import com.company.pms.pmsrepository.house.repository.HouseLocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/9/19 12:45
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseLocationRepositoryTest {

    @Autowired
    private HouseLocationRepository locationRepository;

    @Test
    public void getLocations(){
        List<HouseLocation> locations = this.locationRepository.getAllByCommunityAndBuildingNumber("合胜园小区", 1);
        System.out.println("===============" + locations.size() + "===================");
    }

    public void setLocationRepository(HouseLocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
}