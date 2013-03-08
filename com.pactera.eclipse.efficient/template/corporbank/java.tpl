package com.ecc.liana.corporbank.workflow.jdbc.provider;

/**
 * {chineseName}指令JDBC提供者
 * 
 * @version 
 * @author 
 */
public class {englishName}JDBCProvider implements JDBCProviderInterface {
	/**
	 * 查询指令状态
	 */
	private static final String SQL_QUERY_STATE_AND_MODE = "SELECT {tablePrefix}_STT FROM {tableName} WHERE BSF_FLOWNO=?";
	
	/**
	 * 查询指令信息
	 */
	private static final String SQL_CHECK_MODIFIED_QUERY = "SELECT {tablePrefix}_FLOWNO FROM {tableName} WHERE {tablePrefix}_FLOWNO=? AND {tablePrefix}_LASTMODIFIEDTIME=? ";
	
	/**
	 * 更新指令状态
	 */
	private static final String SQL_UPDATE_STATE = "UPDATE {tableName} SET {tablePrefix}_STT=? WHERE {tablePrefix}_FLOWNO=?";
	/**
	 * 插入指令使用的LOAF配置代码
	 */
	private static final String LOAF_INSERT_BUSINESS_CODE = "{tableName}_ADD";
	/**
	 * 更新指令使用的LOAF配置代码
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
