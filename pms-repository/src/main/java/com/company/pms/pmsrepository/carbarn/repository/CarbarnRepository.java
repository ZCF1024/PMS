package com.company.pms.pmsrepository.carbarn.repository;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.carbarn.domain.Carbarn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarbarnRepository extends GenericRepository<Carbarn, Long> {

    List<Carbarn> findAllByCustomerIdAndDeleted(Long customerId, Boolean deleted);

    List<Carbarn> findAllByPriceBetweenAndDeleted(Double lowest, Double highest, Boolean deleted);

    List<Carbarn> findAllByAddressLikeAndDeleted(String address, Boolean deleted);

    List<Carbarn> findAllByStateAndDeleted(Boolean state, Boolean deleted);

    @Modifying
    @Query(value = "update carbarn set deleted=?2, state=false where id=?1", nativeQuery = true)
    void updateDeleted(Long id, Boolean deleted);

    @Modifying
    @Query(value = "update carbarn set state=?2 where id=?1", nativeQuery = true)
    void updateState(Long id, Boolean state);
}
