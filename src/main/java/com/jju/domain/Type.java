package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_type database table.
 * 
 */

public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String groupid;

	private String typecode;

	private String typename;

	public Type() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupid() {
		return this.groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getTypecode() {
		return this.typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}