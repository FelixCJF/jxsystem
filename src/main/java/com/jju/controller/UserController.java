package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.RowSet;

import com.alibaba.fastjson.JSON;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.formbean.UserBean;
import com.jju.service.IUserService;
import com.jju.service.impl.UserService;
import com.jju.util.WebUtil;


public class UserController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserService();

    
    public UserController() {
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/sysconfig/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if (METHOD_GET.equalsIgnoreCase(method)) {
			String pageIndex = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageModel<UserBean> bean = new PageModel<UserBean>(Integer.valueOf(pageIndex), Integer.valueOf(rows));
			try {
				userService.getList(bean);
				response.getWriter().write(JSON.toJSONString(bean.getRows()));
				return;
			} catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			UserBean userBean = WebUtil.request2Bean(request, UserBean.class);
			String[] role = request.getParameterValues("role");
			userBean.setRole(role);
			try {
				userService.addUser(userBean);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "添加成功"));
				return;
			} catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			UserBean userBean = WebUtil.request2Bean(request, UserBean.class);
			String[] roles = request.getParameterValues("role");
			userBean.setRole(roles);
			try {
				userService.updateUser(userBean);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "修改成功"));
				return;
			} catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_DELETE.equalsIgnoreCase(method)) {
			try {
				String id = request.getParameter("id");
				userService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
			} catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}







