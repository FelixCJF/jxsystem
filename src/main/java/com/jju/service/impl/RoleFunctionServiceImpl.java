
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jju.dao.IRoleFunctionDao;
import com.jju.dao.impl.RoleFunctionDaoImpl;
import com.jju.domain.PageModel;
import com.jju.domain.RoleFunction;
import com.jju.exception.ServiceException;
import com.jju.service.IRoleFunctionService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;


public class RoleFunctionServiceImpl implements IRoleFunctionService {
	private IRoleFunctionDao rolefunctionDao = new RoleFunctionDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#add(java.io.Serializable)
	 */
	@Override
	public boolean add(RoleFunction model) throws ServiceException {
		try {
			if (model.getFunctionId() == null || model.getFunctionId().equals("")) {
				rolefunctionDao.deleteAll(model.getRoleId());
				return true;
			} else {
				String[] ids = model.getFunctionId().split(",");
				PageModel<RoleFunction> rows = new PageModel<>(1, 1000);
				this.getList(rows, model.getRoleId());
				Object[][] insertParams = new Object[ids.length][];
				Map<String, String> operions = new HashMap<>();
				for (RoleFunction item : rows.getRows()) {
					if (item.getOperation() != null && !item.getOperation().equals("")) {
						for (int i = 0; i < ids.length; i++) {
							if (item.getFunctionId().equalsIgnoreCase(ids[i])) {
								operions.put(ids[i], item.getOperation());
							}
						}
					}
				}
				rolefunctionDao.deleteAll(model.getRoleId());
				for (int i = 0; i < ids.length; i++) {
					insertParams[i] = new Object[4];
					insertParams[i][0] = StringUtil.getUUID();
					insertParams[i][1] = operions.get(ids[i]);
					insertParams[i][2] = ids[i];
					insertParams[i][3] = model.getRoleId();
				}
				rolefunctionDao.executeInsertBatch(insertParams);
				return true;
			}
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
			return rolefunctionDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#update(java.io.Serializable)
	 */
	@Override
	public boolean update(RoleFunction model) throws ServiceException {
		try {
			return rolefunctionDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IRoleFunctionService#getList(com.jju.domain.PageModel, java.lang.String)
	 */
	@Override
	public void getList(PageModel<RoleFunction> page, String roleId) throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("roleId", roleId);
		try {
			rolefunctionDao.getList(page, condition);
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IRoleFunctionService#get(java.lang.String, java.lang.String)
	 */
	@Override
	public RoleFunction get(String functionId, String roleId) throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("functionId", functionId)
		        .addEqualsField("roleId", roleId);
		try {
			PageModel<RoleFunction> result = new PageModel<>(1, 1);
			rolefunctionDao.getList(result, condition);
			if (result.getRows().size() > 0) {
				return result.getRows().get(0);
			}
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IRoleFunctionService#addOperation(com.jju.domain.RoleFunction)
	 */
	@Override
	public boolean addOperation(RoleFunction model) throws ServiceException {
		RoleFunction result = this.get(model.getFunctionId(), model.getRoleId());
		try {
			if (result == null) {
				model.setId(StringUtil.getUUID());
				model.setOperation(model.getOperation());
				rolefunctionDao.add(model);
			} else {
				rolefunctionDao.update(model);
			}
			return true;
		}catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
