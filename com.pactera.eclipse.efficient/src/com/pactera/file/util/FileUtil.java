package com.pactera.file.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.pactera.file.filter.DirectoryFilter;
import com.pactera.file.filter.FileContentFilter;
import com.pactera.file.filter.FileFilterComposer;
import com.pactera.file.filter.FileSuffixFilter;
import com.pactera.util.StringFilter;

public class FileUtil {

	/**
	 * 1MB
	 */
	private static long FILE_COPY_BUFFER_SIZE = 1024 * 1024;

	/**
	 * 目录（包括其子目录）下面的所有符合要求的文件内容替换
	 * 
	 * @param srcDir
	 *            要替换的目录
	 * @param regex
	 *            替换内容的正则表达式
	 * @param replacement
	 *            用来替换的内容
	 * @param charset
	 *            文件的编码格式
	 * @param fileType
	 *            需要进行替换的文件类型
	 * @throws IOException
	 */
	public static void directoryIterateReplace(File srcDir, String regex, String replacement, String charset, final String... fileType) throws IOException {
		directoryReplace(srcDir, regex, replacement, charset, fileType);
		File[] dirs = srcDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File dir : dirs) {
			directoryIterateReplace(dir, regex, replacement, charset, fileType);
		}
	}

	/**
	 * 目录（不包括其子目录）下面的所有符合要求的文件内容替换
	 * 
	 * @param srcDir
	 *            要替换的目录
	 * @param regex
	 *            替换内容的正则表达式
	 * @param replacement
	 *            用来替换的内容
	 * @param charset
	 *            文件的编码格式
	 * @param fileType
	 *            需要进行替换的文件类型
	 * @throws IOException
	 */
	public static void directoryReplace(File srcDir, final String regex, String replacement, final String charset, final String... fileType) throws IOException {
		assertDir(srcDir);
		File[] files = srcDir.listFiles(new FileContentFilter(new FileSuffixFilter(fileType), regex, charset));
		for (File file : files) {
			System.out.println(file.getPath());
			replace(file, regex, replacement, charset);
		}
	}

	private static void assertDir(File srcDir) {
		if (!srcDir.isDirectory()) {
			throw new RuntimeException("the " + srcDir + " is not a directory.");
		}
	}

	/**
	 * 文件替换
	 * 
	 * @param src
	 *            需要替换的文件
	 * @param regex
	 *            替换内容的正则表达式
	 * @param replacement
	 *            用来替换的内容
	 * @param charset
	 *            文件的编码格式
	 * @throws IOException
	 */
	public static void replace(File src, String regex, String replacement, String charset) throws IOException {
		writeString2File(readFile2String(src, charset).replaceAll(regex, replacement), src, charset);
	}

	public static List<String> readFile2Lines(File file) throws IOException {
		return readFile2Lines(file, null);
	}

	public static List<String> readFile2Lines(File file, StringFilter filter) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while (null != (line = reader.readLine())) {
				if (filter == null || (filter != null && filter.accept(line))) {
					lines.add(line);
				}
			}
		} finally {
			close(reader);
		}
		return lines;
	}

	public static void writeLines2File(Collection<String> lines, File file) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for (String line : lines) {
				writer.write(line);
				writer.write('\n');
			}
		} finally {
			close(writer);
		}
	}

	public static void writeLines2File(Collection<String> lines, File file, boolean append) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, append));
			for (String line : lines) {
				writer.write(line);
				writer.write('\n');
			}
		} finally {
			close(writer);
		}
	}

	/**
	 * 把文件内容读到String中
	 * 
	 * @param file
	 *            文件
	 * @param charset
	 *            文件编码
	 * @return 文件内容
	 * @throws IOException
	 */
	public static String readFile2String(File file, String charset) throws IOException {
		Reader in = null;
		try {
			StringWriter sw = new StringWriter();
			in = new InputStreamReader(new FileInputStream(file), charset);
			char[] buffer = new char[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				sw.write(buffer, 0, len);
			}
			return sw.toString();
		} finally {
			close(in);
		}
	}

	/**
	 * 把文件内容读到String中
	 * 
	 * @param file
	 *            文件
	 * @return 文件内容
	 * @throws IOException
	 */
	public static String readFile2String(File file) throws IOException {
		return readStream2String(new FileInputStream(file));
	}

	public static String readStream2String(InputStream input) throws IOException {
		Reader reader = null;
		try {
			StringWriter sw = new StringWriter();
			reader = new InputStreamReader(input);
			char[] buffer = new char[1024];
			int len;
			while ((len = reader.read(buffer)) != -1) {
				sw.write(buffer, 0, len);
			}
			return sw.toString();
		} finally {
			close(reader);
		}
	}

	/**
	 * 将String写到文件中
	 * 
	 * @param str
	 *            String
	 * @param file
	 *            文件
	 * @param charset
	 *            文件编码
	 * @throws IOException
	 */
	public static void writeString2File(String str, File file, String charset) throws IOException {
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file), charset);
			writer.write(str);
		} finally {
			close(writer);
		}
	}

	/**
	 * 将String写到文件中
	 * 
	 * @param str
	 *            String
	 * @param file
	 *            文件
	 * @throws IOException
	 */
	public static void writeString2File(String str, File file) throws IOException {
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file));
			writer.write(str);
		} finally {
			close(writer);
		}
	}

	public static String[] search(File file, String regex) {
		RandomAccessFile accessFile = null;
		try {
			accessFile = new RandomAccessFile(file, "r");
			String line = accessFile.readLine();
			Pattern pattern = Pattern.compile(regex);
			List<String> list = new LinkedList<String>();
			int i = 1;
			while (line != null) {
				if (pattern.matcher(line).find()) {
					list.add(i + ":" + line);
				}
				i++;
				line = accessFile.readLine();
			}
			return list.toArray(new String[0]);
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			close(accessFile);
		}
	}

	/**
	 * 列出目录下面所有能与<code>regex</code>匹配的文件
	 * 
	 * @param dir
	 * @param regex
	 * @param charset
	 * @return
	 */
	public static File[] searchFile(File dir, String regex, String charset, String... fileType) {
		assertDir(dir);
		return dir.listFiles(new FileContentFilter(new FileSuffixFilter(fileType), regex, charset));
	}

	public static File[] searchFileIterate(File dir, String regex, String charset, String... fileType) {
		File[] files = searchFile(dir, regex, charset, fileType);
		List<File> list = new ArrayList<File>();
		Collections.addAll(list, files);
		for (File file : dir.listFiles(new DirectoryFilter())) {
			Collections.addAll(list, searchFileIterate(file, regex, charset, fileType));
		}
		return list.toArray(new File[0]);
	}

	/**
	 * 列出<code>directory</code>下面所有符合过滤器的文件，如果目录不存在则返回空数组
	 * 
	 * @param directory
	 *            指定目录
	 * @param filters
	 *            文件过滤器
	 * @return 文件列表
	 */
	public static File[] listFiles(File directory, FileFilter... filters) {
		if (!directory.exists()) {
			return new File[0];
		}
		return directory.listFiles(new FileFilterComposer(filters));
	}

	/**
	 * 列出所有<code>direc ctory</code>及其所有子目录下面符合过滤器的文件，如果目录不存在则返回空数组
	 * 
	 * @param directory
	 *            指定目录
	 * @param filters
	 *            文件过滤器
	 * @return 文件列表
	 */
	public static File[] listAllFiles(File directory, FileFilter... filters) {
		if (!directory.exists()) {
			return new File[0];
		}
		File[] files = listFiles(directory, filters);
		List<File> list = new ArrayList<File>();
		Collections.addAll(list, files);
		for (File file : directory.listFiles(new DirectoryFilter())) {
			Collections.addAll(list, listAllFiles(file, filters));
		}
		return list.toArray(new File[0]);
	}

	public static File[] listFileByDate(File directory, final long timeMillis) {
		return listFiles(directory, new FileFilter() {
			public boolean accept(File file) {
				return file.isFile() && file.lastModified() > timeMillis;
			}
		});
	}

	public static File[] listAllFileByDate(File directory, final long timeMillis) {
		File[] files = listFileByDate(directory, timeMillis);
		List<File> list = new ArrayList<File>();
		Collections.addAll(list, files);
		for (File file : directory.listFiles(new DirectoryFilter())) {
			Collections.addAll(list, listAllFileByDate(file, timeMillis));
		}
		return list.toArray(new File[0]);
	}

	public static File[] listDirectory(File directory) {
		return directory.listFiles(new DirectoryFilter());
	}

	public static boolean mkdir(File directory) {
		if (!directory.exists()) {
			return directory.mkdir();
		}
		return false;
	}

	public static boolean mkdirs(File directory) {
		if (!directory.exists()) {
			return directory.mkdirs();
		}
		return false;
	}

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
			}
		}
	}

	public static void cleanDirectory(File directory) throws IOException {
		if (!(directory.exists())) {
			String message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}
		if (!(directory.isDirectory())) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}
		File[] files = directory.listFiles();
		if (files == null) {
			throw new IOException("Failed to list contents of " + directory);
		}
		IOException exception = null;
		for (int i = 0; i < files.length; ++i) {
			File file = files[i];
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}
		if (null != exception)
			throw exception;
	}

	public static void forceDelete(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			boolean filePresent = file.exists();
			if (!(file.delete())) {
				if (!(filePresent)) {
					throw new FileNotFoundException("File does not exist: " + file);
				}
				String message = "Unable to delete file: " + file;

				throw new IOException(message);
			}
		}
	}

	public static void deleteDirectory(File directory) throws IOException {
		if (!(directory.exists())) {
			return;
		}

		cleanDirectory(directory);
		if (!(directory.delete())) {
			String message = "Unable to delete directory " + directory + ".";

			throw new IOException(message);
		}
	}

	public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static void copyDirectory(File srcDirectory, File destDirectory) throws IOException {
		copyDirectory(srcDirectory, destDirectory, null);
	}

	public static void copyDirectory(File srcDirectory, File destDirectory, FileFilter filter) throws IOException {
		if (!destDirectory.exists()) {
			destDirectory.mkdirs();
		}
		for (File file : filter == null ? srcDirectory.listFiles() : srcDirectory.listFiles(filter)) {
			if (file.isDirectory()) {
				copyDirectory(file, new File(destDirectory, file.getName()), filter);
			} else {
				copyFileToDirectory(file, destDirectory);
			}
		}
	}

	public static void copyFileToDirectory(File srcFile, File directory) throws IOException {
		copyFile(srcFile, new File(directory, srcFile.getName()));
	}

	public static void copyFile(File srcFile, File destFile) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel input = null;
		FileChannel output = null;
		try {
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			input = fis.getChannel();
			output = fos.getChannel();
			long size = input.size();
			long pos = 0;
			long count = 0;
			while (pos < size) {
				count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
				pos += output.transferFrom(input, pos, count);
			}
		} finally {
			close(output);
			close(fos);
			close(input);
			close(fis);
		}
		if (srcFile.length() != destFile.length()) {
			throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
		}
	}

}
