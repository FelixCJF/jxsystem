
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IUserRoleDao;
import com.jju.domain.PageModel;
import com.jju.domain.Userrole;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class UserRoleDaoImpl extends BaseDao<Userrole> implements IUserRoleDao {
	public UserRoleDaoImpl() {
		super(Userrole.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Userrole> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_userrole");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Userrole get(Serializable pk) throws SQLException {
		//String sql = "SELECT * FROM tbl_userrole WHERE id = pk;";
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Userrole model) throws SQLException {
		String sql = "INSERT INTO tbl_userrole(id,roleId,userId) VALUES(?,?,?);";
		return query.update( sql, model.getId(), model.getRoleId(),
		        model.getUserId());
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
	public int update(Userrole model) throws SQLException {
		String sql = "UPDATE tbl_userrole SET roleId=?,userId=? WHERE id =?;";
		return query.update( sql, model.getRoleId(), model.getUserId(),
		        model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_userrole WHERE userid = ?;";
		return query.update( sql, pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.IUserRoleDao#deleteUserRole(java.io.Serializable, java.io.Serializable)
	 */
	@Override
	public int deleteUserRole(Serializable userId, Serializable roleId) throws SQLException {
		String sql = "DELETE FROM tbl_userrole WHERE userid = ? AND roleid = ?;";
		return query.update( sql, userId, roleId);
	}

}
