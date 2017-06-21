package com.wcy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڹ�����
 *
 * @ClassName DateUtil
 * @author wangcongyi
 * @date 2017��6��12�� ����3:31:28
 */
public class DateUtil {

	public static Date date = null;

	public static DateFormat dateFormat = null;

	public static Calendar calendar = null;

	/**
	 * ������������ʽ������
	 * 
	 * @param dateStr
	 *            String �ַ�������
	 * @param format
	 *            String ��ʽ
	 * @return Date ����
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			dateFormat = new SimpleDateFormat(format);
			String dt = dateStr.replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]", "0");
			}
			date = (Date) dateFormat.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * ������������ʽ������
	 * 
	 * @param dateStr
	 *            String �ַ������ڣ�YYYY-MM-DD ��ʽ
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	/**
	 * ������������ʽ���������
	 * 
	 * @param date
	 *            Date ����
	 * @param format
	 *            String ��ʽ
	 * @return �����ַ�������
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * ����������
	 * 
	 * @param date
	 *            Date ����
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * �����������������
	 * 
	 * @param date
	 *            Date ����
	 * @return �������
	 */
	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * ���������������·�
	 * 
	 * @param date
	 *            Date ����
	 * @return �����·�
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * ���������������շ�
	 * 
	 * @param date
	 *            Date ����
	 * @return �����շ�
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��������������Сʱ
	 * 
	 * @param date
	 *            ����
	 * @return ����Сʱ
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * �������������ط���
	 * 
	 * @param date
	 *            ����
	 * @return ���ط���
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * ��������
	 * 
	 * @param date
	 *            Date ����
	 * @return ��������
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * �������������غ���
	 * 
	 * @param date
	 *            ����
	 * @return ���غ���
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * ���������������ַ�������
	 * 
	 * @param date
	 *            ����
	 * @return �����ַ������� yyyy/MM/dd ��ʽ
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * ���������������ַ���ʱ��
	 * 
	 * @param date
	 *            Date ����
	 * @return �����ַ���ʱ�� HH:mm:ss ��ʽ
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * ���������������ַ�������ʱ��
	 * 
	 * @param date
	 *            Date ����
	 * @return �����ַ�������ʱ�� yyyy/MM/dd HH:mm:ss ��ʽ
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * �����������������
	 * 
	 * @param date
	 *            Date ����
	 * @param day
	 *            int ����
	 * @return ������Ӻ������
	 */
	public static Date addDate(Date date, int day) {
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * �����������������
	 * 
	 * @param date
	 *            Date ����
	 * @param date1
	 *            Date ����
	 * @return ��������������
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * ����������ȡ��ָ���·ݵĵ�һ��
	 * 
	 * @param strdate
	 *            String �ַ�������
	 * @return String yyyy-MM-dd ��ʽ
	 */
	public static String getMonthBegin(String strdate) {
		date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * ����������ȡ��ָ���·ݵ����һ��
	 * 
	 * @param strdate
	 *            String �ַ�������
	 * @return String �����ַ��� yyyy-MM-dd��ʽ
	 */
	public static String getMonthEnd(String strdate) {
		date = parseDate(getMonthBegin(strdate));
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * �������������õĸ�ʽ������
	 * 
	 * @param date
	 *            Date ����
	 * @return String �����ַ��� yyyy-MM-dd��ʽ
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * ������������ָ���ĸ�ʽ����ʽ������
	 * 
	 * @param date
	 *            Date ����
	 * @param format
	 *            String ��ʽ
	 * @return String �����ַ���
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d.toString());
		System.out.println(formatDate(d).toString());
		System.out.println(getMonthBegin(formatDate(d).toString()));
		System.out.println(getMonthBegin("2008/07/19"));
		System.out.println(getMonthEnd("2008/07/19"));
		System.out.println(addDate(d, 15).toString());
	}

}
