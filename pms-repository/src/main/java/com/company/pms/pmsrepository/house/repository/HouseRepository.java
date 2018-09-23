package com.company.pms.pmsrepository.house.repository;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.house.domain.House;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HouseRepository extends GenericRepository<House, Long> {

    @Query(value = "SELECT community FROM house GROUP BY community ORDER BY community", nativeQuery = true)
    List<String> getCommunities();

    @Query(value = "SELECT building_number FROM house WHERE community=?1 GROUP BY building_number ORDER BY building_number", nativeQuery = true)
    List<Integer> getBuildingNumbers(String community);

    @Query(value = "SELECT floor_number FROM house WHERE community=?1 AND building_number=?2 GROUP BY floor_number ORDER BY floor_number", nativeQuery = true)
    List<Integer> getFloorNumbers(String community, Integer buildingNumber);

    @Query(value = "SELECT floor_number FROM house WHERE community=?1 AND building_number=?2 AND deleted=0 ORDER BY floor_number DESC LIMIT 1", nativeQuery = true)
    Integer getMaxFloorNumber(String community, Integer buildingNumber);

    @Query(value = "SELECT house_type FROM house GROUP BY house_type ORDER BY house_type", nativeQuery = true)
    List<String> getHouseTypes();

    @Modifying
    @Query(value = "UPDATE house SET deleted=?2 WHERE id=?1", nativeQuery = true)
    Integer updateDeletedById(Long id, Boolean deleted);
}
