
package com.jju.util;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtil {
	static {
		ConvertUtils.register(new Converter() {
			@Override
			@SuppressWarnings("rawtypes")
			public Object convert(Class type, Object value) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return format.parse(value.toString());
				}
				catch (Exception e) {

				}
				return null;
			}
		}, Date.class);
	}

	public static <T> T request2Bean(HttpServletRequest request, Class<T> claszz) throws ServletException {
		try {
			T bean = claszz.newInstance();
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = request.getParameter(name);
				try {
					BeanUtils.setProperty(bean, name, value);
				}
				catch (InvocationTargetException e) {
					throw new ServletException(e);
				}
			}
			return bean;
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}

	}
}
