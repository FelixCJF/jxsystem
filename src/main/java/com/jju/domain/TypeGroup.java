package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_typegroup database table.
 * 
 */

public class TypeGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String groupcode;

	private String groupname;

	public TypeGroup() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupcode() {
		return this.groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}