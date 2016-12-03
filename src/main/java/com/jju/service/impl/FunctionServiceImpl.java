
package com.jju.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.jju.dao.IFunctionDao;
import com.jju.dao.impl.FunctionDaoImpl;
import com.jju.domain.Function;
import com.jju.domain.PageModel;
import com.jju.domain.RoleFunction;
import com.jju.exception.ServiceException;
import com.jju.formbean.FunctionBean;
import com.jju.formbean.UserFunction;
import com.jju.service.IFunctionService;
import com.jju.service.IRoleFunctionService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class FunctionServiceImpl implements IFunctionService {
	private IFunctionDao functionDao = new FunctionDaoImpl();
	private IRoleFunctionService roleFunctionService = new RoleFunctionServiceImpl();

	@Override
	public void getList(PageModel<FunctionBean> page, String roleId) throws ServiceException {
		PageModel<RoleFunction> roleFuns = new PageModel<>(1, 1000);
		roleFunctionService.getList(roleFuns, roleId);
		PageModel<FunctionBean> formBeans = new PageModel<>();
		this.getList(formBeans);
		if (roleFuns.getRows().size() > 0) {
			for (FunctionBean bean : formBeans.getRows()) {
				for (FunctionBean children : bean.getChildren()) {
					for (RoleFunction function : roleFuns.getRows()) {
						if (function.getFunctionId().equalsIgnoreCase(children.getId())) {
							children.setChecked("checked");
							continue;
						}
					}
				}
			}
		}
		page.setRows(formBeans.getRows());
	}

	@Override
	public void getList(PageModel<FunctionBean> page) throws ServiceException {
		PageModel<Function> functions = new PageModel<>(1, 1000);
		try {
			functionDao.getList(functions,
			        QueryCondition.newCondition().addOrderField("functionOrder", QueryCondition.ORDER_ASC));
			List<FunctionBean> beans = new ArrayList<>();
			if (functions.getRows() != null && functions.getRows().size() > 0) {
				Iterator<Function> rowsIter = functions.getRows().iterator();
				while (rowsIter.hasNext()) {
					Function item = rowsIter.next();
					try {
						FunctionBean bean = new FunctionBean();
						List<FunctionBean> children = new ArrayList<>();
						if (item.getParentFunction() == null || "".equals(item.getParentFunction())) {
							BeanUtils.copyProperties(bean, item);
							Iterator<Function> iter = functions.getRows().iterator();
							while (iter.hasNext()) {
								Function fun = iter.next();
								if (item.getId().equalsIgnoreCase(fun.getParentFunction())) {
									FunctionBean childrenBean = new FunctionBean();
									BeanUtils.copyProperties(childrenBean, fun);
									children.add(childrenBean);
								}
							}
							bean.setChildren(children);
							beans.add(bean);
						}

					}
					catch (IllegalAccessException | InvocationTargetException e) {
						throw new ServiceException(e);
					}
				}
			}
			page.setRows(beans);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#add(java.io.Serializable)
	 */
	@Override
	public boolean add(Function model) throws ServiceException {
		model.setId(StringUtil.getUUID());
		if (model.getFunctionLevel() == 0) {
			model.setParentFunction("");
		}
		try {
			return functionDao.add(model) > 0;
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
			return functionDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#update(java.io.Serializable)
	 */
	@Override
	public boolean update(Function model) throws ServiceException {
		try {
			if (model.getFunctionLevel() == 0) {
				model.setParentFunction("");
			} else if (model.getId().equalsIgnoreCase(model.getParentFunction())) {
				throw new SQLException("错误，不能将自己作为自己的父菜单");
			}
			return functionDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IFunctionService#getParentList()
	 */
	@Override
	public List<Function> getParentList() throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("functionLevel", "0")
		        .addOrderField("functionOrder", QueryCondition.ORDER_ASC);
		PageModel<Function> page = new PageModel<>(1, 1000);
		try {
			functionDao.getList(page, condition);
			return page.getRows();
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IFunctionService#getUserFunction(java.io.Serializable)
	 */
	@Override
	public List<UserFunction> getUserFunction(Serializable userid) throws ServiceException {
		try {
			return functionDao.getUserFunction(userid);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
