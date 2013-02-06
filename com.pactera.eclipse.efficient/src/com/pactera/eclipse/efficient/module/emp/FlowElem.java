package com.pactera.eclipse.efficient.module.emp;

public class FlowElem extends Elem {

	private String refId;
	private String fileName;

	public FlowElem(int index) {
		super("flow", "flow" + index, index);
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	protected void appendLocal(StringBuffer xml) {
		xml.append(" refId=\"").append(this.getRefId()).append('\"');
		xml.append(" fileName=\"").append(this.getFileName()).append('\"');
	}

	protected Connector getConnector() {
		return new Connector("transition", "transition" + this.getIndex(), this.getNext());
	}

}
