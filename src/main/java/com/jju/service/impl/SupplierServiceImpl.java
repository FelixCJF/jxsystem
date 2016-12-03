
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.ISupplierDao;
import com.jju.dao.impl.SupplierDaoImpl;
import com.jju.domain.PageModel;
import com.jju.domain.Supplier;
import com.jju.exception.ServiceException;
import com.jju.service.ISupplierService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;


public class SupplierServiceImpl implements ISupplierService {
	private ISupplierDao supplierDao = new SupplierDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.ISupplierService#getList(com.jju.domain.PageModel)
	 */
	@Override
	public void getList(PageModel<Supplier> page, String nameFilter, String numFilter) throws ServiceException {
		try {
			QueryCondition condition = QueryCondition.newCondition().addLikeField("supplierName", nameFilter)
			        .addLikeField("supplierNum", numFilter);
			supplierDao.getList(page, condition);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ISupplierService#add(com.jju.domain.Supplier)
	 */
	@Override
	public boolean add(Supplier supplier) throws ServiceException {
		supplier.setId(StringUtil.getUUID());
		try {
			return supplierDao.add(supplier) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ISupplierService#update(com.jju.domain.Supplier)
	 */
	@Override
	public boolean update(Supplier supplier) throws ServiceException {
		try {
			return supplierDao.update(supplier) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ISupplierService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return supplierDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.ISupplierService#get(java.io.Serializable)
	 */
	@Override
	public Supplier get(Serializable pk) throws ServiceException {
		try {
			return supplierDao.get(pk);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
