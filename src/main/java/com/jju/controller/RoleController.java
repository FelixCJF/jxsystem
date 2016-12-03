
package com.jju.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.jju.domain.FunctionParam;
import com.jju.domain.PageModel;
import com.jju.domain.Role;
import com.jju.domain.RoleFunction;
import com.jju.exception.ServiceException;
import com.jju.formbean.FunctionParamBean;
import com.jju.formbean.JsonMessage;
import com.jju.service.IFunctionParamService;
import com.jju.service.IRoleFunctionService;
import com.jju.service.IRoleService;
import com.jju.service.impl.FunctionParamServiceImpl;
import com.jju.service.impl.RoleFunctionServiceImpl;
import com.jju.service.impl.RoleServiceImpl;
import com.jju.util.WebUtil;

/**
 * @author //TODO
 *
 */
@WebServlet("/sysconfig/role")
public class RoleController extends BaseControlle {
	private static final long serialVersionUID = -5668868711413899447L;
	private final String METHOD_ADDOPER = "addoper";
	private final String METHOD_ADDROLEFUNC = "addrolefun";
	private final String METHOD_GETROLEFUN = "getrolefun";

	private IRoleFunctionService roleFunctionService = new RoleFunctionServiceImpl();
	private IFunctionParamService paramService = new FunctionParamServiceImpl();
	private IRoleService roleService = new RoleServiceImpl();

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/sysconfig/role.jsp").forward(request, resp);
		;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			PageModel<Role> page = new PageModel<>(1, 1000);
			try {
				roleService.getList(page);
				response.getWriter().write(JSON.toJSONString(page.getRows()));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			try {
				Role role = WebUtil.request2Bean(request, Role.class);
				roleService.add(role);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "添加成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			try {
				Role role = WebUtil.request2Bean(request, Role.class);
				roleService.update(role);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "修改成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
		if (METHOD_DELETE.equalsIgnoreCase(method)) {
			try {
				String id = request.getParameter("id");
				roleService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
		String role = request.getParameter("role");
		String functionId = request.getParameter("functionId");
		if (METHOD_ADDROLEFUNC.equalsIgnoreCase(method)) {
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setFunctionId(functionId);
			roleFunction.setRoleId(role);
			try {
				roleFunctionService.add(roleFunction);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "更新成功"));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADDOPER.equalsIgnoreCase(method)) {
			RoleFunction roleFunction = new RoleFunction();
			roleFunction.setFunctionId(functionId);
			roleFunction.setRoleId(role);
			roleFunction.setOperation(request.getParameter("oper"));
			try {
				roleFunctionService.addOperation(roleFunction);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
		if (METHOD_GETROLEFUN.equals(method)) {
			try {
				List<FunctionParam> params = paramService.getList(functionId);
				if (params.size() > 0) {
					RoleFunction roleFunction = roleFunctionService.get(functionId, role);
					List<FunctionParamBean> beans = new ArrayList<FunctionParamBean>();
					for (FunctionParam param : params) {
						FunctionParamBean bean = new FunctionParamBean();
						try {
							BeanUtils.copyProperties(bean, param);
							beans.add(bean);
						}
						catch (IllegalAccessException | InvocationTargetException e) {
							throw new ServletException(e);
						}
					}
					if (roleFunction != null && roleFunction.getOperation() != null) {
						String[] opt = roleFunction.getOperation().split(",");
						if (opt.length > 0) {
							for (FunctionParamBean bean : beans) {
								for (int i = 0; i < opt.length; i++) {
									if (bean.getParamCode().equalsIgnoreCase(opt[i])) {
										bean.setChecked(true);
									}
								}
							}
						}
					}
					response.getWriter().write(JSON.toJSONString(beans));
					return;
				}
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}
}
