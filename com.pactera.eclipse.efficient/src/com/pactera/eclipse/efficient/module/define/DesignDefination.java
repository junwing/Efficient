package com.pactera.eclipse.efficient.module.define;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.pactera.eclipse.efficient.module.AbstractDefination;
import com.pactera.util.StringUtil;

/**
 * 设计定义，主要包括biz定义和mvc定义
 * 
 * @author ruanzr
 * 
 */
public class DesignDefination extends AbstractDefination {

	private File srcFile;
	private double version;
	private String projectName;
	private String requirementName;
	private String tableFileName;

	private Map<String, MenuDefination> menus;
	private Map<String, BizDefination> bizs;
	private Map<String, MvcDefination> mvcs;

	public DesignDefination() {
		init();
	}

	public DesignDefination(String projectName) {
		this.projectName = projectName;
		init();
	}

	private void init() {
		this.menus = new TreeMap<String, MenuDefination>();
		this.bizs = new HashMap<String, BizDefination>();
		this.mvcs = new HashMap<String, MvcDefination>();
	}

	public File getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}

	public void addMenu(String bsnCode, MenuDefination define) {
		this.menus.put(bsnCode, define);
	}

	public void addBiz(String bizName, BizDefination define) {
		this.bizs.put(bizName, define);
	}

	public void addMvc(String mvcName, MvcDefination define) {
		this.mvcs.put(mvcName, define);
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRequirementName() {
		return requirementName;
	}

	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}

	public String getTableFileName() {
		return tableFileName;
	}

	public void setTableFileName(String tableFileName) {
		this.tableFileName = tableFileName;
	}

	public Map<String, MenuDefination> getMenus() {
		return menus;
	}

	public Map<String, BizDefination> getBizs() {
		return bizs;
	}

	public BizDefination getBiz(String bizName) {
		return this.bizs.get(bizName);
	}

	public Map<String, MvcDefination> getMvcs() {
		return mvcs;
	}

	public MvcDefination getMvc(String mvcName) {
		return this.mvcs.get(mvcName);
	}

	public String[] getLOAFTables() {
		Set<String> tables = new HashSet<String>();
		for (String bsnCode : this.menus.keySet()) {
			MenuDefination menu = this.menus.get(bsnCode);
			if (menu.isSubmitAndAuth() && !StringUtil.isEmpty(menu.getTableName())) {
				tables.add(menu.getTableName());
			}
		}
		return tables.toArray(new String[0]);
	}

	public String toTaskInfo() {
		if (this.menus.size() > 0) {
			StringBuffer tsk = new StringBuffer();
			for (String bsnCode : this.menus.keySet()) {
				MenuDefination menu = this.menus.get(bsnCode);
				if (tsk.length() == 0) {
					tsk.append("<TaskGroup id=\"" + menu.getBsnCode().substring(0, 4) + "\" name=\"" + this.getRequirementName() + "\">\n");
				}
				tsk.append(menu.toXml()).append('\n');
			}
			tsk.append("</TaskGroup>\n\n");
			return tsk.toString();
		}
		return null;
	}

	public String toString() {
		return "DesignDefination [projectName=" + projectName + ", requirementName=" + requirementName + ", directoryName=" + directoryName + ", menus=" + menus + ", bizs=" + bizs
				+ ", mvcs=" + mvcs + "]";
	}

}
