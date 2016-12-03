
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import com.jju.dao.IUserDao;
import com.jju.domain.PageModel;
import com.jju.domain.User;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class UserDaoImpl extends BaseDao<User> implements IUserDao {
	public UserDaoImpl() {
		super(User.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.util.QueryCondition)
	 */
	@Override
	public void getList(PageModel<User> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_user");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public User get(Serializable pk) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(User model) throws SQLException {
		String sql = "INSERT INTO tbl_user(id,username,email,realname,password,departId) VALUES(?,?,?,?,?,?);";
		return query.update( sql, model.getId(), model.getUsername(),
		        model.getEmail(), model.getRealName(), model.getPassword(), model.getDepartId());
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
	public int update(User model) throws SQLException {
		String sql = "UPDATE tbl_user SET username=?,email=?,realname=?,password=?,departId=? WHERE id = ?;";
		return query.update( sql, model.getUsername(), model.getEmail(),
		        model.getRealName(), model.getPassword(), model.getDepartId(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_user WHERE id = ?";
		return query.update( sql, pk);
	}

}
