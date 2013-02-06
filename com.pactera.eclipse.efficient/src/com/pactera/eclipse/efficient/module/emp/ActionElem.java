package com.pactera.eclipse.efficient.module.emp;

public class ActionElem extends Elem {

	private String clazz = "com.ecc.liana.controller.LianaCommonRequestController";
	private String type = "normal";
	private String label;
	private String checkSession = "true";

	public ActionElem(int index) {
		super("action", "action" + index, index);
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCheckSession() {
		return checkSession;
	}

	public void setCheckSession(String checkSession) {
		this.checkSession = checkSession;
	}

	protected void appendLocal(StringBuffer xml) {
		if (this.getLabel() != null) {
			xml.append(" label=\"").append(this.getLabel()).append('\"');
		}
		xml.append(" type=\"").append(this.getType()).append('\"');
		xml.append(" checkSession=\"").append(this.getCheckSession()).append('\"');
		xml.append(" class=\"").append(this.getClazz()).append('\"');
	}

	protected Connector getConnector() {
		return new Connector("reference", "ref" + this.getIndex(), this.getNext());
	}

}
