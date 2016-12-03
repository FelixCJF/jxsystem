
package com.jju.service;

import java.io.Serializable;

import com.jju.domain.Branch;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;


public interface IBranchService {
	void getList(PageModel<Branch> page) throws ServiceException;

	boolean add(Branch branch) throws ServiceException;

	boolean update(Branch branch) throws ServiceException;

	boolean delete(Serializable id) throws ServiceException;
}
