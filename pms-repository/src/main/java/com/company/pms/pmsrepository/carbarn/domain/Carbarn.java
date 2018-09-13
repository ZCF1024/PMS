package com.company.pms.pmsrepository.carbarn.domain;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;
import com.company.pms.pmsbase.utils.TimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Carbarn extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String address;

    private Double area;

    private Boolean state;

    private Double price;

    private String introduce;

    @Column(name = "customer_id")
    private Long customerId;

    public Carbarn() {
        this.setEntityType(EntityTypeEnum.Carbarn.ordinal());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append(" address", address)
                .append(" area", area).append(" state", state)
                .append(" price", price).append(" introduce", introduce)
                .append(" customerId", customerId)
                .append(" dateCreated", TimeUtil.getTime(dateCreated))
                .append(" dateModified", TimeUtil.getTime(dateModified)).toString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
