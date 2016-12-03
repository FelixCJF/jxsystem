package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_rolefunction database table.
 * 
 */
public class RoleFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String functionId;

	private String operation;

	private String roleId;

	public RoleFunction() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}