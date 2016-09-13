package com.test.core.sql;

import com.test.tool.Table;

public class BuildSql {

	public static String bulidSimpleSelectSql(Table table) {
		StringBuffer SqlSelectSb = new StringBuffer("SELECT * FROM ");
		if (table == null) {
			return null;
		}
		//
		SqlSelectSb.append("test." + table.getTableName()).append(" where ");
		for (String filed : table.getBuildWhere().keySet()) {
			SqlSelectSb.append(filed).append(" = ")
					.append("'" + table.getBuildWhere().get(filed) + "'")
					.append(" AND ");
		}
		getStringBufferLimit(SqlSelectSb);
		return SqlSelectSb.toString();

	}

	public static String buildSimpleInsertSql(Table table) {
		StringBuffer SqlSelectSb = new StringBuffer("INSERT INTO  ");
		if (table == null || table.getBuildWhere().size() == 0
				|| table.getBuildWhere() == null) {
			return null;
		}

		SqlSelectSb.append("test." + table.getTableName()).append(" ( ");
		for (String filed : table.getBuildWhere().keySet()) {
			SqlSelectSb.append(filed).append(" , ");
		}
		SqlSelectSb.delete(SqlSelectSb.length()-2, SqlSelectSb.length());
		SqlSelectSb.append(" ) ").append(" VALUES ").append(" ( ");
		for (String filed : table.getBuildWhere().keySet()) {
			SqlSelectSb.append("'"+table.getBuildWhere().get(filed)+"'").append(" , ");
		}
		SqlSelectSb.delete(SqlSelectSb.length()-2, SqlSelectSb.length());
		SqlSelectSb.append(" ) "); 
		return SqlSelectSb.toString();

	}

	public static StringBuffer getStringBufferLimit(StringBuffer sb) {
		sb.delete(sb.length() - 5, sb.length());
		return sb;
	}
}
