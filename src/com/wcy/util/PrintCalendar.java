package com.wcy.util;

import java.util.Scanner;


/**
 * ��ӡ����������
 *
 * @ClassName PrintCalendar 
 * @author wangcongyi
 * @date 2017��6��12�� ����2:52:55
 */
public class PrintCalendar {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ��ӡ��������ݣ�");
		int year = input.nextInt();
		System.out.println("��������Ҫ��ӡ�������·ݣ�");
		int month = input.nextInt();
		printTitle(year, month);
		printBody(year, month);

	}

	public static void printTitle(int year, int month) { // �ڶ�ս��--ͷ
		System.out.println("                                                      ��             ��");
		System.out
				.println("                            " + getMonth(month) + "                                " + year);
		System.out.println("        ������     ����һ    ���ڶ�    ������    ������    ������    ������");
		System.out.println("--------------------------------------------------");
	}

	public static String getMonth(int month) { // ��Ͳ�˵ɶ��

		switch (month) {
		case 1:
			return "һ��";
		case 2:
			return "����";
		case 3:
			return "����";
		case 4:
			return "����";
		case 5:
			return "����";
		case 6:
			return "����";
		case 7:
			return "����";
		case 8:
			return "����";
		case 9:
			return "����";
		case 10:
			return "ʮ��";
		case 11:
			return "ʮһ��";
		case 12:
			return "ʮ����";
		}
		System.out.println("������·ݳ��������˳���");
		System.exit(0);
		return "-1";
	}

	public static void printBody(int year, int month) { // ��ս��--��Ҫ����ϸ�ˡ�
		int count = getXingQi(year, month);
		int nim = getNumberInMonth(year, month); // nim ȫƴ�� number in month.
		int day = 1; // ÿ�µ����ӣ��� 1 ��ʼ��
		switch (count) {
		case 0:
			count = 1;
			break;
		case 1:
			count = 2;
			break;
		case 2:
			count = 3;
			break;
		case 3:
			count = 4;
			break;
		case 4:
			count = 5;
			break;
		case 5:
			count = 6;
			break;
		case 6:
			count = 7;
			break;
		}
		for (int i = 1; i < count; i++) {
			System.out.printf("%7s", " ");
		}
		while (day < nim) {

			while (count % 7 != 0) {
				System.out.printf("%7d", day);
				if (day == nim) {
					System.out.printf("\n");
					System.exit(0);
				}
				count++;
				day++;

			}
			System.out.printf("%7d\n", day);
			count++;
			day++;

		}

	}

	public static int getXingQi(int year, int month) { // �õ�ĳ��1�������ڼ���
														// ������õ�zeller��ʽ��
		int m = month;
		if (month == 1) {
			m = 13;
			year--;
		} else if (month == 2) {
			m = 14;
			year--;
		}
		int d = 1;
		int c = year / 100;
		int y = year % 100;
		int w1 = y + y / 4 + c / 4 - 2 * c + 26 * (m + 1) / 10 + d - 1;
		int w = (w1 % 7 + 7) % 7;
		return w;
	}

	public static int getNumberInMonth(int year, int month) { // ����ĳ�¹��м��졣
		if (isRunNian(year)) {
			switch (month) {
			case 1:
				return 31;
			case 2:
				return 29;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			}
		} else {
			switch (month) {
			case 1:
				return 31;
			case 2:
				return 28;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			}
		}
		return -1; // ��������µķ���ֵ��
	}

	public static boolean isRunNian(int year) { // �ж����ꡣ�Ǿ�true
		if (year % 400 == 0 || (year % 4 == 0 & year % 100 != 0))
			return true;
		return false;
	}
}