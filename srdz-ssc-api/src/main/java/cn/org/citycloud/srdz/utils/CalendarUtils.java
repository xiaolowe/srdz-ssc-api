package cn.org.citycloud.srdz.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getFirstDay(Date date){
		Calendar clr = Calendar.getInstance();
		clr.setTime(date);
		clr.set(Calendar.DAY_OF_MONTH,1);
		return df.format(clr.getTime());
	}
	
	public static String getLastDay(Date date){
		Calendar clr = Calendar.getInstance();
		clr.setTime(date);
		clr.set(Calendar.DAY_OF_MONTH, clr.getActualMaximum(Calendar.DAY_OF_MONTH));
		return df.format(clr.getTime());
	}
}
