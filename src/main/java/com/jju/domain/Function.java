package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_function database table.
 * 
 */

public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private int functionLevel;

	private String functionName;

	private int functionOrder;

	private String functionUrl;

	private String parentFunction;

	public Function() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFunctionLevel() {
		return this.functionLevel;
	}

	public void setFunctionLevel(int functionLevel) {
		this.functionLevel = functionLevel;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getFunctionOrder() {
		return this.functionOrder;
	}

	public void setFunctionOrder(int functionOrder) {
		this.functionOrder = functionOrder;
	}

	public String getFunctionUrl() {
		return this.functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public String getParentFunction() {
		return this.parentFunction;
	}

	public void setParentFunction(String parentFunction) {
		this.parentFunction = parentFunction;
	}

}