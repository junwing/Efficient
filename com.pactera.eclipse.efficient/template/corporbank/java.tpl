package com.ecc.liana.corporbank.workflow.jdbc.provider;

/**
 * {chineseName}ָ��JDBC�ṩ��
 * 
 * @version 
 * @author 
 */
public class {englishName}JDBCProvider implements JDBCProviderInterface {
	/**
	 * ��ѯָ��״̬
	 */
	private static final String SQL_QUERY_STATE_AND_MODE = "SELECT {tablePrefix}_STT FROM {tableName} WHERE BSF_FLOWNO=?";
	
	/**
	 * ��ѯָ����Ϣ
	 */
	private static final String SQL_CHECK_MODIFIED_QUERY = "SELECT {tablePrefix}_FLOWNO FROM {tableName} WHERE {tablePrefix}_FLOWNO=? AND {tablePrefix}_LASTMODIFIEDTIME=? ";
	
	/**
	 * ����ָ��״̬
	 */
	private static final String SQL_UPDATE_STATE = "UPDATE {tableName} SET {tablePrefix}_STT=? WHERE {tablePrefix}_FLOWNO=?";
	/**
	 * ����ָ��ʹ�õ�LOAF���ô���
	 */
	private static final String LOAF_INSERT_BUSINESS_CODE = "{tableName}_ADD";
	/**
	 * ����ָ��ʹ�õ�LOAF���ô���
	 */
	private static final String LOAF_UPDATE_BUSINESS_CODE = "{tableName}_QUERY";

	/**
	 * Method getLoafInsertBusinessCode.
	 * 
	 * @return String
	 * @see com.ecc.liana.corporbank.workflow.jdbc.provider.JDBCProviderInterface#getLoafInsertBusinessCode()
	 */
	public String getLoafInsertBusinessCode()
	{
		return LOAF_INSERT_BUSINESS_CODE;
	}

	/**
	 * Method getQuerySQL.
	 * 
	 * @return String
	 * @see com.ecc.liana.corporbank.workflow.jdbc.provider.JDBCProviderInterface#getQuerySQL()
	 */
	public String getQuerySQL()
	{
		return SQL_QUERY_STATE_AND_MODE;
	}

	/**
	 * Method getUpdateStateSQL.
	 * 
	 * @return String
	 * @see com.ecc.liana.corporbank.workflow.jdbc.provider.JDBCProviderInterface#getUpdateStateSQL()
	 */
	public String getUpdateStateSQL()
	{
		return SQL_UPDATE_STATE;
	}

	public String getCheckModifiedQuerySQL()
	{
		return SQL_CHECK_MODIFIED_QUERY;
	}

	public String getLoafUpdateBusinessCode() {
		return LOAF_UPDATE_BUSINESS_CODE;
	}

}
