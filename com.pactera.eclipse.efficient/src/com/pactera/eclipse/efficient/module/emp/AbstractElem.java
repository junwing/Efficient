package com.pactera.eclipse.efficient.module.emp;

import com.pactera.eclipse.efficient.module.Defination;

public abstract class AbstractElem implements Defination {

	private String tagName;
	private String name;

	public AbstractElem(String tagName, String name) {
		this.tagName = tagName;
		this.name = name;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
