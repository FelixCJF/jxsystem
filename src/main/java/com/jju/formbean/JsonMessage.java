/**
 * 
 */
package com.jju.formbean;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * @author //TODO
 *
 */
public class JsonMessage implements Serializable {
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";

	private static final long serialVersionUID = -2166931227525482360L;

	private String message;
	private String status;

	public String getMessage() {
		return message;
	}


	public String getStatus() {
		return status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static String getMessage(String status, String message) {
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setStatus(status);
		jsonMessage.setMessage(message);
		return JSON.toJSONString(jsonMessage);
	}

}
