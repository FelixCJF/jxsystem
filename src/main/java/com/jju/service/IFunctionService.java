
package com.jju.service;

import java.io.Serializable;
import java.util.List;

import com.jju.domain.Function;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.FunctionBean;
import com.jju.formbean.UserFunction;

/**
 * @author //TODO
 *
 */
public interface IFunctionService extends IBaseService<Function> {
	void getList(PageModel<FunctionBean> page) throws ServiceException;

	void getList(PageModel<FunctionBean> page, String roleId) throws ServiceException;

	List<UserFunction> getUserFunction(Serializable userid) throws ServiceException;

	List<Function> getParentList() throws ServiceException;
}
