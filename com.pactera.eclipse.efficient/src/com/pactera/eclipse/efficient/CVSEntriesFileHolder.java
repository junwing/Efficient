package com.pactera.eclipse.efficient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pactera.file.filter.DirectoryFilter;
import com.pactera.file.filter.FilenameFilter;
import com.pactera.file.util.FileUtil;

public class CVSEntriesFileHolder extends AbstractPatchFileHolder {

	public static final String ENTRY_TAG_FOLDER = "D";
	public static final String ENTRY_TAG_ADD = "A ";
	public static final String ENTRY_TAG_REMOVE = "R ";
	public static final SimpleDateFormat DATEFORMAT_CVSENTRY = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy", Locale.US);

	public CVSEntriesFileHolder(String projectPath, Date modifedDate) {
		super(projectPath, modifedDate);
	}

	public List<String> getUpdatedFiles(String folder) {
		return getFilelistAccordingToCVS(projectPath + File.separator + folder, modifedDate);
	}

	public List<String> getFilelistAccordingToCVS(String folder, Date modDate) {
		List<String> filepaths = new ArrayList<String>();
		File cvsEntries = new File(folder, "CVS\\Entries");
		if (cvsEntries.exists()) {
			for (String name : getFilenamesFromCVSEntries(cvsEntries, modDate)) {
				filepaths.add(getListfilePath(new File(folder, name).getPath()));
			}
		}
		File[] dirs = FileUtil.listFiles(new File(folder), new DirectoryFilter(), new FilenameFilter(false, "CVS"));
		for (File dir : dirs) {
			filepaths.addAll(getFilelistAccordingToCVS(dir.getPath(), modDate));
		}
		return filepaths;
	}

	public static List<String> getFilenamesFromCVSEntries(File entryFile, Date modDate) {
		List<String> filenames = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(entryFile));
			String line = null;
			while (null != (line = reader.readLine())) {
				String[] items = line.split("/");
				try {
					if (ENTRY_TAG_FOLDER.equals(items[0]) || ENTRY_TAG_REMOVE.equals(items[0]) || DATEFORMAT_CVSENTRY.parse(items[3]).before(modDate))
						continue;
				} catch (ParseException e) {
				}
				filenames.add(items[1]);
			}
			return filenames;
		} catch (IOException e) {
			//
		} finally {
			FileUtil.close(reader);
		}
		return null;
	}

}
