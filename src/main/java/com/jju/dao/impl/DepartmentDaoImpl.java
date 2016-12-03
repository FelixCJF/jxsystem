
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jju.dao.IDepartmentDao;
import com.jju.domain.Department;
import com.jju.domain.PageModel;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class DepartmentDaoImpl extends BaseDao<Department> implements IDepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Department> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_department");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Department get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_department WHERE id = ?;";
		return query.query( sql, new BeanHandler<>(Department.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Department model) throws SQLException {
		String sql = "INSERT INTO tbl_department(id,departName,departDescription) VALUES(?,?,?);";
		return query.update( sql, model.getId(), model.getDepartName(),
		        model.getDepartDescription());
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
	public int update(Department model) throws SQLException {
		String sql = "UPDATE tbl_department SET departName=?,departDescription=? WHERE id = ?;";
		return query.update( sql, model.getDepartName(),
		        model.getDepartDescription(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_department WHERE id = ?";
		return query.update( sql, pk);
	}

}
