package com.test.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Table {

	List<List<String>> result;
	private String tableName;
	private String primaryKey;
	private List<String> params;
	private Map<String, String> buildWhere;
	private int count;
	

	public Table(){
		this.result=new ArrayList<List<String>>();
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<List<String>> getResult() {
		return result;
	}

	public void setResult(List<List<String>> result) {
		this.result = result;
	}

	public String getTableName() {
		return tableName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public Map<String, String> getBuildWhere() {
		return buildWhere;
	}

	public void setBuildWhere(Map<String, String> buildWhere) throws SqlResultException {
		String tableName = buildWhere.get("tablename");
		String count=buildWhere.get("fieldcount");
		if (tableName != null || StringUtils.isNotBlank(tableName)) {
			this.tableName = buildWhere.get("tablename");
			this.count=Integer.valueOf(count);
			buildWhere.remove("tablename");
			buildWhere.remove("fieldcount");
		}else {
			throw  new SqlResultException("the tablename is not null");
		}
		this.buildWhere = buildWhere;
	}
	
	

}
