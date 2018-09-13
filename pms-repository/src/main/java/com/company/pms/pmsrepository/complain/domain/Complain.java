package com.company.pms.pmsrepository.complain.domain;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.utils.EntityTypeEnum;
import com.company.pms.pmsbase.utils.TimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Complain extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "device_id")
	private Long deviceId;

	@Column(name = "customer_id")
	private Long customerId;

	private String reason;

	public Complain() {
		this.setEntityType(EntityTypeEnum.Complain.ordinal());
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
                .append(" employeeId", employeeId)
				.append(" deviceId", deviceId)
				.append(" customerId", customerId)
				.append(" reasion", reason)
				.append(" dateCreated", TimeUtil.getTime(dateCreated))
				.append(" dateModified", TimeUtil.getTime(dateModified)).toString();
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}