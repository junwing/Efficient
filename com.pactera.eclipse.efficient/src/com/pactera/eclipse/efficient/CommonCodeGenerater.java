package com.pactera.eclipse.efficient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pactera.eclipse.efficient.logging.ELog;
import com.pactera.eclipse.efficient.module.define.ActionDefination;
import com.pactera.eclipse.efficient.module.define.DesignDefination;
import com.pactera.eclipse.efficient.module.define.MvcDefination;
import com.pactera.eclipse.efficient.template.KeyValues;
import com.pactera.eclipse.efficient.template.TemplateHelper;
import com.pactera.file.util.FileUtil;

public class CommonCodeGenerater implements CodeGenerater {

	protected static final String CHARSET = "UTF-8";

	private String projectName;
	private FilePather pather;

	public CommonCodeGenerater(String projectName) {
		this.projectName = projectName;
		this.pather = new FilePather(projectName);
	}

	public List<String> generate(DesignDefination design, File targetProject) throws IOException {
		List<String> fileList = new ArrayList<String>();
		List<String> bizFileList = toBiz(design, targetProject);
		fileList.addAll(bizFileList);

		List<String> mvcFileList = toMvc(design, targetProject);
		fileList.addAll(mvcFileList);

		List<String> jspFileList = toJsp(design, targetProject);
		fileList.addAll(jspFileList);

		ELog.info("new count: biz(" + bizFileList.size() + ") mvc(" + mvcFileList.size() + ") jsp(" + jspFileList.size() + ")");

		return fileList;
	}

	private List<String> toJsp(DesignDefination design, File targetProject) throws IOException {
		List<String> fileList = new ArrayList<String>();
		for (String mvcName : design.getMvcs().keySet()) {
			File mvcDir = new File(targetProject, "designFiles/mvcs/" + design.getProjectName() + "/" + design.getMvc(mvcName).getDirectoryName());
			FileUtil.mkdir(mvcDir);
			fileList.addAll(generateJsps(design.getMvc(mvcName), mvcDir));
		}
		return fileList;
	}

	private List<String> toMvc(DesignDefination design, File targetProject) throws IOException {
		List<String> fileList = new ArrayList<String>();
		for (String mvcName : design.getMvcs().keySet()) {
			File mvcDir = new File(targetProject, "designFiles/mvcs/" + design.getProjectName() + "/" + design.getMvc(mvcName).getDirectoryName());
			FileUtil.mkdir(mvcDir);
			File mvcFile = new File(mvcDir, append(mvcName, ".mvc"));
			if (mvcFile.exists()) {
				log(mvcFile, true);
			} else {
				writeToFile(design.getMvc(mvcName).toXml(), mvcFile);
				fileList.add(log(mvcFile));
			}
		}
		return fileList;
	}

	private List<String> toBiz(DesignDefination design, File targetProject) throws IOException {
		List<String> fileList = new ArrayList<String>();
		for (String bizName : design.getBizs().keySet()) {
			File bizDir = new File(targetProject, "designFiles/bizs/" + design.getProjectName() + "/" + design.getBiz(bizName).getDirectoryName());
			FileUtil.mkdir(bizDir);
			File bizFile = new File(bizDir, append(bizName, ".biz"));
			if (bizFile.exists()) {
				log(bizFile, true);
				continue;
			}
			writeToFile(design.getBiz(bizName).toXml(), bizFile);
			fileList.add(log(bizFile));
			log(bizFile);
		}
		return fileList;
	}

	protected List<String> generateJsps(MvcDefination mvc, File toDir) throws IOException {
		return generateJsps(mvc, toDir, null);
	}

	protected List<String> generateJsps(MvcDefination mvc, File toDir, KeyValues keyValues) throws IOException {
		List<String> fileList = new ArrayList<String>();
		for (ActionDefination actionDef : mvc.getActions()) {
			String targetJsp = actionDef.getTargetJsp();
			File jsp = new File(toDir.getParentFile(), targetJsp);
			if (jsp.exists()) {
				log(jsp, true);
				continue;
			}
			String jspType = targetJsp.substring(targetJsp.lastIndexOf('_') + 1);
			String content = TemplateHelper.getFinalContent(getProjectName() + "/" + jspType, keyValues);
			if (content == null) {
				content = TemplateHelper.getFinalContent(jspType, keyValues);
			}
			if (content == null) {
				content = TemplateHelper.getJspTemplate();
			}
			writeToFile(content, jsp);
			fileList.add(log(jsp));
		}
		return fileList;
	}

	protected void writeToFile(String content, File toFile) throws IOException {
		FileUtil.writeString2File(content, toFile, CHARSET);
	}

	protected String log(File file) {
		return log(file, false);
	}

	/**
	 * 记录文件路径日志，如果文件已存在，则在相对路径前面加<code>- </code>，如果不存在则加<code>+ </code>
	 * 
	 * @param file
	 * @param exists
	 *            是否已存在
	 * @return 文件相对<code>projectName</code>路径
	 */
	protected String log(File file, boolean exists) {
		String listPath = pather.getFileListPath(file);
		ELog.info((exists ? "- " : "+ ") + listPath);
		return listPath;
	}

	protected String append(String fileName, String suffix) {
		if (!fileName.endsWith(suffix)) {
			return fileName += suffix;
		}
		return fileName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private static class FilePather {
		String projectName;

		public FilePather(String projectName) {
			this.projectName = projectName;
		}

		public String getFileListPath(File file) {
			String path = file.getPath();
			int index = path.indexOf(projectName);
			if (index == -1) {
				return path;
			}
			return path.substring(index + projectName.length() + 1);
		}
	}
}
