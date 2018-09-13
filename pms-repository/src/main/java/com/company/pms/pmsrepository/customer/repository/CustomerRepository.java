package com.company.pms.pmsrepository.customer.repository;

import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsrepository.customer.domain.Customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends GenericRepository<Customer, Long> {

    List<Customer> findAllByNameAndDeleted(String name, Boolean deleted);

    List<Customer> findAllByNameLikeAndDeleted(String name, Boolean deleted);

    List<Customer> findAllByAddressLikeAndDeleted(String address, Boolean deleted);

    List<Customer> findAllByAgeBetweenAndDeleted(Integer age1, Integer age2, Boolean deleted);

    List<Customer> findAllByGenderAndDeleted(Boolean gender, Boolean deleted);

    List<Customer> findAllByPhone1LikeOrPhone2LikeAndDeleted(String phone1, String phone2, Boolean deleted);

    List<Customer> findAllByPhone1OrPhone2AndDeleted(String phone1, String phone2, Boolean deleted);

    List<Customer> findAllByPhone1OrPhone2AndPasswordAndDeleted(String phone1, String phone2, String password, Boolean deleted);

    List<Customer> findAllByNameAndPasswordAndDeleted(String name, String password, Boolean deleted);

    @Modifying
    @Query(value = "update customer set deleted=?2 where id=?1", nativeQuery = true)
    void updateDeteled(Long id, Boolean deleted);

}
