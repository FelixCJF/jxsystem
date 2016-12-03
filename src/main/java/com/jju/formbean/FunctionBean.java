/**
 * 
 */
package com.jju.formbean;

import java.io.Serializable;
import java.util.List;

/**
 * @author //TODO
 *
 */
public class FunctionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6555712542676639036L;
	private String id;
	private String functionName;
	private String functionUrl;
	private String functionLevel;
	private String parentFunction;
	private String functionOrder;
	private List<FunctionBean> children;
	private String checked;

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(String parentFunction) {
		this.parentFunction = parentFunction;
	}

	public String getFunctionLevel() {
		return functionLevel;
	}

	public void setFunctionLevel(String functionLevel) {
		this.functionLevel = functionLevel;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public List<FunctionBean> getChildren() {
		return children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	public void setChildren(List<FunctionBean> children) {
		this.children = children;
	}
}
