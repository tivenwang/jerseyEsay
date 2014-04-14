/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.util;

import java.util.Date;
import java.util.Calendar;

/**
 * 
 * @author
 */
public class DateCalc {
	private static Calendar calendar = Calendar.getInstance();

        public static Date backwordHours(Date d, int hours)
        {
            calendar.setTime(d);
            calendar.add(Calendar.HOUR, -hours);
            return calendar.getTime();
        }  
	public static Date backwordDay(Date d, int days) {
		calendar.setTime(d);
		calendar.add(Calendar.DATE, -days);
		return calendar.getTime();
	}
	public static Date forwordDay(Date d, int days) {
		calendar.setTime(d);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static Date backwordMinute(Date d, int minutes) {
		calendar.setTime(d);
		calendar.add(Calendar.MINUTE, -minutes);
		return calendar.getTime();
	}

	public static Date forwordMinute(Date d, int minutes) {
		calendar.setTime(d);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	public static Date backwordMonth(Date d, int months) {
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, -months);
		return calendar.getTime();
	}

	public static Date forwordMonth(Date d, int months) {
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	public static Date backwordYear(Date d, int years) {
		calendar.setTime(d);
		calendar.add(Calendar.YEAR, -years);
		return calendar.getTime();
	}

	public static Date forwordYear(Date d, int years) {
		calendar.setTime(d);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
}
