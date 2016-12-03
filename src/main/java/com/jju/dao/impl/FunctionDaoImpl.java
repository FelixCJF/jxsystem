
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jju.dao.IFunctionDao;
import com.jju.domain.Function;
import com.jju.domain.PageModel;
import com.jju.formbean.UserFunction;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;

/**
 * @author //TODO
 *
 */
public class FunctionDaoImpl extends BaseDao<Function> implements IFunctionDao {

	public FunctionDaoImpl() {
		super(Function.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Function> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_function");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Function get(Serializable pk) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Function model) throws SQLException {
		String sql = "INSERT INTO tbl_function(id,functionName,functionUrl,functionLevel,functionOrder,parentFunction) VALUES(?,?,?,?,?,?);";
		return query.update( sql, model.getId(), model.getFunctionName(),
		        model.getFunctionUrl(), model.getFunctionLevel(), model.getFunctionOrder(), model.getParentFunction());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal(com.jju.util.QueryCondition)
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(Function model) throws SQLException {
		String sql = "UPDATE tbl_function SET functionName=?,functionUrl=?,functionLevel=?,functionOrder=?,parentFunction=? where id = ?";
		return query.update( sql, model.getFunctionName(), model.getFunctionUrl(),
		        model.getFunctionLevel(), model.getFunctionOrder(), model.getParentFunction(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_function WHERE id = ?";
		return query.update( sql, pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.IFunctionDao#getUserFunction(java.io.Serializable)
	 */
	@Override
	public List<UserFunction> getUserFunction(Serializable userId) throws SQLException {
		String sql = "select functionName,functionUrl,functionLevel,functionOrder,parentFunction,operation from tbl_function as fun inner join  ( select * from tbl_rolefunction where roleId = (select roleId from tbl_userrole where userId = ?)) as role on fun.id = role.functionid order by fun.functionOrder asc";
		return query.query( sql, new BeanListHandler<>(UserFunction.class),
		        userId);
	}

}
