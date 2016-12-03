
package com.jju.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jju.dao.IShipDao;
import com.jju.domain.PageModel;
import com.jju.domain.Ship;
import com.jju.util.ConnectionContext;
import com.jju.util.QueryCondition;


public class ShipDaoImpl extends BaseDao<Ship> implements IShipDao {

	public ShipDaoImpl() {
		super(Ship.class);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getList(com.jju.domain.PageModel, com.jju.utili.QueryCondition)
	 */
	@Override
	public void getList(PageModel<Ship> page, QueryCondition condition) throws SQLException {
		StringBuilder querySql = new StringBuilder();
		StringBuilder whereSql = new StringBuilder(condition.buildWhereSql());
		StringBuilder countSql = new StringBuilder();
		querySql.append(
		        "select  ship.id,goodsName as shipGoods,shipCount,ship.`status`,branch.branchAddr as branch,shipDate,ship.remark  from (tbl_ship as ship inner JOIN tbl_goods as goods on ship.shipGoods = goods.id) inner JOIN tbl_branch as branch  on ship.branch = branch.id ");
		countSql.append(
		        "select count(*) from (tbl_ship as ship inner JOIN tbl_goods as goods on ship.shipGoods = goods.id) inner JOIN tbl_branch as branch  on ship.branch = branch.id ");
		List<Object> params = condition.getParams();
		List<Ship> result = null;
		Object count = null;
		querySql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		if (params.size() > 0) {
			result = query.query( querySql.toString(),
			        new BeanListHandler<>(Ship.class), params.toArray());
			count = query.query( countSql.toString(), new ScalarHandler<Object>(),
			        params.toArray());
		} else {
			result = query.query( querySql.toString(),
			        new BeanListHandler<>(Ship.class));
			count = query.query( countSql.toString(),
			        new ScalarHandler<Object>());
		}
		System.out.println(querySql.toString());
		page.setRows(result);
		page.setTotal(Integer.valueOf(count.toString()));
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#get(java.io.Serializable)
	 */
	@Override
	public Ship get(Serializable pk) throws SQLException {
		String sql = "SELECT * FROM tbl_ship WHERE Id= ?;";
		return query.query( sql, new BeanHandler<>(Ship.class), pk);
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public int add(Ship model) throws SQLException {
		String sql = "INSERT INTO tbl_ship(id,shipGoods,shipCount,status,branch,shipDate,remark) VALUES(?,?,?,?,?,?,?);";
		return query.update( sql, model.getId(), model.getShipGoods(),
		        model.getShipCount(), model.getStatus(), model.getBranch(), model.getShipDate(), model.getRemark());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#getTotal(com.jju.utili.QueryCondition)
	 */
	@Override
	public int getTotal(QueryCondition condition) throws SQLException {
		return super.getTotal(condition, "tbl_ship");
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(Ship model) throws SQLException {
		String sql = "UPDATE  tbl_ship SET shipGoods = ?,shipCount=?,status=?,branch=?,shipDate=?,remark=? WHERE id=?;";
		return query.update( sql, model.getShipGoods(), model.getShipCount(),
		        model.getStatus(), model.getBranch(), model.getShipDate(), model.getRemark(), model.getId());
	}

	/* (non-Javadoc)
	 * @see com.jju.dao.basic.IBaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(Serializable pk) throws SQLException {
		String sql = "DELETE FROM tbl_ship WHERE id =?;";
		return query.update( sql, pk);
	}

}
