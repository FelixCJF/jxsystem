
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.jju.dao.ITypeDao;
import com.jju.dao.impl.TypeDaoImpl;
import com.jju.domain.Type;
import com.jju.exception.ServiceException;
import com.jju.service.ITypeService;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class TypeServiceImpl implements ITypeService {
	private ITypeDao typeDao;

	public TypeServiceImpl() {
		typeDao = new TypeDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeService#addType(com.jju.domain.Type)
	 */
	@Override
	public boolean addType(Type model) throws ServiceException {
		model.setId(StringUtil.getUUID());
		int row = 0;
		try {
			row = typeDao.add(model);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
		return row > 0;
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeService#getList()
	 */
	@Override
	public List<Type> getTypes(String groupId) throws ServiceException {
		try {
			return typeDao.getTypes(groupId);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return typeDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeService#update(com.jju.domain.Type)
	 */
	@Override
	public boolean update(Type model) throws ServiceException {
		try {
			return typeDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeService#get(java.io.Serializable)
	 */
	@Override
	public Type get(Serializable id) throws ServiceException {
		try {
			return typeDao.get(id);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ITypeService#getTypesByCode(java.lang.String)
	 */
	@Override
	public List<Type> getTypesByCode(String groupCode) throws ServiceException {
		try {
			return typeDao.getTypesByCode(groupCode);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
