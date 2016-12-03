
package com.jju.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.jju.dao.IDepartmentDao;
import com.jju.dao.IRoleDao;
import com.jju.dao.IUserDao;
import com.jju.dao.IUserRoleDao;
import com.jju.dao.impl.DepartmentDaoImpl;
import com.jju.dao.impl.RoleDaoImpl;
import com.jju.dao.impl.UserDaoImpl;
import com.jju.dao.impl.UserRoleDaoImpl;
import com.jju.domain.Department;
import com.jju.domain.PageModel;
import com.jju.domain.Role;
import com.jju.domain.User;
import com.jju.domain.Userrole;
import com.jju.exception.ServiceException;
import com.jju.formbean.LoginBean;
import com.jju.formbean.UserBean;
import com.jju.service.IUserService;
import com.jju.util.QueryCondition;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new UserDaoImpl();
	private IUserRoleDao userroleDao = new UserRoleDaoImpl();
	private IDepartmentDao departDao = new DepartmentDaoImpl();
	private IRoleDao roleDao = new RoleDaoImpl();

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#add(java.io.Serializable)
	 */
	@Override
	public boolean add(User model) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#delete(java.io.Serializable)
	 */
	@Override
	public boolean delete(Serializable id) throws ServiceException {
		try {
			return userDao.delete(id) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IBaseService#update(java.io.Serializable)
	 */
	@Override
	public boolean update(User model) throws ServiceException {
		try {
			return userDao.update(model) > 0;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IUserService#getList(com.jju.domain.PageModel)
	 */
	@Override
	public void getList(PageModel<UserBean> page) throws ServiceException {
		PageModel<User> rows = new PageModel<>(page.getPageIndex(), page.getPageSize());
		try {
			userDao.getList(rows, QueryCondition.newCondition());
			if (rows.getRows().size() > 0) {
				List<UserBean> beans = new ArrayList<>();
				for (User user : rows.getRows()) {
					UserBean bean = new UserBean();
					try {
						BeanUtils.copyProperties(bean, user);
						Department depart = departDao.get(bean.getDepartId());
						bean.setDepartId(depart.getDepartName());
						PageModel<Userrole> userroles = new PageModel<>(1, 1000);
						userroleDao.getList(userroles,
						        QueryCondition.newCondition().addEqualsField("userId", bean.getId()));
						String[] roleNames = new String[userroles.getRows().size()];
						int index = 0;
						for (Userrole userrole : userroles.getRows()) {
							Role role = roleDao.get(userrole.getRoleId());
							roleNames[index] = role.getRoleName();
							index++;
						}
						bean.setRole(roleNames);
						beans.add(bean);
					}
					catch (IllegalAccessException | InvocationTargetException e) {
						throw new SQLException();
					}
				}
				page.setRows(beans);
			}
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IUserService#addUser(com.jju.formbean.UserBean)
	 */
	@Override
	public boolean addUser(UserBean user) throws ServiceException {
		user.setId(StringUtil.getUUID());
		User model = new User();
		try {
			BeanUtils.copyProperties(model, user);
			if (userDao.add(model) > 0) {
				for (int i = 0; i < user.getRole().length; i++) {
					Userrole userrole = new Userrole();
					userrole.setId(StringUtil.getUUID());
					userrole.setUserId(model.getId());
					userrole.setRoleId(user.getRole()[i]);
					userroleDao.add(userrole);
				}

			} else {
				throw new SQLException();
			}
			return true;
		}
		catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IUserService#updateUser(com.jju.formbean.UserBean)
	 */
	@Override
	public boolean updateUser(UserBean user) throws ServiceException {
		User model = new User();
		try {
			BeanUtils.copyProperties(model, user);
			if (userDao.update(model) > 0) {
				userroleDao.delete(user.getId());
				for (int i = 0; i < user.getRole().length; i++) {
					Userrole userrole = new Userrole();
					userrole.setId(StringUtil.getUUID());
					userrole.setUserId(user.getId());
					userrole.setRoleId(user.getRole()[i]);
					userroleDao.add(userrole);
				}
			} else {
				throw new SQLException();
			}
		}
		catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jju.service.IUserService#login(com.jju.formbean.LoginBean)
	 */
	@Override
	public User login(LoginBean bean) throws ServiceException {
		QueryCondition condition = QueryCondition.newCondition().addEqualsField("username", bean.getUsername())
		        .addEqualsField("password", bean.getPassword());
		PageModel<User> pageModel = new PageModel<>(1, 1);
		try {
			userDao.getList(pageModel, condition);
			if (pageModel.getRows().size() > 0) {
				return pageModel.getRows().get(0);
			}
			return null;
		}
		catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
}
