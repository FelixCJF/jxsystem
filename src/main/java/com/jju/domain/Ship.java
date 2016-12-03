package com.jju.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tbl_ship database table.
 * 
 */
public class Ship implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String branch;

	private String remark;

	private int shipCount;

	private Date shipDate;

	private String shipGoods;

	private String status;

	public Ship() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getShipCount() {
		return this.shipCount;
	}

	public void setShipCount(int shipCount) {
		this.shipCount = shipCount;
	}

	public Date getShipDate() {
		return this.shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getShipGoods() {
		return this.shipGoods;
	}

	public void setShipGoods(String shipGoods) {
		this.shipGoods = shipGoods;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}