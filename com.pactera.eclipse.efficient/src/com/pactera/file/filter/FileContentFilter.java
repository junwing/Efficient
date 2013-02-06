package com.pactera.file.filter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.regex.Pattern;

import com.pactera.file.util.FileUtil;

public class FileContentFilter implements FileFilter {

	private FileFilter fileFilter;
	private String regex;
	private String charset;

	public FileContentFilter() {
	}

	public FileContentFilter(String regex, String charset) {
		this.regex = regex;
		this.charset = charset;
	}

	public FileContentFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	public FileContentFilter(FileFilter fileFilter, String regex, String charset) {
		this.fileFilter = fileFilter;
		this.regex = regex;
		this.charset = charset;
	}

	public boolean accept(File pathname) {
		if (this.fileFilter != null && !this.fileFilter.accept(pathname)) {
			return false;
		}
		try {
			String content = FileUtil.readFile2String(pathname, charset);
			return content != null && Pattern.compile(regex).matcher(content).find();
		} catch (IOException e) {
			return false;
		}
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
