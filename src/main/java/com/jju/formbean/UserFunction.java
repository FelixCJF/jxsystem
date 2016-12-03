/**
 * 
 */
package com.jju.formbean;

/**
 * @author //TODO
 *
 */
public class UserFunction {
	private String functionName;
	private String functionUrl;
	private String functionLevel;
	private String functionOrder;
	private String parentFunction;
	private String operation;

	public String getFunctionName() {
		return functionName;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public String getFunctionLevel() {
		return functionLevel;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public String getParentFunction() {
		return parentFunction;
	}

	public String getOperation() {
		return operation;
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

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	public void setParentFunction(String parentFunction) {
		this.parentFunction = parentFunction;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
