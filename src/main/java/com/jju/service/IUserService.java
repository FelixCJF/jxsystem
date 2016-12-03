package com.jju.service;

import com.jju.domain.PageModel;
import com.jju.domain.User;
import com.jju.exception.ServiceException;
import com.jju.formbean.LoginBean;
import com.jju.formbean.UserBean;

public interface IUserService extends IBaseService<User> {
	void getList(PageModel<UserBean> page) throws ServiceException;

	boolean addUser(UserBean user) throws ServiceException;

	boolean updateUser(UserBean user) throws ServiceException;

	User login(LoginBean bean) throws ServiceException;
}
