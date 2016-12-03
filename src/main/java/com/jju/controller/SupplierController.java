package com.jju.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.PageModel;
import com.jju.domain.Supplier;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.service.ISupplierService;
import com.jju.service.impl.SupplierServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/basic/supplier")
public class SupplierController extends BaseControlle implements Servlet {
	private static final long serialVersionUID = 1L;
	private ISupplierService supplierService = new SupplierServiceImpl();

	/**
	 * Default constructor.
	 */
	public SupplierController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/basic/supplier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			PageModel<Supplier> page = new PageModel<>(Integer.valueOf(pageStr), Integer.valueOf(rowsStr));
			String nameFilter = request.getParameter("nameFilter");
			String numFilter = request.getParameter("numFilter");
			try {
				supplierService.getList(page, nameFilter, numFilter);
				response.getWriter().write(JSON.toJSONString(page.getRows()));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Supplier supplier = WebUtil.request2Bean(request, Supplier.class);
			try {
				supplierService.add(supplier);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}

		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			Supplier supplier = WebUtil.request2Bean(request, Supplier.class);
			try {
				supplierService.update(supplier);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}

		if (METHOD_DELETE.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				supplierService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}
