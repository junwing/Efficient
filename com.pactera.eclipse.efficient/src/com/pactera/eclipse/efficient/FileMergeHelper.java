package com.pactera.eclipse.efficient;

import static com.pactera.eclipse.efficient.ListConstants.SQL_DIR_NAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import com.pactera.eclipse.efficient.module.PatchInfos;
import com.pactera.eclipse.efficient.module.SqlInfos;
import com.pactera.file.filter.DirectoryFilter;
import com.pactera.file.util.FileUtil;
import com.pactera.util.DateUtil;

public class FileMergeHelper {

	private Map<String, List<String>> appMap = null;
	private List<String> folders = null;
	private String today = null;
	private List<Observer> obervers;

	public FileMergeHelper(List<String> folders) {
		this.folders = folders;
		this.today = DateUtil.getFormatedToday();
		appMap = new HashMap<String, List<String>>(6);
		appMap.put("corporbank", new ArrayList<String>());
		appMap.put("perbank", new ArrayList<String>());
		appMap.put("innermanage", new ArrayList<String>());
		appMap.put("serviceController", new ArrayList<String>());
		appMap.put("lianaCore", new ArrayList<String>());
		appMap.put("commonLib", new ArrayList<String>());
		this.obervers = new ArrayList<Observer>();
		initFolder();
	}

	public void initFolder() {
		initFolder(this.folders);
	}

	public void initFolder(List<String> folders) {
		for (String appName : appMap.keySet()) {
			for (String folder : folders) {
				initAPPFolder(appName, folder);
			}
		}
	}

	public void initAPPFolder(String appName, String folder) {
		initAPPFolder(appName, new File(folder));
	}

	public void initAPPFolder(String appName, File folder) {
		for (File dir : folder.listFiles(new DirectoryFilter())) {
			if (appName.equals(dir.getName())) {
				appMap.get(appName).add(dir.getPath());
			} else {
				initAPPFolder(appName, dir);
			}
		}
	}

	public void mergeAllList() throws IOException {
		for (String appName : appMap.keySet()) {
			mergeAppList(appName);
		}
	}

	public void mergeAllSql() throws IOException {
		SqlInfos sqlInfos = new SqlInfos();
		for (Observer o : this.obervers) {
			sqlInfos.addObserver(o);
		}
		for (String folder : this.folders) {
			File sqlDirectory = new File(folder, SQL_DIR_NAME);
			if (sqlDirectory.exists()) {
				sqlInfos.merge(sqlDirectory.getPath());
			}
		}
		File toDir = new File(new File(folders.get(0)).getParent(), today + File.separator + "sql");
		FileUtil.mkdir(toDir);
		sqlInfos.toFile(toDir.getPath());
	}

	public void mergeAppList(String appName) throws IOException {
		if (appMap.get(appName).size() == 0) {
			return;
		}
		File toDir = new File(new File(folders.get(0)).getParent(), today);
		FileUtil.mkdir(toDir);
		File appDir = new File(toDir, appName);
		FileUtil.mkdir(appDir);
		PatchInfos patch = new PatchInfos();
		for (Observer o : this.obervers) {
			patch.addObserver(o);
		}
		for (String folder : appMap.get(appName)) {
			patch.merge(folder);
		}
		patch.toFile(appDir.getPath());
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String appName : appMap.keySet()) {
			sb.append(appName);
			sb.append(':');
			sb.append(appMap.get(appName));
			sb.append('\n');
		}
		return sb.toString();
	}

	public void addObserver(Observer o) {
		obervers.add(o);
	}

}
