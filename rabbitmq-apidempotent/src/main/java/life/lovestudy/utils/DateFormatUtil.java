package life.lovestudy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	
	private static final String pattern = "yyyy-MM-dd HH:mm:ss";
	
	// 将date类型的日期转换成指定格式的日期
	public static String format(Date date){
		return new SimpleDateFormat(pattern).format(date);
	}
	
}
