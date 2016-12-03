
package com.jju.service;

import com.jju.domain.PageModel;
import com.jju.domain.RoleFunction;
import com.jju.exception.ServiceException;


public interface IRoleFunctionService extends IBaseService<RoleFunction> {
	public void getList(PageModel<RoleFunction> page,String roleId) throws ServiceException;
	
	public RoleFunction get(String functionId,String roleId) throws ServiceException;
	
	boolean addOperation(RoleFunction model) throws ServiceException;
}
