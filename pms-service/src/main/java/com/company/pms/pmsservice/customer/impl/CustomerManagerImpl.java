package com.company.pms.pmsservice.customer.impl;

import com.company.pms.pmsbase.service.impl.GenericManagerImpl;
import com.company.pms.pmsrepository.customer.domain.Customer;
import com.company.pms.pmsrepository.customer.repository.CustomerRepository;
import com.company.pms.pmsservice.customer.CustomerManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllByName(String name) {
        return this.customerRepository.findAllByNameAndDeleted(name, false);
    }

    @Override
    public List<Customer> findAllByNameLike(String name) {
        return this.customerRepository.findAllByNameLikeAndDeleted("%" + name + "%", false);
    }

    @Override
    public List<Customer> findAllByAddressLike(String address) {
        return this.customerRepository.findAllByAddressLikeAndDeleted("%" + address + "%", false);
    }

    @Override
    public List<Customer> findAllByAgeBetween(int age1, int age2) {
        return this.customerRepository.findAllByAgeBetweenAndDeleted(age1, age2, false);
    }

    @Override
    public List<Customer> findAllByGender(Boolean gender) {
        return this.customerRepository.findAllByGenderAndDeleted(gender, false);
    }

    @Override
    public List<Customer> findAllByPhone(String phone) {
        return this.customerRepository.findAllByPhone1OrPhone2AndDeleted(phone, phone, false);
    }

    @Override
    public List<Customer> findAllByPhone1Like(String phone) {
        phone = "%" + phone + "%";
        return this.customerRepository.findAllByPhone1LikeOrPhone2LikeAndDeleted(phone, phone, false);
    }

    @Override
    public List<Customer> findAllByPhoneAndPassword(String phone, String password) {
        return this.customerRepository.findAllByPhone1OrPhone2AndPasswordAndDeleted(phone, phone, password, false);
    }

    @Override
    public List<Customer> findAllByNameAndPassword(String name, String password) {
        return this.customerRepository.findAllByNameAndPasswordAndDeleted(name, password, false);
    }

    @Override
    @Transactional
    public void updateDeteled(long id, boolean deleted) {
        this.customerRepository.updateDeteled(id, deleted);
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.repository = this.customerRepository;
    }

}
