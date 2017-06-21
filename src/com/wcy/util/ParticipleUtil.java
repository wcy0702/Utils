package com.wcy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 分词搜索工具类
 * 
 * @description : ParticipleUtil
 * @author : wuhaijun
 * @date : 2016年11月4日 上午11:45:15
 */
public class ParticipleUtil {

	public static void main(String[] args) {
		String str = "123 g456e4 789";
		List<String> keywordList = new ArrayList<>();
		keywordList.add("1");
		keywordList.add("2");
		keywordList.add("3");
		keywordList.add("123");
		keywordList.add("123");
		keywordList.add("456");
		keywordList.add("789");
		keywordList.add("aaa");
		keywordList.add("bbb");
		keywordList.add("ccc");
		keywordList.add("ddd");
		keywordList.add("eee");
		List<String> result = ParticipleUtil.getParticiple(str, keywordList, true, true);
		for (String string : result) {
			System.out.println(string);
		}

	}

	/**
	 * @description : 获取分词搜索
	 * @author : wuhaijun
	 * @date : 2016年11月4日 上午11:47:30
	 * @param :
	 *            mulFlag 重复的关键字是否重复写到List
	 * @param :
	 *            blankFlag 空格拆分
	 * @return : List<String>
	 */
	public static List<String> getParticiple(String str, List<String> keywordList, boolean mulFlag, boolean blankFlag) {
		Map<String, Boolean> keyMap = new HashMap<String, Boolean>();
		List<String> list = new ArrayList<String>();

		// 基础判断
		str = str == null ? "" : str.trim();
		if (str.length() < 1) {
			return list;
		}
		if (str.length() < 3) {
			list.add(str);
			return list;
		}

		// 关键字拆分
		if (keywordList != null && keywordList.size() > 0) {
			for (String keyword : keywordList) {
				keyword = keyword == null ? "" : keyword.trim();
				if ("".equals(keyword) || keyMap.containsKey(keyword)) {
					continue;
				}
				if (str.contains(keyword) && mulFlag) {
					keyMap.put(keyword, true);
					int len = mul(str, keyword);
					for (int i = 0; i < len; i++) {
						list.add(keyword);
					}
				} else if (str.contains(keyword)) {
					keyMap.put(keyword, true);
					list.add(keyword);
				}
			}
		}

		// 空格拆分
		if (blankFlag) {
			String[] strs = str.split(" ");
			for (String ss : strs) {
				if (ss.length() < 1 || keyMap.containsKey(ss)) {
					continue;
				}
				if (mulFlag) {
					int len = mul(str, ss);
					for (int i = 0; i < len; i++) {
						list.add(ss);
					}
				} else {
					list.add(ss);
				}
				keyMap.put(ss, true);
			}
		}

		// 全词匹配
		if (!keyMap.containsKey(str)) {
			list.add(str);
		}

		return list;
	}

	// 重复关键字的次数
	private static int mul(String str, String keyword) {
		int k = 1;
		int len = 0;
		int keywordLen = keyword.length();
		for (int i = 0; i < k; i++) {
			int index = str.indexOf(keyword, i);
			if (index > -1) {
				len++;
				i = index + keywordLen - 1;
				k = i + 2;
			}
		}
		return len;
	}
}
