
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jju.dao.IPurchaseDao;
import com.jju.domain.PageModel;
import com.jju.domain.Purchase;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class PurchaseDaoImpl extends BaseDao<Purchase> implements IPurchaseDao {

	public PurchaseDaoImpl() {
		super(Purchase.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Purchase> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_purchase");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Purchase get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_purchase WHERE id = ?";
		return query.query( sql, new BeanHandler<>(Purchase.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Purchase model) throws SQLException {
		String sql = "INSERT INTO tbl_purchase(id,purchaseGoods,goodsName,purchaseNum,purchaseCount,price,status,supplier,supplierName,contactName,contactTel,remark) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		return query.update( sql, model.getId(), model.getPurchaseGoods(),
		        model.getGoodsName(), model.getPurchaseNum(), model.getPurchaseCount(), model.getPrice(),
		        model.getStatus(), model.getSupplier(), model.getSupplierName(), model.getContactName(),
		        model.getContactTel(), model.getRemark());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal(com.jju.utili.QueryCondition)
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		return super.getTotal(condition, "tbl_purchase");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(Purchase model) throws SQLException {
		String sql = "UPDATE tbl_purchase SET purchaseGoods=?,goodsName=?,purchaseNum=?,purchaseCount=?,price=?,status=?,supplier=?,supplierName=?,contactName=?,contactTel=?,remark=? WHERE id =?";
		return query.update( sql, model.getPurchaseGoods(), model.getGoodsName(),
		        model.getPurchaseNum(), model.getPurchaseCount(), model.getPrice(), model.getStatus(),
		        model.getSupplier(), model.getSupplierName(), model.getContactName(), model.getContactTel(),
		        model.getRemark(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_purchase WHERE id =?";
		return query.update( sql, pk);
	}

}
