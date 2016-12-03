package com.jju.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the tbl_purchase database table.
 * 
 */

public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String contactName;

	private String contactTel;

	private BigDecimal price;

	private int purchaseCount;

	private String purchaseGoods;

	private String goodsName;

	private String purchaseNum;

	private String remark;

	private String status;

	private String supplier;

	private String supplierName;

	public String getGoodsName() {
		return goodsName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Purchase() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return this.contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getPurchaseCount() {
		return this.purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getPurchaseGoods() {
		return this.purchaseGoods;
	}

	public void setPurchaseGoods(String purchaseGoods) {
		this.purchaseGoods = purchaseGoods;
	}

	public String getPurchaseNum() {
		return this.purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}