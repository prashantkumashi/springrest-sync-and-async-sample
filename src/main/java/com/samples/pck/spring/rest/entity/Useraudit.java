package com.samples.pck.spring.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="useraudit")
public class Useraudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="auditid")
	private Long auditId;
	
	@Column(name="userid")
	private Long userId;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name="addedon")
	private Date addedOn;
	
	@Column(name="status")
	private String status;
	
	@Column(name="old_value")
	private String oldValue;
	
	@Column(name="new_value")
	private String newValue;
	
	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		return "Useraudit [auditId=" + auditId + ", userId=" + userId + ", addedOn=" + addedOn + ", status=" + status
				+ ", oldValue=" + oldValue + ", newValue=" + newValue + "]";
	}
	
	
}
