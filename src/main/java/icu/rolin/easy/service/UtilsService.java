package icu.rolin.easy.service;

import icu.rolin.easy.model.PO.LoginPO;
import icu.rolin.easy.utils.Base64ToImageUtil;
import icu.rolin.easy.utils.Common;
import icu.rolin.easy.utils.Constant;
import icu.rolin.easy.utils.TokenUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class UtilsService {
    private final static Logger logger = LoggerFactory.getLogger(UtilsService.class);

    /**
     * 生成Token操作
     * @param lpo 传入一个登录信息参数对象
     * @param uid 传入一个UID
     * @return 返回一个Token字符串密钥
     */
    public static  String generateToken(LoginPO lpo, int uid){
        return TokenUtil.sign(uid,lpo.getPassword());
    }




    /**
     * 将BASE64编码的JPG图片转化为本地图片并返回一个URL
     * 通过MD5判断图片是否已经在本地有了，有的话直接返回而不需要重新存储,文件同理
     * @param imageBASE64 Base64编码的图片
     * @return 返回一个相对URL，指向图片资源
     */
    public static String base64ToImage(String imageBASE64)  {
        //将base64 转换成 MultipartFile
        MultipartFile file = Base64ToImageUtil.base64ToMultipart(imageBASE64);
        //图片名称
        String fileName = file.getOriginalFilename();
        //获取图片的后缀名
        String[] suffixName1 = fileName.split(";");
        String suffix = suffixName1[0].split("\\.")[1];
        return handleFile(file,suffix);
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
        return handleFile(file,suffixName);
    }

    /**
     * 处理文件保存事项，对比MD5值，如果相同则不再重复保存
     * 如果不同则保存到本地，并返回一个名称
     * 通过MD5判断是否已经在本地有了，有的话直接返回而不需要重新存储,文件同理
     * @param file MultipartFile
     * @param suffix 文件后缀
     * @return 返回文件名称
     */
    public static String handleFile (MultipartFile file,String suffix){
        String fileMD5 = Common.getMd5(file);
        String newName = fileMD5+ "." +suffix;
        // 如果该文件名一致，既MD5一致，直接返回链接
        if(Common.FILE_MD5_LIST.contains(newName)){
            return newName;
        }else{ //不一致，执行保存操作
            try {
                // 写入文件
                file.transferTo(new File(Constant.FILE_PATH + File.separator + newName));
                Common.FILE_MD5_LIST.add(newName);
                logger.info("文件上传成功，上传路径为： " + Constant.FILE_PATH + newName);
                return newName;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件上传失败");
                return null;
            }
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
            File file = new File(Constant.FILE_PATH + File.separator + fileName);
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
