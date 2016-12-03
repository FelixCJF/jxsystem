package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_department database table.
 * 
 */

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	private String departDescription;

	private String departName;

	public Department() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartDescription() {
		return this.departDescription;
	}

	public void setDepartDescription(String departDescription) {
		this.departDescription = departDescription;
	}

	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

}