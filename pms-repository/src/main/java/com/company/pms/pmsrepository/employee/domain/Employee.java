package com.company.pms.pmsrepository.employee.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;
import com.company.pms.pmsbase.utils.TimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean gender;

    private Integer age;

    private String phone;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private PositionTypeEnum position;

    private String performance;

    public Employee() {
        this.setEntityType(EntityTypeEnum.Employee.ordinal());
    }

    public Employee(String name, Boolean gender, int age, String password,
                    PositionTypeEnum position, String performance) {
        super.setDeleted(false);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.password = password;
        this.position = position;
        this.performance = performance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append(" name", name).append(" gender", gender)
                .append(" age", age).append(" phone", phone).append(" password", password)
                .append(" position", position).append(" performance", performance)
                .append(" dateCreated", TimeUtil.getTime(dateCreated))
                .append(" dateModified", TimeUtil.getTime(dateModified)).toString();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PositionTypeEnum getPosition() {
        return position;
    }

    public void setPosition(PositionTypeEnum position) {
        this.position = position;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
