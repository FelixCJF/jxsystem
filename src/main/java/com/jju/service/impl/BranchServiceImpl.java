
package com.jju.service.impl;

import java.io.Serializable;
import java.sql.SQLException;

import com.jju.dao.IBranchDao;
import com.jju.dao.impl.BranchDaoImpl;
import com.jju.domain.Branch;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.service.IBranchService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class BranchServiceImpl implements IBranchService {
	private IBranchDao branchDao = new BranchDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IBranchService#getList(com.jju.domain.PageModel)
	 */
	@Override
	public void getList(PageModel<Branch> page) throws ServiceException {
		try {
			branchDao.getList(page, QueryCondition.newCondition());
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBranchService#add(com.jju.domain.Branch)
	 */
	@Override
	public boolean add(Branch branch) throws ServiceException {
		try {
			branch.setId(StringUtil.getUUID());
			return branchDao.add(branch) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBranchService#update(com.jju.domain.Branch)
	 */
	@Override
	public boolean update(Branch branch) throws ServiceException {
		try {
			return branchDao.update(branch) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBranchService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return branchDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

}
