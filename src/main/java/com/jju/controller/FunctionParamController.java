package com.jju.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.FunctionParam;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.service.IFunctionParamService;
import com.jju.service.impl.FunctionParamServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class FunctionParamController
 */
@WebServlet("/sysconfig/function/param")
public class FunctionParamController extends BaseControlle {
	private IFunctionParamService paramService = new FunctionParamServiceImpl();
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FunctionParamController() {
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
		if (METHOD_GET.equalsIgnoreCase(method)) {
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
		if (METHOD_ADD.equalsIgnoreCase(method)) {
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
		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
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
		if (METHOD_DELETE.equalsIgnoreCase(method)) {
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
