/*
*/
package com.wcy.util;

import java.math.BigDecimal;

/**
 * ���Ҽ���ĸ�����
 *
 * @ClassName CurrencyUtil
 * @author wangcongyi
 * @date 2017��6��12�� ����2:58:12
 */
public class CurrencyUtil {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * �ṩ��ȷ�ļӷ�����
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������ĺ�
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * �ṩ��ȷ�ļ�������
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������Ĳ�
	 */
	public static double substract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * �ṩ��ȷ�ĳ˷�����
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������Ļ�
	 */
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * �ṩ����ԣ���ȷ�ĳ�������,�����������������ʱ, ��ȷ��С�����Ժ�10λ,�Ժ��������������.
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ������������
	 */
	public static double divide(double v1, double v2) {
		return divide(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * �ṩ����ԣ���ȷ�ĳ�������. �����������������ʱ,��scale����ָ ������,�Ժ��������������.
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @param scale
	 *            ��ʾ��Ҫ��ȷ��С�����Ժ�λ
	 * @return ������������
	 */
	public static double divide(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �ṩ��ȷ��С��λ�������봦��
	 * 
	 * @param v
	 *            ��Ҫ�������������
	 * @param scale
	 *            С���������λ
	 * @return ���������Ľ��
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static void main(String[] args) {
		// ֱ��ʹ�ø��������м��㣬�õ��Ľ�����������
		System.out.println(0.01 + 0.05);

		System.out.println(CurrencyUtil.add(0.01, 0.05));

		// ʹ����BigDecimal����м���󣬿���������ȷ����
		System.out.println(CurrencyUtil.add(0.0000000000005, 0.00000001));

		System.out.println("�ܼ�:" + CurrencyUtil.multiply(2.1, 100));
	}
}
