package com.company.pms.pmsrepository.device.domain;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;
import com.company.pms.pmsbase.utils.TimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;

@Entity
public class Device extends BaseEntity {

    private String name;

    private String address;

    private Boolean state;

    private Long houseId;

    private Long employeeId;

    public Device() {
        this.setEntityType(EntityTypeEnum.Device.ordinal());
    }

    @Override
    public String toString() {
        return  new ToStringBuilder(this).append("id", id).append(" name", name)
                .append(" address", address).append(" state", state)
                .append(" houseId", houseId).append(" employeeId",employeeId)
                .append(" dateCreated", TimeUtil.getTime(dateCreated))
                .append(" dateModified", TimeUtil.getTime(dateModified)).toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}