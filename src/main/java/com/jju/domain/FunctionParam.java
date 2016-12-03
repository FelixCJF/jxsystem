package com.jju.domain;

import java.io.Serializable;


/**
 * The persistent class for the tbl_functionparam database table.
 * 
 */
public class FunctionParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String functionId;

	private String paramCode;

	private String paramName;

	public FunctionParam() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}