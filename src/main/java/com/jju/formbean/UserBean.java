/**
 * 
 */
package com.jju.formbean;

import java.io.Serializable;

/**
 * @author //TODO
 *
 */
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4965430805439292170L;
	private String id;
	private String username;
	private String email;
	private String realName;
	private String departId;
	private String[] role;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getRealName() {
		return realName;
	}

	public String getDepartId() {
		return departId;
	}

	public String[] getRole() {
		return role;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setDepartId(String depart) {
		this.departId = depart;
	}

	public void setRole(String[] role) {
		this.role = role;
	}
}
