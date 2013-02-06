package com.pactera.eclipse.efficient.module.define;

import com.pactera.eclipse.efficient.module.Defination;
import com.pactera.eclipse.efficient.template.TemplateHelper;

public class FlowDefination implements Defination {

	private String flowId;
	private String flowName;

	public FlowDefination(String flowId, String flowName) {
		this.flowId = flowId;
		this.flowName = flowName;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String toString() {
		return "\n\t\tFlowDefine [flowId=" + flowId + ", flowName=" + flowName + "]";
	}

	public String toXml() {
		return TemplateHelper.getOperationTemplate().replaceAll("\\{flowId\\}", this.getFlowId()).replaceAll("\\{flowName\\}", this.getFlowName());
	}

}
