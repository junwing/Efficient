package com.pactera.eclipse.efficient.module;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.pactera.eclipse.efficient.FileHolder;
import com.pactera.eclipse.efficient.converter.Converter;
import com.pactera.eclipse.efficient.converter.PathConvertUtil;
import com.pactera.file.util.FileUtil;
import com.pactera.util.DateUtil;

/**
 * 清单信息。用户生成清单。
 * 
 * @author ruanzr
 * 
 */
public class PatchInfo extends Observable {

	private static final Map<String, PatchType[]> DIRECTORY_TO_PATCHFILE;
	private static final Map<PatchType, Converter> PATCH_FILE_CONVERTER;
	private Map<PatchType, List<String>> patchMap;

	static {
		DIRECTORY_TO_PATCHFILE = new HashMap<String, PatchType[]>(3);
		DIRECTORY_TO_PATCHFILE.put("JavaSource", PatchType.values());
		DIRECTORY_TO_PATCHFILE.put("designFiles", PatchType.values());
		DIRECTORY_TO_PATCHFILE.put("WebContent", new PatchType[] { PatchType.HTTP });

		PATCH_FILE_CONVERTER = new HashMap<PatchType, Converter>(3);
		PATCH_FILE_CONVERTER.put(PatchType.APP, new Converter() {

			public String convert(String path) {
				return PathConvertUtil.convertAppPath(path);
			}
		});
		PATCH_FILE_CONVERTER.put(PatchType.CODE, new Converter() {

			public String convert(String path) {
				return path;
			}
		});
		PATCH_FILE_CONVERTER.put(PatchType.HTTP, new Converter() {

			public String convert(String path) {
				return PathConvertUtil.convertStaticPath(path);
			}
		});
	}

	public PatchInfo(FileHolder holder) {
		patchMap = new HashMap<PatchType, List<String>>(3);
		for (PatchType type : PatchType.values()) {
			patchMap.put(type, new ArrayList<String>());
		}
		List<String> updatedFiles = holder.getUpdatedFiles();
		for (PatchType type : patchMap.keySet()) {
			for (String updatedFile : updatedFiles) {
				String path = PATCH_FILE_CONVERTER.get(type).convert(updatedFile);
				if (path != null) {
					patchMap.get(type).add(path);
				}
			}
		}
	}

	public void toFile(String folder) throws IOException {
		toFile(folder, null);
	}

	public void toFile(String folder, String suffix) throws IOException {
		for (PatchType file : patchMap.keySet()) {
			List<String> lines = patchMap.get(file);
			if (lines.size() == 0) {
				continue;
			}
			String fileName = file.getName();
			String patchName = suffix == null ? fileName : fileName.substring(0, fileName.length() - 4) + suffix;
			Collections.sort(lines);
			FileUtil.writeLines2File(lines, new File(folder, patchName));
			setChanged();
			notifyObservers(patchName);
		}
	}

	public void appendFile(String folder) throws IOException {
		for (PatchType file : patchMap.keySet()) {
			List<String> lines = patchMap.get(file);
			if (lines.size() == 0) {
				continue;
			}
			String fileName = file.getName();
			Collections.sort(lines);
			final File patchFile = new File(folder, fileName);
			if (patchFile.exists()) {
				lines.add(0, "\n\n-- added at " + DateUtil.getFormatedDateTime() + "");
			}
			FileUtil.writeLines2File(lines, patchFile, true);
			setChanged();
			notifyObservers(fileName);
		}
	}

	public void appendFile(File dir) throws IOException {
		FileUtil.mkdirs(dir);
		for (PatchType file : patchMap.keySet()) {
			List<String> lines = patchMap.get(file);
			if (lines.size() == 0) {
				continue;
			}
			String fileName = file.getName();
			Collections.sort(lines);
			final File patchFile = new File(dir, fileName);
			if (patchFile.exists()) {
				lines.add(0, "\n\n-- added at " + DateUtil.getFormatedDateTime() + "");
			}
			FileUtil.writeLines2File(lines, patchFile, true);
			setChanged();
			notifyObservers(fileName);
		}
	}

}
