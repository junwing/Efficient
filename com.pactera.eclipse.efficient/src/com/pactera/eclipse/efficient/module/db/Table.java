package com.pactera.eclipse.efficient.module.db;

import java.util.ArrayList;
import java.util.List;

import com.pactera.eclipse.efficient.template.KeyValues;
import com.pactera.eclipse.efficient.template.TemplateHelper;
import com.pactera.eclipse.efficient.template.TemplateType;
import com.pactera.util.DateUtil;
import com.pactera.util.ListUtil;
import com.pactera.util.StringUtil;

/**
 * @author ruanzr
 * 
 */
public class Table {

	private String chineseName;
	private String englishName;
	private String prefix;
	private List<Column> columns;
	private String description;
	private List<Column> primaryKeys;

	public Table(String chineseName, String englishName, String prefix, String description) {
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.prefix = prefix;
		this.description = description;
		this.columns = new ArrayList<Column>();
		this.primaryKeys = new ArrayList<Column>();
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
			this.primaryKeys.add(column);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Column> getPrimaryKeys() {
		return primaryKeys;
	}

	public String toString() {
		return "Table [chineseName=" + chineseName + ", englishName=" + englishName + ", prefix=" + prefix + ", columns=" + columns + ", description=" + description + "]";
	}

	/**
	 * 生成建表脚本
	 * 
	 * @return 建表脚本
	 */
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
			sql.append(col.getDescription().replaceAll("\\r?\\n", " "));
			sql.append('\n');
		}
		if (getPrimaryKeys().size() > 0) {
			List<String> names = new ArrayList<String>(getPrimaryKeys().size());
			for (Column col : getPrimaryKeys()) {
				names.add(col.getName());
			}
			sql.append('\t').append("PRIMARY KEY(").append(ListUtil.toString(names, ", ")).append(')').append('\n');
		} else {
			sql.deleteCharAt(sql.lastIndexOf(","));
		}
		sql.append(");\n");
		return sql.toString();
	}

	public String toComment() {
		StringBuffer comment = new StringBuffer();
		comment.append("COMMENT ON TABLE ").append(getEnglishName()).append(" IS '").append(getChineseName()).append("';\n");
		for (Column col : getColumns()) {
			comment.append("COMMENT ON COLUMN ").append(getEnglishName()).append(".").append(col.getName()).append(" IS '");
			comment.append(col.getDescription().replaceAll("\\r?\\n", " ")).append("';\n");
		}
		return comment.toString();
	}

	/**
	 * 对只有一个主键的表并且主键后缀为<code>NO</code>的字段生成<code>sequence</code>
	 * 
	 * @return sequence
	 */
	public String toSequence() {
		if (getPrimaryKeys().size() == 1) {
			Column col = getPrimaryKeys().get(0);
			if (col.getName().endsWith("NO")) {
				KeyValues kvs = new KeyValues();
				kvs.addPair("name", getEnglishName() + "_NO");
				kvs.addPair("description", getChineseName().replaceFirst("流水表?", "") + col.getDescription());
				kvs.addPair("date", DateUtil.getFormatedDateTime("yyyy.MM.dd"));
				String lens = StringUtil.findFirst("(?<=\\()\\d+(?=\\))", col.getType());
				int len = StringUtil.isEmpty(lens) ? 10 : Integer.parseInt(lens);
				kvs.addPair("minValue", "1" + StringUtil.repeat('0', len - 1));
				kvs.addPair("maxValue", StringUtil.repeat('9', len));
				return TemplateHelper.getFinalContent(TemplateType.SEQUENCE, kvs);
			}
		}
		return null;
	}

}
