package icu.rolin.easy.utils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
     * @return
     */
    public static String formatException(Exception e){
        String str  = "";
        str = "<h1>Error Type : "+e.getMessage()+"</h1>\n<br/>\n";
        str += "<h4>Error Details:</h4>\n<br/>";
        for (StackTraceElement ele : e.getStackTrace()) {
            str += "<p>"+ele.toString()+"</p>";
        }

        return str;
    }
}
