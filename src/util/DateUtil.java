package util;

// 获得各种数据

import org.omg.CORBA.DATA_CONVERSION;

import java.awt.dnd.DropTarget;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long millisecodsOfOneday = 1000*60*60*24;  // 获取1天的毫秒数

    // 通过日期控件获取到的日期类型是java.util.Date,
    // 而在JDBC的statement中插入用的日期类型又是java.sql.Date，
    // 所以需要进行转换
    public static java.sql.Date util2sql(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

    // 获取今天的日期
    // 并且把时，分，秒和毫秒都置0
    public static Date today(){
        Calendar c = Calendar.getInstance();   // 获取当前时间
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    // 获取本月第一天
    // 在统计消费一览信息的时候，查看本月的消费数据，
    // 其实就是从数据库中去把从本月第一天到最后一天的数据查出来，
    // 再进行简单统计，
    // 所以需要一个获取本月第一天的方法
    public static Date monthBegin(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        // 月初
        c.set(Calendar.DATE,1);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date monthEnd(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        // 月末
        // 下一月的1号倒推1天
        c.set(Calendar.DATE,1);
        c.add(Calendar.MONTH,1);
        c.add(Calendar.DATE,-1);
        return c.getTime();
    }

    // 本月天数
    public static int DaysofThisMonth(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();
        return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/millisecodsOfOneday) +1;
    }

    // 本月还剩多少天
    public static int thisMonthLeft(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecodsOfOneday) +1;
    }

    // 测试
    public static void main(String[] args) {
        System.out.println(DateUtil.monthEnd());
    }
}
