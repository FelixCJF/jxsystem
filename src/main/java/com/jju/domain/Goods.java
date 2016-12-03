package com.jju.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the tbl_goods database table.
 * 
 */

public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private BigDecimal costPrice;

	private String goodsName;

	private String goodsType;

	private String remark;

	public Goods() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsname) {
		this.goodsName = goodsname;
	}

	public String getGoodsType() {
		return this.goodsType;
	}

	public void setGoodsType(String goodstype) {
		this.goodsType = goodstype;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}