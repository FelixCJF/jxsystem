
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IShipDao;
import com.jju.dao.IStockDao;
import com.jju.dao.impl.ShipDaoImpl;
import com.jju.dao.impl.StockDaoImpl;
import com.jju.domain.PageModel;
import com.jju.domain.Ship;
import com.jju.domain.Stock;
import com.jju.exception.ServiceException;
import com.jju.service.IShipService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;


public class ShipServiceImpl implements IShipService {
	public static final String STATUS_WAITING = "1";
	public static final String STATUS_PASS = "2";
	public static final String STATUS_REJECT = "3";
	public static final String STATUS_DONE = "4";

	private IShipDao shipDao = new ShipDaoImpl();
	private IStockDao stockDao = new StockDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IShipService#getList(com.jju.domain.PageModel, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void getList(PageModel<Ship> page, String statusFilter, String goodsNameFilter, String branchFilter,
	        String beginDate, String endDate) throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("status", statusFilter)
		        .addLikeField("goodsName", goodsNameFilter).addLikeField("branchAddr", branchFilter)
		        .addBetweenField("shipDate", new Object[] { beginDate, endDate });
		try {
			shipDao.getList(page, condition);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IShipService#add(com.jju.domain.Ship)
	 */
	@Override
	public boolean add(Ship ship) throws ServiceException {
		try {
			ship.setId(StringUtil.getUUID());
			ship.setStatus(STATUS_WAITING);
			return shipDao.add(ship) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IShipService#setStatus(java.io.Serializable, java.lang.String)
	 */
	@Override
	public boolean setStatus(Serializable id, String status) throws ServiceException {
		try {
			Ship ship = shipDao.get(id);
			ship.setStatus(status);
			if (shipDao.update(ship) > 0) {
				if (STATUS_DONE.equalsIgnoreCase(status)) {
					Stock stock = stockDao.get(ship.getShipGoods());
					stock.setTotalCount(stock.getTotalCount() - ship.getShipCount());
					return stockDao.update(stock) > 0;
				}
			} else {
				throw new SQLException();
			}
			return true;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IShipService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return shipDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
