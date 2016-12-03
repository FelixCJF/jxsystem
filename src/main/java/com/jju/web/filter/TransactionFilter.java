
package com.jju.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.jju.formbean.JsonMessage;
import com.jju.util.ConnectionContext;
import com.jju.util.JdbcUtil;

/**
 * @author //TODO
 *
 */
public class TransactionFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (!httpRequest.getMethod().equalsIgnoreCase("get")) {
			Connection conn = JdbcUtil.getConnection();
			ConnectionContext.getInstance().bind(conn);
			try {
				conn.setAutoCommit(false);
				chain.doFilter(request, response);
				conn.commit();
			}
			catch (SQLException | ServletException e) {
				try {
					conn.rollback();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				httpResponse.getWriter().write(JsonMessage.getMessage(JsonMessage.FAILED, "服务器出现错误:" + e.getMessage()));
				/*httpRequest.getSession().setAttribute("exception", e);
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/500.jsp");*/
			}
			finally {
				ConnectionContext.getInstance().remove();
			}
		} else {
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
