package com.company.pms.pmsrepository.device.repository;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.device.domain.Device;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends GenericRepository<Device, Long> {

    @Modifying
    @Query(value = "update device set deleted=?2 where id=?1", nativeQuery = true)
    void updateDeteled(Long id, Boolean deleted);

}
