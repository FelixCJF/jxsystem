
package com.jju.service;

import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.StockBean;

/**
 * @author //TODO
 *
 */
public interface IStockService {
	void getList(PageModel<StockBean> page, String goodsName, String goodsType) throws ServiceException;
}
