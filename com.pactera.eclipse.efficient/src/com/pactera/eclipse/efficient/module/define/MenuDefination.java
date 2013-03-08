package com.pactera.eclipse.efficient.module.define;

import com.pactera.eclipse.efficient.module.Defination;

/**
 * @author ruanzr
 * 
 */
public class MenuDefination implements Defination, Comparable<MenuDefination> {

	public static final String TYPE_QUERY = "查询";
	public static final String TYPE_SUBMIT_AUTH = "录入授权";
	public static final String TYPE_QUERY_MAINTAIN = "查询维护";

	private String bsnCode;
	private String chineseName;
	private String englishName;
	private String type;
	private String tableName;
	private String tranCode;
	private String refer;
	private String remark;

	public MenuDefination(String bsnCode, String chineseName, String englishName, String type, String tableName, String tranCode, String refer, String remark) {
		this.bsnCode = bsnCode;
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.type = type;
		this.tableName = tableName;
		this.tranCode = tranCode;
		this.refer = refer;
		this.remark = remark;
	}

	public String getBsnCode() {
		return bsnCode;
	}

	public void setBsnCode(String bsnCode) {
		this.bsnCode = bsnCode;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAction() {
		return getBsnCode() + "_" + getEnglishName() + ".do";
	}

	/**
	 * 是否为录入授权类型，如果是则返回<code>true</code>，否则返回<code>false</code>
	 * 
	 * @return 如果是录入授权类型则返回<code>true</code>，否则返回<code>false</code>
	 */
	public boolean isSubmitAndAuth() {
		return TYPE_SUBMIT_AUTH.equals(getType());
	}

	/**
	 * 是否为查询维护类型，如果是则返回<code>true</code>，否则返回<code>false</code>
	 * 
	 * @return 如果是查询维护类型则返回<code>true</code>，否则返回<code>false</code>
	 */
	public boolean isQueryMaintain() {
		return TYPE_QUERY_MAINTAIN.equals(getType());
	}

	public String getBSNDEFType() {
		if (TYPE_QUERY.equals(getType())) {
			return "1";
		}
		if (TYPE_SUBMIT_AUTH.equals(getType())) {
			return "2";
		}
		if (TYPE_QUERY_MAINTAIN.equals(getType())) {
			return "5";
		}
		return "1";// 查询
	}

	public String toXml() {
		return "\t<TaskInfo id=\"" + getBsnCode() + "\" name=\"" + getChineseName() + "\" action=\"" + getAction() + "\"/>";
	}

	public String toString() {
		return "\nMenuDefination [bsnCode=" + bsnCode + ", chineseName=" + chineseName + ", englishName=" + englishName + ", type=" + type + ", tableName=" + tableName
				+ ", tranCode=" + tranCode + ", remark=" + remark + "]";
	}

	public int compareTo(MenuDefination define) {
		if (define == null) {
			return -1;
		}
		return this.getBsnCode().compareTo(define.getBsnCode());
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bsnCode == null) ? 0 : bsnCode.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuDefination other = (MenuDefination) obj;
		if (bsnCode == null) {
			if (other.bsnCode != null)
				return false;
		} else if (!bsnCode.equals(other.bsnCode))
			return false;
		return true;
	}

	public String getBusinessNameEn() {
		return this.englishName.replaceAll("Manage", "");
	}

	public String getBusinessNameCh() {
		return this.chineseName.replaceAll("查询维护", "");
	}

}
