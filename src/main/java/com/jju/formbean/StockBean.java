/**
 * 
 */
package com.jju.formbean;

import java.io.Serializable;

/**
 * @author //TODO
 *
 */
public class StockBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4475828888230435122L;
	private String id;
	private String goodsId;
	private String totalCount;
	private String goodsName;

	public String getId() {
		return id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}
