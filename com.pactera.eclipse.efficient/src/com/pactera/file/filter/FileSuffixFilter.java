package com.pactera.file.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileSuffixFilter implements FileFilter {

	private List<String> suffixes = new ArrayList<String>();

	public List<String> getSuffixes() {
		return suffixes;
	}

	public FileSuffixFilter() {
	}

	public FileSuffixFilter(String suffix) {
		this.addSuffix(suffix);
	}

	public FileSuffixFilter(String... suffixs) {
		for (String suffix : suffixs) {
			this.addSuffix(suffix);
		}
	}

	public void setSuffixes(List<String> suffixes) {
		this.suffixes = suffixes;
	}

	public void addSuffix(String suffix) {
		suffixes.add(suffix);
	}

	public void removeSuffix(String suffix) {
		suffixes.remove(suffix);
	}

	public boolean accept(File pathname) {
		if (suffixes.size() == 0) {
			return true;
		}
		for (String suffix : suffixes) {
			return pathname.getName().endsWith(suffix);
		}
		return false;
	}

}
