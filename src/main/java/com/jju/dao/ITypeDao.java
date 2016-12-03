
package com.jju.dao;

import java.sql.SQLException;
import java.util.List;

import com.jju.domain.Type;



public interface ITypeDao extends IBaseDao<Type> {
	List<Type> getTypes(String groupId) throws SQLException;

	List<Type> getTypesByCode(String groupCode) throws SQLException;

	/**
	 * @param groupCode
	 * @param typeCode
	 * @return
	 * @throws SQLException
	 */
	Type getTypeByCode(String groupCode, String typeCode) throws SQLException;
}
