package com.weitw.study.sbt.utils;

import cn.hutool.core.date.DateUnit;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.YEAR;

public final class DateUtils {
	public static final  String PATTERN_CHINESE1 = "yyyy年MM月dd日HH时mm分";
	public static final  String PATTERN_CHINESE2 = "yyyy年MM月dd日ah时mm分";
	public static final  String PATTERN_CHINESE1_DIAN = "yyyy年MM月dd日HH点mm分";
	public static final  String PATTERN_CHINESE2_DIAN = "yyyy年MM月dd日ah点mm分";
	public static final  String PATTERN_CHINESE3 = "yyyy年MM月dd日HH:mm";
	public static final  String PATTERN_CHINESE4 = "yyyy年MM月dd日ah:mm";
	public static final  String PATTERN_CHINESE5 = "yyyy.MM.ddHH时mm分";
	public static final  String PATTERN_CHINESE6 = "yyyy.MM.ddah时mm分";
	public static final  String PATTERN_CHINESE5_DIAN = "yyyy.MM.ddHH点mm分";
	public static final  String PATTERN_CHINESE6_DIAN = "yyyy.MM.ddah点mm分";
	public static final  String PATTERN_CHINESE7 = "yyyy.MM.ddHH:mm";
	public static final  String PATTERN_CHINESE8 = "yyyy.MM.ddah:mm";
	public static final  String PATTERN_NORMAL = "yyyy-MM-ddHH:mm";
	public static final  String PATTERN_NORMAL2 = "yyyy-MM-dd HH:mm";
	public static final  String PATTERN_NORMAL3 = "yyyy/MM/dd HH:mm";
	public static final  String PATTERN_CHINESE_SIMPLE1 = "yyyy-MM-dd";
	public static final  String PATTERN_CHINESE_SIMPLE2 = "yyyy.MM.dd";
	public static final  String PATTERN_CHINESE_SIMPLE3 = "yyyy~MM~dd";
	public static final  String PATTERN_YYMMDD_HHMMSS1 = "yyyyMMdd HH:mm:ss";
	public static final  String PATTERN_YYMMDD_HHMMSS2 = "yyyy-MM-dd HH:mm:ss";

	private DateUtils() {
	}

	public static Date[] calculateDateSegment() {
		Date[] dates = new Date[2];

		Calendar calendar = Calendar.getInstance();
		// 截止时间就是现在
		// dates[1] = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		dates[1] = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		dates[0] = calendar.getTime();
		return dates;
	}

	public static boolean isToday(Date date) {
		Calendar input = Calendar.getInstance();
		input.setTime(date);
		Calendar now = Calendar.getInstance();
		return now.get(YEAR) == input.get(YEAR) && now.get(DAY_OF_YEAR) == input.get(DAY_OF_YEAR);
	}

	/**
	 * 日期简单如（11.19）
	 * 
	 * @param date
	 * @return
	 */
	public static String shortDateFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("M.d");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}

	/**
	 * 日期简单如（2016-11-19）
	 * 
	 * @param date
	 * @return
	 */
	public static String textDateFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyyMMdd");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}

	/**
	 * 日期简单如（2016-11-19）
	 * 
	 * @param date
	 * @return
	 */
	public static String chineseDateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
		if (null == date)
			return "";
		return sdf.format(date);
	}

	public static String chineseDateFormatYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		if (null == date)
			return "";
		return sdf.format(date);
	}
	
	/**
	 * 日期简单如（2016-11-19 00:00:00）
	 * 
	 * @param date
	 * @return
	 */
	public static String textDateTimeFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}
	
	/**
	 * 日期简单如（2016-11-19 00:00:00）
	 * 
	 * @param date
	 * @return
	 */
	public static String textDateMinuteFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyyMMddHHmm");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}

	/**
	 * 日期简单如（2016111914,年月日时）
	 *
	 * @param date
	 * @return
	 */
	public static String textDateHourFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyyMMddHH");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}

	/**
	 * 日期简单如（2016-11-19 00:00:00）
	 * 
	 * @param date
	 * @return
	 */
	public static String textYearFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyy");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}


	/**
	 * 日期简单如（20161119_1008）
	 * @author wl
	 * @date 2020-08-07 09:48:48
	 * @param date
	 * @return java.lang.String
	 */
	public static String String2DateTime(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyyMMdd_HHmm");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}
	
	public static Date string2Date(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isBlank(date)) {
			return null;
		}
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Date string2DateWithT(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isBlank(date)) {
			return null;
		}
		date = date.replace("T", " ");
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static Date stringDateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (StringUtils.isBlank(date)) {
			return null;
		}
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 转换成当天开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateToBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 转换成当天结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateToEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 日期简单如（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String normalDateFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}

	public static String notNormalDateFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyy.MM.dd_HHmm");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}
	
	/**
	 * 前n天时间区间
	 * 
	 * @param n
	 * @return
	 */
	public static Date[] beforeDate(int n) {
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -n);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		dates[1] = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		dates[0] = calendar.getTime();
		return dates;
	}

	/**
	 * 获取今年第一天
	 * 
	 * @return
	 */
	public static Date firstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取前一天
	 * 
	 * @return
	 */
	public static Date beforDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取上个月第一天第一秒
	 * 
	 * @author chengxu
	 * @date 2018年11月27日
	 * @return
	 */
	public static Date firstDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取上个月最后一天最后一秒
	 * 
	 * @author chengxu
	 * @date 2018年11月27日
	 * @return
	 */
	public static Date lastDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 获取上周周日第一秒
	 * 
	 * @author chengxu
	 * @date 2018年11月27日
	 * @return
	 */
	public static Date firstDayOfLastWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取这周周日最后一天最后一秒
	 * 
	 * @author chengxu
	 * @date 2018年11月27日
	 * @return
	 */
	public static Date lastDayOfLastWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 获取上周一的第一秒（周日为一周第一天）
	 * 
	 * @author chengxu
	 * @date 2018年12月12日
	 * @return
	 */
	public static Date mondayOfLastWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取这周日的第一秒（周日为一周的第一天）
	 * 
	 * @author chengxu
	 * @date 2018年12月12日
	 * @return
	 */
	public static Date sundayOfThistWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 获取当月第一天第一秒
	 * 
	 * @author xuwei
	 * @date 2019年02月19日
	 * @return
	 */
	public static Date firstDayOfThisMonth(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(time);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取当月最后一天最后一秒
	 * 
	 * @author xuwei
	 * @date 2019年02月19日
	 * @return
	 */
	public static Date lastDayOfThisMonth(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(time);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 获取n天后的最后一秒
	 */
	public static Date nDaysOfLaterEnd(Date date, Integer ndays) {
		if(date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, ndays);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 获取n天后的第一秒
	 */
	public static Date nDaysOfLaterStart(Date date, Integer ndays) {
		if(date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, ndays);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取n天以前
	 */
	public static Date nDaysLater(Date date, Integer ndays) {
		if(date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, ndays);
		return calendar.getTime();
	}
	/**
	 * 获取时间差。分钟
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int findMinutes(Date start, Date end) {
		if (end.getTime() - start.getTime() < 0)
			return 0;
		return (int) ((end.getTime() - start.getTime()) / (1000 * 60));
	}
	/**
	 * 获取时间差-分钟，一分钟以内也算成一分钟
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int findMinutesExcept0(Date start, Date end) {
		if (end.getTime() - start.getTime() < 0)
			return 0;
		int min = (int)((end.getTime() - start.getTime()) / (1000 * 60));
		if(min == 0){//如果时间在1分钟以内，则默认设置1分钟
			min = 1;
		}
		return min;
	}

	public static List<String> listDays(Date startDate, Date endDate) {
		if(startDate == null || endDate == null) {
			return null;
		}
		endDate = DateUtils.dateToEnd(endDate);
		List<String> dates = Lists.newArrayList();
		boolean flag = true;
		while (flag) {
			if (startDate.before(endDate)) {
				dates.add(DateUtils.textDateFormat(startDate));
				startDate = DateUtils.nDaysOfLaterStart(startDate, 1);
				continue;
			}
			break;
		}
		return dates;
	}

	/**
	 * 获取时间差。天
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int findDays(Date start, Date end) {
		if (end.getTime() - dateToBegin(start).getTime() < 0)
			return 0;
		return (int) ((end.getTime() - dateToBegin(start).getTime()) / (1000 * 60 * 60 * 24));
	}
	
    public static Date[] getDateByType(Date date, int type){
		
		Date beginTime = null;
        Date endTime = null;
		if(type == 0){//本月
			beginTime = firstDayOfThisMonth(date);
	        endTime = lastDayOfThisMonth(date);
		}else if(type == 1){//上月
			beginTime = firstDayOfLastMonth();
	        endTime = lastDayOfLastMonth();
		}else if(type == 2){//本季
			beginTime = getFirstDayOfQuarter(date);
			endTime = getLastDayOfQuarter(date);
		}else if(type == 3){//全年
			beginTime = getFirstDayOfYear(date);
			endTime = getLastDayOfYear(date);
		}else{
			beginTime = firstDayOfThisMonth(date);
	        endTime = lastDayOfThisMonth(date);
		}
		
		return new Date[]{beginTime, endTime};
	}
	
	/**
     * 得到本季度第一天的日期
     *
     * @return Date
     * @Methods Name getFirstDayOfQuarter
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int curMonth = cDay.get(Calendar.MONTH);
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
            cDay.set(Calendar.MONTH, Calendar.JANUARY);
        }
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
            cDay.set(Calendar.MONTH, Calendar.APRIL);
        }
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
            cDay.set(Calendar.MONTH, Calendar.JULY);
        }
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
            cDay.set(Calendar.MONTH, Calendar.OCTOBER);
        }
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
        cDay.set(Calendar.HOUR_OF_DAY, 0);
        cDay.set(Calendar.MINUTE, 0);
        cDay.set(Calendar.SECOND, 0);
        return cDay.getTime();
    }

    /**
     * 得到本季度最后一天的日期
     *
     * @return Date
     * @Methods Name getLastDayOfQuarter
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int curMonth = cDay.get(Calendar.MONTH);
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
            cDay.set(Calendar.MONTH, Calendar.MARCH);
        }
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
            cDay.set(Calendar.MONTH, Calendar.JUNE);
        }
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
            //cDay.set(Calendar.MONTH, Calendar.AUGUST); 
            cDay.set(Calendar.MONTH, Calendar.SEPTEMBER);
        }
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
            cDay.set(Calendar.MONTH, Calendar.DECEMBER);
        }
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        cDay.set(Calendar.HOUR_OF_DAY, 23);
        cDay.set(Calendar.MINUTE, 59);
        cDay.set(Calendar.SECOND, 59);
        return cDay.getTime();
    }
    
    /**
     * 本年第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date){
    	Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.MONTH,cDay.getActualMinimum(Calendar.MONTH));
        cDay.set(Calendar.DAY_OF_MONTH,cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
        cDay.set(Calendar.HOUR_OF_DAY, 0);
        cDay.set(Calendar.MINUTE, 0);
        cDay.set(Calendar.SECOND, 0);
        return cDay.getTime();
    }
    
    /**
     * 本年最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date){
    	Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.MONTH,cDay.getActualMaximum(Calendar.MONTH));
        cDay.set(Calendar.DAY_OF_MONTH,cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        cDay.set(Calendar.HOUR_OF_DAY, 23);
        cDay.set(Calendar.MINUTE, 59);
        cDay.set(Calendar.SECOND, 59);
        return cDay.getTime();
    }
    
    /**
     * 前六个月集合，包括当月
     * @param date
     * @return
     */
    public static List<String> getSixMonth(String date) {
        //返回值
        List<String> list = new ArrayList<String>();
        int month = Integer.parseInt(date.substring(5, 7));
        int year = Integer.parseInt(date.substring(0, 4));
        for (int i = 5; i >= 0; i--) {
            if (month > 6) {
                if (month - i >= 10) {
                    list.add(year + "-" + String.valueOf(month - i));
                } else {
                    list.add(year + "-0" + String.valueOf(month - i));
                }
            } else {
                if (month - i <= 0) {
                    if (month - i + 12 >= 10) {
                        list.add(String.valueOf(year - 1) + "-" + String.valueOf(month - i + 12));
                    } else {
                        list.add(String.valueOf(year - 1) + "-0" + String.valueOf(month - i + 12));
                    }
                } else {
                    if (month - i >= 10) {
                        list.add(String.valueOf(year) + "-" + String.valueOf(month - i));
                    } else {
                        list.add(String.valueOf(year) + "-0" + String.valueOf(month - i));
                    }
                }
            }
        }
        return list;

    }
    
    public static String getFirstDayOfYear(){
    	Date date = firstDay();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == date)
			return "";
		return sdf.format(date);
    }
    
    public static String dayDateFormat(Date date) {
		DateFormat tIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date)
			return "";
		return tIMEFORMAT.format(date);
	}

	/**
	 * 日期格式：yyyy/MM/dd HH:mm
	 * @param date
	 * @return
	 */
	public static String dayDateFormat1(Date date) {
		DateFormat df = new SimpleDateFormat(PATTERN_NORMAL3);
		if (null == date)
			return "";
		return df.format(date);
	}

	/**
	 * 根据格式化参数 将 Date类型转String
	 * @param date
	 * @param pattern
	 * @return
	 */
    public static String date2StringByPattern(Date date, String pattern) {
    	if(null == date || StringUtils.isEmpty(pattern)){
    		return "";
		}
		DateFormat tIMEFORMAT = new SimpleDateFormat(pattern);
		return tIMEFORMAT.format(date);
	}

	/**
	 * 根据格式化参数 将 String类型转Date
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date string2DateFormat(String date,String pattern) {
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return d;
	}

	public static Date string2DateFormat(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(date)) {
			return null;
		}
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将字符串按照指定格式化成 Date
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date string2DateFormatSettingPattern(String date, String pattern) {
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将毫秒型字符串转成指定格式化型字符串
	 * @param milliSecond
	 * @param pattern
	 * @return
	 */
	public static String millis2StringFormatSettingPattern(long milliSecond, String pattern) {
		if(milliSecond == 0 || StringUtils.isEmpty(pattern)){
			return null;
		}
		Date date = new Date();
		date.setTime(milliSecond);
		return new SimpleDateFormat(pattern).format(date.getTime());
	}

	public static Date string2DateFormat2(String date,String pattern) {
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Date string2DateFormatNYR(String date) {
		return string2DateFormat2(date, "yyyyMMdd");
	}

	public static String date2StringFormatNY(Date date) {
		return date2StringByPattern(date, "yyyyMM");
	}

	/**
	 * 获取指定日期的年份YYYY
	 * @param date
	 * @return
	 */
	public static int getYearOfDate(Date date){
		if (null == date) {
			return 0;
		}
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		return cDay.get(Calendar.YEAR);
	}

	/**
	 * 获取指定日期的月份MM
	 * @param date
	 * @return
	 */
	public static int getMonthOfDate(Date date){
		if (null == date) {
			return 0;
		}
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		return cDay.get(Calendar.MONTH) + 1;
	}

	public static Date shortString2Date(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(date)) {
			return null;
		}
		Date d = new Date();
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 日期转为LocalDateTime
	 *
	 * @param date 日期
	 * @return LocalDateTime
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		if (null == date) {
			return null;
		}
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}
	/**
	 * 日期转为LocalDate
	 *
	 * @param date 日期
	 * @return LocalDateTime
	 */
	public static LocalDate dateToLocalDate(Date date) {
		if (null == date) {
			return null;
		}
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDate localDate = instant.atZone(zoneId).toLocalDate();
		return localDate;
	}

	/**
	 * LocalDateTime转为日期
	 *
	 * @param localDateTime LocalDateTime
	 * @return 日期
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		if (null == localDateTime) {
			return null;
		}
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}

	/**
	 * LocalDate转为日期
	 *
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		if (null == localDate) {
			return null;
		}
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDate.atStartOfDay().atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}

	/**
	 * 获取指定时间后多久的时间(用Java8时间，保证线程安全)
	 * @param date 指定时间
	 * @param num 时间变化的数量，正数表示计算date后的时间，负数表示计算date前的时间
	 * @param unit 时间维度 例如：年月日时分秒
	 * @return Date
	 */
	public static Date getDateAfterDate(Date date, int num, ChronoUnit unit) {
		if (date == null || unit == null) {
			return date;
		}
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		// 计算时间
		localDateTime = localDateTime.plus(num, unit);
		return localDateTimeToDate(localDateTime);
	}

	/**
	 * 计算时间间隔
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @return end-begin
	 */
	public static long computeTimeInterval(Date begin, Date end, DateUnit unit) {
		if (begin == null) {
			return Long.MIN_VALUE;
		}
		if (end == null) {
			return Long.MAX_VALUE;
		}
		long diff = end.getTime() - begin.getTime();
		return diff / unit.getMillis();
	}

    public static String getChineseDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (null == date)
            return "";
        String riqi = sdf.format(date);

        String[] newsdate = new String[8];
        for (int i = 0; i < riqi.length(); i++) {
            int k = Integer.parseInt(Character.toString(riqi.charAt(i)));
            switch (k) {
                case 0:
                    newsdate[i] = "〇";
                    break;
                case 1:
                    newsdate[i] = "一";
                    break;
                case 2:
                    newsdate[i] = "二";
                    break;
                case 3:
                    newsdate[i] = "三";
                    break;
                case 4:
                    newsdate[i] = "四";
                    break;
                case 5:
                    newsdate[i] = "五";
                    break;
                case 6:
                    newsdate[i] = "六";
                    break;
                case 7:
                    newsdate[i] = "七";
                    break;
                case 8:
                    newsdate[i] = "八";
                    break;
                case 9:
                    newsdate[i] = "九";
                    break;
            }
        }

        // 加入年月日
        List<String> s1 = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                s1.add(newsdate[i]);
            } else if (i == 4) {
                s1.add("年");
                s1.add(newsdate[i]);
            } else if (i == 5) {
                s1.add(newsdate[i]);
            } else if (i == 6) {
                s1.add("月");
                s1.add(newsdate[i]);
            } else if (i == 7) {
                s1.add(newsdate[i]);
                s1.add("日");
            }

        }

        String newstr = "";
        for (String s : s1) {
            newstr += s;
        }

        /*
         * 截取月份、日期
         */
        int i = newstr.indexOf("年");
        int j = newstr.indexOf("月");
        String month = newstr.substring(i + 1, j);
        String day = newstr.substring(j + 1, newstr.length() - 1);
        /*
         * 处理月份
         */
        String str1 = month.substring(0, 1);
        String str2 = month.substring(1);
        String newmonth = "";
        if ("〇".equals(str1)) {
            newmonth = str2;
        } else if ("一".equals(str1) && "〇".equals(str2)) {
            newmonth = "十";
        } else if ("一".equals(str1) && !"〇".equals(str2)) {
            newmonth = "十" + str2;
        }

        /*
         * 处理日期
         */
        String st1 = day.substring(0, 1);
        String st2 = day.substring(1);
        String newday = "";
        if ("〇".equals(st1)) {
            newday = st2;
        } else if ("一".equals(st1) && "〇".equals(st2)) {
            newday = "十";
        } else if ("一".equals(st1) && !"〇".equals(st2)) {
            newday = "十" + st2;
        } else if ("二".equals(st1) && "〇".equals(st2)) {
            newday = st1 + "十";
        } else if ("二".equals(st1) && !"〇".equals(st2)) {
            newday = st1 + "十" + st2;
        } else if ("三".equals(st1) && "〇".equals(st2)) {
            newday = st1 + "十";
        } else if ("三".equals(st1) && !"〇".equals(st2)) {
            newday = st1 + "十" + st2;
        }
        String newstring = newstr.substring(0, i) + "年" + newmonth + "月" + newday + "日";
        return newstring;

    }

	/**
	 * 将时间的年月日和时分秒分开，拼成一个数据返回
	 * @param inputDateTimeStr
	 * @param pattern
	 * @return
	 */
	public static String[] getDateTimeArray(String inputDateTimeStr, String pattern) {
		try {
			// 给定的日期时间字符串

			// 创建SimpleDateFormat实例，用于解析输入的日期时间字符串
			SimpleDateFormat inputFormat = new SimpleDateFormat(pattern, Locale.getDefault());

			// 解析输入的日期时间字符串为Date对象
			Date date = inputFormat.parse(inputDateTimeStr);

			// 创建SimpleDateFormat实例，用于格式化日期为yyyyMMdd格式
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
			// 格式化Date对象为字符串
			String dateStr = dateFormat.format(date);

			// 创建SimpleDateFormat实例，用于格式化时间为HHmmss格式
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss", Locale.getDefault());
			// 格式化Date对象为字符串（这里实际上只取时间部分，但因为我们是从完整的日期时间解析来的，所以不影响结果）
			String timeStr = timeFormat.format(date);

			// 输出结果
			System.out.println("Date in yyyyMMdd format: " + dateStr);
			System.out.println("Time in HHmmss format: " + timeStr);
			return new String[]{dateStr, timeStr};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Date begin = DateUtils.string2Date("2021-01-01 24:23:12");
		Date end = DateUtils.string2Date("2022-01-01 01:34:23");
		System.out.println(DateUtils.computeTimeInterval(begin, end, DateUnit.DAY));


        String dateStr = "2017-05-06";
//        DateUtils.getDateAfterDate(new Date(),20,ChronoUnit.DAYS)
        String date = DateUtils.getChineseDate(new Date());

        System.out.println("转换为日期对象: "+date);

        System.out.println(DateUtils.date2StringByPattern(new Date(),"yyyyMMdd"));
	}


}
