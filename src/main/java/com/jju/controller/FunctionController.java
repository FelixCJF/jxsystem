package com.jju.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.Function;
import com.jju.domain.FunctionParam;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.FunctionBean;
import com.jju.formbean.JsonMessage;
import com.jju.service.IFunctionParamService;
import com.jju.service.IFunctionService;
import com.jju.service.impl.FunctionParamServiceImpl;
import com.jju.service.impl.FunctionServiceImpl;
import com.jju.util.WebUtil;


@WebServlet("/sysconfig/function")
public class FunctionController extends BaseControlle {
	private final String METHOD_GETPARENT = "getparent";
	private final String METHOD_GETPARAM = "getparam";
	private final String METHOD_ADDPARAM = "addparam";
	private final String METHOD_UPDATEPARAM = "updateparam";
	private final String METHOD_DELETEPARAM = "deleteparam";
	private static final long serialVersionUID = 1L;
	private IFunctionService functionService = new FunctionServiceImpl();
	private IFunctionParamService paramService = new FunctionParamServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FunctionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/sysconfig/function.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			PageModel<FunctionBean> page = new PageModel<>();
			String roleId = request.getParameter("roleId");
			try {
				if (roleId == null || "".equals(roleId)) {
					functionService.getList(page);
				} else {
					functionService.getList(page, roleId);
				}

				response.getWriter().write(JSON.toJSONString(page.getRows()));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_GETPARENT.equalsIgnoreCase(method)) {
			try {
				List<Function> result = functionService.getParentList();
				response.getWriter().write(JSON.toJSONString(result));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Function function = WebUtil.request2Bean(request, Function.class);
			try {
				functionService.add(function);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "添加成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			Function function = WebUtil.request2Bean(request, Function.class);
			try {
				functionService.update(function);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "修改成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_DELETE.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				functionService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_GETPARAM.equalsIgnoreCase(method)) {
			String functionId = request.getParameter("functionId");
			try {
				List<FunctionParam> params = paramService.getList(functionId);
				response.getWriter().write(JSON.toJSONString(params));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADDPARAM.equalsIgnoreCase(method)) {
			FunctionParam param = WebUtil.request2Bean(request, FunctionParam.class);
			try {
				paramService.add(param);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "添加成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_UPDATEPARAM.equalsIgnoreCase(method)) {
			FunctionParam param = WebUtil.request2Bean(request, FunctionParam.class);
			try {
				paramService.update(param);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "修改成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_DELETEPARAM.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				paramService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}
