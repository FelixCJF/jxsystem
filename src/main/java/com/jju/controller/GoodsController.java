package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.Goods;
import com.jju.domain.PageModel;
import com.jju.domain.Type;
import com.jju.exception.ServiceException;
import com.jju.formbean.GoodsBean;
import com.jju.formbean.GrouptypeBean;
import com.jju.formbean.JsonMessage;
import com.jju.service.IGoodsService;
import com.jju.service.ITypeService;
import com.jju.service.impl.GoodsServiceImpl;
import com.jju.service.impl.TypeServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class GoodsController
 */
@WebServlet("/basic/goods")
public class GoodsController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private IGoodsService goodsService = new GoodsServiceImpl();
	private ITypeService typeService = new TypeServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/basic/goods.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			int index = Integer.parseInt(request.getParameter("page"));
			int size = Integer.parseInt(request.getParameter("rows"));
			String type = request.getParameter("type");
			String searchKey = request.getParameter("searchKey");
			PageModel<GoodsBean> page = new PageModel<>(index, size);
			try {
				goodsService.getList(page, type, searchKey);
				response.getWriter().write(JSON.toJSONString(page));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
			return;
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Goods goods = WebUtil.request2Bean(request, Goods.class);
			try {
				goodsService.add(goods);
				response.getWriter().write(returnGoodsJson(goods));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			Goods goods = WebUtil.request2Bean(request, Goods.class);
			try {
				goodsService.update(goods);
				response.getWriter().write(returnGoodsJson(goods));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_DELETE.equals(method)) {
			String id = request.getParameter("id");
			try {
				if (goodsService.delete(id)) {
					response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
				}
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

	private String returnGoodsJson(Goods goods) throws ServletException {
		Type type;
		try {
			type = typeService.get(goods.getGoodsType());
			GoodsBean bean = new GoodsBean();
			GrouptypeBean typebean = new GrouptypeBean();
			typebean.setGroupId(type.getId());
			typebean.setGroupName(type.getTypename());
			bean.setCostPrice(goods.getCostPrice().doubleValue());
			bean.setGoodsName(goods.getGoodsName());
			bean.setGoodsType(typebean);
			bean.setRemark(goods.getRemark());
			bean.setId(goods.getId());
			return JSON.toJSONString(bean);
		}
		catch (ServiceException e) {
			throw new ServletException(e);
		}

	}

}
