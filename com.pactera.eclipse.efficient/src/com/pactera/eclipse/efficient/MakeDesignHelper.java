package com.pactera.eclipse.efficient;

import static com.pactera.eclipse.efficient.ListConstants.LIST_DIR_NAME;
import static com.pactera.eclipse.efficient.ListConstants.SQL_DIR_NAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.pactera.eclipse.efficient.module.PatchInfo;
import com.pactera.eclipse.efficient.module.db.Table;
import com.pactera.eclipse.efficient.module.define.ActionDefination;
import com.pactera.eclipse.efficient.module.define.DesignDefination;
import com.pactera.eclipse.efficient.poi.DesignParser;
import com.pactera.eclipse.efficient.template.TemplateHelper;
import com.pactera.eclipse.efficient.template.TemplateType;
import com.pactera.file.util.FileUtil;

public class MakeDesignHelper extends Observable {

	private static final String CHARSET = "UTF-8";
	private FilePather pather;
	private DesignDefination design;

	private File file;
	private String projectPath;
	
	public MakeDesignHelper(File file, String projectPath) {
		super();
		this.file = file;
		this.projectPath = projectPath;
	}

	/**
	 * 根据设计文件生成biz, mvc, jsp到对应工程目录下
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<String> makeDesign() throws IOException {
		List<String> fileList = new ArrayList<String>();
		design = DesignParser.parseDesignFile(file);
		final String projectName = design.getProjectName();
		File targetProject = new File(new File(projectPath).getParentFile(), projectName);
		if (!targetProject.exists()) {
			return fileList;
		}
		final File bizDir = new File(targetProject, "designFiles/bizs/" + projectName + "/" + design.getDirectoryName());
		FileUtil.mkdir(bizDir);
		final File mvcDir = new File(targetProject, "designFiles/mvcs/" + projectName + "/" + design.getDirectoryName());
		FileUtil.mkdir(mvcDir);

		pather = new FilePather(projectName);
		int bizCount = 0, mvcCount = 0, jspCount = 0;
		for (String bizName : design.getBizs().keySet()) {
			File bizFile = new File(bizDir, append(bizName, ".biz"));
			if (bizFile.exists()) {
				notifyFileExists(bizFile);
				continue;
			}
			FileUtil.writeString2File(design.getBiz(bizName).toXml(), bizFile, CHARSET);
			bizCount++;
			notifyAdd(bizFile);
			fileList.add(getFileListPath(bizFile));
		}
		for (String mvcName : design.getMvcs().keySet()) {
			File mvcFile = new File(mvcDir, append(mvcName, ".mvc"));
			if (mvcFile.exists()) {
				notifyFileExists(mvcFile);
			} else {
				FileUtil.writeString2File(design.getMvc(mvcName).toXml(), mvcFile, CHARSET);
				mvcCount++;
				notifyAdd(mvcFile);
				fileList.add(getFileListPath(mvcFile));
			}
			// 生成jsp
			for (ActionDefination actionDef : design.getMvc(mvcName).getActions()) {
				File jsp = new File(mvcDir.getParentFile(), actionDef.getTargetJsp());
				if (jsp.exists()) {
					notifyFileExists(jsp);
					continue;
				}
				String template = null;
				if (actionDef.getTargetJsp().endsWith(TemplateType.JSP_AJAX.getName())) {
					template = TemplateHelper.getTemplate(TemplateType.JSP_AJAX);
				} else {
					template = TemplateHelper.getJspTemplate();
				}
				FileUtil.writeString2File(template, jsp, CHARSET);
				jspCount++;
				notifyAdd(jsp);
				fileList.add(getFileListPath(jsp));
			}
		}
		notify("new count: biz(" + bizCount + ") mvc(" + mvcCount + ") jsp(" + jspCount + ")");
		return fileList;
	}

	/**
	 * 根据数据库设计文件生成SQL
	 * 
	 * @throws IOException
	 */
	public void makeDB() throws IOException {
		List<Table> tables = DesignParser.parseDBFile(file);
		File dir = new File(file.getParent(), SQL_DIR_NAME + File.separator + "01_Table");
		FileUtil.mkdirs(dir);
		for (Table table : tables) {
			final File sqlFile = new File(dir, table.getEnglishName() + ".sql");
			FileUtil.writeString2File(table.toSQL(), sqlFile, "GBK");
			notify("+ " + sqlFile.getName());
		}
	}

	/**
	 * 根据文件清单生产清单文件到指定目录
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

	private String getFilePath(File file) {
		return pather.getFilePath(file);
	}

	private String getFileListPath(File file) {
		return pather.getFileListPath(file);
	}

	private void notify(String msg) {
		setChanged();
		notifyObservers(msg);
	}

	private void notifyAdd(File file) {
		notify("+ " + getFilePath(file));
	}

	private void notifyFileExists(File file) {
		notify("= " + getFilePath(file));
	}

	private String append(String fileName, String suffix) {
		if (!fileName.endsWith(suffix)) {
			return fileName += suffix;
		}
		return fileName;
	}

	private class FilePather {
		String projectName;

		public FilePather(String projectName) {
			this.projectName = projectName;
		}

		public String getFilePath(File file) {
			final String path = file.getPath();
			return path.substring(path.indexOf(projectName));
		}
		
		public String getFileListPath(File file) {
			final String path = file.getPath();
			return path.substring(path.indexOf(projectName) + projectName.length() + 1);
		}
	}

}
