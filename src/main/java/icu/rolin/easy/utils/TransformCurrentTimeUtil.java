package icu.rolin.easy.utils;

import java.text.SimpleDateFormat;

public class TransformCurrentTimeUtil {

    public static String returnCurrentTime(){
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(currentTime);

        return time;
    }

}
