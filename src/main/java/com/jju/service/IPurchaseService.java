
package com.jju.service;

import java.io.Serializable;

import com.jju.domain.PageModel;
import com.jju.domain.Purchase;
import com.jju.exception.ServiceException;


public interface IPurchaseService {
	void getList(PageModel<Purchase> page, String purchaseNum, String status, String supplier, String goodsName)
	        throws ServiceException;

	boolean purchase(Purchase purchase) throws ServiceException;

	boolean delete(Serializable pk) throws ServiceException;

	/**
	 * @param pk
	 * @param status
	 * @return
	 * @throws ServiceException
	 */
	boolean setStatus(Serializable pk, String status) throws ServiceException;
}
