package issac.tools;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtils {

    public static final int YEAR_RETURN = 0;

    public static final int MONTH_RETURN = 1;

    public static final int DAY_RETURN = 2;

    public static final int HOUR_RETURN = 3;

    public static final int MINUTE_RETURN = 4;

    public static final int SECOND_RETURN = 5;

    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    public static String formatDate(final Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTime(final Date date) {
        return formatDate(date, "yyyyMMddHHmmss");
    }

    public static String getFormatDate(final Date date) {
        return formatDate(date, "yyyyMMdd");

    }

    public static String getFormatDates(final Date date) {
        return formatDate(date, "yyyy-MM-dd");

    }

    public static String getFormatDateMin(final Date date) {
        return formatDate(date, "yyyyMMddHHmm");
    }

    public static String formatDate(final Date date, final String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static int getBetweenDays(final Date beginDate, final Date endDate) {
        Long dateBetween = getBetween(beginDate, endDate, DAY_RETURN);
        return dateBetween.intValue();
    }

    public static boolean isBefore(Date beginDate, Date endDate) {
        if (Objects.isNull(beginDate) || Objects.isNull(endDate)) {
            return false;
        }
        ZonedDateTime beginDateTime = beginDate.toInstant().atZone(ZoneId.systemDefault());
        ZonedDateTime endDateTime = endDate.toInstant().atZone(ZoneId.systemDefault());
        return beginDateTime.isBefore(endDateTime);
    }

    public static int getBetweenSeconds(final Date beginDate, final Date endDate) {
        Long dateBetween = getBetween(beginDate, endDate, SECOND_RETURN);
        return dateBetween.intValue();
    }

    public static Long getBetween(final Date beginDate, final Date endDate, int returnPattern) {
        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        endCalendar.setTime(endDate);
        endCalendar.setTime(endDate);
        switch (returnPattern) {
            case YEAR_RETURN:
                return DateUtils.getByField(beginCalendar, endCalendar, Calendar.YEAR);
            case MONTH_RETURN:
                return (DateUtils.getByField(beginCalendar, endCalendar, Calendar.YEAR) * 12)
                        + DateUtils.getByField(beginCalendar, endCalendar, Calendar.MONTH);
            case DAY_RETURN:
                return DateUtils.getTime(beginDate, endDate) / (24 * 60 * 60 * 1000);
            case HOUR_RETURN:
                return DateUtils.getTime(beginDate, endDate) / (60 * 60 * 1000);
            case MINUTE_RETURN:
                return DateUtils.getTime(beginDate, endDate) / (60 * 1000);
            case SECOND_RETURN:
                return DateUtils.getTime(beginDate, endDate) / 1000;
            default:
                return 0L;
        }
    }

    /**
     * 时间计算
     *
     * @param beginDate
     * @param value
     * @return
     */
    public static Date calculate(final Date beginDate, final int value, int type) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        beginCalendar.add(type, value);
        return beginCalendar.getTime();

    }

    public static Date buildDate(final Date date, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar returnDate = Calendar.getInstance();
        returnDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), value);
        return returnDate.getTime();
    }

    private static long getByField(Calendar beginCalendar, Calendar endCalendar, int calendarField) {
        return endCalendar.get(calendarField) - beginCalendar.get(calendarField);
    }

    private static long getTime(Date beginDate, Date endDate) {
        return endDate.getTime() - beginDate.getTime();
    }

    public static List<Date> getFullDay(Date date) {
        List<Date> dates = new ArrayList<>();
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(date);
        beginCalendar.set(Calendar.HOUR_OF_DAY, 0);
        beginCalendar.set(Calendar.MINUTE, 0);
        beginCalendar.set(Calendar.SECOND, 0);
        beginCalendar.set(Calendar.MILLISECOND, 0);
        dates.add(beginCalendar.getTime());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        dates.add(endCalendar.getTime());
        return dates;
    }

    public static Date parseDate(Date date) {
        return parse(getFormatDate(date), "yyyyMMdd");
    }

    public static Date parseDates(Date date) {
        return parse(getFormatDates(date), "yyyy-MM-dd");
    }

    public static Date parseDateMin(Date date) {
        return parse(getFormatDateMin(date), "yyyyMMddHHmm");
    }

    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDateTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDateDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 最大化查询日期
     * <p>
     * eg：<br>
     * 输入2017-01-01，输出2017-01-01 23:59:59<br>
     * 输入2017-01-01 12:12:12，输出2017-01-01 23:59:59<br>
     *
     * @param date
     */
    public static Date upper(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 最小化查询日期
     *
     * @param date
     * @return
     */
    public static Date down(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 得到该月第一天
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFirstDayOfMonth(Date date, String pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return DateFormatUtils.format(c, pattern);
    }

    /**
     * 得到该周第一天
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFirstDayOfWeek(Date date, String pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(Calendar.DATE, -1);
        }
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.add(Calendar.DATE, 1);
        return DateFormatUtils.format(c, pattern);
    }

    /**
     * 得到该月最后一天
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getLastDayOfMonth(Date date, String pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return DateFormatUtils.format(c, pattern);
    }

    /**
     * 得到上一个月
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getLastMonth(Date date, String pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return DateFormatUtils.format(c, pattern);
    }

    /**
     * 得到上一个月
     *
     * @param date
     * @return
     */
    public static Date getLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * 时间转日期 eg：2017-01-01 23:23:23 -> 2017-01-01 00:00:00
     *
     * @param dateTime
     * @return
     */
    public static Date getDate(Date dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 格式化日期
     *
     * @param dateList
     * @param pattern
     * @return
     */
    public static List<String> format(List<Date> dateList, String pattern) {
        List<String> result = new ArrayList<String>(dateList.size());
        for (Date date : dateList) {
            result.add(DateFormatUtils.format(date, pattern));
        }
        return result;
    }

    /**
     * 返回前一天
     *
     * @return
     */
    public static Date yesterday() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    public static Date calDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     * 解析
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            // throw new SystemException(e);
        }
        return null;
    }

    /**
     * 解析int类型的时间
     *
     * @param updateTime 10位时间戳
     * @return
     */
    public static Date parse(int updateTime) {
        if (updateTime > 0) {
            return new Date(updateTime * 1000L);
        } else {
            return DateUtils.parse("1970-01-01 08:00:01", "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 获得时间范围的一段日期列表
     */
    public static List<Date> getAllDays(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        int days = getBetweenDays(startDate, endDate) + 1;
        for (int i = 0; i < days; i++) {
            dates.add(DateUtils.calculate(startDate, i, Calendar.DATE));
        }
        return dates;
    }

    /**
     * 获取日期范围内的各自然周的活跃量 周一到周日的自然周列表
     */
    public static List<String> getAllWeeks(Date startDate, Date endDate) {
        int days = getBetweenDays(startDate, endDate);
        // 时间间隔<6,不是一个完整的周
        if (days < Calendar.DAY_OF_WEEK - 1) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();

        // 时间间隔=6，且开始时间不是周一，不是一个完整的周一至周日
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(startDate);
        int sweek = calStart.get(Calendar.DAY_OF_WEEK);

        if (days == Calendar.DAY_OF_WEEK - 1) {
            if (sweek != Calendar.MONDAY) {
                return Collections.emptyList();
            } else {
                list.add(formatDate(startDate, SHORT_DATE_FORMAT) + "~" + formatDate(endDate, SHORT_DATE_FORMAT));
                return list;
            }
        }

        if (days > Calendar.DAY_OF_WEEK - 1) {
            for (int i = 0; i < days; i++) {
                if (calStart.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    Date weekEnd = DateUtils.calculate(startDate, Calendar.DAY_OF_WEEK - 1, Calendar.DATE);
                    if (endDate.equals(weekEnd) || weekEnd.before(endDate)) {
                        list.add(formatDate(startDate, SHORT_DATE_FORMAT) + "~"
                                + formatDate(weekEnd, SHORT_DATE_FORMAT));
                    }
                    startDate = DateUtils.calculate(startDate, Calendar.DAY_OF_WEEK - 1, Calendar.DATE);
                    calStart.setTime(startDate);
                } else {
                    startDate = DateUtils.calculate(startDate, 1, Calendar.DATE);
                    calStart.setTime(startDate);
                }
            }
        }
        return list;
    }

    /**
     * 获取当月的天数
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期范围内的各自然月的活跃量 1号到月末的自然月列表
     */
    public static List<String> getAllMonths(Date startDate, Date endDate) {
        // 开始时间当月的天数
        int daysOfMonth = getDaysOfMonth(startDate);
        // 两个时间相差天数
        int days = getBetweenDays(startDate, endDate);
        // 两个日期相差不是一个完整的月
        if (days < daysOfMonth - 1) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(startDate);
        int sMonth = calStart.get(Calendar.DAY_OF_MONTH);

        if (days == daysOfMonth - 1) {
            // 不是一号
            if (sMonth != 1) {
                return Collections.emptyList();
            } else {
                list.add(formatDate(startDate, SHORT_DATE_FORMAT) + "~" + formatDate(endDate, SHORT_DATE_FORMAT));
                return list;
            }
        }

        if (days > daysOfMonth) {
            for (int i = 0; i < days; i++) {
                int calInt = calStart.get(Calendar.DAY_OF_MONTH);
                if (calInt == 1) {
                    Calendar calEnd = Calendar.getInstance();
                    calEnd.set(Calendar.DAY_OF_MONTH, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
                    calEnd.setTime(calStart.getTime());
                    calEnd.add(Calendar.MONTH, 1);
                    calEnd.add(Calendar.DAY_OF_MONTH, -1);
                    Date MonthEnd = calEnd.getTime();

                    if (endDate.equals(MonthEnd) || MonthEnd.before(endDate)) {
                        list.add(formatDate(calStart.getTime(), SHORT_DATE_FORMAT) + "~"
                                + formatDate(calEnd.getTime(), SHORT_DATE_FORMAT));
                    }
                    startDate = DateUtils.calculate(startDate, calStart.getActualMaximum(Calendar.DAY_OF_MONTH),
                            Calendar.DATE);
                    calStart.setTime(startDate);
                } else {
                    calStart.set(Calendar.DAY_OF_MONTH, 1);
                    calStart.add(Calendar.MONTH, 1);
                }
            }
        }
        return list;
    }

    public static Date getMonth(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * 判断日期是否为当月
     */
    public static boolean isThisMonth(Date mydate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);

        calendar.setTime(mydate);
        int dateYear = calendar.get(Calendar.YEAR);
        int dateMonth = calendar.get(Calendar.MONTH);
        if (nowYear == dateYear && nowMonth == dateMonth) {
            return true;
        }
        return false;
    }

    public static Date dayOfStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取当前时间
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;

    }

    /**
     * 功能描述：返回年
     *
     * @param date
     * @return 返回月份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);

    }

    /**
     * 功能描述：返回日期
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();

    }

    /**
     * 返回当前时间的前10分钟
     *
     * @param date
     * @return
     */
    public static long getDeMillis(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTimeInMillis();
    }

    /**
     * @param date
     * @return 星期一，星期二........
     */
    public static String getWeek(Date date) {
        return new SimpleDateFormat("EEEE").format(date);
    }

    public static Date transformDate(Long times) {
        // 13位的秒级别的时间戳
        return parse(new SimpleDateFormat(SHORT_DATE_FORMAT).format(times), SHORT_DATE_FORMAT);
    }

    /**
     * 把时间戳转化为Date
     */
    public static Date transformDates(Long times) {
        // 13位的秒级别的时间戳
        return parse(new SimpleDateFormat(FORMAT_FULL).format(times), FORMAT_FULL);
    }

    /**
     * 把时间戳转化为String
     */
    public static String getDateTime(Long timeLong) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(timeLong);
    }
}