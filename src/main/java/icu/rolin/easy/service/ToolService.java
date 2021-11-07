package icu.rolin.easy.service;

import icu.rolin.easy.mapper.MailMapper;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.PO.SendMailPO;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.model.POJO.CollegePOJO;
import icu.rolin.easy.model.VO.CollegeListVO;
import icu.rolin.easy.utils.Base64ToImageUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ToolService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailMapper mailMapper;
    private final static Logger logger = LoggerFactory.getLogger(ToolService.class);
    // 判断用户学号与手机号是否唯一
    public int verifyAccountUniqueness(UniVariablePO uv){

        Integer code = userMapper.verifyAccount(uv.getStudentID(), uv.getPhone());
        if (code == 0){
            return 0;
        }else if (code == 1) {
            Integer Student_numberUniCode = userMapper.verifyStudent_number(uv.getStudentID());
            Integer PhoneUniCode = userMapper.verifyPhone(uv.getPhone());
            if (Student_numberUniCode == 1 && PhoneUniCode == 0) {
                return 1;
            } else if (Student_numberUniCode == 0 && PhoneUniCode == 1) {
                return 2;
            } else if (Student_numberUniCode == 1 && PhoneUniCode == 1) {
                return 3;
            }else{
                return 4;
            }
        }else if(code == 2){
            return 3;
        }else {
            logger.error("核验账号学号方法出现了超过2条数据的情况，检查语句是否出现错误！");
            return 4;
        }

    }

    //定义上传图片保存路径
    @Value("${web.static.path}")
    private String path;
    // base64转存图片返回连接
    public String base64ToImage(String imageBASE64)  {
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
            logger.info("文件上传失败");
            return null;
        }

    }

    public String uploadFile(MultipartFile file){
        System.out.println("file:"+file);
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

    public ResponseEntity<byte[]> downloadFile(String fileName){
        System.out.println(fileName);
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

    public boolean sendEmail(SendMailPO sm){
        Integer code = mailMapper.insertMail(sm.getFromuid(), sm.getTouid(), sm.getTitle(), sm.getContent(), sm.getIsSystem(), sm.getMailType());
        if (code == null){
            logger.error("邮件写入失败，数据库返回空集！");
            code = -1;
        }
        return code == 1;
    }

    public String getUserNameById(Integer uid){
        String userName = userMapper.getNameById(uid);
        if (userName == null){
            return "用户名为空！";
        }else {
            return userName;
        }
    }

    // 获取学院列表的业务方法
    public CollegeListVO getCollegeList(){
        CollegeListVO cvo = new CollegeListVO();

        CollegePOJO[] cps = new CollegePOJO[1];
    }

}
