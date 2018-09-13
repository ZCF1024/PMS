package com.company.pms.pmsservice.charge;

import com.company.pms.pmsbase.service.GenericManager;
import com.company.pms.pmsrepository.charge.domain.Charge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ChargeManager extends GenericManager<Charge, Long> {

    /**
     * 通过房屋ID查找缴费记录
     * @param houseId
     * @return
     */
    List<Charge> findAllByHouseId(long houseId);

    /**
     * 查找某个时间段所有的缴费记录
     * @param date1 起始时间
     * @param date2 结束时间
     * @return
     */
    List<Charge> findAllByDateCreatedBetween(Date date1, Date date2);

    /**
     * 查找某个时间段某栋房屋所有的缴费记录
     * @param houseId 房屋编号
     * @param date1 起始时间
     * @param date2 结束时间
     * @return
     */
    List<Charge> findAllByHouseIdAndDateCreatedBetween(long houseId, Date date1, Date date2);

    /**
     * 查找使用中或空闲的房屋
     * @param state 状态(使用中/空闲)
     * @return
     */
    List<Charge> findAllByState(boolean state);

    /**
     * 删除房屋
     * @param id
     * @param deleted 删除标志
     */
    void updateDeteled(long id, boolean deleted);

}
