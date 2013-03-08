package com.pactera.eclipse.efficient;

import static com.pactera.eclipse.efficient.ListConstants.SQL_DIR_NAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pactera.eclipse.efficient.module.db.Table;
import com.pactera.eclipse.efficient.module.define.DesignDefination;
import com.pactera.eclipse.efficient.module.define.MenuDefination;
import com.pactera.eclipse.efficient.module.define.MvcDefination;
import com.pactera.eclipse.efficient.poi.DesignParser;
import com.pactera.eclipse.efficient.template.KeyValues;
import com.pactera.eclipse.efficient.template.TemplateHelper;
import com.pactera.eclipse.efficient.template.TemplateType;
import com.pactera.file.util.FileUtil;
import com.pactera.util.StringUtil;

public class CBCodeGenerater extends CommonCodeGenerater {

	public CBCodeGenerater() {
		super("corporbank");
	}

	public List<String> generate(DesignDefination design, File targetProject) throws IOException {
		List<String> fileList = new ArrayList<String>();

		File bizDir = new File(targetProject, "designFiles/bizs/" + design.getProjectName() + "/" + design.getDirectoryName());
		FileUtil.mkdir(bizDir);
		File mvcDir = new File(targetProject, "designFiles/mvcs/" + design.getProjectName() + "/" + design.getDirectoryName());
		FileUtil.mkdir(mvcDir);

		File tableFile = new File(design.getSrcFile().getParent(), design.getTableFileName());
		List<Table> tables = DesignParser.parseDBFile(tableFile);
		Map<String, Table> tableMap = new HashMap<String, Table>(tables.size());
		for (Table table : tables) {
			tableMap.put(table.getEnglishName(), table);
		}

		final File fregment = new File(targetProject, design.getRequirementName() + ".txt");
		FileUtil.writeString2File(design.toTaskInfo(), fregment);
		fileList.add(log(fregment));

		Map<String, String> tableProviderMap = new HashMap<String, String>();
		for (String tableName : design.getLOAFTables()) {
			Table table = tableMap.get(tableName);
			KeyValues keyValues = new KeyValues();
			keyValues.addPair("tableName", table.getEnglishName());
			keyValues.addPair("chineseName", table.getChineseName());
			keyValues.addPair("tablePrefix", table.getPrefix());
			String loafCt = TemplateHelper.getFinalContent(TemplateType.LOAF, keyValues);
			FileUtil.writeString2File(loafCt, fregment, true);

			String name = StringUtil.toClassName(tableName.substring(tableName.indexOf('_') + 1));
			keyValues.addPair("englishName", name);
			File javaFile = new File(targetProject, "JavaSource\\com\\ecc\\liana\\corporbank\\workflow\\jdbc\\provider\\" + name + "JDBCProvider.java");
			String javaCt = TemplateHelper.getFinalContent(TemplateType.JAVA, keyValues);
			FileUtil.writeString2File(javaCt, javaFile);
			fileList.add(log(javaFile));

			tableProviderMap.put(tableName, "com.ecc.liana.corporbank.workflow.jdbc.provider." + name + "JDBCProvider");
		}

		StringBuffer providers = new StringBuffer();
		StringBuffer initSql = new StringBuffer();
		for (String bsnCode : design.getMenus().keySet()) {
			MenuDefination menu = design.getMenus().get(bsnCode);
			if(menu.isSubmitAndAuth()) {
				// 生成biz mvc jsp
				KeyValues keyValues = new KeyValues();
				keyValues.addPair("englishName", menu.getEnglishName());
				keyValues.addPair("EnglishName", StringUtil.upperFirst(menu.getEnglishName()));
				keyValues.addPair("english_name", StringUtil.toUnderlineSplitName(menu.getEnglishName()));
				keyValues.addPair("chineseName", menu.getChineseName());
				keyValues.addPair("tableName", menu.getTableName());
				keyValues.addPair("tablePrefix", tableMap.get(menu.getTableName()).getPrefix());
				keyValues.addPair("provider", tableProviderMap.get(menu.getTableName()));
				keyValues.addPair("tranCode", menu.getTranCode());
				String bn = design.getMenus().get(menu.getRefer()).getBusinessNameEn();
				keyValues.addPair("manageName", bn);
				keyValues.addPair("ManageName", StringUtil.upperFirst(bn));
				String bizCt = TemplateHelper.getFinalContent(TemplateType.BIZ_CB_SA, keyValues);
				File bizFile = new File(bizDir, menu.getEnglishName() + ".biz");
				writeToFile(bizCt, bizFile);
				fileList.add(log(bizFile));

				keyValues.addPair("bsnCode", menu.getBsnCode());
				keyValues.addPair("directoryName", design.getDirectoryName());
				String mvcCt = TemplateHelper.getFinalContent(TemplateType.MVC_CB_SA, keyValues);
				MvcDefination mvc = DesignParser.parseMvc(menu.getEnglishName(), mvcCt);
				File mvcFile = new File(mvcDir, menu.getEnglishName() + ".mvc");
				writeToFile(mvc.toXml(), mvcFile);
				fileList.add(log(mvcFile));

				generateJsps(mvc, mvcDir, keyValues);

			} else if (menu.isQueryMaintain()) {
				String provider = tableProviderMap.get(menu.getTableName());
				providers.append(provider).append('=').append(menu.getBusinessNameCh()).append(';');

				KeyValues keyValues = new KeyValues();
				keyValues.addPair("bizId", menu.getEnglishName());
				String englishName = menu.getBusinessNameEn();
				keyValues.addPair("englishName", englishName);
				keyValues.addPair("EnglishName", StringUtil.upperFirst(englishName));
				keyValues.addPair("english_name", StringUtil.toUnderlineSplitName(englishName));
				keyValues.addPair("chineseName", menu.getBusinessNameCh());
				keyValues.addPair("tableName", menu.getTableName());
				keyValues.addPair("tablePrefix", tableMap.get(menu.getTableName()).getPrefix());
				keyValues.addPair("provider", tableProviderMap.get(menu.getTableName()));

				String bizCt = TemplateHelper.getFinalContent(TemplateType.BIZ_CB_M, keyValues);
				File bizFile = new File(bizDir, menu.getEnglishName() + ".biz");
				writeToFile(bizCt, bizFile);
				fileList.add(log(bizFile));

				keyValues.addPair("bsnCode", menu.getBsnCode());
				keyValues.addPair("directoryName", design.getDirectoryName());
				String mvcCt = TemplateHelper.getFinalContent(TemplateType.MVC_CB_M, keyValues);
				MvcDefination mvc = DesignParser.parseMvc(menu.getEnglishName(), mvcCt);
				File mvcFile = new File(mvcDir, menu.getEnglishName() + ".mvc");
				writeToFile(mvc.toXml(), mvcFile);
				fileList.add(log(mvcFile));

				generateJsps(mvc, mvcDir, keyValues);
			}
			// initSQL
			initSql.append(toInitSql(menu));
		}
		// 生成初始化脚本
		File sqlDir = new File(design.getSrcFile().getParent(), SQL_DIR_NAME);
		FileUtil.mkdir(sqlDir);
		File initSqlFile = new File(sqlDir, "INIT_DATA.sql");
		FileUtil.writeString2File(initSql.toString(), initSqlFile);
		log(initSqlFile);

		// 插入actionProfile
		File actionProfile = new File(targetProject, "designFiles\\commons\\actionProfile.xml");
		String profile = FileUtil.readFile2String(actionProfile);
		profile = profile.replaceAll(providers.toString(), "");// 防止重复
		profile = profile.replaceAll("((?<=\")com.ecc.liana.corporbank.workflow.jdbc.provider.TransferJDBCProvider.+?(?=\"))", "$1" + providers.toString());
		FileUtil.writeString2File(profile, actionProfile);
		fileList.add(log(actionProfile));

		fileList.addAll(super.generate(design, targetProject));
		return fileList;
	}

	private String toInitSql(MenuDefination menu) {
		return "INSERT INTO CB_BSNDEF (BDF_BSNCODE, BDF_NAME, BDF_ALIAS, BDF_DESC, BDF_TYPE, BDF_BSNLV, BDF_GROUP, BDF_USERLV, BDF_STT, BDF_OPERATION)\nVALUES ('"
				+ menu.getBsnCode() + "', '" + menu.getChineseName() + "', '" + menu.getChineseName() + "', null, '" + menu.getBSNDEFType() + "', '0', '0', '1', '0', '5');\n";
	}

}
