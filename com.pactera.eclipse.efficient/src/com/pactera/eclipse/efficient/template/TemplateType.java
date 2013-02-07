package com.pactera.eclipse.efficient.template;

public enum TemplateType {

	BIZ("biz"), OPERATION("operation"), JSP("jsp"), JSP_AJAX("ajax.jsp"), SEQUENCE("sequence");

	private String name;

	private TemplateType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
