package com.pactera.util;

import java.util.List;

public class ListUtil {

	public static <E> String toString(List<E> list, String split) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size() - 1; i++) {
			sb.append(list.get(i).toString()).append(split);
		}
		sb.append(list.get(list.size() - 1));
		return sb.toString();
	}

	public static <E> String toString(List<E> list, char ch) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size() - 1; i++) {
			sb.append(list.get(i).toString()).append(ch);
		}
		sb.append(list.get(list.size() - 1));
		return sb.toString();
	}

}
