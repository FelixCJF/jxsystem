package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.PageModel;
import com.jju.domain.Purchase;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.service.IPurchaseService;
import com.jju.service.impl.PurchaseServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class PurchaseController
 */
@WebServlet("/stock/purchase")
public class PurchaseController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private final String METHOD_REJECT = "reject";
	private final String METHOD_PASS = "pass";
	private final String METHOD_DONE = "done";

	private IPurchaseService purchaseService = new PurchaseServiceImpl();

	/**
	 * Default constructor.
	 */
	public PurchaseController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/stock/purchase.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			String pageIndex = request.getParameter("page");
			String rows = request.getParameter("rows");
			String statusFilter = request.getParameter("statusFilter");
			String purchaseNumFilter = request.getParameter("purchaseNumFilter");
			String goodsNameFilter = request.getParameter("goodsNameFilter");
			String supplierFilter = request.getParameter("supplierFilter");
			PageModel<Purchase> page = new PageModel<>(Integer.valueOf(pageIndex), Integer.valueOf(rows));
			try {
				purchaseService.getList(page, purchaseNumFilter, statusFilter, supplierFilter, goodsNameFilter);
				response.getWriter().write(JSON.toJSONString(page));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Purchase purchase = WebUtil.request2Bean(request, Purchase.class);
			try {
				purchaseService.purchase(purchase);
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
				purchaseService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}

		if (METHOD_REJECT.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				purchaseService.setStatus(id, PurchaseServiceImpl.STATUS_REJECT);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		
		if (METHOD_PASS.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				purchaseService.setStatus(id, PurchaseServiceImpl.STATUS_PASS);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		
		if (METHOD_DONE.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				purchaseService.setStatus(id, PurchaseServiceImpl.STATUS_DONE);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}
