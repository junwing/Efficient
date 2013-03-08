package com.pactera.eclipse.efficient.module;


public class AbstractDefination implements Defination {

	protected String directoryName;

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public String toXml() {
		return "";
	}

}
