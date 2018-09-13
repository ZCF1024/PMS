package com.company.pms.pmsrepository.charge.domain;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;
import com.company.pms.pmsbase.utils.TimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class Charge extends BaseEntity {

	private static final long serialVersionUID = -4376674977047164142L;

	private static final  double ELECTRIC_PRICE = 0.500;
	private static final double WATER_PRICE = 1.00;
	private  static final double PROPERTY_PRICE = 200.00;

	@Column(name = "consum_electric")
	private Double consumOfElectric;  //用电量

	@Column(name = "charge_electric")
	private Double chargeOfElectric;  //缴纳电费

	@Column(name = "consum_water")
	private Double consumOfWater;  //用水量

	@Column(name = "charge_water")
	private Double chargeOfWater;  //缴纳水费

	@Column(name = "charge_property")
	private Double chargeOfProperty;  //缴纳物业费

	private Boolean state;

	@Column(name = "house_id")
	private Long houseId;

	public Charge() {
		this.setEntityType(EntityTypeEnum.Charge.ordinal());
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append(" consumOfElectric", consumOfElectric)
				.append(" chargeOfElectric", chargeOfElectric)
				.append(" consumOfWater", consumOfWater)
				.append(" chargeOfWater", chargeOfWater)
				.append(" chargeOfProperty", chargeOfProperty)
				.append(" state", state)
				.append(" houseId", houseId)
				.append(" dateCreated", TimeUtil.getTime(dateCreated))
				.append(" dateModified", TimeUtil.getTime(dateModified)).toString();
	}

	public Double getConsumOfElectric() {
		return consumOfElectric;
	}

	public void setConsumOfElectric(Double consumOfElectric) {
		this.consumOfElectric = consumOfElectric;
	}

	public Double getChargeOfElectric() {
		return chargeOfElectric;
	}

	public void setChargeOfElectric(Double chargeOfElectric) {
		this.chargeOfElectric = chargeOfElectric;
	}

	public Double getConsumOfWater() {
		return consumOfWater;
	}

	public void setConsumOfWater(Double consumOfWater) {
		this.consumOfWater = consumOfWater;
	}

	public Double getChargeOfWater() {
		return chargeOfWater;
	}

	public void setChargeOfWater(Double chargeOfWater) {
		this.chargeOfWater = chargeOfWater;
	}

	public Double getChargeOfProperty() {
		return chargeOfProperty;
	}

	public void setChargeOfProperty(Double chargeOfProperty) {
		this.chargeOfProperty = chargeOfProperty;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
}