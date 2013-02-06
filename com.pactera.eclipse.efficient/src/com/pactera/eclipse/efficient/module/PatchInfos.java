package com.pactera.eclipse.efficient.module;

import static com.pactera.eclipse.efficient.ListConstants.LIST_DIR_NAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import com.pactera.file.util.FileUtil;
import com.pactera.util.StringFilter;
import com.pactera.util.StringUtil;

/**
 * 多个清单列表的并集， 按工程，如perbank,corporbank,innermanage
 * 
 * @author ruanzr
 * 
 */
public class PatchInfos extends Observable {

	private static final String README_TXT = "readme.txt";

	private Map<PatchType, Set<String>> patchMap;
	private StringBuffer readme;

	private static final String HORIZONTAL = StringUtil.repeat('=', 80);

	static final StringFilter PATH_FILTER = new StringFilter() {
		public boolean accept(String src) {
			return src.matches("[\\w\\.\\-\\\\]+");
		}
	};

	private static final Comparator<String> COMPARATOR = new Comparator<String>() {
		public int compare(String o1, String o2) {
			return o1.toLowerCase().compareTo(o2.toLowerCase());
		}
	};

	public PatchInfos() {
		patchMap = new HashMap<PatchType, Set<String>>(3);
		for (PatchType type : PatchType.values()) {
			patchMap.put(type, new HashSet<String>());
		}
		readme = new StringBuffer();
	}

	public void merge(String folder) throws IOException {
		for (PatchType type : patchMap.keySet()) {
			mergePatch(folder, type);
		}
		mergeReadme(folder);
	}

	private void mergePatch(String folder, PatchType type) throws IOException {
		File file = new File(folder, type.getName());
		if (file.exists()) {
			List<String> lines = FileUtil.readFile2Lines(file, PATH_FILTER);
			patchMap.get(type).addAll(lines);
		}
	}

	private void mergeReadme(String folder) throws IOException {
		File rm = new File(folder, README_TXT);
		if (rm.exists()) {
			appendReadme(HORIZONTAL);
			appendReadme(getRequirementName(rm));
			appendReadme(HORIZONTAL);
			appendReadme(FileUtil.readFile2String(rm));
		}
	}

	private String getRequirementName(File file) {
		final File parentFile = file.getParentFile().getParentFile();
		String name = parentFile.getName();
		return name.equals(LIST_DIR_NAME) ? parentFile.getParentFile().getName() : name;
	}

	private void appendReadme(String s) {
		readme.append(s).append('\n');
	}

	public void toFile(String folder) throws IOException {
		for (PatchType type : patchMap.keySet()) {
			Set<String> pathSet = patchMap.get(type);
			if (pathSet.size() == 0) {
				continue;
			}
			List<String> pathList = null;
			if (PatchType.CODE == type) {
				pathList = new ArrayList<String>();
				List<String> jarList = new ArrayList<String>();
				for (String path : pathSet) {
					if (path.endsWith(".jar")) {
						jarList.add(path);
					} else {
						pathList.add(path);
					}
				}
				if (jarList.size() > 0) {
					File commonLibDir = new File(new File(folder).getParent(), "commonLib");
					FileUtil.mkdir(commonLibDir);
					sortList(jarList);
					File file = new File(commonLibDir, type.getName());
					FileUtil.writeLines2File(jarList, file);
					notify(file);
				}
			} else {
				pathList = convertToList(pathSet);
			}
			sortList(pathList);
			File file = new File(folder, type.getName());
			FileUtil.writeLines2File(pathList, file);
			notify(file);
		}
		if (readme.length() > 0) {
			File rm = new File(folder, README_TXT);
			FileUtil.writeString2File(readme.toString(), rm);
			notify(rm);
		}
	}

	private void notify(File file) {
		setChanged();
		notifyObservers(getSortPath(file.getPath()));
	}

	static String getSortPath(String path) {
		return path.substring(StringUtil.lastNTimesIndexOf(path, '\\', 3) + 1);
	}

	private static List<String> convertToList(Set<String> set) {
		List<String> list = new ArrayList<String>();
		for (String e : set) {
			list.add(e);
		}
		return list;
	}

	private static void sortList(List<String> list) {
		Collections.sort(list, COMPARATOR);
	}

}
