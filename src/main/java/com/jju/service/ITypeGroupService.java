
package com.jju.service;

import java.io.Serializable;
import java.util.List;

import com.jju.domain.TypeGroup;
import com.jju.exception.ServiceException;

/**
 * @author //TODO
 *
 */
public interface ITypeGroupService {
	boolean addGroup(TypeGroup model) throws ServiceException;
	
	List<TypeGroup> getList() throws ServiceException;
	
	boolean delete(Serializable id) throws ServiceException;
	
	boolean update(TypeGroup model) throws ServiceException;
}
