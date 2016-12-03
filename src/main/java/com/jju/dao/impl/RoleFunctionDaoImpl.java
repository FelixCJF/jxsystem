
package com.jju.dao.impl;

import com.jju.domain.PageModel;
import com.jju.domain.RoleFunction;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IRoleFunctionDao;



public class RoleFunctionDaoImpl extends BaseDao<RoleFunction> implements IRoleFunctionDao {
	public RoleFunctionDaoImpl() {
		super(RoleFunction.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<RoleFunction> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_rolefunction");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public RoleFunction get(Serializable pk) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(RoleFunction model) throws SQLException {

		String sql = "INSERT INTO tbl_rolefunction (id,operation,functionId,roleId) VALUES(?,?,?,?);";
		return query.update( sql, model.getId(), model.getOperation(),
		        model.getFunctionId(), model.getRoleId());
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
	public int update(RoleFunction model) throws SQLException {
		String sql = "UPDATE tbl_rolefunction SET operation =? WHERE functionId =? AND roleId = ?;";
		return query.update( sql, model.getOperation(), model.getFunctionId(),
		        model.getRoleId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_rolefunction WHERE id = pk;";
		return query.update( sql, pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.IRoleFunctionDao#deleteAll(java.lang.String)
	 */
	@Override
	public int deleteAll(String roleId) throws SQLException {
		String sql = "DELETE FROM tbl_rolefunction WHERE roleid=?";
		return query.update( sql, roleId);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.IRoleFunctionDao#executeInsertBatch(java.lang.Object[][])
	 */
	@Override
	public int executeInsertBatch(Object[][] params) throws SQLException {
		String sql = "INSERT INTO tbl_rolefunction (id,operation,functionId,roleId) VALUES(?,?,?,?);";
		return query.batch( sql, params).length;
	}

}
