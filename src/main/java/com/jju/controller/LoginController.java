package com.jju.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jju.domain.User;
import com.jju.exception.ServiceException;
import com.jju.formbean.JsonMessage;
import com.jju.formbean.LoginBean;
import com.jju.formbean.UserFunction;
import com.jju.service.IFunctionService;
import com.jju.service.IUserService;
import com.jju.service.impl.FunctionServiceImpl;
import com.jju.service.impl.UserServiceImpl;
import com.jju.util.WebUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends BaseControlle {
	private static final long serialVersionUID = 1L;
	private final String METHOD_LOGIN = "login";
	private final String METHOD_LOGOUT = "loginout";
	private IUserService userService = new UserServiceImpl();
	private IFunctionService functionService = new FunctionServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_LOGOUT.equalsIgnoreCase(method)) {
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("userFunctions");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_LOGIN.equalsIgnoreCase(method)) {
			LoginBean bean = WebUtil.request2Bean(request, LoginBean.class);
			try {
				User user = userService.login(bean);
				if (user == null) {
					response.getWriter().write(JsonMessage.getMessage(JsonMessage.FAILED, "用户名或密码错误"));
				} else {
					response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, ""));
					request.getSession().setAttribute("user", user);
					List<UserFunction> userFuns = functionService.getUserFunction(user.getId());
					request.getSession().setAttribute("userFunctions", userFuns);
				}
				return;
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
		}

	}

}
