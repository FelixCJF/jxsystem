
package com.jju.dao;

import java.sql.SQLException;

import com.jju.domain.RoleFunction;


public interface IRoleFunctionDao extends IBaseDao<RoleFunction> {
	int deleteAll(String roleId) throws SQLException;

	int executeInsertBatch(Object[][] params) throws SQLException;
}
