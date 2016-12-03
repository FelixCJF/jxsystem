package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.StockBean;
import com.jju.service.IStockService;
import com.jju.service.impl.StockServiceImpl;

/**
 * Servlet implementation class StockController
 */
@WebServlet("/stock/stock")
public class StockController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private IStockService stockService = new StockServiceImpl();

	/**
	 * Default constructor.
	 */
	public StockController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/stock/stock.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			String goodsTypeFilter = request.getParameter("goodsTypeFilter");
			String goodsNameFilter = request.getParameter("goodsNameFilter");
			String pageIndex = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageModel<StockBean> page = new PageModel<>(Integer.valueOf(pageIndex), Integer.valueOf(rows));
			try {
				stockService.getList(page, goodsNameFilter, goodsTypeFilter);
				response.getWriter().write(JSON.toJSONString(page));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}
