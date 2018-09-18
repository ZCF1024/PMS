package com.company.pms.pmsrepository.house.domain;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;
import com.company.pms.pmsbase.utils.TimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class House extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String address;

	private Double area;

	private Integer state;

	private Double price;

	private String introduce;

	private String community;

	@Column(name="building_number")
	private Integer buildingNumber;

	@Column(name="floor_number")
	private Integer floorNumber;

	@Column(name="house_number")
	private Integer houseNumber;

	@Column(name="house_type")
	private String houseType;

	@Column(name="customer_id")
	private Long customerId;

	public House() {
		this.setEntityType(EntityTypeEnum.House.ordinal());
	}

	public House(Long id, String community, Integer buildingNumber) {
		this.id = id;
		this.community = community;
		this.buildingNumber = buildingNumber;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append(" address", address)
				.append(" area", area).append(" state", state)
				.append(" price", price).append(" introduce", introduce)
				.append(" customerId", customerId)
				.append(" dateCreated", TimeUtil.getTime(dateCreated))
				.append(" dateModified", TimeUtil.getTime(dateModified))
		        .append("community", community)
				.append("buildingNumber", buildingNumber)
				.append("floorNumber", floorNumber)
				.append("houseNumber", houseNumber)
				.append("houseType", houseType).toString();
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
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

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
}
