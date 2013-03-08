package com.pactera.eclipse.efficient.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.pactera.eclipse.efficient.exception.EfficientException;
import com.pactera.eclipse.efficient.module.db.Table;
import com.pactera.eclipse.efficient.module.define.ActionDefination;
import com.pactera.eclipse.efficient.module.define.BizDefination;
import com.pactera.eclipse.efficient.module.define.DesignDefination;
import com.pactera.eclipse.efficient.module.define.FlowDefination;
import com.pactera.eclipse.efficient.module.define.MenuDefination;
import com.pactera.eclipse.efficient.module.define.MvcDefination;
import com.pactera.file.util.FileUtil;
import com.pactera.util.StringUtil;

/**
 * EMP设计模板解释器
 * 
 * @author ruanzr
 * 
 */
public class DesignParser {

	public static DesignDefination parseDesignFile(File file) throws IOException {
		FileInputStream input = null;
		DesignDefination designDefine = null;
		try {
			input = new FileInputStream(file);
			HSSFSheet sheet = new HSSFWorkbook(new POIFSFileSystem(input)).getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = row.getCell(3);
			if (cell != null) {
				designDefine = parse2(getCellValue(row, 1), sheet);
				designDefine.setVersion(cell.getNumericCellValue());
			} else {
				designDefine = parse(getCellValue(row, 1), sheet);
			}
			designDefine.setSrcFile(file);
			return designDefine;
		} finally {
			FileUtil.close(input);
		}
	}

	private static DesignDefination parse(String projectName, HSSFSheet sheet) {
		DesignDefination designDefine = new DesignDefination(projectName);
		HSSFRow row = sheet.getRow(1);
		designDefine.setRequirementName(getCellValue(row, 1));
		designDefine.setDirectoryName(getCellValue(row, 3));

		String name = null;
		boolean mvcFlag = false;
		for (int i = 3; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			final String value = getCellValue(row, 0);
			if (!StringUtil.isEmpty(value)) {
				if (!mvcFlag && "mvc定义".equals(value)) {
					mvcFlag = true;
					continue;
				}
				name = value;
			}
			if (!mvcFlag) {
				BizDefination biz = designDefine.getBiz(name);
				final FlowDefination flow = new FlowDefination(getCellValue(row, 1), getCellValue(row, 2));
				if (biz != null) {
					biz.addFlow(flow);
				} else {
					BizDefination bizDef = new BizDefination(designDefine.getDirectoryName(), name, name);
					bizDef.addFlow(flow);
					designDefine.addBiz(name, bizDef);
				}
			} else {
				MvcDefination mvc = designDefine.getMvc(name);
				String bizRef = getCellValue(row, 3);
				if (!StringUtil.isEmpty(bizRef)) {
					bizRef = designDefine.getDirectoryName() + "\\" + bizRef;
				}
				String targetJsp = getCellValue(row, 4);
				if (targetJsp.indexOf('/') == -1) {
					targetJsp = designDefine.getDirectoryName() + "/" + targetJsp;
				}
				ActionDefination action = new ActionDefination(getCellValue(row, 1), getCellValue(row, 2), bizRef, targetJsp);
				if (mvc != null) {
					mvc.addAction(action);
				} else {
					MvcDefination mvcDef = new MvcDefination(designDefine.getDirectoryName(), name);
					mvcDef.addAction(action);
					designDefine.addMvc(name, mvcDef);
				}
			}
		}
		return designDefine;
	}

	private static DesignDefination parse2(String projectName, HSSFSheet sheet) {
		DesignDefination designDefine = new DesignDefination(projectName);
		HSSFRow row = sheet.getRow(1);
		designDefine.setRequirementName(getCellValue(row, 1));
		designDefine.setDirectoryName(getCellValue(row, 3));
		designDefine.setTableFileName(getCellValue(row, 5));

		int[] splitIndex = { 2, 0, 0, sheet.getLastRowNum() + 1 };
		for (int i = 3; i <= sheet.getLastRowNum(); i++) {
			String value = getCellValue(sheet.getRow(i), 0);
			if ("biz目录".equals(value)) {
				splitIndex[1] = i;
			} else if ("mvc目录".equals(value)) {
				splitIndex[2] = i;
			}
		}
		final int step = 2;
		String name = null;
		String dirName = null;
		for (int i = 0; i < 3; i++) {
			for (int j = splitIndex[i] + 1; j < splitIndex[i + 1]; j++) {
				row = sheet.getRow(j);
				if (i > 0) {
					String value = getCellValue(row, 0);
					if (!StringUtil.isEmpty(value)) {
						dirName = value;
					}
					value = getCellValue(row, 1);
					if (!StringUtil.isEmpty(value)) {
						name = value;
					}
				}
				switch (i) {
				case 0:
					parseMenu(designDefine, row);
					break;
				case 1:
					parseBiz(designDefine, row, step, dirName, name);
					break;
				case 2:
					parseMvc(designDefine, row, step, dirName, name);
					break;
				}
			}
		}
		return designDefine;
	}

	private static void parseMenu(DesignDefination designDefine, HSSFRow row) {
		final String bsnCode = getCellValue(row, 0);
		designDefine.addMenu(bsnCode, new MenuDefination(bsnCode, getCellValue(row, 1), getCellValue(row, 2), getCellValue(row, 3), getCellValue(row, 4), getCellValue(row, 5), getCellValue(row, 6), getCellValue(row, 7)));
	}

	private static void parseBiz(DesignDefination designDefine, HSSFRow row, final int step, String dirName, String name) {
		BizDefination biz = designDefine.getBiz(name);
		FlowDefination flow = new FlowDefination(getCellValue(row, 1 * step + 1), getCellValue(row, 2 * step + 1));
		if (biz == null) {
			biz = new BizDefination(dirName, name, name);
			designDefine.addBiz(name, biz);
		}
		biz.addFlow(flow);
	}

	private static void parseMvc(DesignDefination designDefine, HSSFRow row, final int step, String dirName, String name) {
		MvcDefination mvc = designDefine.getMvc(name);
		String bizRef = getCellValue(row, 3 * step + 1);
		if (!StringUtil.isEmpty(bizRef)) {
			bizRef = designDefine.getDirectoryName() + "\\" + bizRef;
		}
		String targetJsp = getCellValue(row, 4 * step + 1);
		if (targetJsp.indexOf('/') == -1) {
			targetJsp = designDefine.getDirectoryName() + "/" + targetJsp;
		}
		ActionDefination action = new ActionDefination(getCellValue(row, 1 * step + 1), getCellValue(row, 2 * step + 1), bizRef, targetJsp);
		if (mvc == null) {
			mvc = new MvcDefination(dirName, name);
			designDefine.addMvc(name, mvc);
		}
		mvc.addAction(action);
	}

	public static MvcDefination parseMvc(String name, String actionsCfg) {
		MvcDefination mvc = new MvcDefination(null, name);
		String[] actionCfgs = actionsCfg.split("\\r*\\n");
		for (String cfg : actionCfgs) {
			String[] elems = cfg.split(",");
			mvc.addAction(new ActionDefination(elems[0], elems[1], elems[2], elems[3]));
		}
		return mvc;
	}

	public static List<Table> parseDBFile(File file) throws IOException {
		List<Table> tables = new ArrayList<Table>();
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new POIFSFileSystem(input));
			int num = hssfWorkbook.getNumberOfSheets();
			for (int i = 0; i < num; i++) {
				HSSFSheet sheet = hssfWorkbook.getSheetAt(i);
				Table table = new TableSheet(sheet).getTable();
				tables.add(table);
			}
		} catch (FileNotFoundException e) {
			throw new EfficientException(e.getMessage());
		} finally {
			FileUtil.close(input);
		}
		return tables;
	}

	private static String getCellValue(HSSFRow row, int col) {
		return StringUtil.nvl(row.getCell(col).getStringCellValue()).trim();
	}

}
