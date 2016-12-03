package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_stock database table.
 * 
 */
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String goodsId;

	private int totalCount;
	

	public Stock() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}