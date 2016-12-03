
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IRoleDao;
import com.jju.dao.impl.RoleDaoImpl;
import com.jju.domain.PageModel;
import com.jju.domain.Role;
import com.jju.exception.ServiceException;
import com.jju.service.IRoleService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class RoleServiceImpl implements IRoleService {
	private IRoleDao roleDao = new RoleDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#add(java.io.Serializable)
	 */
	@Override
	public boolean add(Role model) throws ServiceException {
		try {
			model.setId(StringUtil.getUUID());
			return roleDao.add(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return roleDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#update(java.io.Serializable)
	 */
	@Override
	public boolean update(Role model) throws ServiceException {
		try {
			return roleDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IRoleService#getList(com.jju.domain.PageModel)
	 */
	@Override
	public void getList(PageModel<Role> page) throws ServiceException {
		try {
			roleDao.getList(page, QueryCondition.newCondition());
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
