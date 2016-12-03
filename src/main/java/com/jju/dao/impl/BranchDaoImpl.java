
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jju.dao.IBranchDao;
import com.jju.domain.Branch;
import com.jju.domain.PageModel;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;

/**
 * @author //TODO
 *
 */
public class BranchDaoImpl extends BaseDao<Branch> implements IBranchDao {

	public BranchDaoImpl() {
		super(Branch.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Branch> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_branch");

	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Branch get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_branch WHERE id = ?;";
		return query.query( sql, new BeanHandler<>(Branch.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Branch model) throws SQLException {
		String sql = "INSERT INTO tbl_branch(id,branchAddr,branchTel,leaderName,leaderTel,zipcode) VALUES(?,?,?,?,?,?);";
		return query.update( sql, model.getId(), model.getBranchAddr(),
		        model.getBranchTel(), model.getLeaderName(), model.getLeaderTel(), model.getZipcode());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal(com.jju.utili.QueryCondition)
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		return super.getTotal(condition, "tbl_branch");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(Branch model) throws SQLException {
		String sql = "UPDATE tbl_branch SET branchAddr = ?,branchTel=?,leaderName=?,leaderTel=?,zipcode=? WHERE id =?;";
		return query.update( sql, model.getBranchAddr(), model.getBranchTel(),
		        model.getLeaderName(), model.getLeaderTel(), model.getZipcode(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_branch WHERE id =?;";
		return query.update( sql, pk);
	}

}
