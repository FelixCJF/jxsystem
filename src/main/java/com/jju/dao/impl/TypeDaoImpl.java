
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jju.dao.ITypeDao;
import com.jju.domain.PageModel;
import com.jju.domain.Type;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class TypeDaoImpl implements ITypeDao {
	private QueryRunner query;

	public TypeDaoImpl() {
		query = new QueryRunner();
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#getTypes(java.lang.String)
	 */
	@Override
	public List<Type> getTypes(String groupId) throws SQLException {
		String sql = "SELECT * FROM tbl_type where groupid = ?";
		List<Type> result = query.query( sql, new BeanListHandler<>(Type.class),
		        groupId);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#getType(java.io.Serializable)
	 */
	@Override
	public Type get(Serializable id) throws SQLException {
		String sql = "SELECT * FROM tbl_type WHERE id = ?;";
		Type type = query.query( sql, new BeanHandler<>(Type.class), id);
		return type;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#addType(com.jju.domain.Type)
	 */
	@Override
	public int add(Type model) throws SQLException {
		String sql = "INSERT INTO tbl_type(id,typename,typecode,groupid) VALUES(?,?,?,?);";
		return query.update( sql, model.getId(), model.getTypename(),
		        model.getTypecode(), model.getGroupid());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#update(com.jju.domain.Type)
	 */
	@Override
	public int update(Type model) throws SQLException {
		String sql = "UPDATE tbl_type SET typename = ?,typecode = ? WHERE id = ?;";
		return query.update( sql, model.getTypename(), model.getTypecode(),
		        model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable id) throws SQLException {
		String sql = "DELETE FROM tbl_type WHERE id = ?;";
		return query.update( sql, id);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#getTypesByCode(java.lang.String)
	 */
	@Override
	public List<Type> getTypesByCode(String groupCode) throws SQLException {
		String sql = "SELECT * from tbl_type where groupid=(select id from tbl_typegroup where groupcode=?);";
		return query.query( sql, new BeanListHandler<>(Type.class), groupCode);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.ITypeDao#getTypesByCode(java.lang.String)
	 */
	@Override
	public Type getTypeByCode(String groupCode, String typeCode) throws SQLException {
		String sql = "SELECT * from tbl_type where groupid=(select id from tbl_typegroup where groupcode=?) AND typeCode =?;";
		return query.query( sql, new BeanHandler<>(Type.class), groupCode,
		        typeCode);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Type> page, QueryCondition condition) throws SQLException {
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
