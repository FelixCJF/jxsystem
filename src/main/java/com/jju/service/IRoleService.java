
package com.jju.service;

import com.jju.domain.PageModel;
import com.jju.domain.Role;
import com.jju.exception.ServiceException;

/**
 * @author //TODO
 *
 */
public interface IRoleService extends IBaseService<Role> {
	void getList(PageModel<Role> page) throws ServiceException;
}
