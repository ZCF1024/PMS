package com.company.pms.pmsservice.employee;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsbase.utils.UpdateUtils;
import com.company.pms.pmsrepository.employee.domain.Employee;
import com.company.pms.pmsrepository.employee.domain.PositionTypeEnum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author zcf
 * @Create 2018/7/29 16:33
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeManager employeeManager;

    @Test
    public void testFindAllByNameLike() {
        List<Employee> list = this.employeeManager.findAllByNameLike("name_1");
        for (Employee employee : list) {
            System.out.println("name: " + employee.getName() + " phone: " + employee.getPhone());
        }
    }

    @Test
    public void testFindAllByAgeBetween() {
        List<Employee> list = this.employeeManager.findAllByAgeBetween(10, 20);
        for (Employee employee : list) {
            System.out.println("name: " + employee.getName() + " age: " + employee.getAge());
        }
    }

    @Test
    public void testFindAllByGender() {
        List<Employee> list = this.employeeManager.findAllByGender(true);
        for (Employee employee : list) {
            System.out.println("name: " + employee.getName() + " gender: " + employee.getGender());
        }
    }

    @Test
    public void testFindAllByPhone() {
        List<Employee> list = this.employeeManager.findAllByPhone("13849063916");
        for (Employee employee : list) {
            System.out.println("name: " + employee.getName() + " phone: " + employee.getPhone());
        }
    }

    @Test
    public void testFindAllByPosition() {
        List<Employee> list = this.employeeManager.findAllByPosition(PositionTypeEnum.CEO);
        for (Employee employee : list) {
            System.out.println("name: " + employee.getName() + " position: " + employee.getPosition());
        }
    }

    @Test
    public void testFindAllByNameAndPassword() {
        List<Employee> list = this.employeeManager.findAllByNameAndPassword("name_1", "password_1");
        for (Employee employee : list) {
            System.out.println("name: " + employee.getName() + " password: " + employee.getPassword());
        }
    }

    @Test
    public void testFindAllByPhoneAndPassword() {
        List<Employee> list = this.employeeManager.findAllByPhoneAndPassword("13849063901", "password_1");
        for (Employee employee : list) {
            System.out.println("phone: " + employee.getPhone() + " password: " + employee.getPassword());
        }
    }

    @Test
    public void testUpdateDeteled() {
        this.employeeManager.updateDeteled((long) 1, true);
    }

    @Test
    public void generator() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setName("name_" + i);
            employee.setAge(i);
            employee.setGender(i % 2 == 0 ? false : true);
            employee.setPhone("138490639" + (i / 10 == 0 ? "0" + i : i));
            employee.setPassword("password_" + i);
            employee.setPosition(PositionTypeEnum.values()[i % 4]);
            employee.setPerformance("performance_" + i);
            list.add(employee);
        }
        this.employeeManager.save(list);
    }

    @Test
    public void testDynamicUpdate() {
        Employee employee1 = new Employee();
        employee1.setName("0");
        System.out.println(employee1.getName());
        Employee employee2 = new Employee();
        employee2.setName("朱才富");
        employee2.setId((long) 1);
        System.out.println("===========================================");
        System.out.println("before: " + employee1);
        UpdateUtils.copyNotNullProperties(employee2, employee1);
        System.out.println("after: " + employee1);
        System.out.println("===========================================");
    }

    @Test
    public void toExcel() throws IOException {
        List<Employee> list = this.employeeManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<Employee> exportExcelUtil = new ExportExcelUtil<Employee>();
        List<Integer> filterCol = Arrays.asList(5, 8);
        exportExcelUtil.exportExcel("员工信息", FieldUtils.EMPLOYEE_FIELDS, list, out, filterCol);
    }

    @Test
    public void testFindAll(){
        List<Employee> list = this.employeeManager.findAll(false);
        for(Employee employee : list){
            System.out.println(employee);
        }
    }

}