package com.jju.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jju.formbean.JsonMessage;
import com.jju.formbean.UserFunction;


public class AuthFilter implements Filter {
	private List<String> noCheckUrl;
	private List<String> noCheckMethod;
	private List<String> onlyLoginReadUrl;
	private String loginPath;

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		noCheckUrl = new ArrayList<String>();
		noCheckMethod = new ArrayList<>();
		onlyLoginReadUrl = new ArrayList<>();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getServletPath().toLowerCase();
		String uri = httpRequest.getRequestURI().toLowerCase();
		if (uri.contains("plug-ins/")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		if (noCheckUrl.contains(url)) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect(loginPath);
			} else {
				List<UserFunction> userFunctions = (List<UserFunction>) httpRequest.getSession()
				        .getAttribute("userFunctions");
				if (userFunctions == null) {
					httpResponse.sendRedirect(loginPath);
					return;
				}
				if (onlyLoginReadUrl.contains(url)) {
					chain.doFilter(httpRequest, httpResponse);
					return;
				}
				String method = httpRequest.getParameter("method");
				for (UserFunction item : userFunctions) {
					if (item.getFunctionUrl().equalsIgnoreCase(url)) {
						String oper = item.getOperation();
						if (method != null) {
							for (String methodItem : noCheckMethod) {
								if (method.contains(methodItem)) {
									chain.doFilter(httpRequest, httpResponse);
									return;
								}
							}
							String[] opers = oper.split(",");
							for (int i = 0; i < opers.length; i++) {
								if (method.contains(opers[i])) {
									chain.doFilter(httpRequest, httpResponse);
									return;
								}
							}
							httpResponse.getWriter().write(JsonMessage.getMessage(JsonMessage.FAILED, "你没有该权限"));
							return;
						} else {
							chain.doFilter(httpRequest, httpResponse);
							return;
						}
					}
				}
				httpResponse.sendRedirect(loginPath);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String[] nocheckUrlarrs = fConfig.getInitParameter("nocheck").split(",");
		for (int i = 0; i < nocheckUrlarrs.length; i++) {
			noCheckUrl.add(nocheckUrlarrs[i].toLowerCase());
		}
		String[] onlyLoginUrls = fConfig.getInitParameter("onlylogin").split(",");
		for (int i = 0; i < onlyLoginUrls.length; i++) {
			onlyLoginReadUrl.add(onlyLoginUrls[i].toLowerCase());
		}
		String[] nocheckMethods = fConfig.getInitParameter("nocheckMethod").split(",");
		for (int i = 0; i < nocheckMethods.length; i++) {
			noCheckMethod.add(nocheckMethods[i].toLowerCase());
		}
		loginPath = fConfig.getServletContext().getContextPath() + "/" + fConfig.getInitParameter("loginpath");
	}

}
