
package com.jju.dao;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.domain.Userrole;


public interface IUserRoleDao extends IBaseDao<Userrole> {
	int deleteUserRole(Serializable userId, Serializable roleId) throws SQLException;
}
