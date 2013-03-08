package com.pactera.eclipse.efficient.module.define;

import java.util.ArrayList;
import java.util.List;

import com.pactera.eclipse.efficient.module.AbstractDefination;

/**
 * mvc定义，包括mvc文件名和mvc中的action定义
 * 
 * @author ruanzr
 * 
 */
public class MvcDefination extends AbstractDefination {

	private String mvcName;
	private List<ActionDefination> actions;

	public MvcDefination(String directoryName, String mvcName) {
		this.directoryName = directoryName;
		this.mvcName = mvcName;
		this.actions = new ArrayList<ActionDefination>();
	}

	public String getMvcName() {
		return mvcName;
	}

	public void setMvcName(String mvcName) {
		this.mvcName = mvcName;
	}

	public void addAction(ActionDefination action) {
		this.actions.add(action);
	}

	public List<ActionDefination> getActions() {
		return actions;
	}

	public String toString() {
		return "\n\tMvcDefine [\n\tmvcName=" + mvcName + ", \n\tactions=" + actions + "\n\t]";
	}

	public String toXml() {
		StringBuffer content = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n\n<EMPMVC name=\"" + this.getMvcName() + "\" x=\"0\" height=\""
				+ (actions.size() > 6 ? actions.size() * 100 : 600) + "\" width=\"800\" y=\"0\">\n");
		int i = 0;
		for (ActionDefination action : actions) {
			content.append(action.toXml(i++));
		}
		content.append("</EMPMVC>");
		return content.toString();
	}

}
