
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.jju.dao.ITypeGroupDao;
import com.jju.dao.impl.TypeGroupDaoImpl;
import com.jju.domain.TypeGroup;
import com.jju.exception.ServiceException;
import com.jju.service.ITypeGroupService;
import com.jju.util.StringUtil;


public class TypeGroupServiceImpl implements ITypeGroupService {
	private ITypeGroupDao typeGroupDao;

	public TypeGroupServiceImpl() {
		typeGroupDao = new TypeGroupDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeGroupService#addGroup(com.jju.domain.TypeGroup)
	 */
	@Override
	public boolean addGroup(TypeGroup model) throws ServiceException {
		model.setId(StringUtil.getUUID());
		int row = 0;
		try {
			row = typeGroupDao.add(model);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
		return row > 0;
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeGroupService#getList()
	 */
	@Override
	public List<TypeGroup> getList() throws ServiceException {
		try {
			return typeGroupDao.get();
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeGroupService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return typeGroupDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeGroupService#update(com.jju.domain.TypeGroup)
	 */
	@Override
	public boolean update(TypeGroup model) throws ServiceException {
		try {
			return typeGroupDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
