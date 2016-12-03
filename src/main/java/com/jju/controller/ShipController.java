package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.PageModel;
import com.jju.domain.Ship;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.service.IShipService;
import com.jju.service.impl.ShipServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class ShipController
 */
@WebServlet("/stock/ship")
public class ShipController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private final String METHOD_REJECT = "reject";
	private final String METHOD_PASS = "pass";
	private final String METHOD_DONE = "done";
	private IShipService shipService = new ShipServiceImpl();

	/**
	 * Default constructor.
	 */
	public ShipController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/stock/ship.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			String goodsNameFilter = request.getParameter("goodsNameFilter");
			String statusFilter = request.getParameter("statusFilter");
			String branchFilter = request.getParameter("branchFilter");
			String beginDate = request.getParameter("beginDateFilter");
			String endDate = request.getParameter("endDateFilter");
			String pageIndex = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageModel<Ship> page = new PageModel<>(Integer.valueOf(pageIndex), Integer.valueOf(rows));
			try {
				shipService.getList(page, statusFilter, goodsNameFilter, branchFilter, beginDate, endDate);
				response.getWriter().write(JSON.toJSONString(page.getRows()));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Ship ship = WebUtil.request2Bean(request, Ship.class);
			try {
				if (shipService.add(ship)) {
					response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "出库成功"));
					return;
				}
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_DELETE.equalsIgnoreCase(method)) {
			String id = request.getParameter("id");
			try {
				shipService.delete(id);
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
				shipService.setStatus(id, ShipServiceImpl.STATUS_DONE);
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
				shipService.setStatus(id, ShipServiceImpl.STATUS_PASS);
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
				shipService.setStatus(id, ShipServiceImpl.STATUS_REJECT);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}
