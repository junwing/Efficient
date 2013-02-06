package com.pactera.eclipse.efficient.module.define;

import com.pactera.eclipse.efficient.module.Defination;
import com.pactera.eclipse.efficient.module.emp.ActionElem;
import com.pactera.eclipse.efficient.module.emp.FlowElem;
import com.pactera.eclipse.efficient.module.emp.ViewElem;
import com.pactera.util.StringUtil;

/**
 * action定义，包括了一个action的.do、描述、biz和结果jsp
 * 
 * @author ruanzr
 * 
 */
public class ActionDefination implements Defination {

	private String actionDesc;
	private String actionId;
	private String bizRef;
	private String targetJsp;

	public ActionDefination(String actionDesc, String actionId, String bizRef, String targetJsp) {
		this.actionDesc = actionDesc;
		this.actionId = actionId;
		this.bizRef = bizRef;
		this.targetJsp = targetJsp;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getBizRef() {
		return bizRef;
	}

	public void setBizRef(String bizRef) {
		this.bizRef = bizRef;
	}

	public String getTargetJsp() {
		return targetJsp;
	}

	public void setTargetJsp(String targetJsp) {
		this.targetJsp = targetJsp;
	}

	public String toString() {
		return "\n\t\tActionDefine [actionDesc=" + actionDesc + ", actionId=" + actionId + ", bizRef=" + bizRef + ", targetJsp=" + targetJsp + "]";
	}

	public String toXml() {
		return null;
	}

	public static final int ACTION_WIDTH = 180;
	public static final int ACTION_HEIGHT = 70;
	public static final int ELEMENT_WIDTH = 160;
	public static final int ELEMENT_HEIGHT = 70;
	public static final int ACTION_START_X = 20;
	public static final int ACTION_START_Y = 20;
	public static final int ELEMENT_START_Y = ACTION_START_Y + (ACTION_HEIGHT - ELEMENT_HEIGHT) / 2;
	public static final int MARGIN_X = 20;
	public static final int MARGIN_Y = 10;
	public static final int ROW_HEIGHT = ACTION_HEIGHT + MARGIN_Y;
	public static final int[] ELEMENT_XS = { ACTION_START_X + ACTION_WIDTH + MARGIN_X, ACTION_START_X + ACTION_WIDTH + ELEMENT_WIDTH + MARGIN_X * 2 };

	public String toXml(int i) {
		ActionElem actionElem = new ActionElem(i);
		actionElem.setArea(ACTION_START_X, ACTION_START_Y + ROW_HEIGHT * i, ACTION_WIDTH, ACTION_HEIGHT);
		actionElem.setId(this.getActionId());
		actionElem.setLabel(this.getActionDesc());

		ViewElem viewElem = new ViewElem(i);
		final int indexOf = this.getTargetJsp().indexOf('/');
		viewElem.setId((indexOf != -1) ? this.getTargetJsp().substring(indexOf + 1) : this.getTargetJsp());
		viewElem.setJspFile(this.getTargetJsp());

		FlowElem flowElem = null;
		if (!StringUtil.isEmpty(this.bizRef)) {
			String[] bizInfo = this.bizRef.substring(this.bizRef.indexOf('\\') + 1).split("\\.");
			flowElem = new FlowElem(i);
			flowElem.setArea(ELEMENT_XS[0], ELEMENT_START_Y + ROW_HEIGHT * i, ELEMENT_WIDTH, ELEMENT_HEIGHT);
			flowElem.setId(bizInfo[0]);
			flowElem.setRefId(bizInfo[1]);
			flowElem.setFileName(this.getBizRef().substring(0, this.getBizRef().indexOf('.')) + ".biz");

			flowElem.setNext(viewElem);
		}

		viewElem.setArea(ELEMENT_XS[flowElem == null ? 0 : 1], ELEMENT_START_Y + ROW_HEIGHT * i, ELEMENT_WIDTH, ELEMENT_HEIGHT);

		actionElem.setNext(flowElem == null ? viewElem : flowElem);

		return actionElem.toXml() + (flowElem == null ? "" : flowElem.toXml()) + viewElem.toXml();
	}

}
