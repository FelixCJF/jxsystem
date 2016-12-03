
package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.Branch;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.service.IBranchService;
import com.jju.service.impl.BranchServiceImpl;
import com.jju.util.WebUtil;

/**
 * @author //TODO
 *
 */
@WebServlet("/basic/branch")
public class BranchController extends BaseControlle {
	private static final long serialVersionUID = -2243810254722850037L;
	private IBranchService branchService = new BranchServiceImpl();

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/basic/branch.jsp").forward(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			String pageIndex = request.getParameter("page");
			String rows = request.getParameter("rows");
			PageModel<Branch> page = new PageModel<>(Integer.valueOf(pageIndex), Integer.valueOf(rows));
			try {
				branchService.getList(page);
				response.getWriter().write(JSON.toJSONString(page.getRows()));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}

		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Branch branch = WebUtil.request2Bean(request, Branch.class);
			try {
				branchService.add(branch);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}

		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			Branch branch = WebUtil.request2Bean(request, Branch.class);
			try {
				branchService.update(branch);
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
				branchService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}
}
