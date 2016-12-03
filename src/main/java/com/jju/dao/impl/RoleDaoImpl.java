
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jju.dao.IRoleDao;
import com.jju.domain.PageModel;
import com.jju.domain.Role;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class RoleDaoImpl extends BaseDao<Role> implements IRoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Role> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_role");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Role get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_role WHERE id = ?;";
		return query.query( sql, new BeanHandler<>(Role.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Role model) throws SQLException {
		String sql = "INSERT INTO tbl_role(id,roleName,roleCode) VALUES(?,?,?);";
		return query.update( sql, model.getId(), model.getRoleName(),
		        model.getRoleCode());
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
	public int update(Role model) throws SQLException {
		String sql = "UPDATE tbl_role SET roleName=?,roleCode=? WHERE id =?;";
		return query.update( sql, model.getRoleName(), model.getRoleCode(),
		        model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_role WHERE id = ?";
		return query.update( sql, pk);
	}

}
