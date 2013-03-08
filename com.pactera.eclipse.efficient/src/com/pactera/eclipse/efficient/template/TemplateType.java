package com.pactera.eclipse.efficient.template;

public enum TemplateType {

	BIZ("biz"), OPERATION("operation"), JSP("jsp"), JSP_AJAX("ajax.jsp"), SEQUENCE("sequence"), LOAF("corporbank/LOAF"), JAVA("corporbank/java"),
	/**
	 * ¼����Ȩbiz
	 */
	BIZ_CB_SA("corporbank/submit&auth.biz"),
	/**
	 * ¼����Ȩmvc����
	 */
	MVC_CB_SA("corporbank/submit&auth.mvc.cfg"),
	/**
	 * ��ѯά��biz
	 */
	BIZ_CB_M("corporbank/manage.biz"),
	/**
	 * ��ѯά��mvc����
	 */
	MVC_CB_M("corporbank/manage.mvc.cfg"),
	/**
	 * ¼��ģ��
	 */
	JSP_CB_INPUT("corporbank/input.jsp"),
	/**
	 * ȷ��ģ��
	 */
	JSP_CB_CONFIRM("corporbank/confirm.jsp"),
	/**
	 * ��ϸģ��
	 */
	JSP_CB_DETAIL("corporbank/detail.jsp");

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
