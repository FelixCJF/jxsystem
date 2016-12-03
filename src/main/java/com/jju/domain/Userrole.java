package com.jju.domain;

import java.io.Serializable;



/**
 * The persistent class for the tbl_userrole database table.
 * 
 */

public class Userrole implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String roleId;

	private String userId;

	public Userrole() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}