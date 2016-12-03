
package com.jju.service;

import java.io.Serializable;

import com.jju.domain.PageModel;
import com.jju.domain.Ship;
import com.jju.exception.ServiceException;

/**
 * @author //TODO
 *
 */
public interface IShipService {
	void getList(PageModel<Ship> page, String statusFilter, String goodsNameFilter, String branchFilter,
	        String beaginDate, String endDate) throws ServiceException;

	boolean add(Ship ship) throws ServiceException;

	boolean setStatus(Serializable id, String status) throws ServiceException;
	
	boolean delete(Serializable id) throws ServiceException;
}
