package com.pactera.eclipse.efficient.module.define;

import java.util.ArrayList;
import java.util.List;

import com.pactera.eclipse.efficient.module.Defination;
import com.pactera.eclipse.efficient.template.TemplateHelper;

public class BizDefination implements Defination {

	private String bizName;
	private String bizId;
	private List<FlowDefination> flows;

	public BizDefination(String bizName, String bizId) {
		this.bizName = bizName;
		this.bizId = bizId;
		this.flows = new ArrayList<FlowDefination>();
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void addFlow(FlowDefination flow) {
		this.flows.add(flow);
	}

	public List<FlowDefination> getFlows() {
		return flows;
	}

	public String toString() {
		return "\n\tBizDefine [\n\tbizName=" + bizName + ", \n\tbizId=" + bizId + ", \n\tflows=" + flows + "\n\t]";
	}

	public String toXml() {
		StringBuffer sb = new StringBuffer();
		for (FlowDefination def : this.getFlows()) {
			sb.append(def.toXml());
		}
		return TemplateHelper.getBizTemplate().replaceAll("\\{bizId\\}", this.getBizId()).replaceAll("\\{operations\\}", sb.substring(0, sb.length() - 2).toString());
	}

}
