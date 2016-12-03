
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.jju.dao.IFunctionParamDao;
import com.jju.dao.impl.FunctionParamDaoImpl;
import com.jju.domain.FunctionParam;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.service.IFunctionParamService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class FunctionParamServiceImpl implements IFunctionParamService {
	IFunctionParamDao functionparamDao = new FunctionParamDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#add(java.io.Serializable)
	 */
	@Override
	public boolean add(FunctionParam model) throws ServiceException {
		try {
			model.setId(StringUtil.getUUID());
			return functionparamDao.add(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return functionparamDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#update(java.io.Serializable)
	 */
	@Override
	public boolean update(FunctionParam model) throws ServiceException {
		try {
			return functionparamDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<FunctionParam> getList(String functionId) throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("functionId", functionId);
		PageModel<FunctionParam> params = new PageModel<>(1, 1000);
		try {
			functionparamDao.getList(params, condition);
			return params.getRows();
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
