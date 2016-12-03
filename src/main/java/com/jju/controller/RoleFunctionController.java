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
import com.jju.domain.RoleFunction;
import com.jju.exception.ServiceException;
import com.jju.formbean.FunctionParamBean;
import com.jju.formbean.JsonMessage;
import com.jju.service.IFunctionParamService;
import com.jju.service.IRoleFunctionService;
import com.jju.service.impl.FunctionParamServiceImpl;
import com.jju.service.impl.RoleFunctionServiceImpl;

/**
 * Servlet implementation class RoleFunctionController
 */
@WebServlet("/sysconfig/rolefunction")
public class RoleFunctionController extends BaseControlle {
	private final String METHOD_ADDOPER = "addoper";
	private static final long serialVersionUID = 1L;
	private IRoleFunctionService roleFunctionService = new RoleFunctionServiceImpl();
	private IFunctionParamService paramService = new FunctionParamServiceImpl();

	/**
	 * Default constructor.
	 */
	public RoleFunctionController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		response.setStatus(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		String role = request.getParameter("role");
		String functionId = request.getParameter("functionId");
		if (METHOD_ADD.equalsIgnoreCase(method)) {
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
		if (METHOD_GET.equals(method)) {
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
