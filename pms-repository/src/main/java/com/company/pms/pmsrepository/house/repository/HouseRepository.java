package com.company.pms.pmsrepository.house.repository;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.house.domain.House;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends GenericRepository<House, Long> {

    @Query(value = "select community from house group by community order by community", nativeQuery = true)
    List<String> getCommunities();

    @Query(value = "select building_number from house where community=?1 group by building_number order by building_number", nativeQuery = true)
    List<Integer> getBuildingNumbers(String community);

    @Query(value = "select floor_number from house where community=?1 and building_number=?2 group by floor_number order by floor_number", nativeQuery = true)
    List<Integer> getFloorNumbers(String community, Integer buildingNumber);

    @Query(value = "select house_type from house group by house_type order by house_type", nativeQuery = true)
    List<String> getHouseTypes();
}
