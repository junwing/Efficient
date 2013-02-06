package com.pactera.eclipse.efficient.module.define;

import java.util.HashMap;
import java.util.Map;

import com.pactera.eclipse.efficient.module.AbstractDefination;

/**
 * 设计定义，主要包括biz定义和mvc定义
 * 
 * @author ruanzr
 * 
 */
public class DesignDefination extends AbstractDefination {

	private String projectName;
	private String requirementName;
	private String directoryName;

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
		this.bizs = new HashMap<String, BizDefination>();
		this.mvcs = new HashMap<String, MvcDefination>();
	}

	public void AddBiz(String bizName, BizDefination define) {
		this.bizs.put(bizName, define);
	}

	public void AddMvc(String mvcName, MvcDefination define) {
		this.mvcs.put(mvcName, define);
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

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
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

	public String toString() {
		return "DesignDefine [\nprojectName=" + projectName + ", \nrequirementName=" + requirementName + ", \ndirectoryName=" + directoryName + ", \nbizs=" + bizs + ", \nmvcs="
				+ mvcs + "\n]";
	}

}
