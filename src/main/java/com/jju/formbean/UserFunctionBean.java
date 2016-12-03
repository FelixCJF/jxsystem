/**
 * 
 */
package com.jju.formbean;

import java.util.List;

/**
 * @author //TODO
 *
 */
public class UserFunctionBean {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String functionName;
	private String functionUrl;
	private String functionLevel;
	private String operation;
	private List<UserFunctionBean> children;

	public String getFunctionName() {
		return functionName;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public String getFunctionLevel() {
		return functionLevel;
	}

	public String getOperation() {
		return operation;
	}

	public List<UserFunctionBean> getChildren() {
		return children;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public void setFunctionLevel(String functionLevel) {
		this.functionLevel = functionLevel;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setChildren(List<UserFunctionBean> children) {
		this.children = children;
	}
}
