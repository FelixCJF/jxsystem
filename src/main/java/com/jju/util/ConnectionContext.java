/**
 * 
 */
package com.jju.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author //TODO
 *
 */
public class ConnectionContext {
	private static ConnectionContext instance;
	private ThreadLocal<Connection> local;

	static {
		instance = new ConnectionContext();
	}

	private ConnectionContext() {
		local = new ThreadLocal<>();
	}

	public static ConnectionContext getInstance() {
		return instance;
	}

	public void bind(Connection conn) {
		local.set(conn);
	}

	public Connection get() {
		return local.get();
	}

	public void remove() {
		Connection conn = local.get();
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		local.remove();
	}
}
