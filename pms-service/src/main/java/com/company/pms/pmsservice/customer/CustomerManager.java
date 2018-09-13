package com.company.pms.pmsservice.customer;

import com.company.pms.pmsbase.service.GenericManager;
import com.company.pms.pmsrepository.customer.domain.Customer;

import java.util.List;

public interface CustomerManager extends GenericManager<Customer, Long> {

    /**
     * 通过客户姓名精确查找客户
     * @param name 客户姓名
     * @return
     */
    List<Customer> findAllByName(String name);

    /**
     * 通过用户姓名模糊查找客户
     * @param name 客户姓名
     * @return
     */
    List<Customer> findAllByNameLike(String name);

    /**
     * 通过联系地址查找客户
     * @param address 联系地址
     * @return
     */
    List<Customer> findAllByAddressLike(String address);

    /**
     * 查找某个年龄段的所有客户
     * @param age1 起始年龄
     * @param age2 结束年龄
     * @return
     */
    List<Customer> findAllByAgeBetween(int age1, int age2);

    /**
     * 通过性别查找客户
     * @param gender 性别
     * @return
     */
    List<Customer> findAllByGender(Boolean gender);

    /**
     * 通过联系电话精确查找客户
     * @param phone
     * @return
     */
    List<Customer> findAllByPhone(String phone);

    /**
     * 通过联系电话模糊查询
     * @param phone
     * @return
     */
    List<Customer> findAllByPhone1Like(String phone);

    /**
     * 通过手机号(phone1或phone2)和密码查找用户(用于登录)
     * @param phone 电话号码
     * @param password  密码
     * @return
     */
    List<Customer> findAllByPhoneAndPassword(String phone, String password);

    /**
     * 通过客户名和密码查找用户(用于登录)
     * @param name 客户名
     * @param password 密码
     * @return
     */
    List<Customer> findAllByNameAndPassword(String name, String password);

    /**
     * 删除客户信息
     * @param id 客户ID
     * @param deleted 删除标志
     */
    void updateDeteled(long id, boolean deleted);

}
