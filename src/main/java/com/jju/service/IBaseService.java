package com.jju.service;

import java.io.Serializable;

import com.jju.exception.ServiceException;

public interface IBaseService<T extends Serializable> {

	boolean add(T model) throws ServiceException;

	boolean delete(Serializable id) throws ServiceException;

	boolean update(T model) throws ServiceException;
}
