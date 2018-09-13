package com.company.pms.controller.customer;

import com.company.pms.pmsbase.web.GenericController;
import com.company.pms.pmsrepository.customer.domain.Customer;
import com.company.pms.pmsservice.customer.CustomerManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerAPIController extends GenericController<Customer, Long, CustomerManager> {

    private final Logger logger = LoggerFactory.getLogger(CustomerAPIController.class);

    private CustomerManager customerManager;

    @GetMapping("index")
    public String index() {
        return "Customer Index";
    }

    @GetMapping("name")
    public List<Customer> testFindAllByNameLike() {
        return this.customerManager.findAllByNameLike("name_1");
    }

    @Autowired
    public void setComplainManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
        this.manager = this.customerManager;
    }

}
