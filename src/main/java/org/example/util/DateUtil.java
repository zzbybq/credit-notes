package org.example.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static Date getDateOfCurrcentMonth(Date date, int day) {
        if (date == null || day < 1 || day > 31) {
            throw new IllegalArgumentException("日期不能为空，天数有效范围1至31");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (day > 28) {
            // 判断这个月有没有28天
            int maxDay = calendar.getMaximum(Calendar.DATE);
            if (day > maxDay) {
                throw new IllegalArgumentException("当前月份，没有指定的日期");
            }
        }
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static int getIntervalDays(Date date, Date otherDate) {
        return Math.abs(getIntervalDaysActual(date, otherDate));
    }

    public static int getIntervalDaysActual(Date date, Date otherDate) {
        if (date == null || otherDate == null) {
            throw new IllegalArgumentException("日期不能为空");
        }
        long day = 24L * 3600 * 1000;
        int d1 = (int) (date.getTime() / day);
        int d2 = (int) (otherDate.getTime() / day);
        return d1 - d2;
    }
}
