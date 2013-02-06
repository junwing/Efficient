package com.pactera.eclipse.efficient.module.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pactera.util.StringUtil;

/**
 * @author Install
 * 
 */
public class Column {

	static final Pattern DEFAULT_VALUE_PATTERN = Pattern.compile("'?(\\w+)'?");

	private String name;
	private String type;
	private String isNull;
	private String constraint;
	private String defaultVal;
	private String description;

	public Column() {
	}

	public Column(String name, String type, String isNull, String constraint, String description) {
		this.name = upperCase(name);
		this.type = upperCase(type);
		this.isNull = upperCase(isNull);
		this.constraint = constraint;
		this.setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		if (defaultVal != null && getType().startsWith("VARCHAR") && !defaultVal.contains("'")) {
			defaultVal = "'" + defaultVal + "'";
		}
		this.defaultVal = defaultVal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		if (!StringUtil.isEmpty(description)) {
			setDefaultVal(fetchDefaultValue(description));
		}
	}

	private static String fetchDefaultValue(String desc) {
		int index = desc.indexOf("Ä¬ÈÏ");
		if (index > -1) {
			Matcher matcher = DEFAULT_VALUE_PATTERN.matcher(desc.replaceAll("[¡®¡¯]", "'").substring(index));
			if (matcher.find()) {
				return matcher.group();
			}
		}
		return null;
	}

	private static String upperCase(String s) {
		return StringUtil.nvl(s).toUpperCase();
	}

	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", isNull=" + isNull + ", constraint=" + constraint + ", defaultVal=" + defaultVal + ", description=" + description
				+ "]";
	}

}
