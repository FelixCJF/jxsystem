
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jju.dao.IGoodsDao;
import com.jju.dao.IStockDao;
import com.jju.dao.ITypeDao;
import com.jju.dao.impl.GoodsDaoImpl;
import com.jju.dao.impl.StockDaoImpl;
import com.jju.dao.impl.TypeDaoImpl;
import com.jju.domain.Goods;
import com.jju.domain.PageModel;
import com.jju.domain.Stock;
import com.jju.domain.Type;
import com.jju.exception.ServiceException;
import com.jju.formbean.GoodsBean;
import com.jju.formbean.GrouptypeBean;
import com.jju.service.IGoodsService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class GoodsServiceImpl implements IGoodsService {
	private IGoodsDao goodsDao = new GoodsDaoImpl();
	private ITypeDao typedao = new TypeDaoImpl();
	private IStockDao stockDao = new StockDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IGoodsService#getList(com.jju.domain.PageModel, java.util.LinkedHashMap)
	 */
	@Override
	public void getList(PageModel<GoodsBean> page, String filterType, String searchKey) throws ServiceException {
		PageModel<Goods> goodsModel = new PageModel<>(page.getPageIndex(), page.getPageSize());
		try {
			QueryCondition condition = QueryCondition.newCondition();
			if (filterType != null && !"".equals(filterType)) {
				if (!filterType.equalsIgnoreCase("all")) {
					condition.addEqualsField("goodsType", filterType);
				}
			}
			if (searchKey != null && !"".equals(searchKey)) {
				condition.addLikeField("goodsName", searchKey);
			}
			goodsDao.getList(goodsModel, condition);
			page.setRows(new ArrayList<GoodsBean>());
			for (Goods item : goodsModel.getRows()) {
				GoodsBean bean = new GoodsBean();
				bean.setId(item.getId());
				bean.setGoodsName(item.getGoodsName());
				bean.setRemark(item.getRemark());
				bean.setCostPrice(item.getCostPrice().doubleValue());
				Type type = typedao.get(item.getGoodsType());
				GrouptypeBean typebean = new GrouptypeBean();
				typebean.setGroupId(type.getId());
				typebean.setGroupName(type.getTypename());
				bean.setGoodsType(typebean);
				page.getRows().add(bean);
			}
			page.setTotal(goodsModel.getTotal());
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}

	}

	/* (non-Javadoc)
	 * @see com.jju.service.IGoodsService#add(com.jju.domain.Goods)
	 */
	@Override
	public boolean add(Goods goods) throws ServiceException {
		goods.setId(StringUtil.getUUID());
		try {
			if (goodsDao.add(goods) > 0) {
				Stock stock = new Stock();
				stock.setId(StringUtil.getUUID());
				stock.setGoodsId(goods.getId());
				stock.setTotalCount(0);
				if (stockDao.add(stock) > 0) {
					return true;
				}
			}
			throw new SQLException("添加失败");
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IGoodsService#update(com.jju.domain.Goods)
	 */
	@Override
	public boolean update(Goods goods) throws ServiceException {
		try {
			return goodsDao.update(goods) > 0; 
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IGoodsService#delete(com.jju.domain.Goods)
	 */
	@Override
	public boolean delete(Serializable goods) throws ServiceException {
		try {
			return goodsDao.delete(goods) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IGoodsService#get(java.io.Serializable)
	 */
	@Override
	public Goods get(Serializable pk) throws ServiceException {
		try {
			return goodsDao.get(pk);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
