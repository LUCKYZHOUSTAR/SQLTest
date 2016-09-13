package com.test.tool;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlUtils {

	public static Table query(String sql, Table table) throws Exception {
		// 注册驱动
		// 创建数据库连接对象
		java.sql.Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			for (int i = 0; i < table.getCount(); i++) {
				List<String> result = new ArrayList<String>();
				result.add(rs.getString(i + 1));
				table.getResult().add(result);
			}

		}
		return table;

	}

	public static int insert(String sql, Table table) throws Exception {
		// 注册驱动
		// 创建数据库连接对象
		java.sql.Connection connection = DBUtils.getConnection();
		Statement statement = connection.createStatement();
		return statement.executeUpdate(sql);

	}
}
