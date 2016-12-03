
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;



import com.jju.dao.IGoodsDao;
import com.jju.domain.Goods;
import com.jju.domain.PageModel;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;




public class GoodsDaoImpl extends BaseDao<Goods> implements IGoodsDao {
	/**
	 * @param claszz
	 */
	public GoodsDaoImpl() {
		super(Goods.class);
	}

	/* (non-Javadoc)
	 * @seecom.jju.dao.IGoodsDao#getList(com.todo.jxcsystem.domain.PageModel, java.util.LinkedHashMap)
	 */
	@Override
	public void getList(PageModel<Goods> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_goods");
	}

	/* (non-Javadoc)
	 * @seecom.jju.dao.IGoodsDao#addGoods(com.todo.jxcsystem.domain.Goods)
	 */
	@Override
	public int add(Goods model) throws SQLException {
		String sql = "INSERT INTO tbl_goods(id,goodsname,goodstype,costprice,remark) VALUES(?,?,?,?,?);";
		return query.update( sql, model.getId(), model.getGoodsName(),
		        model.getGoodsType(), model.getCostPrice(), model.getRemark());
	}

	/* (non-Javadoc)
	 * @seecom.jju.dao.IGoodsDao#update(com.todo.jxcsystem.domain.Goods)
	 */
	@Override
	public int update(Goods model) throws SQLException {
		String sql = "UPDATE tbl_goods SET goodsname = ?,goodstype=?,costprice=?,remarK=? WHERE id = ? ;";
		return query.update( sql, model.getGoodsName(), model.getGoodsType(),
		        model.getCostPrice(), model.getRemark(), model.getId());
	}

	/* (non-Javadoc)
	 * @seecom.jju.dao.IGoodsDao#delete(com.todo.jxcsystem.domain.Goods)
	 */
	@Override
	public int delete(Serializable id) throws SQLException {
		String sql = "DELETE FROM tbl_goods WHERE id = ?;";
		return query.update( sql, id);
	}

	/* (non-Javadoc)
	 * @seecom.jju.dao.IGoodsDao#getTotal()
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		return super.getTotal(condition, "tbl_goods");
	}

	/* (non-Javadoc)
	 * @seecom.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Goods get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_goods  WHERE id = ?;";
		return query.query( sql, new BeanHandler<>(Goods.class), pk);
	}

}
