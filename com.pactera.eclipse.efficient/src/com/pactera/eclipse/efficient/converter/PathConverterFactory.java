package com.pactera.eclipse.efficient.converter;

public class PathConverterFactory {

	public final static Converter CONVERTER_JAVA = new JavaFileConverter();
	public final static Converter CONVERTER_JSP = new JspFileConverter();
	public final static Converter CONVERTER_BIZ = new BizFileConverter();
	public final static Converter CONVERTER_MVC = new MvcFileConverter();
	public final static Converter CONVERTER_WEBCONTENT = new WebContentFileConverter();
	public final static Converter CONVERTER_NODESETTINGS = new NodeSettingsFileConverter();
	public final static Converter CONVERTER_SERVERFLOW = new ServerFlowFileConverter();
	public final static Converter CONVERTER_DESIGNFILES = new OtherDesignFileConverter();
	public final static Converter CONVERTER_NONE = new NullConverter();

	public static Converter getAppConverter(String path) {
		if (path.endsWith(".java")) {
			return CONVERTER_JAVA;
		} else if (path.startsWith("WebContent") && (path.indexOf("WEB-INF") > 0 || path.endsWith(".jsp"))) {
			return CONVERTER_WEBCONTENT;
		} else if (path.endsWith(".biz")) {
			return CONVERTER_BIZ;
		} else if (path.endsWith(".mvc")) {
			return CONVERTER_MVC;
		} else if (path.endsWith(".jsp")) {
			return CONVERTER_JSP;
		} else if (path.endsWith("nodeSettings.xml")) {
			return CONVERTER_NODESETTINGS;
		} else if (path.endsWith("serverFlow.xml")) {
			return CONVERTER_SERVERFLOW;
		} else if (path.startsWith("designFiles")) {
			return CONVERTER_DESIGNFILES;
		}
		return CONVERTER_NONE;
	}

	public static Converter getStaticConverter(String path) {
		if (path.startsWith("WebContent") && !(path.indexOf("WEB-INF") > 0 || path.endsWith(".jsp"))) {
			return CONVERTER_WEBCONTENT;
		}
		return CONVERTER_NONE;
	}

	/**
	 * "designFiles\\".length()
	 */
	protected static final int DESIGNFILE_PREFIX_LENGTH = 12;
	protected static final String WEBINF_PREFIX = "WEB-INF\\";
	protected static final String PATH_SUFFIX_XML = ".xml";

	static class BizFileConverter implements Converter {

		protected static final String SRC_PREFIX = "designFiles\\bizs\\";
		protected static final String PATH_PREFIX = "WEB-INF\\bizs\\";
		protected static final String OPERATIONS = "\\operations";

		public String convert(String path) {
			String projectName = path.substring(SRC_PREFIX.length(), path.indexOf("\\", SRC_PREFIX.length()));
			String fileName = path.substring(path.lastIndexOf("\\"), path.length() - 4);
			return PATH_PREFIX + projectName + OPERATIONS + fileName + PATH_SUFFIX_XML;
		}

	}

	static class MvcFileConverter implements Converter {

		protected static final String SRC_PREFIX = "designFiles\\mvcs\\";
		protected static final String PATH_PREFIX = "WEB-INF\\mvcs\\";
		protected static final String OPERATIONS = "\\actions";

		public String convert(String path) {
			String projectName = path.substring(SRC_PREFIX.length(), path.indexOf("\\", SRC_PREFIX.length()));
			String fileName = path.substring(path.lastIndexOf("\\"), path.length() - 4);
			return PATH_PREFIX + projectName + OPERATIONS + fileName + PATH_SUFFIX_XML;
		}

	}

	static class JavaFileConverter implements Converter {

		public String convert(String path) {
			return "WEB-INF\\classes\\" + path.substring(path.indexOf('\\') + 1, path.length() - 5) + ".class";
		}

	}

	static class JspFileConverter implements Converter {

		public String convert(String path) {
			return WEBINF_PREFIX + path.substring(DESIGNFILE_PREFIX_LENGTH);
		}

	}

	static class WebContentFileConverter implements Converter {

		public String convert(String path) {
			return path.substring("WebContent\\".length());
		}

	}

	static class NodeSettingsFileConverter implements Converter {

		/**
		 * "nodeSettings.xml".length()
		 */
		static int FILENAME_LENGTH = 16;

		public String convert(String path) {
			return WEBINF_PREFIX + path.substring(DESIGNFILE_PREFIX_LENGTH, path.length() - FILENAME_LENGTH) + "contexts.xml\n"
					+ WEBINF_PREFIX + path.substring(DESIGNFILE_PREFIX_LENGTH, path.length() - FILENAME_LENGTH) + "data.xml";
		}

	}

	static class ServerFlowFileConverter implements Converter {
		/**
		 * "serverFlow.xml".length()
		 */
		static int FILENAME_LENGTH = 14;

		public String convert(String path) {
			return WEBINF_PREFIX + path.substring(DESIGNFILE_PREFIX_LENGTH, path.length() - FILENAME_LENGTH) + "operations.xml";
		}

	}

	static class OtherDesignFileConverter implements Converter {

		public String convert(String path) {
			if (path.endsWith("dataDict.xml") || path.endsWith("Profile.xml")) {
				return null;
			}
			return WEBINF_PREFIX + path.substring(DESIGNFILE_PREFIX_LENGTH);
		}

	}

	static class NullConverter implements Converter {
		public String convert(String path) {
			return null;
		}
	}

}
