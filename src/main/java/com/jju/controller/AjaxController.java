package com.jju.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.jju.domain.Function;
import com.jju.domain.Type;
import com.jju.exception.ServiceException;
import com.jju.formbean.GrouptypeBean;
import com.jju.formbean.UserFunction;
import com.jju.formbean.UserFunctionBean;
import com.jju.service.IFunctionService;
import com.jju.service.ITypeService;
import com.jju.service.impl.FunctionServiceImpl;
import com.jju.service.impl.TypeServiceImpl;


/**
 * Servlet implementation class AjaxController
 */
@WebServlet("/ajax")
public class AjaxController extends HttpServlet {
	private final String METHOD_GETTYPES = "gettypes";
	private final String METHOD_GETFUNTREE = "getfuntree";
	private static final long serialVersionUID = 1L;
	private ITypeService typeService = new TypeServiceImpl();
	private IFunctionService functionService = new FunctionServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxController() {
		super();
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		if (METHOD_GETFUNTREE.equalsIgnoreCase(method)) {
			List<UserFunctionBean> tree = new ArrayList<UserFunctionBean>();
			try {
				List<Function> parentList = functionService.getParentList();
				for (Function parent : parentList) {
					UserFunctionBean bean = new UserFunctionBean();
					try {
						BeanUtils.copyProperties(bean, parent);
						bean.setChildren(new ArrayList<UserFunctionBean>());
						tree.add(bean);
					}
					catch (Exception e) {
						throw new ServletException(e);
					}
				}
				List<UserFunction> userfunction = (List<UserFunction>) request.getSession()
				        .getAttribute("userFunctions");
				for (int i = 0; i < userfunction.size(); i++) {
					UserFunction function = userfunction.get(i);
					for (UserFunctionBean parent : tree) {
						if (function.getParentFunction().equals(parent.getId())) {
							UserFunctionBean bean = new UserFunctionBean();
							try {
								BeanUtils.copyProperties(bean, function);
								bean.setFunctionUrl(request.getContextPath() + "/" + bean.getFunctionUrl());
								parent.getChildren().add(bean);
							}
							catch (Exception e) {
								throw new ServletException(e);
							}
						}
					}
				}
				Iterator<UserFunctionBean> iter = tree.iterator();
				while (iter.hasNext()) {
					UserFunctionBean bean = iter.next();
					if (bean.getChildren().size() == 0) {
						iter.remove();
					}
				}
				response.getWriter().write(JSON.toJSONString(tree));
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
		if (METHOD_GETTYPES.equalsIgnoreCase(method)) {
			String groupCode = request.getParameter("groupCode");
			if (groupCode == null || groupCode.equals("")) {
				throw new ServletException("缺少参数");
			} else {
				try {
					List<Type> types = typeService.getTypesByCode(groupCode);
					if (types != null && types.size() > 0) {
						List<GrouptypeBean> beans = new ArrayList<>();
						for (Type item : types) {
							GrouptypeBean bean = new GrouptypeBean();
							bean.setGroupId(item.getId());
							bean.setGroupName(item.getTypename());
							bean.setGroupCode(item.getTypecode());
							beans.add(bean);
						}
						response.getWriter().write(JSON.toJSONString(beans));
					}
				}
				catch (ServiceException e) {
					throw new ServletException(e);
				}
			}

		}
	}

}
