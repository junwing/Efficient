package com.pactera.eclipse.efficient.template;

public enum TemplateType {

	BIZ("biz"), OPERATION("operation"), JSP("jsp"), JSP_AJAX("ajax.jsp"), SEQUENCE("sequence"), LOAF("corporbank/LOAF"), JAVA("corporbank/java"),
	/**
	 * 录入授权biz
	 */
	BIZ_CB_SA("corporbank/submit&auth.biz"),
	/**
	 * 录入授权mvc配置
	 */
	MVC_CB_SA("corporbank/submit&auth.mvc.cfg"),
	/**
	 * 查询维护biz
	 */
	BIZ_CB_M("corporbank/manage.biz"),
	/**
	 * 查询维护mvc配置
	 */
	MVC_CB_M("corporbank/manage.mvc.cfg"),
	/**
	 * 录入模板
	 */
	JSP_CB_INPUT("corporbank/input.jsp"),
	/**
	 * 确认模板
	 */
	JSP_CB_CONFIRM("corporbank/confirm.jsp"),
	/**
	 * 明细模板
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
