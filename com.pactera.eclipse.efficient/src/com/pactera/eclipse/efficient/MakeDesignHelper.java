package com.pactera.eclipse.efficient;

import static com.pactera.eclipse.efficient.ListConstants.LIST_DIR_NAME;
import static com.pactera.eclipse.efficient.ListConstants.SQL_DIR_NAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.pactera.eclipse.efficient.exception.EfficientException;
import com.pactera.eclipse.efficient.logging.ELog;
import com.pactera.eclipse.efficient.module.PatchInfo;
import com.pactera.eclipse.efficient.module.db.Table;
import com.pactera.eclipse.efficient.module.define.DesignDefination;
import com.pactera.eclipse.efficient.poi.DesignParser;
import com.pactera.file.util.FileUtil;

public class MakeDesignHelper extends Observable {

	private DesignDefination design;

	private File file;

	public MakeDesignHelper(File file) {
		super();
		this.file = file;
	}

	/**
	 * ʹ��<code>makeDesign()</code>����ǰҪ��<code>override</code>�÷������Ը���<code>projectName</code>���ع��̵�ʵ��·��
	 * 
	 * @param projectName
	 * @return
	 */
	public String getPath(String projectName) {
		throw new EfficientException("must override this method first.");
	}

	/**
	 * ��������ļ�����biz, mvc, jsp����Ӧ����Ŀ¼�¡����ø÷���ǰӦ��<code>override getPath()</code>����
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<String> makeDesign() throws IOException {
		List<String> fileList = new ArrayList<String>();
		design = DesignParser.parseDesignFile(file);
		String projectName = design.getProjectName();
		String path = getPath(projectName);
		if (path == null) {
			ELog.info(projectName + " does not exists");
			return fileList;
		}
		File targetProject = new File(path);
		if (!targetProject.exists()) {
			return fileList;
		}

		if (design.getVersion() == 0.1) {
			GenerateFactory.getGenerater(projectName).generate(design, targetProject);
		} else {
			new CommonCodeGenerater(projectName).generate(design, targetProject);
		}

		return fileList;
	}

	/**
	 * �������ݿ�����ļ����ɽ������нű�
	 * 
	 * @throws IOException
	 */
	public void makeDB() throws IOException {
		List<Table> tables = DesignParser.parseDBFile(file);
		File tableDir = new File(file.getParent(), SQL_DIR_NAME + File.separator + "01_Table");
		File seqDir = new File(file.getParent(), SQL_DIR_NAME + File.separator + "02_Sequence");
		FileUtil.mkdirs(tableDir);
		for (Table table : tables) {
			File sqlFile = new File(tableDir, table.getEnglishName() + ".sql");
			FileUtil.writeString2File(table.toSQL() + table.toComment(), sqlFile, "GBK");
			ELog.info("+ TABLE: " + sqlFile.getName());
			String seq = table.toSequence();
			if (seq != null) {
				FileUtil.mkdirs(seqDir);
				File seqFile = new File(seqDir, table.getEnglishName() + "_NO.sql");
				FileUtil.writeString2File(seq, seqFile, "GBK");
				ELog.info("+ SEQUENCE: " + seqFile.getName());
			}
		}
	}

	/**
	 * �����ļ��嵥�����嵥�ļ���ָ��Ŀ¼
	 * 
	 * @param fileList
	 * @throws IOException
	 */
	public void toFile(final List<String> fileList) throws IOException {
		new PatchInfo(new FileHolder() {
			public List<String> getUpdatedFiles() {
				return fileList;
			}
		}).appendFile(new File(file.getParent(), LIST_DIR_NAME + File.separator + getDesign().getProjectName()));
	}

	public DesignDefination getDesign() {
		return design;
	}

}
