package com.pactera.file.filter;

import java.io.File;
import java.io.FileFilter;

public class FilenameFilter implements FileFilter {

	private boolean include = true;

	private String[] names;

	public FilenameFilter() {
	}

	public FilenameFilter(boolean include, String... names) {
		this.include = include;
		this.names = names;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public boolean accept(File file) {
		if (include) {
			for (String name : names) {
				if (!file.getName().equals(name)) {
					return false;
				}
			}
		} else {
			for (String name : names) {
				if (file.getName().equals(name)) {
					return false;
				}
			}
		}
		return true;
	}
}
