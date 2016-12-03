/**
 * 
 */
package com.jju.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author //TODO
 *
 */
public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = 7120962720945075573L;

	private int pageIndex;
	private int pageSize;
	private List<T> rows;
	private int total;

	public PageModel() {
		
	}

	public PageModel(int index, int size) {
		this.pageIndex = index;
		this.pageSize = size;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotal() {
		return this.total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
