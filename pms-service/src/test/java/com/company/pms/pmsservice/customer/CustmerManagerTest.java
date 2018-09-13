package com.company.pms.pmsservice.customer;

import com.company.pms.pmsbase.utils.ExportExcelUtil;
import com.company.pms.pmsbase.utils.FieldUtils;
import com.company.pms.pmsrepository.customer.domain.Customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustmerManagerTest {

    private CustomerManager customerManager;

    @Test
    public void testFindAllByNameLike() {
        List<Customer> list = this.customerManager.findAllByNameLike("name_1");
        for(Customer customer : list){
            System.out.println("id: " + customer.getId() + " name: " + customer.getName());
        }
    }

    @Test
    public void testFindAllByAddressLike() {
        List<Customer> list = this.customerManager.findAllByAddressLike("address_1");
        for(Customer customer : list){
            System.out.println("id: " + customer.getId()
                    + " address: " + customer.getAddress());
        }
    }

    @Test
    public void testFindAllByAgeBetween() {
        List<Customer> list = this.customerManager.findAllByAgeBetween(10, 15);
        for(Customer customer : list){
            System.out.println("id: " + customer.getId() + " age: " + customer.getAge());
        }
    }

    @Test
    public void testFindAllByGender() {
        List<Customer> list = this.customerManager.findAllByGender(false);
        for(Customer customer : list){
            System.out.println("id: " + customer.getId() + " gender: " + customer.getGender());
        }
    }

    @Test
    public void testFindAllByPhone() {
        List<Customer> list = this.customerManager.findAllByPhone("13849063031");
        for(Customer customer : list){
            System.out.println("id: " + customer.getId() + " phone1: " + customer.getPhone1() + " phone2: " + customer.getPhone2());
        }
    }

    @Test
    public void testUpdateDeteled() {
        this.customerManager.updateDeteled(1, true);
    }

    @Test
    public void generator() {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Customer customer = new Customer();
            customer.setName("name_" + i);
            customer.setAge(i);
            customer.setAddress("address_" + i);
            customer.setGender(i % 2 == 0 ? false : true);
            customer.setPassword("password_" + i);
            customer.setPhone1("13849063" + (i / 10 == 0 ? "0" + i : i) + "0");
            customer.setPhone2("13849063" + (i / 10 == 0 ? "0" + i : i) + "1");
            list.add(customer);
        }
        this.customerManager.save(list);
    }

    @Test
    public void toExcel() throws IOException {
        List<Customer> list = this.customerManager.findAll();
        OutputStream out = new FileOutputStream("C:\\Users\\zcf\\Desktop\\ex.xls");
        ExportExcelUtil<Customer> exportExcelUtil = new ExportExcelUtil<Customer>();
        exportExcelUtil.exportExcel("客户信息", FieldUtils.CUSTOMER_FIELDS, list, out, null);
    }

    @Autowired
    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
}