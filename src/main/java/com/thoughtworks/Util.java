package com.thoughtworks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类：将日期转为星期几
 * 后续其他工具扩充
 * @author zlx
 *
 */
public class Util {
	
	/**
	 * 将日期转为周几，并且返回枚举类型
	 * 枚举类型有周内和周末
	 * @param datetime
	 * @return
	 */
	public static DayType dateToWeek(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期天","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(date);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w <=0||w==6){
        	return DayType.WEEKEND;
        }else
            return DayType.WEEKDAY;
    }
	
	/**
	 * 枚举类型
	 */
	enum DayType{
		WEEKDAY,WEEKEND
	}
	
	/** 
	 * 字符串转时间格式，返回时间的小时
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static int stringToTime(String time) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("HH:MM");
		return format.parse(time).getHours();
	}
}
