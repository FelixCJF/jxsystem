
package com.jju.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jju.domain.Type;
import com.jju.domain.TypeGroup;
import com.jju.exception.ServiceException;
import com.jju.formbean.GrouptypeBean;
import com.jju.formbean.JsonMessage;
import com.jju.service.ITypeGroupService;
import com.jju.service.ITypeService;
import com.jju.service.impl.TypeGroupServiceImpl;
import com.jju.service.impl.TypeServiceImpl;
import com.jju.util.StringUtil;

/**
 * @author //TODO
 *
 */
@WebServlet("/sysconfig/dataconfig")
public class DataConfigController extends BaseControlle {

	private ITypeGroupService typeGroupService = new TypeGroupServiceImpl();
	private ITypeService typeService = new TypeServiceImpl();

	private static final long serialVersionUID = 7042367076091858868L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (METHOD_GET.equalsIgnoreCase(req.getParameter("method"))) {

		} else {
			req.getRequestDispatcher("/WEB-INF/pages/sysconfig/dataconfig.jsp").forward(req, resp);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		String method = request.getParameter("method");
		String optype = request.getParameter("groupoprtype");
		String groupname = request.getParameter("groupname");
		String groupcode = request.getParameter("groupcode");
		String groupId = request.getParameter("groupid");

		if (METHOD_GET.equalsIgnoreCase(method)) {
			response.getWriter().write(getAllTypes());
			return;
		}

		if (METHOD_ADD.equalsIgnoreCase(method)) {
			if ("group".equalsIgnoreCase(optype)) {
				TypeGroup group = new TypeGroup();
				group.setGroupname(groupname);
				group.setGroupcode(groupcode);
				group.setId(StringUtil.getUUID());
				try {
					if (typeGroupService.addGroup(group)) {
						 response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS,
						 "操作成功"));
						 return;
					}
				}
				catch (ServiceException e) {

					throw new ServletException(e);
				}
			} else {
				if (groupId != null && groupId != "") {
					Type type = new Type();
					type.setId(StringUtil.getUUID());
					type.setTypecode(groupcode);
					type.setTypename(groupname);
					type.setGroupid(groupId);
					try {
						if (typeService.addType(type)) {
							response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
							return;
						}
					}
					catch (ServiceException e) {

						throw new ServletException(e);
					}
				}
			}
			return;
		}
		if (METHOD_DELETE.equalsIgnoreCase(method)) {
			try {
				if ("group".equalsIgnoreCase(optype)) {
					if (typeGroupService.delete(groupId)) {
						response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "刪除成功"));
					}
				} else {
					if (typeService.delete(groupId)) {
						response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "刪除成功"));
					}
				}
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}
			return;
		}
		if (METHOD_UPDATE.equals("update")) {
			try {
				if ("group".equalsIgnoreCase(optype)) {
					TypeGroup group = new TypeGroup();
					group.setId(groupId);
					group.setGroupcode(groupcode);
					group.setGroupname(groupname);
					if (typeGroupService.update(group)) {
						response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
					}
				} else {
					Type type = new Type();
					type.setId(groupId);
					type.setTypecode(groupcode);
					type.setTypename(groupname);
					if (typeService.update(type)) {
						response.getWriter().write(JsonMessage.getMessage(JsonMessage.SUCCESS, "操作成功"));
					}
				}
			}
			catch (ServiceException e) {
				throw new ServletException(e);
			}

		}
	}

	private String getAllTypes() throws ServletException {
		try {
			List<GrouptypeBean> beans = new ArrayList<>();
			List<TypeGroup> groups = typeGroupService.getList();
			if (groups != null) {
				for (TypeGroup group : groups) {
					GrouptypeBean parent = new GrouptypeBean();
					parent.setGroupId(group.getId());
					parent.setGroupCode(group.getGroupcode());
					parent.setGroupName(group.getGroupname());
					parent.setLevel("1");
					List<Type> types = typeService.getTypes(group.getId());
					if (types != null) {
						List<GrouptypeBean> typeBeans = new ArrayList<>();
						for (Type type : types) {
							GrouptypeBean children = new GrouptypeBean();
							children.setGroupCode(type.getTypecode());
							children.setGroupId(type.getId());
							children.setGroupName(type.getTypename());
							children.setLevel("2");
							typeBeans.add(children);
						}
						parent.setChildren(typeBeans);
					}
					beans.add(parent);
				}
				String json = JSON.toJSONString(beans);
				return json;
			}

		}
		catch (ServiceException e) {
			throw new ServletException(e);
		}
		return null;
	}

}
