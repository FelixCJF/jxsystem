
package com.jju.service;

import com.jju.domain.Department;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;

/**
 * @author //TODO
 *
 */
public interface IDepartmentService extends IBaseService<Department> {
	void getList(PageModel<Department> page) throws ServiceException;
}
