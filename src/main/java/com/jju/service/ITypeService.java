
package com.jju.service;

import java.io.Serializable;
import java.util.List;

import com.jju.domain.Type;
import com.jju.exception.ServiceException;


public interface ITypeService {
	boolean addType(Type model) throws ServiceException;
	
	List<Type> getTypes(String groupId) throws ServiceException;
	
	List<Type> getTypesByCode(String groupCode) throws ServiceException;
	
	boolean delete(Serializable id) throws ServiceException;
	
	boolean update(Type model) throws ServiceException;
	
	Type get(Serializable id) throws ServiceException;
}
