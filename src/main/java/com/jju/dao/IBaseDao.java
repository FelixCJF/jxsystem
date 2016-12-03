package com.jju.dao;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.domain.PageModel;
import com.jju.util.QueryCondition;

public interface IBaseDao<T> {
	void getList(PageModel<T> page, QueryCondition condition) throws SQLException;

	T get(Serializable pk) throws SQLException;
	
	int add(T model) throws SQLException;
	
	int getTotal(QueryCondition condition) throws SQLException;
	
	int update(T model) throws SQLException;
	
	int delete(Serializable pk) throws SQLException;

}
