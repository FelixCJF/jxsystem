package com.jju.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class QueryCondition {
	public static final String ORDER_ASC = "asc";
	public static final String ORDER_DESC = "desc";
	
	private LinkedHashMap<Object, Object> equalsFields;
	private LinkedHashMap<String, String> likeFields;
	private LinkedHashMap<String, Object[]> betweenFields;
	private LinkedHashMap<String, String> orderFields;
	private List<Object> params;

	private QueryCondition() {
		equalsFields = new LinkedHashMap();
		likeFields = new LinkedHashMap();
		betweenFields = new LinkedHashMap();
		orderFields = new LinkedHashMap();
		params = new ArrayList();
	}
	public static QueryCondition newCondition() {
		return new QueryCondition();
	}

	public QueryCondition addEqualsField(Object field, Object value) {
		this.equalsFields.put(field, value);
		return this;
	}

	public QueryCondition addLikeField(String field, String value) {
		this.likeFields.put(field, value);
		return this;
	}

	public QueryCondition addOrderField(String field, String value) {
		this.orderFields.put(field, value);
		return this;
	}

	public QueryCondition addBetweenField(String field, Object[] values) {
		this.betweenFields.put(field, values);
		return this;
	}

	public String buildWhereSql() {
		StringBuilder sql = new StringBuilder();
		params.clear();
		if (equalsFields.size() > 0) {
			sql.append(" WHERE ");
			for (Entry<Object, Object> item : equalsFields.entrySet()) {
				if (item.getValue() != null && !"".equalsIgnoreCase(item.getValue().toString().trim())) {
					sql.append(item.getKey());
					sql.append(" = ? AND ");
					params.add(item.getValue());
				}
			}
		}

		if (likeFields.size() > 0) {
			if (sql.length() == 0) {
				sql.append(" WHERE ");
			}
			for (Entry<String, String> item : likeFields.entrySet()) {
				if (item.getValue() != null && !"".equalsIgnoreCase(item.getValue().trim())) {
					sql.append(item.getKey());
					sql.append(" LIKE ? AND ");
					params.add("%" + item.getValue() + "%");
				}
			}
		}

		if (betweenFields.size() > 0) {
			if (sql.length() == 0) {
				sql.append(" WHERE ");
			}
			for (Entry<String, Object[]> item : betweenFields.entrySet()) {
				if (item.getValue() != null && item.getValue().length >= 2) {
					boolean sign = true;
					for (int i = 0; i < 2; i++) {
						if (item.getValue()[i] == null || "".equalsIgnoreCase(item.getValue()[i].toString())) {
							sign = false;
							break;
						}
					}
					if (sign) {
						sql.append(item.getKey());
						sql.append(" BETWEEN ? AND ? AND");
						params.add(item.getValue()[0]);
						params.add(item.getValue()[1]);
					}
				}
			}
		}

		if (params.size() > 0) {
			sql.delete(sql.length() - 4, sql.length());
		} else if (sql.length() > 0) {
			sql.delete(sql.length() - 6, sql.length());
		}
		return sql.toString();
	}

	public String buildLimitSql(int pageIndex, int pageSize) {
		StringBuilder sql = new StringBuilder();
		int startIndex = (pageIndex - 1) * pageSize;
		int endIndex = (pageIndex * pageSize);
		sql.append(" LIMIT ");
		sql.append(startIndex);
		sql.append(" , ");
		sql.append(endIndex);
		return sql.toString();
	}

	public String buildOrderSql() {
		if (orderFields.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(" ORDER BY ");
			for (Entry<String, String> item : orderFields.entrySet()) {
				if (item.getKey() != null && !"".equals(item.getKey())) {
					sb.append(item.getKey());
					sb.append("  ");
					sb.append(item.getValue());
					sb.append(",");
				}
			}
			sb.delete(sb.length() - 1, sb.length());
			return sb.toString();
		}
		return "";
	}

	public List<Object> getParams() {
		return params;
	}
}
