/**
 * 
 */
package com.jju.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.jju.domain.Function;
import com.jju.formbean.UserFunction;



public interface IFunctionDao extends IBaseDao<Function> {
	List<UserFunction> getUserFunction(Serializable userId) throws SQLException;
}
