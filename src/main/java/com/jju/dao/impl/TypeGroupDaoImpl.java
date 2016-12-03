
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jju.dao.ITypeGroupDao;
import com.jju.domain.PageModel;
import com.jju.domain.TypeGroup;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class TypeGroupDaoImpl implements ITypeGroupDao {
	private QueryRunner query;

	public TypeGroupDaoImpl() {
		query = new QueryRunner();
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeGroupDao#addGroup(com.jju.domain.TypeGroup)
	 */
	@Override
	public int add(TypeGroup model) throws SQLException {
		String sql = "INSERT INTO tbl_typegroup(id,groupcode,groupname) VALUES(?,?,?);";
		return query.update( sql, model.getId(), model.getGroupcode(),
		        model.getGroupname());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeGroupDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable id) throws SQLException {
		String sql = "DELETE FROM tbl_typegroup WHERE id = ?;";
		return query.update( sql, id);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeGroupDao#get(java.io.Serializable)
	 */
	@Override
	public TypeGroup get(Serializable id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeGroupDao#update(com.jju.domain.TypeGroup)
	 */
	@Override
	public int update(TypeGroup model) throws SQLException {
		String sql = "UPDATE tbl_typegroup SET groupname = ?,groupcode = ? WHERE id = ?;";
		return query.update( sql, model.getGroupname(),model.getGroupcode(),model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeGroupDao#get()
	 */
	@Override
	public List<TypeGroup> get() throws SQLException {
		String sql = "SELECT * FROM tbl_typegroup";
		List<TypeGroup> result = query.query( sql,
		        new BeanListHandler<>(TypeGroup.class));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<TypeGroup> page, QueryCondition condition) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal()
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
