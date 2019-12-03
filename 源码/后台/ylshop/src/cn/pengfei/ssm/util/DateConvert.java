package cn.pengfei.ssm.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
	/**
	 * 转换日期格式
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date){
		  Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(currentTime);
		   return dateString;
	}
}
