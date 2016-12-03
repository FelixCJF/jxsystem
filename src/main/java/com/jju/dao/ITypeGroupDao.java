
package com.jju.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.jju.domain.TypeGroup;


public interface ITypeGroupDao extends IBaseDao<TypeGroup> {
	
	int delete(Serializable id ) throws SQLException;
	
	TypeGroup get(Serializable id) throws SQLException;
	
	int update(TypeGroup model) throws SQLException;
	
	List<TypeGroup> get() throws SQLException; 
}	
