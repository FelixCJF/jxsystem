
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IPurchaseDao;
import com.jju.dao.IStockDao;
import com.jju.dao.impl.PurchaseDaoImpl;
import com.jju.dao.impl.StockDaoImpl;
import com.jju.domain.PageModel;
import com.jju.domain.Purchase;
import com.jju.domain.Stock;
import com.jju.exception.ServiceException;
import com.jju.service.IPurchaseService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;


public class PurchaseServiceImpl implements IPurchaseService {
	public static final String STATUS_WAITING = "1";
	public static final String STATUS_PASS = "2";
	public static final String STATUS_REJECT = "3";
	public static final String STATUS_DONE = "4";

	private IPurchaseDao purchaseDao = new PurchaseDaoImpl();
	private IStockDao stockDao = new StockDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IPurchaseService#getList(com.jju.domain.PageModel, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void getList(PageModel<Purchase> page, String purchaseNum, String status, String supplier, String goodsName)
	        throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("status", status)
		        .addLikeField("purchaseNum", purchaseNum).addLikeField("supplierName", supplier)
		        .addLikeField("goodsName", goodsName);
		try {
			purchaseDao.getList(page, condition);
			/*	for (Purchase item : page.getRows()) {
					Type type = typeDao.getTypeByCode("status", item.getStatus());
					item.setStatus(type.getTypename());
				}*/
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IPurchaseService#purchase(com.jju.domain.Purchase)
	 */
	@Override
	public boolean purchase(Purchase purchase) throws ServiceException {
		try {
			// String goodsName =
			// goodsDao.get(purchase.getPurchaseGoods()).getGoodsName();
			// String supplierName =
			// supplierDao.get(purchase.getSupplier()).getSupplierName();
			purchase.setId(StringUtil.getUUID());
			/*purchase.setGoodsName(goodsName);
			purchase.setSupplierName(supplierName);*/
			purchase.setStatus(STATUS_WAITING);
			return purchaseDao.add(purchase) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IPurchaseService#setStatus(java.lang.String)
	 */
	@Override
	public boolean setStatus(Serializable pk, String status) throws ServiceException {
		try {
			Purchase purchase = purchaseDao.get(pk);
			purchase.setStatus(status);
			if (purchaseDao.update(purchase) > 0) {
				if (status.equalsIgnoreCase(STATUS_DONE)) {
					Stock stock = stockDao.get(purchase.getPurchaseGoods());
					stock.setTotalCount(stock.getTotalCount() + purchase.getPurchaseCount());
					if (stockDao.update(stock) > 0) {
						return true;
					} else {
						throw new SQLException("修改失败");
					}
				}
				return true;
			} else {
				throw new SQLException();
			}
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IPurchaseService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable pk) throws ServiceException {
		try {
			Purchase purchase = purchaseDao.get(pk);
			if (purchase.getStatus().equalsIgnoreCase(STATUS_DONE)) {
				return false;
			} else {
				return purchaseDao.delete(pk) > 0;
			}
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
