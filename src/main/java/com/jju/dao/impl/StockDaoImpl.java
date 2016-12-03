
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jju.dao.IStockDao;
import com.jju.domain.PageModel;
import com.jju.domain.Stock;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class StockDaoImpl extends BaseDao<Stock> implements IStockDao {

	public StockDaoImpl() {
		super(Stock.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Stock> page, QueryCondition condition) throws SQLException {
		// super.getList(page, condition, "tbl_stock");
		StringBuilder sql = new StringBuilder();
		StringBuilder whereSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM tbl_stock ");
		sql.append("SELECT * FROM tbl_stock ");
		whereSql.append(" WHERE goodsId in (SELECT id from tbl_goods ");
		whereSql.append(condition.buildWhereSql());
		whereSql.append(") ");
		sql.append(whereSql.toString());
		sql.append(condition.buildLimitSql(page.getPageIndex(), page.getPageSize()));
		countSql.append(whereSql.toString());
		List<Object> params = condition.getParams();
		List<Stock> result = null;
		Object totalCount = null;
		if (params.size() > 0) {
			System.out.println(params);
			result = query.query( sql.toString(),
			        new BeanListHandler<>(Stock.class), params.toArray());
			totalCount = query.query( countSql.toString(),
			        new ScalarHandler<Object>(), params.toArray());
		} else {
			result = query.query(ConnectionContext.getInstance().get(), sql.toString(),
			        new BeanListHandler<>(Stock.class));
			totalCount = query.query( countSql.toString(),
			        new ScalarHandler<Object>());
		}
		page.setRows(result);
		page.setTotal(Integer.valueOf(totalCount.toString()));
		System.out.println(sql.toString());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Stock get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_stock WHERE goodsId = ?;";
		return query.query( sql, new BeanHandler<>(Stock.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Stock model) throws SQLException {
		String sql = "INSERT INTO tbl_stock(id,goodsId,totalCount) VALUES(?,?,?)";
		return query.update( sql, model.getId(), model.getGoodsId(),
		        model.getTotalCount());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal(com.jju.utili.QueryCondition)
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		return super.getTotal(condition, "tbl_stock");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(Stock model) throws SQLException {
		String sql = "UPDATE tbl_stock SET totalCount=? WHERE goodsId = ?;";
		return query.update( sql, model.getTotalCount(), model.getGoodsId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
