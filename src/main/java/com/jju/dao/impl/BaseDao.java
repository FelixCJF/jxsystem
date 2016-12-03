
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jju.dao.IBaseDao;
import com.jju.domain.PageModel;
import com.jju.util.ConnectionContext;
import com.jju.util.JdbcUtil;
import com.jju.util.QueryCondition;


public abstract class BaseDao<T extends Serializable> implements IBaseDao<T> {
	protected QueryRunner query;
	private Class<T> claszz;

	protected BaseDao(Class<T> claszz) {
		query = new QueryRunner(JdbcUtil.getDataSource());
		this.claszz = claszz;
	}

	protected void getList(PageModel<T> page, QueryCondition condition, String tableName) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<T> result;
		sql.append("SELECT * FROM ");
		sql.append(tableName);
		List<Object> params = null;
		sql.append(condition.buildWhereSql());
		params = condition.getParams();
		sql.append(condition.buildOrderSql());
		sql.append(condition.buildLimitSql(page.getPageIndex(), page.getPageSize()));
		if (params.size() > 0) {
			result = query.query( sql.toString(), new BeanListHandler<>(claszz),
			        params.toArray());
		} else {
			result = query.query( sql.toString(), new BeanListHandler<>(claszz));
		}
		page.setRows(result);
		page.setTotal(getTotal(condition, tableName));
	}

	protected int getTotal(QueryCondition condition, String tableName) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ");
		sql.append(tableName);
		sql.append(condition.buildWhereSql());
		List<Object> params = condition.getParams();
		Object result = null;
		System.out.println(sql.toString());
		if (params.size() > 0) {
			result = query.query( sql.toString(), new ScalarHandler<Object>(),
			        params.toArray());
		} else {
			result = query.query( sql.toString(), new ScalarHandler<Object>());
		}
		if (result != null) {
			return Integer.parseInt(result.toString());
		} else {
			return 0;
		}
	}
}
