package icu.rolin.easy.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 *
 */
public class common {


    /**
     * 获取当前系统的时间戳
     * @return String,当前系统时间戳
     */
    public static String getTimestamp(){
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime datetime = ZonedDateTime.now(zid);
        return datetime.toString();
    }


    /**
     * 传入一个异常对象，返回一个格式化后的错误信息字符串
     * 返回一个HTML格式的字符串
     * @param e
     * @return 返回一个由错误信息组成的HTML代码
     */
    public static String formatException(Exception e){
        String str  = "";
        str = "<h2>Error Type : "+e.getMessage()+"</h2>\n<br/>\n";
        str += "<h4>Error Details:</h4>\n<br/>";
        for (StackTraceElement ele : e.getStackTrace()) {
            str += "<p>"+ele.toString()+"</p>";
        }
        return str;
    }

    /**
     * 将数据库时间戳转化为特定格式的日期字符串
     * @param timestamp 数据库时间戳
     * @param pattern 日期格式：例如 yyyy-MM-dd HH:mm:ss
     * @return 返回一个转换完成的字符串
     */
    public static String convertTimestamp2Date(Timestamp timestamp, String pattern) {
        long time = timestamp.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(time));
    }

    public static Integer transPostType(String postType){
        Integer result = 1;
        switch (postType){
            case "系统公告":
                result =0;
                break;
            case "交流贴":
                result = 1;
                break;
            default:
                result = 2;
        }
        return result;
    }

}
