package com.company.pms.pmsbase.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class BaseEntity extends BaseDomain {

	private static final long serialVersionUID = -6163675075289529459L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATECREATED")
	protected Date dateCreated = new Date();

	/**
	 * 实体修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATEMODIFIED")
	protected Date dateModified = new Date();

	/**
	 * 实体是否被删除
	 */
	@Column(name = "DELETED")
	protected Boolean deleted = false;

	@Column(name = "ENTITY_TYPE")
	protected int entityType;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Override
	public boolean equals(Object obj) {
		if (null != obj) {
			if (obj instanceof BaseEntity) {
				BaseEntity domain = (BaseEntity) obj;
				if (this.id == domain.id) {
					return true;
				}
			}
		}
		return false;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public int getEntityType() {
		return entityType;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		if (this.id == null) {
			this.id = Long.valueOf(0);
		}
		return HashCodeBuilder.reflectionHashCode(this.id);
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}