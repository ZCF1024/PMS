package com.company.pms.pmsrepository.house.repository;

import com.company.pms.pmsrepository.house.domain.HouseLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/9/19 12:39
 * @Desc
 */
public interface HouseLocationRepository extends JpaRepository<HouseLocation, Long> {

    List<HouseLocation> getAllByCommunityAndBuildingNumber(String community, Integer buildingNumber);

}