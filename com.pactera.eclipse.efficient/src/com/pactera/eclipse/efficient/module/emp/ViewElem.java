package com.pactera.eclipse.efficient.module.emp;

public class ViewElem extends Elem {

	private String jspFile;

	public ViewElem(int index) {
		super("view", "view" + index, index);
	}

	public String getJspFile() {
		return jspFile;
	}

	public void setJspFile(String jspFile) {
		this.jspFile = jspFile;
	}

	protected void appendLocal(StringBuffer xml) {
		xml.append(" jspFile=\"").append(this.getJspFile()).append('\"');
	}

	protected Connector getConnector() {
		return new Connector("reference", "ref" + this.getIndex(), this.getNext());
	}

}
