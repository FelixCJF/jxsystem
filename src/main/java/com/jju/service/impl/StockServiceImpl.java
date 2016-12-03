
package com.jju.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jju.dao.IGoodsDao;
import com.jju.dao.IStockDao;
import com.jju.dao.impl.GoodsDaoImpl;
import com.jju.dao.impl.StockDaoImpl;
import com.jju.domain.Goods;
import com.jju.domain.PageModel;
import com.jju.domain.Stock;
import com.jju.exception.ServiceException;
import com.jju.formbean.StockBean;
import com.jju.service.IStockService;
import com.jju.util.QueryCondition;


public class StockServiceImpl implements IStockService {
	private IStockDao StockDaoImpl = new StockDaoImpl();
	private IGoodsDao goodsDao = new GoodsDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IStockService#getList(com.jju.domain.PageModel, java.lang.String, java.lang.String)
	 */
	@Override
	public void getList(PageModel<StockBean> page, String goodsName, String goodsType) throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("goodsType", goodsType)
		        .addLikeField("goodsName", goodsName);
		try {
			PageModel<Stock> result = new PageModel<>(page.getPageIndex(), page.getPageSize());
			StockDaoImpl.getList(result, condition);
			page.setRows(new ArrayList<StockBean>());
			for (Stock item : result.getRows()) {
				StockBean bean = new StockBean();
				Goods goods = goodsDao.get(item.getGoodsId());
				bean.setGoodsId(item.getGoodsId());
				bean.setGoodsName(goods.getGoodsName());
				bean.setId(item.getId());
				bean.setTotalCount(String.valueOf(item.getTotalCount()));
				page.getRows().add(bean);
			}
			page.setTotal(result.getTotal());
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
