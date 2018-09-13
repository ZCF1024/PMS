package com.company.pms.pmsservice.carbarn;

import com.company.pms.pmsbase.service.GenericManager;
import com.company.pms.pmsrepository.carbarn.domain.Carbarn;

import java.util.List;

public interface CarbarnManager extends GenericManager<Carbarn,Long> {

    /**
     * 通过用户ID查找用户名下所有车库
     * @param customerId
     * @return
     */
    List<Carbarn> findAllByCustomerId(long customerId);

    /**
     * 查找某一价格区间的车库
     * @param lowest 最低价格
     * @param highest 最高价格
     * @return
     */
    List<Carbarn> findAllByPriceBetween(double lowest, double highest);

    /**
     * 通过地址查找车库
     * @param address 车库地址
     * @return
     */
    List<Carbarn> findAllByAddressLike(String address);

    /**
     * 查找所有正在使用或空闲的车库
     * @param state 车库状态(使用中/空闲)
     * @return
     */
    List<Carbarn> findAllByState(boolean state);

    /**
     * 删除车库
     * @param id 车库编号
     * @param deleted 删除标志
     */
    void updateDeleted(long id, boolean deleted);

    /**
     * 修改车库状态（被使用，空闲）
     * @param id 车库编号
     * @param state 车库状态
     */
    void updateState(long id, boolean state);

}
