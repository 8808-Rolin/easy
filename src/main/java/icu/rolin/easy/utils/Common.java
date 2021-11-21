package icu.rolin.easy.utils;

import icu.rolin.easy.model.POJO.PostsPOJO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


public class Common {
    public static Set<String> FILE_MD5_LIST = new HashSet<String>();

    /**
     * 帖子类型以及帖子类型代号直接的对应关系，是一个Map键值对形式
     * */
    public static final Map<Integer,String> POST_TYPE = new HashMap<Integer,String>();
    static {
      POST_TYPE.put(0,"系统公告");
      POST_TYPE.put(1,"交流帖");
      POST_TYPE.put(2,"疑问帖");
      POST_TYPE.put(3,"咨询帖");
      POST_TYPE.put(4,"求助帖");
      POST_TYPE.put(5,"其它");
    };
    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

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


    /**
     * 将字符串时间转化成Long类型的时间戳方便对比时间
     * 常用时间模板：yyyy-MM-dd HH:mm:ss
     * @param dateString 时间字符串
     * @param pattern 匹配模板
     * @return 返回一个Long字符串，如果失败返回0
     */
    public static Long date2Stamp(String dateString,String pattern)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return new Long(0);
        }
        long ts = date.getTime();
        return ts;
    }

    /**
     * 传入一个PostsPOJO数组，对其进行截断去对应limit
     * @param objs
     * @param page
     * @param limit
     * @return
     */
    public static PostsPOJO[] getPageLimitPost(PostsPOJO[] objs, int page, int limit){
        System.out.println(objs.length+"---"+page+"---"+limit);
        int size = objs.length;

        int start = (page -1)*limit ;

        int end = start+limit;
        if(start > size) return null;
        if (end > size) end = size;
        PostsPOJO[] newObjs = new PostsPOJO[end-start];
        System.out.println(newObjs.length);
        for (int i = 0; i < newObjs.length; i++) {
            newObjs[i] = objs[start + i];
        }
        return newObjs;
    }

    /**
     * 获取某一文件夹下所有的文件名称
     * @param dirPath 文件夹路径
     * @return 返回一个列表
     */
    public static ArrayList<String> getPathAllFileName(String dirPath){
        ArrayList<String> names = new ArrayList<String>();
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file : files) {
            names.add(file.getName());
        }
        return names;
    }

    /**
     * 获取上传文件的md5
     * @param file
     * @return
     */
    public static String getMd5(MultipartFile file) {
        try {
            //获取文件的byte信息
            byte[] uploadBytes = file.getBytes();
            // 拿到一个MD5转换器
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            //转换为16进制
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
