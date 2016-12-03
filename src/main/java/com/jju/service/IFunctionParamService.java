
package com.jju.service;

import java.util.List;

import com.jju.domain.FunctionParam;
import com.jju.exception.ServiceException;


public interface IFunctionParamService extends IBaseService<FunctionParam> {
	List<FunctionParam> getList(String functionId) throws ServiceException;
}
