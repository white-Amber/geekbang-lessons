package cn.yz.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类<br/>
 * 如若新增函数，请添加完整的JDK标准注释。
 * @Description
 * @Date 2023/6/13
 * @Author yuze
 */
public class DateUtil {

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final String NORM_TIME_PATTERN = "HH:mm:ss";
    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    //--------------------------------------------------------------------------------------------------------------------------------
    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    public static final String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";
    /**
     * 标准日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String CHINESE_DATE_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒";
    //--------------------------------------------------------------------------------------------------------------------------------
    /**
     * 标准日期格式：yyyyMMdd
     */
    public static final String PURE_DATE_PATTERN = "yyyyMMdd";
    /**
     * 标准日期格式：HHmmss
     */
    public static final String PURE_TIME_PATTERN = "HHmmss";
    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    public static final String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";
    /**
     * 标准日期格式：yyyyMMddHHmmssSSS
     */
    public static final String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * 根据特定格式格式化日期
     * @param date 被格式化的日期
     * @param format 日期格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    /**
     * 格式化日期时间<br>
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
        return format(date, DateUtil.NORM_DATETIME_PATTERN);
    }

    /**
     * 格式化日期部分（不包括时间）<br>
     * 格式 yyyy-MM-dd
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        return format(date, DateUtil.NORM_DATE_PATTERN);
    }

    /**
     * 格式化时间<br>
     * 格式 HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatTime(Date date) {
        return format(date, DateUtil.NORM_TIME_PATTERN);
    }

    /**
     * 将特定格式的日期转换为Date对象
     * @param dateStr 特定格式的日期
     * @param format 格式，例如yyyy-MM-dd
     * @return Date
     */
    public static Date parse(CharSequence dateStr, String format) {
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr.toString());
        } catch (ParseException e) {
            throw new RuntimeException(String.format("Parse [%s] with format [%s] error!", dateStr, format), e);
        }
    }

    /**
     * 解析日期时间字符串<br/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static Date parseDateTime(CharSequence dateString) {
        return parse(dateString, DateUtil.NORM_DATETIME_PATTERN);
    }

    /**
     * 解析日期字符串，忽略时分秒，<br/>
     * 格式：yyyy-MM-dd
     *
     * @param dateString 标准形式的日期字符串
     * @return 日期对象
     */
    public static Date parseDate(CharSequence dateString) {
        return parse(dateString, DateUtil.NORM_DATE_PATTERN);
    }

    /**
     * 解析时间，格式HH:mm:ss，日期部分默认为1970-01-01
     *
     * @param timeString 标准形式的日期字符串
     * @return 日期对象
     */
    public static Date parseTime(CharSequence timeString) {
        return parse(timeString, DateUtil.NORM_TIME_PATTERN);
    }

    /**
     * 修改日期为某个时间字段起始时间<br/>
     * 例如：2023-01-01 12:12:12，若dateField=Calendar.HOUR，则结果是2023-01-01 12:00:00
     *
     * @param date      {@link Date}
     * @param dateField 时间字段
     * @return {@link Date}
     */
    public static Date truncate(Date date, int dateField) {
        return DateUtils.truncate(date, dateField);
    }

    /**
     * 修改日期为某个时间字段四舍五入时间
     *
     * @param date      {@link Date}
     * @param dateField 时间字段
     * @return {@link Date}
     */
    public static Date round(Date date, int dateField) {
        return DateUtils.round(date, dateField);
    }

    /**
     * 修改日期为某个时间字段结束时间
     *
     * @param date      {@link Date}
     * @param dateField 时间字段
     * @return {@link Date}
     */
    public static Date ceiling(Date date, int dateField) {
        return DateUtils.ceiling(date, dateField);
    }

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return DateFormatUtils.format(new Date(), DateUtil.NORM_DATETIME_PATTERN);
    }

    /**
     * 当前日期，格式 yyyy-MM-dd
     * @return 当前日期的标准形式字符串
     */
    public static String today() {
        return DateFormatUtils.format(new Date(), DateUtil.NORM_DATE_PATTERN);
    }

    /**
     * 获取指定日期字段的值
     * @param date 日期
     * @param field 字段
     * @return 字段的值
     */
    public static int get(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获取指定日期字段的值
     * @param date 日期
     * @param field 字段
     * @param firstDayOfWeek 周的起始天
     * @return 字段的值
     */
    public static int get(Date date, int field, int firstDayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        return calendar.get(field);
    }

    /**
     * 获得年的部分
     * @param date 日期
     * @return 年的部分
     */
    public static int year(Date date) {
        return get(date, Calendar.YEAR);
    }

    /**
     * 获得指定日期所属季度，从1开始计数
     *
     * @param date 日期
     * @return 第几个季度
     * @since 4.1.0
     */
    public static int quarter(Date date) {
        return month(date) / 3 + 1;
    }

    /**
     * 获得月份，从0开始计数
     *
     * @param date 日期
     * @return 月份，从0开始计数
     */
    public static int month(Date date) {
        return get(date, Calendar.MONTH);
    }

    /**
     * 获得指定日期是所在年份的第几周(默认以周一作为一周的起始)<br>
     *
     * @param date 日期
     * @return 周
     */
    public static int weekOfYear(Date date) {
        return get(date, Calendar.WEEK_OF_YEAR, Calendar.MONDAY);
    }

    /**
     * 获得指定日期是所在月份的第几周(默认以周一作为一周的起始)<br>
     *
     * @param date 日期
     * @return 周
     */
    public static int weekOfMonth(Date date) {
        return get(date, Calendar.WEEK_OF_MONTH, Calendar.MONDAY);
    }

    /**
     * 获得指定日期是这个日期所在月份的第几天<br>
     *
     * @param date 日期
     * @return 天
     */
    public static int dayOfMonth(Date date) {
        return get(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定日期是这个日期所在年的第几天
     *
     * @param date 日期
     * @return 天
     * @since 5.3.6
     */
    public static int dayOfYear(Date date) {
        return get(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得指定日期是星期几，1表示周日，2表示周一
     *
     * @param date 日期
     * @return 天
     */
    public static int dayOfWeek(Date date) {
        return get(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得指定日期星期的中文名
     *
     * @param date 日期
     * @return 星期的中文名
     */
    public static String dayOfWeekChinese(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 是否为周末（周六或周日）
     *
     * @param date 判定的日期{@link Date}
     * @return 是否为周末（周六或周日）
     */
    public static boolean isWeekend(Date date) {
        return dayOfWeek(date)==Calendar.SUNDAY || dayOfWeek(date)==Calendar.SATURDAY;
    }

    /**
     * 获得指定日期的小时数部分<br>
     *
     * @param date          日期
     * @param is24HourClock 是否24小时制
     * @return 小时数
     */
    public static int hour(Date date, boolean is24HourClock) {
        return get(date, is24HourClock ? Calendar.HOUR_OF_DAY : Calendar.HOUR);
    }

    /**
     * 获得指定日期的分钟数部分<br>
     * 例如：10:04:15.250 =》 4
     *
     * @param date 日期
     * @return 分钟数
     */
    public static int minute(Date date) {
        return get(date, Calendar.MINUTE);
    }

    /**
     * 获得指定日期的秒数部分<br>
     *
     * @param date 日期
     * @return 秒数
     */
    public static int second(Date date) {
        return get(date, Calendar.SECOND);
    }

    /**
     * 获得指定日期的毫秒数部分<br>
     *
     * @param date 日期
     * @return 毫秒数
     */
    public static int millisecond(Date date) {
        return get(date, Calendar.MILLISECOND);
    }

    /**
     * 是否为上午
     *
     * @param date 日期
     * @return 是否为上午
     */
    public static boolean isAM(Date date) {
        return get(date, Calendar.AM_PM) == Calendar.AM;
    }

    /**
     * 是否为下午
     *
     * @param date 日期
     * @return 是否为下午
     */
    public static boolean isPM(Date date) {
        return get(date, Calendar.AM_PM) == Calendar.PM;
    }

    /**
     * 获取指定日期偏移指定时间后的时间，生成的偏移日期不影响原日期
     *
     * @param date      基准日期
     * @param dateField 偏移的粒度大小（小时、天、月等）{@link Calendar}
     * @param offset    偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date offset(Date date, int dateField, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateField, offset);
        return calendar.getTime();
    }

    /**
     * 偏移年
     *
     * @param date   日期
     * @param offset 偏移年数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetYear(Date date, int offset) {
        return offset(date, Calendar.YEAR, offset);
    }

    /**
     * 偏移月
     *
     * @param date   日期
     * @param offset 偏移月数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetMonth(Date date, int offset) {
        return offset(date, Calendar.MONTH, offset);
    }

    /**
     * 偏移周
     *
     * @param date   日期
     * @param offset 偏移周数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetWeek(Date date, int offset) {
        return offset(date, Calendar.WEEK_OF_YEAR, offset);
    }

    /**
     * 偏移天
     *
     * @param date   日期
     * @param offset 偏移天数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetDay(Date date, int offset) {
        return offset(date, Calendar.DAY_OF_YEAR, offset);
    }

    /**
     * 偏移小时
     *
     * @param date   日期
     * @param offset 偏移小时数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetHour(Date date, int offset) {
        return offset(date, Calendar.HOUR_OF_DAY, offset);
    }

    /**
     * 偏移分钟
     *
     * @param date   日期
     * @param offset 偏移分钟数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetMinute(Date date, int offset) {
        return offset(date, Calendar.MINUTE, offset);
    }

    /**
     * 偏移秒数
     *
     * @param date   日期
     * @param offset 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetSecond(Date date, int offset) {
        return offset(date, Calendar.SECOND, offset);
    }

    /**
     * 偏移毫秒数
     *
     * @param date   日期
     * @param offset 偏移毫秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetMillisecond(Date date, int offset) {
        return offset(date, Calendar.MILLISECOND, offset);
    }

    /**
     * 判断两个日期相差的毫秒数
     *
     * @param beginDate 起始日期
     * @param endDate   结束日期
     * @return 日期差
     */
    public static long betweenMs(Date beginDate, Date endDate) {
        return new DateBetween(beginDate, endDate).between(DateUnit.MS);
    }

    /**
     * 判断两个日期相差的天数<br>
     *
     * <pre>
     * 有时候我们计算相差天数的时候需要忽略时分秒。
     * 比如：2016-02-01 23:59:59和2016-02-02 00:00:00相差一秒
     * 如果isReset为{@code false}相差天数为0。
     * 如果isReset为{@code true}相差天数将被计算为1
     * </pre>
     *
     * @param beginDate 起始日期
     * @param endDate   结束日期
     * @param isReset   是否重置时间为起始时间
     * @return 日期差
     */
    public static long betweenDay(Date beginDate, Date endDate, boolean isReset) {
        if (isReset) {
            beginDate = truncate(beginDate, Calendar.DAY_OF_MONTH);
            endDate = truncate(endDate, Calendar.DAY_OF_MONTH);
        }
        return new DateBetween(beginDate, endDate).between(DateUnit.DAY);
    }

    /**
     * 计算指定时间区间内的周数<br/>
     * 如果isReset为{@code false}，计算时分秒的精确比较，例如2023-06-11 00:00:01 - 2023-06-18 00:00:00，返回0。
     * 如果isReset为{@code true}，只计算年月日部分，例如2023-06-11 00:00:01 - 2023-06-18 00:00:00，返回1。
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param isReset   是否重置时间为起始时间
     * @return 周数
     */
    public static long betweenWeek(Date beginDate, Date endDate, boolean isReset) {
        if (isReset) {
            beginDate = truncate(beginDate, Calendar.DAY_OF_MONTH);
            endDate = truncate(endDate, Calendar.DAY_OF_MONTH);
        }
        return new DateBetween(beginDate, endDate).between(DateUnit.WEEK);
    }

    /**
     * 计算指定时间区间内的小时数<br/>
     * 相差不足1小时返回0
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 小时数
     */
    public static long betweenTime(Date beginDate, Date endDate) {
        return new DateBetween(beginDate, endDate).between(DateUnit.HOUR);
    }

    /**
     * 比较两个日期是否为同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否为同一天
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = calendar(date1.getTime());
        Calendar cal2 = calendar(date2.getTime());
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && //
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && //
            cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA);
    }

    /**
     * 转换为Calendar对象
     *
     * @param millis 时间戳
     * @return Calendar对象
     */
    private static Calendar calendar(long millis) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal;
    }

    /**
     * 计时，常用于记录某段代码的执行时间，单位：毫秒
     *
     * @param preTime 之前记录的时间
     * @return 时间差，毫秒
     */
    public static long spendMs(long preTime) {
        return System.currentTimeMillis() - preTime;
    }

    /**
     * 计时，常用于记录某段代码的执行时间，单位：纳秒
     *
     * @param preTime 之前记录的时间
     * @return 时间差，纳秒
     */
    public static long spendNt(long preTime) {
        return System.nanoTime() - preTime;
    }

    /**
     * 计算相对于dateToCompare的年龄，长用于计算指定生日在某年的年龄
     *
     * @param birthday      生日
     * @param dateToCompare 需要对比的日期
     * @return 年龄
     */
    public static int age(Date birthday, Date dateToCompare) {
        Assert.notNull(birthday, "Birthday can not be null !");
        if (null == dateToCompare) {
            dateToCompare = new Date();
        }
        return age(birthday.getTime(), dateToCompare.getTime());
    }


    /**
     * 计算相对于dateToCompare的年龄，长用于计算指定生日在某年的年龄
     *
     * @param birthday      生日
     * @param dateToCompare 需要对比的日期
     * @return 年龄
     */
    private static int age(long birthday, long dateToCompare) {
        if (birthday > dateToCompare) {
            throw new IllegalArgumentException("Birthday is after dateToCompare!");
        }

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateToCompare);

        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        final boolean isLastDayOfMonth = dayOfMonth == cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.setTimeInMillis(birthday);
        int age = year - cal.get(Calendar.YEAR);

        final int monthBirth = cal.get(Calendar.MONTH);
        if (month == monthBirth) {

            final int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            final boolean isLastDayOfMonthBirth = dayOfMonthBirth == cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            if ((!isLastDayOfMonth || !isLastDayOfMonthBirth) && dayOfMonth < dayOfMonthBirth) {
                // 如果生日在当月，但是未达到生日当天的日期，年龄减一
                age--;
            }
        } else if (month < monthBirth) {
            // 如果当前月份未达到生日的月份，年龄计算减一
            age--;
        }

        return age;
    }

    /**
     * {@code null}安全的对象比较
     *
     * @param <T>           被比较对象类型（必须实现Comparable接口）
     * @param c1            对象1，可以为{@code null}
     * @param c2            对象2，可以为{@code null}
     * @param isNullGreater 当被比较对象为null时是否排在后面，true表示null大于任何对象，false反之
     * @return 比较结果，如果c1 &lt; c2，返回数小于0，c1==c2返回0，c1 &gt; c2 大于0
     * @see java.util.Comparator#compare(Object, Object)
     */
    private static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean isNullGreater) {
        if (c1 == c2) {
            return 0;
        } else if (c1 == null) {
            return isNullGreater ? 1 : -1;
        } else if (c2 == null) {
            return isNullGreater ? -1 : 1;
        }
        return c1.compareTo(c2);
    }

    /**
     * {@code null}安全的日期比较，{@code null}对象排在末尾
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 比较结果，如果date1 &lt; date2，返回数小于0，date1==date2返回0，date1 &gt; date2 大于0
     */
    public static int compare(Date date1, Date date2) {
        return compare(date1, date2, false);
    }

    /**
     * 日期间隔
     */
    private static class DateBetween implements Serializable {
        /**
         * 开始日期
         */
        private final Date begin;
        /**
         * 结束日期
         */
        private final Date end;

        /**
         * 构造<br>
         * 在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数
         *
         * @param begin 起始时间
         * @param end   结束时间
         */
        public DateBetween(Date begin, Date end) {
            this(begin, end, true);
        }

        /**
         * 构造<br>
         * 在前的日期做为起始时间，在后的做为结束时间
         *
         * @param begin 起始时间
         * @param end   结束时间
         * @param isAbs 日期间隔是否只保留绝对值正数
         */
        public DateBetween(Date begin, Date end, boolean isAbs) {
            Assert.notNull(begin, "Begin date is null !");
            Assert.notNull(end, "End date is null !");

            if (isAbs && begin.after(end)) {
                // 间隔只为正数的情况下，如果开始日期晚于结束日期，置换之
                this.begin = end;
                this.end = begin;
            } else {
                this.begin = begin;
                this.end = end;
            }
        }

        /**
         * 判断两个日期相差的时长<br>
         * 返回 给定单位的时长差
         *
         * @param unit 相差的单位：相差 天{@link DateUnit#DAY}、小时{@link DateUnit#HOUR} 等
         * @return 时长差
         */
        public long between(DateUnit unit) {
            long diff = end.getTime() - begin.getTime();
            return diff / unit.getMillis();
        }
    }

    /**
     * 日期时间单位，每个单位都是以毫秒为基数
     */
    private enum DateUnit {
        /**
         * 一毫秒
         */
        MS(1),
        /**
         * 一秒的毫秒数
         */
        SECOND(1000),
        /**
         * 一分钟的毫秒数
         */
        MINUTE(SECOND.getMillis() * 60),
        /**
         * 一小时的毫秒数
         */
        HOUR(MINUTE.getMillis() * 60),
        /**
         * 一天的毫秒数
         */
        DAY(HOUR.getMillis() * 24),
        /**
         * 一周的毫秒数
         */
        WEEK(DAY.getMillis() * 7);

        private final long millis;

        DateUnit(long millis) {
            this.millis = millis;
        }

        /**
         * @return 单位对应的毫秒数
         */
        public long getMillis() {
            return this.millis;
        }
    }


}
