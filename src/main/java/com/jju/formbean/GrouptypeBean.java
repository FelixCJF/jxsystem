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
public class GrouptypeBean implements Serializable {

	private static final long serialVersionUID = -1616048160899116810L;

	private String groupId;
	private String groupName;
	private String groupCode;
	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	private List<GrouptypeBean> children;

	public String getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public List<GrouptypeBean> getChildren() {
		return children;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public void setChildren(List<GrouptypeBean> children) {
		this.children = children;
	}
}
