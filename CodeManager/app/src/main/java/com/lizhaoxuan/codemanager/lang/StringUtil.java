package com.lizhaoxuan.codemanager.lang;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

/**
 * 字符串操作工具类
 */
public class StringUtil {
	private static final String TAG = StringUtil.class.getSimpleName();

	private static final String ELLIPSIS = "...";

	/**
	 * 将字符串垂直显示
	 * 
	 * @param before
	 *            初始字符串
	 * @return 垂直的字符串 before为null，返回 ""
	 */
	public static String verticalString(String before) {
		if (before == null || before.equals(""))
			return "";
		String[] texts = before.split("");
		StringBuffer sbf = new StringBuffer();
		for (String s : texts) {
			sbf.append(s);
			sbf.append("\n");
		}
		return sbf.toString();
	}

	/**
	 * 三位计数法 分割字符串
	 * 
	 * @param value
	 *            要处理的字符串
	 * @return value为null或""时，返回 ""；value格式不合法，返回原值
	 */
	public static String splitByThreeCount(String value) {
		if (value == null || value.equals(""))
			return "";
		String myValue = value;
		try {
			value = value.replace(",", ""); // 防止字符串中已经用逗号分隔
			String[] str = value.split("\\.");

			DecimalFormat myformat = new DecimalFormat();
			myformat.applyPattern("##,###,###,###,##0");
			if (null != str && str.length == 1) {
				try {
					myValue = myformat.format(Double.parseDouble(value));
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}

			} else if (null != str && str.length == 2) {
				try {
					myValue = new StringBuffer()
							.append(myformat.format(Double.parseDouble(str[0])))
							.append(".").append(str[1]).toString();
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return myValue;
	}

	/***
	 * 按指定长度分割字符串,通常用于短信
	 * 
	 * @param msg
	 *            字符串
	 * @param num
	 *            指定字符数字 min:2
	 * @return msg为null或""时， 或num<2时，返回 null
	 */
	public static String[] splitStringByLength(String msg, int num) {
		if (msg == null || msg.equals(""))
			return null;
		if (num < 2) {
			return null;
		}
		num += 1;
		int len = msg.length();
		if (len <= num) {
			return new String[] { msg };
		}

		int count = len / (num - 1);
		count += len > (num - 1) * count ? 1 : 0; // 这里应该值得注意

		String[] result = new String[count];

		int pos = 0;
		int splitLen = num - 1;
		for (int i = 0; i < count; i++) {
			if (i == count - 1) {
				splitLen = len - pos;
			}
			result[i] = msg.substring(pos, pos + splitLen);
			pos += splitLen;
		}

		return result;
	}

	/***
	 * 截取指定长度字符串+省略号 例： abcdfef = abcd。。。
	 * 
	 * @param string
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return 指定长度字符串+省略号 string=null时，返回""
	 */
	public static String limitString(String string, int length) {
		if (string != null) {
			if (string.length() > length) {
				return string.substring(0, length) + ELLIPSIS;
			} else {
				return string;
			}

		} else {
			return "";
		}
	}

	/**
	 * 去除字符串中匹配正则表达式的内容
	 * 
	 * @param source
	 *            字符串
	 * @param reg
	 *            正则表达式
	 * @return 失败返回 ""
	 */
	public static String extractStr(String source, String reg) {
		if (source == null || source.equals(""))
			return "";
		if (reg == null || reg.equals(""))
			return source;
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(source);
		return m.replaceAll("").trim();
	}

	/**
	 * 如果string为空，返回空字符串，否则返回string
	 * 
	 * @param string
	 * @return 失败返回 ""
	 */
	public static String getText(String string) {
		if (null != string && !string.isEmpty()) {
			return string;
		}
		return "";
	}

	/**
	 * 判断是否为空
	 * 
	 * @param string
	 * @return true 为空或者为空字符串，false不为空
	 */
	public static boolean isEmpty(String string) {

		if (null != string && !string.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 截取两个char[]中最长的公共部分
	 * 
	 * @param str1
	 * @param str2
	 * @return str1或str2为null时，返回""
	 */
	public static String longestCommonSubstring(char[] str1, char[] str2) {
		if (str1 == null || str2 == null)
			return "";
		int i, len1, len2, len, s1Start, s2Start, idx, curmax, max;
		int k = -1;
		len1 = str1.length;
		len2 = str2.length;
		len = len1 + len2;
		max = 0;
		for (i = 0; i < len; i++) {
			s1Start = 0;
			s2Start = 0;
			if (i < len1) {
				s1Start = len1 - i; // 每次开始匹配的起始位置
			} else {
				s2Start = i - len1;
			}
			curmax = 0;
			for (idx = 0; (s1Start + idx < len1) && (s2Start + idx < len2); idx++) {
				if (str1[s1Start + idx] == str2[s2Start + idx]) {
					curmax++;
					// 只要有一个不相等，就说明相等的公共字符断了，不连续了，要保存curmax与max中的最大值，并将curmax重置为0
				} else {
					if (curmax > max) {
						max = curmax;
						k = s1Start + idx - 1; // 保存连续子串长度增加时连续子串最后一个字符在str1字符串中的下标位置，便于输出公共连续子串
					}
					curmax = 0;
				}
			}
			if (curmax > max) {
				max = curmax;
				k = s1Start + idx - 1;
			}
		}

		// 输出公共子串
		char[] s = new char[len2];
		if (k != -1) {
			for (i = 0; i < max; i++) {
				s[i] = str1[k - max + 1 + i]; // 公共字串在str1中的下标起始位置为k-max+1，结束位置为k
			}
		}
		return String.copyValueOf(s).trim();
	}

	/**
	 * 把字符串转换为Double
	 * 
	 * @param value
	 * @return value为null或不是double格式的值，返回0.0
	 */
	public static Double stringToDouble(String value) {
		Double result = 0.0;
		try {
			value = value.replace(",", ""); // 防止字符串中已经用逗号分隔
			result = Double.valueOf(value);
		} catch (Exception e) {
			Log.e(TAG, "", e);
		}
		return result;
	}

	/**
	 * 返回对应格式的字符串形式的数字
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	private static String getFormartValue(String value, String format) {
		if (value == null)
			return value;
		DecimalFormat df = new DecimalFormat(format);
		String myValue = null;
		try {
			myValue = StringUtil.splitByThreeCount(df.format(Double.parseDouble(value)));
		} catch (Exception e) {
			myValue = value;
		}
		return myValue;
	}

}
