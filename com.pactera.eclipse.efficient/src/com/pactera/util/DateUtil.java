package com.pactera.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
	public static final String DEFAULT_DATETIME_PATTERN = "yyyyMMddHHmmss";
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	public static final SimpleDateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);

	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static String getFormatedToday() {
		return geFormatedDate(getToday());
	}

	public static String getFormatedDateTime(String pattern) {
		if (pattern == null) {
			return getFormatedDateTime();
		}
		return getFormatedDate(new Date(), pattern);
	}

	public static String getFormatedDateTime() {
		return DEFAULT_DATETIME_FORMAT.format(new Date());
	}

	public static String geFormatedDate(Date date) {
		return DEFAULT_DATE_FORMAT.format(date);
	}

	public static String getFormatedDate(Date date, String pattern) {
		if (pattern == null) {
			return geFormatedDate(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date toDate(String date, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * ʹ��Ĭ�����ڸ�ʽ<code>yyyyMMdd</code>����ʽ������
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDate(String date) {
		return toDate(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * ʹ��Ĭ�����ڸ�ʽ<code>yyyyMMddHHmmss</code>����ʽ������
	 * 
	 * @param date
	 * @return
	 */
	public static Date toDateTime(String date) {
		return toDate(date, DEFAULT_DATETIME_PATTERN);
	}

}
