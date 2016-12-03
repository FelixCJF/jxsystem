/**
 * 
 */
package com.jju.formbean;

/**
 * @author //TODO
 *
 */
public class FunctionParamBean {
	private String id;
	private String paramName;
	private String paramCode;
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public String getParamName() {
		return paramName;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
}
