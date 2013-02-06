package com.pactera.eclipse.efficient.converter;

/**
 * @author ruanzr
 *
 */
public class PathConvertUtil {

	public static String convertAppPath(String path) {
		return PathConverterFactory.getAppConverter(path).convert(path);
	}

	public static String convertStaticPath(String path) {
		return PathConverterFactory.getStaticConverter(path).convert(path);
	}

}
