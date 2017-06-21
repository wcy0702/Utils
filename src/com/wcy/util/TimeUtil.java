package com.wcy.util;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * ʱ����㹤����
 *
 * @ClassName TimeUtil
 * @author wangcongyi
 * @date 2017��6��12�� ����3:35:37
 */
public class TimeUtil {

	/**
	 * ʱ���ֶγ�������ʾ���롱
	 */
	public final static int SECOND = 0;

	/**
	 * ʱ���ֶγ�������ʾ���֡�
	 */
	public final static int MINUTE = 1;

	/**
	 * ʱ���ֶγ�������ʾ��ʱ��
	 */
	public final static int HOUR = 2;

	/**
	 * ʱ���ֶγ�������ʾ���족
	 */
	public final static int DAY = 3;

	/**
	 * ��������������ֵ
	 */
	private final int[] maxFields = { 59, 59, 23, Integer.MAX_VALUE - 1 };

	/**
	 * �������������Сֵ
	 */
	private final int[] minFields = { 0, 0, 0, Integer.MIN_VALUE };

	/**
	 * Ĭ�ϵ��ַ�����ʽʱ��ָ���
	 */
	private String timeSeparator = ":";

	/**
	 * ʱ����������
	 */
	private int[] fields = new int[4];

	/**
	 * �޲ι��죬�����ֶ���Ϊ 0
	 */
	public TimeUtil() {
		this(0, 0, 0, 0);
	}

	/**
	 * ʹ��ʱ���ֹ���һ��ʱ��
	 * 
	 * @param hour
	 *            Сʱ
	 * @param minute
	 *            ����
	 */
	public TimeUtil(int hour, int minute) {
		this(0, hour, minute, 0);
	}

	/**
	 * ʹ��ʱ���֡��빹��һ��ʱ��
	 * 
	 * @param hour
	 *            Сʱ
	 * @param minute
	 *            ����
	 * @param second
	 *            ��
	 */
	public TimeUtil(int hour, int minute, int second) {
		this(0, hour, minute, second);
	}

	/**
	 * ����������ʹ��һ���ַ�������ʱ��<br/>
	 * TimeTools time = new TimeTools("14:22:23");
	 * 
	 * @param time
	 *            �ַ�����ʽ��ʱ�䣬Ĭ�ϲ��á�:����Ϊ�ָ���
	 */
	public TimeUtil(String time) {
		this(time, null);
	}

	/**
	 * ʹ���졢ʱ���֡��빹��ʱ�䣬����ȫ�ַ��Ĺ���
	 * 
	 * @param day
	 *            ��
	 * @param hour
	 *            ʱ
	 * @param minute
	 *            ��
	 * @param second
	 *            ��
	 */
	public TimeUtil(int day, int hour, int minute, int second) {
		set(DAY, day);
		set(HOUR, hour);
		set(MINUTE, minute);
		set(SECOND, second);
	}

	/**
	 * ����������ʹ��һ���ַ�������ʱ�䣬ָ���ָ���, TimeTools time = new TimeTools("14-22-23", "-");
	 * 
	 * @param time
	 *            �ַ�����ʽ��ʱ��
	 * @param timeSeparator
	 *            ʱ��ηָ���
	 */
	public TimeUtil(String time, String timeSeparator) {
		if (timeSeparator != null) {
			setTimeSeparator(timeSeparator);
		}
		String pattern = patternQuote(this.timeSeparator);
		String matcher = new StringBuffer().append("\\d+?").append(pattern).append("\\d+?").append(pattern)
				.append("\\d+?").toString();
		if (!time.matches(matcher)) {
			throw new IllegalArgumentException(
					time + ", time format error, HH" + this.timeSeparator + "mm" + this.timeSeparator + "ss");
		}
		String[] times = time.split(pattern);
		set(DAY, 0);
		set(HOUR, Integer.parseInt(times[0]));
		set(MINUTE, Integer.parseInt(times[1]));
		set(SECOND, Integer.parseInt(times[2]));
	}

	/**
	 * ��������������ʱ���ֶε�ֵ
	 * 
	 * @param field
	 *            ʱ���ֶγ���
	 * @param value
	 *            ʱ���ֶε�ֵ
	 */
	public void set(int field, int value) {
		if (value < minFields[field]) {
			throw new IllegalArgumentException(value + ", time value must be positive.");
		}
		fields[field] = value % (maxFields[field] + 1);
		// ���н�λ����
		int carry = value / (maxFields[field] + 1);
		if (carry > 0) {
			int upFieldValue = get(field + 1);
			set(field + 1, upFieldValue + carry);
		}
	}

	/**
	 * �������������ʱ���ֶε�ֵ
	 * 
	 * @param field
	 *            ʱ���ֶγ���
	 * @return ��ʱ���ֶε�ֵ
	 */
	public int get(int field) {
		if (field < 0 || field > fields.length - 1) {
			throw new IllegalArgumentException(field + ", field value is error.");
		}
		return fields[field];
	}

	/**
	 * ������������ʱ����С��ӡ����㣬������һ��ʱ��
	 * 
	 * @param time
	 *            ��Ҫ�ӵ�ʱ��
	 * @return ������ʱ��
	 */
	public TimeUtil addTime(TimeUtil time) {
		TimeUtil result = new TimeUtil();
		int up = 0; // ��λ��־
		for (int i = 0; i < fields.length; i++) {
			int sum = fields[i] + time.fields[i] + up;
			up = sum / (maxFields[i] + 1);
			result.fields[i] = sum % (maxFields[i] + 1);
		}
		return result;
	}

	/**
	 * ������������ʱ����С��������㣬����ȥһ��ʱ��
	 * 
	 * @param time
	 *            ��Ҫ����ʱ��
	 * @return ������ʱ��
	 */
	public TimeUtil subtractTime(TimeUtil time) {
		TimeUtil result = new TimeUtil();
		int down = 0; // ��λ��־
		for (int i = 0, k = fields.length - 1; i < k; i++) {
			int difference = fields[i] + down;
			if (difference >= time.fields[i]) {
				difference -= time.fields[i];
				down = 0;
			} else {
				difference += maxFields[i] + 1 - time.fields[i];
				down = -1;
			}
			result.fields[i] = difference;
		}
		result.fields[DAY] = fields[DAY] - time.fields[DAY] + down;
		return result;
	}

	/**
	 * �������������ʱ���ֶεķָ���
	 * 
	 * @return
	 */
	public String getTimeSeparator() {
		return timeSeparator;
	}

	/**
	 * ��������������ʱ���ֶεķָ����������ַ�����ʽ��ʱ�䣩
	 * 
	 * @param timeSeparator
	 *            �ָ����ַ���
	 */
	public void setTimeSeparator(String timeSeparator) {
		this.timeSeparator = timeSeparator;
	}

	/**
	 * ����������������ʽ���ô�������Դ�� JDK
	 * 
	 * @link java.util.regex.Pattern#quote(String)
	 * @param s
	 * @return
	 */
	private String patternQuote(String s) {
		int slashEIndex = s.indexOf("\\E");
		if (slashEIndex == -1)
			return "\\Q" + s + "\\E";

		StringBuilder sb = new StringBuilder(s.length() * 2);
		sb.append("\\Q");
		slashEIndex = 0;
		int current = 0;
		while ((slashEIndex = s.indexOf("\\E", current)) != -1) {
			sb.append(s.substring(current, slashEIndex));
			current = slashEIndex + 2;
			sb.append("\\E\\\\E\\Q");
		}
		sb.append(s.substring(current, s.length()));
		sb.append("\\E");
		return sb.toString();
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("00");
		return new StringBuffer().append(fields[DAY]).append(", ").append(df.format(fields[HOUR])).append(timeSeparator)
				.append(df.format(fields[MINUTE])).append(timeSeparator).append(df.format(fields[SECOND])).toString();
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + Arrays.hashCode(fields);
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		final TimeUtil other = (TimeUtil) obj;
		if (!Arrays.equals(fields, other.fields)) {
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeUtil timeUtil=new TimeUtil(18,15);
		TimeUtil timeUtil1=new TimeUtil(2,15);
		TimeUtil timeUtil2=timeUtil.addTime(timeUtil1);
		System.out.println(timeUtil2.toString());
	}

}
