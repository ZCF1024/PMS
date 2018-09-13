package com.company.pms.pmsservice.employee;

import com.company.pms.pmsbase.service.GenericManager;
import com.company.pms.pmsrepository.employee.domain.Employee;
import com.company.pms.pmsrepository.employee.domain.PositionTypeEnum;

import java.util.List;

public interface EmployeeManager extends GenericManager<Employee, Long> {

    /**
     * 通过员工姓名查找员工
     * @param name 用工姓名
     * @return
     */
    List<Employee> findAllByNameLike(String name);

    /**
     * 查找某个年龄区间所有的员工
     * @param age1 起始年龄
     * @param age2 结束年龄
     * @return
     */
    List<Employee> findAllByAgeBetween(Integer age1, Integer age2);

    /**
     * 通过性别查找员工
     * @param gender 性别
     * @return
     */
    List<Employee> findAllByGender(Boolean gender);

    /**
     * 通过联系电话查找员工
     * @param phone 联系电话
     * @return
     */
    List<Employee> findAllByPhone(String phone);

    /**
     * 通过职位查找员工
     * @param position 职位
     * @return
     */
    List<Employee> findAllByPosition(PositionTypeEnum position);

    /**
     * 通过员工姓名和密码查找员工
     * @param name 员工姓名
     * @param password 密码
     * @return
     */
    List<Employee> findAllByNameAndPassword(String name, String password);

    /**
     * 通过联系电话和密码查找员工
     * @param phone 联系电话
     * @param password 密码
     * @return
     */
    List<Employee> findAllByPhoneAndPassword(String phone, String password);

    /**
     * 删除员工信息
     * @param id 员工ID
     * @param deleted 删除标志
     */
    void updateDeteled(Long id, Boolean deleted);
}
