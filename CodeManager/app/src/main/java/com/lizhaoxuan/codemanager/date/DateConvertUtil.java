package com.lizhaoxuan.codemanager.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期转换
 * Created by lizhaoxuan on 16/2/20.
 */
public class DateConvertUtil {

    public static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance();

    public static final DateFormat DATE_TIME_FORMAT = SimpleDateFormat.getDateTimeInstance();

    public static final DateFormat TIME_FORMAT = SimpleDateFormat.getTimeInstance();

    private Calendar mCalendar = GregorianCalendar.getInstance();

    /**
     * 字符串转Date
     */
    public Date toDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
            //return DATE_TIME_FORMAT.parse(date);
            //return TIME_FORMAT.parse(date);
            //DateFormat format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
            //return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 拿到Date 年月日
     *
     * @param date
     */
    public void getYearMonthDay(Date date) {
        mCalendar.setTime(date);
        int dateYear = mCalendar.get(Calendar.YEAR);
        int dateMonth = mCalendar.get(Calendar.MONTH);
        int dateDay = mCalendar.get(Calendar.DATE);
    }

    /**
     * 计算是否循环状态下，当前日期与给定日期之间的天数
     *
     * <p/>
     * 非循环
     * day = now - target
     * nameStr = 已经
     * 循环
     * target-year = now-year
     * nameStr = 还有；
     * if(target < now){
     * day = target - now;
     * }else if (target == now){
     * day = 0;
     * nameStr = 是
     * }else {
     * now-year + 1;
     * day = now - target;
     * }
     */
    private long calculateDate(Date nowDate, Date targetDate, boolean loop) {
        long day;
        if (loop) {
            mCalendar.setTime(nowDate);
            int nowYear = mCalendar.get(Calendar.YEAR);

            mCalendar.setTime(targetDate);
            mCalendar.set(Calendar.YEAR, nowYear);

            Date target = mCalendar.getTime();
            day = target.getTime() - nowDate.getTime();

            if (day == 0) {
            } else if (day > 0) {
                mCalendar.setTime(nowDate);
                mCalendar.add(Calendar.YEAR, 1);
                day = target.getTime() - nowDate.getTime();
            }
        } else {
            day = ((nowDate.getTime() - targetDate.getTime()) / 1000 / 60 / 60 / 24);
        }
        return day;
    }


}
