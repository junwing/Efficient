package com.pactera.util;

public class StringUtil {

	public static String repeat(String s, int times) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < times; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String repeat(char ch, int times) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < times; i++) {
			sb.append(ch);
		}
		return sb.toString();
	}

	public static String nvl(String s) {
		return nvl(s, "");
	}

	public static String nvl(String s, String def) {
		return s == null ? def : s;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static String paddingRight(String s, int len, char c) {
		if (s == null || s.length() == 0) {
			return repeat(c, len);
		}
		if (s.length() > len) {
			s = s.substring(0, len);
		} else if (s.length() < len) {
			s += repeat(c, len - s.length());
		}
		return s;
	}

	/**
	 * 获取倒数第n个<code>ch</code>的位置
	 * 
	 * @param s
	 * @param ch
	 * @param times
	 * @return
	 */
	public static int lastNTimesIndexOf(String s, char ch, int times) {
		int j = s.length();
		for (int i = 0; i < times; i++) {
			j = s.lastIndexOf(ch, j - 1);
			if (j == -1) {
				return -1;
			}
		}
		return j;
	}

	/**
	 * 一个<code>Tab</code>等于4个字符的长度
	 * 
	 * @param s
	 * @param count
	 * @return
	 */
	public static String paddingTab(String s, int count) {
		if (isEmpty(s)) {
			return repeat('\t', count);
		}
		return s + repeat('\t', count - s.length() / 4);
	}

}
