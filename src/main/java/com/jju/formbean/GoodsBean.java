/**
 * 
 */
package com.jju.formbean;

import java.io.Serializable;

/**
 * @author //TODO
 *
 */
public class GoodsBean implements Serializable {
	private static final long serialVersionUID = -1812675724935815523L;

	private String id;
	private String goodsName;
	private GrouptypeBean goodsType;
	private double costPrice;
	private String remark;

	public String getId() {
		return id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public GrouptypeBean getGoodsType() {
		return goodsType;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsType(GrouptypeBean goodsType) {
		this.goodsType = goodsType;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
