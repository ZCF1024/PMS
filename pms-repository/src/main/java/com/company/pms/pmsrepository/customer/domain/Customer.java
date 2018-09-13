package com.company.pms.pmsrepository.customer.domain;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean gender;

    private Integer age;

    private String address;

    private String phone1;

    private String phone2;

    private String password;

    public Customer() {
        this.setEntityType(EntityTypeEnum.Customer.ordinal());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append(" name", name)
                .append(" gender", gender).append(" age", age)
                .append(" address", address).append(" phone1", phone1)
                .append(" phone2", phone2).append(" password", password).toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}