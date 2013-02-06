package com.pactera.eclipse.efficient.module;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.pactera.file.util.FileUtil;
import com.pactera.util.StringUtil;

public class SqlInfos extends Observable {

	private static final String HORIZONTAL = StringUtil.repeat('*', 80);

	private Map<String, StringBuffer> sqlMap;
	private List<String> directories;

	public SqlInfos() {
		this.sqlMap = new HashMap<String, StringBuffer>(2);
		this.sqlMap.put("INIT_DATA.sql", new StringBuffer());
		this.sqlMap.put("ROLLBACK.sql", new StringBuffer());
		this.directories = new ArrayList<String>();
	}

	public void merge(String folder) throws IOException {
		this.directories.add(folder);
		for (String fileName : sqlMap.keySet()) {
			mergeSqlFile(folder, fileName);
		}
	}

	private void mergeSqlFile(String folder, String fileName) throws IOException {
		File sqlFile = new File(folder, fileName);
		if (sqlFile.exists()) {
			addComment(sqlMap.get(fileName), folder).append(FileUtil.readFile2String(sqlFile)).append('\n');
		}
	}

	private StringBuffer addComment(StringBuffer sb, String folder) {
		sb.append('/').append(HORIZONTAL).append('\n');
		sb.append(" * ").append(new File(folder).getParentFile().getName()).append('\n');
		sb.append(' ').append(HORIZONTAL).append('/').append('\n');
		return sb;
	}

	public void toFile(String folder) throws IOException {
		// copy
		for (String directory : this.directories) {
			FileUtil.copyDirectory(new File(directory), new File(folder), new FileFilter() {
				public boolean accept(File file) {
					return !sqlMap.keySet().contains(file.getName());
				}
			});
			setChanged();
			notifyObservers(PatchInfos.getSortPath(directory) + " -> " + PatchInfos.getSortPath(folder));
		}
		// write INIT_DATA.sql and ROLLBACK.sql
		for (String fileName : sqlMap.keySet()) {
			if (sqlMap.get(fileName).length() == 0) {
				continue;
			}
			File mergedFile = new File(folder, fileName);
			FileUtil.writeString2File(sqlMap.get(fileName).toString(), mergedFile);
			setChanged();
			notifyObservers(PatchInfos.getSortPath(mergedFile.getPath()));
		}
	}

}
