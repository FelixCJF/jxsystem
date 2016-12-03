package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_role database table.
 * 
 */

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String roleCode;

	private String roleName;

	public Role() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}