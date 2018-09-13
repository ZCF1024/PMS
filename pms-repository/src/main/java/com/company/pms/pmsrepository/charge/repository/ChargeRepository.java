package com.company.pms.pmsrepository.charge.repository;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.charge.domain.Charge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ChargeRepository extends GenericRepository<Charge, Long> {

    List<Charge> findAllByHouseIdAndDeleted(Long houseId, Boolean deleted);

    List<Charge> findAllByDateCreatedBetweenAndDeleted(Date date1, Date date2, Boolean deleted);

    List<Charge> findAllByHouseIdAndDateCreatedBetweenAndDeleted(Long houseId, Date date1, Date date2, Boolean deleted);

    List<Charge> findAllByStateAndDeleted(Boolean state, Boolean deleted);

    @Modifying
    @Query(value = "update charge set deleted=?2 where id=?1", nativeQuery = true)
    void updateDeteled(Long id, Boolean deleted);
}
