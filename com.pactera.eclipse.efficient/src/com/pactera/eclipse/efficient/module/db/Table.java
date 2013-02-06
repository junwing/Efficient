package com.pactera.eclipse.efficient.module.db;

import java.util.ArrayList;
import java.util.List;

import com.pactera.util.DateUtil;
import com.pactera.util.ListUtil;
import com.pactera.util.StringUtil;

public class Table {

	private String chineseName;
	private String englishName;
	private String prefix;
	private List<Column> columns;
	private String description;
	private List<String> primaryKeys;

	public Table(String chineseName, String englishName, String prefix, String description) {
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.prefix = prefix;
		this.description = description;
		this.columns = new ArrayList<Column>();
		this.primaryKeys = new ArrayList<String>();
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void addColumn(Column column) {
		this.columns.add(column);
		if ("主键".equals(column.getConstraint())) {
			this.primaryKeys.add(column.getName());
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public String toString() {
		return "Table [chineseName=" + chineseName + ", englishName=" + englishName + ", prefix=" + prefix + ", columns=" + columns + ", description=" + description + "]";
	}

	public String toSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("--------------------------------------\n");
		sql.append("--  表名称--").append(getChineseName()).append('\n');
		sql.append("--  适用数据库：ORACLE\n");
		sql.append("--  字段前缀 ： ").append(getPrefix()).append('\n');
		sql.append("--  最后修改人：").append('\n');
		sql.append("--  最后修改日期： ").append(DateUtil.getFormatedDateTime("yyyy.MM.dd")).append('\n');
		sql.append("--------------------------------------\n");
		sql.append("DROP TABLE ").append(getEnglishName()).append(";\n");
		sql.append('\n');
		sql.append("CREATE TABLE ").append(getEnglishName()).append(" (\n");
		for (Column col : getColumns()) {
			sql.append('\t');
			sql.append(StringUtil.paddingTab(col.getName(), 6));
			sql.append(StringUtil.paddingTab(col.getType(), 5));
			String defaultVal = col.getDefaultVal();
			if (!StringUtil.isEmpty(defaultVal)) {
				defaultVal = "DEFAULT " + defaultVal;
			}
			sql.append(StringUtil.paddingTab(defaultVal, 4));
			sql.append(StringUtil.paddingTab(col.getIsNull(), 2));
			sql.append(',');
			sql.append('\t');
			sql.append("--");
			sql.append(col.getDescription());
			sql.append('\n');
		}
		if (getPrimaryKeys().size() > 0) {
			sql.append('\t').append("PRIMARY KEY(").append(ListUtil.toString(getPrimaryKeys(), ", ")).append(')').append('\n');
		} else {
			sql.deleteCharAt(sql.lastIndexOf(","));
		}
		sql.append(");\n");
		return sql.toString();
	}

}
