package com.lizhaoxuan.codemanager.function;

import android.util.Log;

import com.lizhaoxuan.codemanager.lang.StringUtil;

import java.text.DecimalFormat;

/**
 * 单位转换工具类
 */
public class UnitUtil {
	private static final String TAG = UnitUtil.class.getSimpleName();
	private static final DecimalFormat df = new DecimalFormat("0.0");

	/**
	 * KW to MW 去掉目标对象中符号、小于1000不与转换，符合条件后除以1000 返回null时，通常调用者需使用原值，单位不变。
	 * 转换成功，单位改变
	 * 
	 * @param value
	 *            需要转换的KWH
	 * @return value为null、""、<1000 不予转换，返回null。转换失败，返回null。(调用者须对null予以处理)
	 */
	public static String kwToMW(String value) {
		return transformUpUnit(value);
	}

	/**
	 * KWH to MWH 去掉目标对象中符号、小于1000不与转换，符合条件后除以1000 返回null时，通常调用者需使用原值，单位不变.
	 * 转换成功，单位改变
	 * 
	 * @param value
	 *            需要转换的KWH
	 * @return value为null、""、<1000 不予转换，返回null。转换失败，返回null 。(调用者须对null予以处理)
	 */
	public static String kwhToMWh(String value) {
		return transformUpUnit(value);
	}

	/**
	 * KWH to MWH or KW to MW 去掉目标对象中符号、小于1000不与转换，符合条件后除以1000
	 * 
	 * @param value
	 *            需要转换的KWH
	 * @return 转换失败，返回null value为null、""、<1000 不予转换，返回null。(调用者须对null予以处理)
	 */
	private static String transformUpUnit(String value) {
		try {
			value = value.replace(",", ""); // 防止字符串中已经用逗号分隔
			Double valueResult = Double.parseDouble(value);
			if (valueResult >= 1000) {
				valueResult = valueResult / 1000;
			} else {
				return null;
			}
			return StringUtil.splitByThreeCount(df.format(valueResult));
		} catch (Exception e) {
			Log.e(TAG, "", e);
		}
		return null;
	}

}
