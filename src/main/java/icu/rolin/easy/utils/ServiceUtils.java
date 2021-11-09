package icu.rolin.easy.utils;

import icu.rolin.easy.model.PO.LoginPO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ServiceUtils {
    private final static Logger logger = LoggerFactory.getLogger(ServiceUtils.class);

    /**
     * 生成Token操作
     * @param lpo 传入一个登录信息参数对象
     * @param uid 传入一个UID
     * @return 返回一个Token字符串密钥
     */
    public static  String generateToken(LoginPO lpo, int uid){
        return TokenUtil.sign(uid,lpo.getPassword());
    }


    //定义上传保存路径
    @Value("${web.static.path}")
    private static String path;


    /**
     * 将BASE64编码的JPG图片转化为本地图片并返回一个URL
     * @param imageBASE64
     * @return 返回一个相对URL，指向图片资源
     */
    public static String base64ToImage(String imageBASE64)  {
        //将base64 转换成 MultipartFile
        MultipartFile file = Base64ToImageUtil.base64ToMultipart(imageBASE64);
        System.out.println("file:"+file);
        //图片名称
        String fileName = file.getOriginalFilename();
        //获取图片的后缀名
        String[] suffixName = fileName.split(";");
        //生成新的图片名称
        String newImgName = UUID.randomUUID() + suffixName[0];
        File filepath = new File(path, newImgName);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + newImgName));
            logger.info("文件上传成功，上传路径为： " + path + newImgName);
            return newImgName;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("图片上传失败");
            return null;
        }
    }


    /**
     * 将用户上传的文件保存到本地并返回一个相对URL给用户
     * @param file 传入一个文件
     * @return 返回一个相对URL，指向文件资源
     */
    public static String uploadFile(MultipartFile file){
        //文件名称
        String fileName = file.getOriginalFilename();
        //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成新的图片名称
        String newFileName = UUID.randomUUID() + suffixName;
        File filepath = new File(path, newFileName);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + newFileName));
            logger.info("文件上传成功，上传路径为： " + path + newFileName);
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("文件上传失败");
            return null;
        }
    }


    /**
     * 下载文件业务
     * @param fileName 用户提供一个文件名（或文件路径），能够返回用户一个文件下载页面
     * @return 返回文件，通过HTTP传输
     */
    public static ResponseEntity<byte[]> downloadFile(String fileName){
        if (fileName == null){
            logger.warn("缺失文件名，无法定位文件位置！");
            return null;
        }else {
            File file = new File(path + File.separator + fileName);
            if (!file.exists()){
                logger.error("文件缺失，请检查存储库.");
                return null;
            }else {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentDispositionFormData("attachment",fileName);
                httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                try {
                    return new ResponseEntity<>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.OK);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("文件下载失败");
                    return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), httpHeaders, HttpStatus.EXPECTATION_FAILED);
                }
            }
        }

    }
}
