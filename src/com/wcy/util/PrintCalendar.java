package com.wcy.util;

import java.util.Scanner;


/**
 * 打印日历工具类
 *
 * @ClassName PrintCalendar 
 * @author wangcongyi
 * @date 2017年6月12日 下午2:52:55
 */
public class PrintCalendar {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入需要打印日历的年份：");
		int year = input.nextInt();
		System.out.println("请输入需要打印日历的月份：");
		int month = input.nextInt();
		printTitle(year, month);
		printBody(year, month);

	}

	public static void printTitle(int year, int month) { // 第二战场--头
		System.out.println("                                                      日             历");
		System.out
				.println("                            " + getMonth(month) + "                                " + year);
		System.out.println("        星期日     星期一    星期二    星期三    星期四    星期五    星期六");
		System.out.println("--------------------------------------------------");
	}

	public static String getMonth(int month) { // 这就不说啥了

		switch (month) {
		case 1:
			return "一月";
		case 2:
			return "二月";
		case 3:
			return "三月";
		case 4:
			return "四月";
		case 5:
			return "五月";
		case 6:
			return "六月";
		case 7:
			return "七月";
		case 8:
			return "八月";
		case 9:
			return "九月";
		case 10:
			return "十月";
		case 11:
			return "十一月";
		case 12:
			return "十二月";
		}
		System.out.println("输入的月份出错，程序退出！");
		System.exit(0);
		return "-1";
	}

	public static void printBody(int year, int month) { // 主战场--可要看仔细了。
		int count = getXingQi(year, month);
		int nim = getNumberInMonth(year, month); // nim 全拼是 number in month.
		int day = 1; // 每月的日子，从 1 开始。
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

	public static int getXingQi(int year, int month) { // 得到某月1号是星期几。
														// 这里会用到zeller公式。
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

	public static int getNumberInMonth(int year, int month) { // 返回某月共有几天。
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
		return -1; // 错误情况下的返回值。
	}

	public static boolean isRunNian(int year) { // 判断闰年。是就true
		if (year % 400 == 0 || (year % 4 == 0 & year % 100 != 0))
			return true;
		return false;
	}
}