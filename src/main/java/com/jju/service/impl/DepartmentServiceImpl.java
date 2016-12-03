
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IDepartmentDao;
import com.jju.dao.impl.DepartmentDaoImpl;
import com.jju.domain.Department;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.service.IDepartmentService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;


public class DepartmentServiceImpl implements IDepartmentService {
	private IDepartmentDao departmentDao = new DepartmentDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#add(java.io.Serializable)
	 */
	@Override
	public boolean add(Department model) throws ServiceException {
		try {
			model.setId(StringUtil.getUUID());
			return departmentDao.add(model) > 0;
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
			return departmentDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#update(java.io.Serializable)
	 */
	@Override
	public boolean update(Department model) throws ServiceException {
		try {
			return departmentDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IDepartmentService#getList(com.jju.domain.PageModel)
	 */
	@Override
	public void getList(PageModel<Department> page) throws ServiceException {
		try {
			departmentDao.getList(page, QueryCondition.newCondition());
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
