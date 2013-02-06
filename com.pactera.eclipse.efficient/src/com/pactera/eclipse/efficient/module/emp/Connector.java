package com.pactera.eclipse.efficient.module.emp;

public class Connector extends AbstractElem {

	public Connector(String tagName, String name) {
		super(tagName, name);
	}

	public Connector(String tagName, String name, Elem elem) {
		super(tagName, name);
		this.dest = elem.getName();
	}

	private String dest;

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public void setDest(Elem elem) {
		this.dest = elem.getName();
	}

	public String toXml() {
		return "<" + this.getTagName() + " name=\"" + this.getName() + "\" dest=\"" + this.getDest() + "\"/>";
	}

}
