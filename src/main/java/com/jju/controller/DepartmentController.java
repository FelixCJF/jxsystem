package com.jju.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.Department;
import com.jju.domain.PageModel;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.service.IDepartmentService;
import com.jju.service.impl.DepartmentServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class DepartmentController
 */
@WebServlet("/sysconfig/department")
public class DepartmentController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private IDepartmentService departmentService = new DepartmentServiceImpl();

	/**
	 * Default constructor.
	 */
	public DepartmentController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/sysconfig/department.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GET.equalsIgnoreCase(method)) {
			PageModel<Department> page = new PageModel<>(1, 10000);
			try {
				departmentService.getList(page);
				response.getWriter().write(JSON.toJSONString(page.getRows()));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_ADD.equalsIgnoreCase(method)) {
			Department department = WebUtil.request2Bean(request, Department.class);
			try {
				departmentService.add(department);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "添加成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
		if (METHOD_UPDATE.equalsIgnoreCase(method)) {
			Department department = WebUtil.request2Bean(request, Department.class);
			try {
				departmentService.update(department);
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
				departmentService.delete(id);
				response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "删除成功"));
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}
	}

}
