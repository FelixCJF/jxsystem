
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jju.dao.ISupplierDao;
import com.jju.domain.PageModel;
import com.jju.domain.Supplier;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class SupplierDaoImpl extends BaseDao<Supplier> implements ISupplierDao {
	/**
	 * @param claszz
	 */
	public SupplierDaoImpl() {
		super(Supplier.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Supplier> page, QueryCondition condition) throws SQLException {
		super.getList(page, condition, "tbl_supplier");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Supplier get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_supplier WHERE id = ?;";
		return query.query( sql, new BeanHandler<>(Supplier.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Supplier model) throws SQLException {
		String sql = "INSERT INTO tbl_supplier(id,supplierName,supplierAddr,supplierTel,supplierEmail,supplierNum,contactPerson,contactTel) VALUES(?,?,?,?,?,?,?,?)";
		return query.update( sql, model.getId(), model.getSupplierName(),
		        model.getSupplierAddr(), model.getSupplierTel(), model.getSupplierEmail(), model.getSupplierNum(),
		        model.getContactPerson(), model.getContactTel());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal()
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		return super.getTotal(condition, "tbl_supplier");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(Supplier model) throws SQLException {
		String sql = "UPDATE tbl_supplier SET supplierName=?,supplierAddr=?,supplierTel=?,supplierEmail=?,supplierNum=?,contactPerson=?,contactTel=? WHERE id = ?;";
		return query.update( sql, model.getSupplierName(),
		        model.getSupplierAddr(), model.getSupplierTel(), model.getSupplierEmail(), model.getSupplierNum(),
		        model.getContactPerson(), model.getContactTel(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_supplier WHERE id =?";
		return query.update( sql, pk);
	}

}
