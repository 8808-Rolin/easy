package icu.rolin.easy.service;

import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.utils.Base64ToImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ToolService {

    @Autowired
    private UserMapper userMapper;
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
}
