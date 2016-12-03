
package com.jju.service;

import java.io.Serializable;

import com.jju.domain.PageModel;
import com.jju.domain.Supplier;
import com.jju.exception.ServiceException;

public interface ISupplierService {
	void getList(PageModel<Supplier> page, String nameFilter, String numFilter) throws ServiceException;

	boolean add(Supplier supplier) throws ServiceException;

	boolean update(Supplier supplier) throws ServiceException;

	boolean delete(Serializable id) throws ServiceException;

	Supplier get(Serializable pk) throws ServiceException;
}
