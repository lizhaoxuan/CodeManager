package com.lizhaoxuan.codemanager.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.text.TextUtils;
import android.util.Log;

import com.lizhaoxuan.codemanager.lang.StringUtil;

/**
 * 日期操作工具类
 */
public class DateUtil {

	private static final String TAG = DateUtil.class.getSimpleName();

	/** 年 */
	public static final String Y = "yyyy";

	/** 年-月 */
	public static final String Y_M = "yyyy-MM";

	/** 年-月-日 */
	public static final String Y_M_D = "yyyy-MM-dd";

	/** 年-月-日 时:分:秒 */
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

	/** 月 */
	public static final String M = "MM";

	/** 月-日 */
	public static final String M_D = "MM-dd";

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 *            日期对象
	 * @param format
	 *            转换格式
	 * @return 失败返回null
	 */
	public static String dateToString(Date date, String format) {
		if (date == null) {
			return null;
		} else if (TextUtils.isEmpty(format)) {
			return new SimpleDateFormat(Y_M_D_H_M_S, Locale.getDefault())
					.format(date);
		} else {
			return new SimpleDateFormat(format, Locale.getDefault())
					.format(date);
		}
	}

	/**
	 * 将日期类型转换成long型
	 * 
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss类型
	 * @return 返回 -1，计算错误。否则返回正长整型
	 */
	public static long dateToLong(String date) {
		if (date == null) {
			return -1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(Y_M_D_H_M_S,
				Locale.getDefault());
		if (date.length() < Y_M_D_H_M_S.length()) {
			date += ":00";
		}
		Date dt2 = null;
		try {
			dt2 = sdf.parse(date);
		} catch (ParseException e) {
			Log.e(TAG, "", e);
		}
		return (null != dt2) ? (dt2.getTime()) : -1;
	}

	/**
	 * 字符串转换成日期对象
	 * 
	 * @param date
	 *            string类型的日期 例如:1970-01-01 23:23:23
	 * @param format
	 *            传入类型的格式 例如:yyyy-MM-dd HH:mm:ss
	 * @return 失败返回null
	 */
	public static Date stringToDate(String date, String format) {
		try {
			if (TextUtils.isEmpty(date)) {
				return null;
			} else if (TextUtils.isEmpty(format)) {
				return new SimpleDateFormat(Y_M_D_H_M_S, Locale.getDefault())
						.parse(date);
			} else {
				return new SimpleDateFormat(format, Locale.getDefault())
						.parse(date);
			}
		} catch (Exception e) {
			Log.e(TAG, "", e);
			return null;
		}
	}

	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param format
	 *            日期格式
	 * @return 失败返回null
	 */
	public static Float diffOfMinutes(String startTime, String endTime,
			String format) {
		if (StringUtil.isEmpty(startTime) || StringUtil.isEmpty(endTime)
				|| StringUtil.isEmpty(format)) {
			return null;
		}
		try {
			SimpleDateFormat sd = new SimpleDateFormat(format,
					Locale.getDefault());
			long mm = 1000 * 60;// 一分钟的毫秒数
			long diff = 0;
			// 获得两个时间的毫秒时间差异
			try {
				diff = sd.parse(endTime).getTime()
						- sd.parse(startTime).getTime();
			} catch (ParseException e) {
				Log.e(TAG, "", e);
			}
			return (float) Math.floor(diff / mm);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 计算两个日期之间相差的小时数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param format 日期格式
	 * @return 失败返回null
	 */
	public static Float diffOfHours(String startTime, String endTime,
			String format) {
		if (StringUtil.isEmpty(startTime) || StringUtil.isEmpty(endTime)
				|| StringUtil.isEmpty(format)) {
			return null;
		}
		try {
			// 按照传入的格式生成一个simpledateformate对象
			SimpleDateFormat sd = new SimpleDateFormat(format);
			long nm = 1000 * 60;// 一分钟的毫秒数
			long diff = 0;
			// 获得两个时间的毫秒时间差异
			try {
				diff = sd.parse(endTime).getTime()
						- sd.parse(startTime).getTime();
			} catch (ParseException e) {
				Log.e(TAG, "", e);
			}
			float hour = (float) Math.floor(diff / nm) / 60;// 计算差多少分钟 % nd % nh
			return hour;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param d1
	 *            目标日期
	 * @param d2
	 *            当前日期
	 * @return 失败返回null 日期为空，计算错误。否则返回正整数
	 */
	public static Integer differOfDays(Date d1, Date d2, String format) {
		if (null == d1 || null == d2 || StringUtil.isEmpty(format)) {
			return null;
		}
		try {
			if (d1 != null && d2 != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				d1 = sdf.parse(sdf.format(d1));
				d2 = sdf.parse(sdf.format(d2));
				Calendar cal = Calendar.getInstance();
				cal.setTime(d1);
				long time1 = cal.getTimeInMillis();
				cal.setTime(d2);
				long time2 = cal.getTimeInMillis();
				long between_days = (time1 - time2) / (1000 * 3600 * 24);
				return Integer.parseInt(String.valueOf(between_days));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "", e);
		}
		return null;
	}

	/**
	 * 获取两个日期之间的年
	 * 
	 * @param d1
	 *            当前日期
	 * @param d2
	 *            目标日期
	 * @return 返回 null 日期为空，计算错误。否则返回正整数
	 */
	public static Integer differOfYears(Date d1, Date d2) {
		if (null == d1 || null == d2) {
			return null;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
	}

	/**
	 * 获取两个日期之间的月数
	 * 
	 * @param d1
	 *            当前日期
	 * @param d2
	 *            目标日期
	 * @return 返回 null 日期为空，计算错误。否则返回正整数
	 */
	public static Integer differOfMonths(Date d1, Date d2) {
		if (null == d1 || null == d2) {
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int diffmon = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		int diffyear = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		return diffmon + diffyear * 12;
	}

	/**
	 * 毫秒转换成时间字符串
	 * 
	 * @param mill
	 *            毫秒数
	 * @return 失败返回null
	 */
	public static String timeLongToString(long mill, String format) {
		if (null == format) {
			return null;
		}
		SimpleDateFormat sdf = null;
		Date d = null;
		try {
			sdf = new SimpleDateFormat(format, Locale.getDefault());
			d = new Date(mill);
			return sdf.format(d);
		} catch (Exception e) {
			Log.e(TAG, "", e);
			return null;
		}

	}

	/**
	 * 时间字符串转换成毫秒数
	 * 
	 * @param time
	 *            时间字符串
	 * @param format
	 * @return 失败返回null
	 */
	public static Long timeStringToLong(String time, String format) {
		if (null == format || null == time) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format,
					Locale.getDefault());
			Date d = (Date) sdf.parse(time);
			return d.getTime();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
