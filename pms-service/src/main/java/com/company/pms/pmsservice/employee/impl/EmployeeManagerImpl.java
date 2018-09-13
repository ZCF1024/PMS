package com.company.pms.pmsservice.employee.impl;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.employee.domain.Employee;
import com.company.pms.pmsrepository.employee.EmployeeRepository;
import com.company.pms.pmsrepository.employee.domain.PositionTypeEnum;
import com.company.pms.pmsservice.employee.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeManagerImpl extends GenericManagerImpl<Employee, Long> implements EmployeeManager {

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllByNameLike(String name) {
        return employeeRepository.findAllByNameLikeAndDeleted("%" + name + "%", false);
    }

    @Override
    public List<Employee> findAllByAgeBetween(Integer age1, Integer age2) {
        return this.employeeRepository.findAllByAgeBetweenAndDeleted(age1, age2, false);
    }

    @Override
    public List<Employee> findAllByGender(Boolean gender) {
        return this.employeeRepository.findAllByGenderAndDeleted(gender, false);
    }

    @Override
    public List<Employee> findAllByPhone(String phone) {
        return this.employeeRepository.findAllByPhoneAndDeleted(phone, false);
    }

    @Override
    public List<Employee> findAllByPosition(PositionTypeEnum position) {
        return this.employeeRepository.findAllByPositionAndDeleted(position, false);
    }

    @Override
    public List<Employee> findAllByNameAndPassword(String name, String password) {
        return this.employeeRepository.findAllByNameAndPasswordAndDeleted(name, password, false);
    }

    @Override
    public List<Employee> findAllByPhoneAndPassword(String phone, String password) {
        return this.employeeRepository.findAllByPhoneAndPasswordAndDeleted(phone, password,false);
    }

    @Override
    @Transactional
    public void updateDeteled(Long id, Boolean deleted) {
        this.employeeRepository.updateDeteled(id, deleted);
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.repository = this.employeeRepository;
    }

}
