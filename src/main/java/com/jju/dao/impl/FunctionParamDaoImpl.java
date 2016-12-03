
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IFunctionParamDao;
import com.jju.domain.FunctionParam;
import com.jju.domain.PageModel;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class FunctionParamDaoImpl extends BaseDao<FunctionParam> implements IFunctionParamDao {

	public FunctionParamDaoImpl() {
		super(FunctionParam.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<FunctionParam> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_functionparam");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public FunctionParam get(Serializable pk) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(FunctionParam model) throws SQLException {
		String sql = "INSERT INTO tbl_functionParam(id,paramName,paramCode,functionId) VALUES(?,?,?,?);";
		return query.update( sql, model.getId(), model.getParamName(),
		        model.getParamCode(), model.getFunctionId());
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
	public int update(FunctionParam model) throws SQLException {
		String sql = "UPDATE  tbl_functionParam SET paramName=?,paramCode=?,functionId=? WHERE id =?;";
		return query.update( sql, model.getParamName(), model.getParamCode(),
		        model.getFunctionId(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_functionParam WHERE id=?";
		return query.update( sql, pk);
	}

}
