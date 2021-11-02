package icu.rolin.easy.utils;

import java.sql.Timestamp;

public class common {


    /**
     * 获取当前系统的时间戳
     * @return long,当前系统时间戳
     */
    public static long getTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
}
