/**
 * 
 */
package com.jju.formbean;

import java.io.Serializable;

/**
 * @author //TODO
 *
 */
public class ComboboxBean implements Serializable {
	private static final long serialVersionUID = -5364048851153839884L;
	private String text;
	private String id;

	public String getText() {
		return text;
	}

	public String getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setId(String id) {
		this.id = id;
	}
}
