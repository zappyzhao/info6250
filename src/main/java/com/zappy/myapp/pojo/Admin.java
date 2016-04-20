package com.zappy.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="adminTable")
@PrimaryKeyJoinColumn(name="personID")
public class Admin extends Person {

//	@Id
//	@GeneratedValue
//	@Column(name="staffId", unique=true, nullable = false)
//	private long staffId;
	
	@Column(name="department")
	private String department;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

//	public long getStaffId() {
//		return staffId;
//	}
//
//	public void setStaffId(long staffId) {
//		this.staffId = staffId;
//	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
