package net.oschina.htmlsucker;

import org.junit.Test;

import java.time.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : liulq
 * @date: 创建时间: 2018/1/30 10:59
 * @version: 1.0
 * @Description:
 */
public class TimeTest {

    /**
     * * 7.最新的Date/Time API (JSR 310)
     */
    @Test
    public void DateTime() {
        //1.Clock
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        //2. ISO-8601格式且无时区信息的日期部分
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);

        System.out.println(date);
        System.out.println(dateFromClock);

        // ISO-8601格式且无时区信息的时间部分
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);

        System.out.println(time);
        System.out.println(timeFromClock);

        // 3.ISO-8601格式无时区信息的日期与时间
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        // 4.特定时区的日期/时间，
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);

        //5.在秒与纳秒级别上的一段时间
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }

    /**
     * 9.数组并行（parallel）操作
     */
    @Test
    public void testParallel() {
        long[] arrayOfLong = new long[20000];
        //1.给数组随机赋值
        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        //2.打印出前10个元素
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();
        //3.数组排序
        Arrays.parallelSort(arrayOfLong);
        //4.打印排序后的前10个元素
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();
    }
}
