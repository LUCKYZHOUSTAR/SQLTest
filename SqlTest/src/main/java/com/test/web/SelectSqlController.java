package com.test.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.test.core.sql.BuildSql;
import com.test.tool.RequestUtils;
import com.test.tool.SqlResultException;
import com.test.tool.Table;
import com.test.tool.SqlUtils;

@Controller
@RequestMapping(value = "/selectsql")
public class SelectSqlController {

	/*
	 * SELECT FILEDS FROM TABLE1 AS ALIAS1,TABLE2 AS ALIAS2 WHERE TABLE1.FIELDS
	 */

	// SELECT * FROM TABLE WHERE A1= AND A2=AND
	// http://localhost:8080/SqlTest/selectsql/simpleSQL?tablename=group&id=1&fieldcount=3
	// http://localhost:8080/SqlTest/selectsql/simpleSQL?tablename=group&id=1&fieldcount=3&name=admin
	// SELECT * FROM GROUP WHERE ID=1
	// select * from test.group where id='1';
	@RequestMapping(value = "simpleSQL")
	@ResponseBody
	public void SimpleSqlSelect(String sql, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ?TABLENAME=&filed=value1&field=value2
		Table table = new Table();
		Map<String, String> paramsMap = RequestUtils.getParamsMaps(request);
		table.setBuildWhere(paramsMap);
		table = SqlUtils.query(BuildSql.bulidSimpleSelectSql(table), table);
		PrintWriter out = response.getWriter();
		System.out.println(table.getResult());
		out.print(JSON.toJSONString(table));
	}
	
	
	//// http://localhost:8080/SqlTest/selectsql/insertSQL?tablename=group&fieldcount=3&name=haha
	@RequestMapping(value = "insertSQL")
	@ResponseBody
	public void SimpleSqlInsert(String sql,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Table table = new Table();
		Map<String, String> paramsMap = RequestUtils.getParamsMaps(request);
		table.setBuildWhere(paramsMap);
		int result =SqlUtils.insert(BuildSql.buildSimpleInsertSql(table), table);
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(result));
	}
}
