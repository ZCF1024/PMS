package com.company.pms.pmsrepository.employee;

import com.company.pms.pmsrepository.employee.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @Test
    public void testSave(){
        List<Employee> list = this.employeeRepository.findAll();
        System.out.println(list.size());
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}