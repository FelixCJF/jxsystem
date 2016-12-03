package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_branch database table.
 * 
 */

public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String branchAddr;

	private String branchTel;

	private String leaderName;

	private String leaderTel;

	private String zipcode;

	public Branch() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranchAddr() {
		return this.branchAddr;
	}

	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}

	public String getBranchTel() {
		return this.branchTel;
	}

	public void setBranchTel(String branchTel) {
		this.branchTel = branchTel;
	}

	public String getLeaderName() {
		return this.leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderTel() {
		return this.leaderTel;
	}

	public void setLeaderTel(String leaderTel) {
		this.leaderTel = leaderTel;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}