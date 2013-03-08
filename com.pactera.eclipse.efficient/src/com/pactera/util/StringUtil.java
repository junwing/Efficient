package com.pactera.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static String findFirst(Pattern pattern, String s) {
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	public static String findFirst(String regex, String s) {
		return findFirst(Pattern.compile(regex), s);
	}

	public static List<String> findAll(Pattern pattern, String s) {
		List<String> groups = new ArrayList<String>();
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			groups.add(matcher.group());
		}
		return groups;
	}

	public static List<String> findAll(String regex, String s) {
		return findAll(Pattern.compile(regex), s);
	}

	/**
	 * @param s 以下划线分隔的单词组合
	 * @return
	 */
	public static String toClassName(String s) {
		String[] words = s.split("_");
		StringBuffer name  = new StringBuffer();
		for (String word : words) {
			name.append(upperFirst(word.toLowerCase()));
		}
		return name.toString();
	}

	public static String upperFirst(String s) {
		if (isEmpty(s)) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 按单词转换为以下划线分隔的字符串，如输入<code>englishName</code>，则返回<code>english_name</code>
	 * 
	 * @param s
	 * @return
	 */
	public static String toUnderlineSplitName(String s) {
		StringBuffer sb = new StringBuffer();
		for (char ch : s.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				sb.append('_');
			}
			sb.append(Character.toLowerCase(ch));
		}
		return sb.toString();
	}

}
