package icu.rolin.easy.service;

import com.alibaba.fastjson.JSON;
import icu.rolin.easy.mapper.AssociationMapper;
import icu.rolin.easy.mapper.CollegeTableMapper;
import icu.rolin.easy.mapper.MailMapper;
import icu.rolin.easy.mapper.UserMapper;
import icu.rolin.easy.model.DO.Association;
import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.PO.SendMailPO;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.model.POJO.AssOverviewPOJO;
import icu.rolin.easy.model.POJO.CollegePOJO;
import icu.rolin.easy.model.VO.CollegeListVO;
import icu.rolin.easy.model.VO.SimpleVO;
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
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ToolService {
    
    // 注入Mapper
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private CollegeTableMapper collegeTableMapper;
    @Autowired
    private AssociationMapper am;

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

    /**
     * 由系统发送邮件给用户，该右键无需注明发送发送者
     * @param sm 邮件参数对象(该参数没有from_uid选项)
     * @return 返回一个boolean值，是当前业务的状态
     */
    public SimpleVO sendEmailWithSystem(SendMailPO sm){
        SimpleVO svo = new SimpleVO();
        Integer code = mailMapper.insertMailWithSystem(sm.getTouid(), sm.getTitle(), sm.getContent(), sm.getMailType());
        logger.error("发送邮件数据库返回码："+code.toString());
        if(code == 1){
            svo.setCode(mailMapper.getMaxMid());
            svo.setMsg("系统发送邮件成功");
        }else{
            svo.setCode(-1);
            svo.setMsg("发送邮件失败");
        }
        return svo;
    }

    /**
     * 由用户发送邮件给用户，该邮件需要注明发送者和接收者的id
     * @param sm 邮件参数对象
     * @return 返回一个boolean值，是当前业务的状态
     */
    public SimpleVO sendEmailWithUser(SendMailPO sm){
        SimpleVO svo = new SimpleVO();
        Integer code = mailMapper.insertMailWithUser(sm.getFromuid(), sm.getTouid(), sm.getTitle(), sm.getContent(),sm.getMailType());
        logger.error("发送邮件数据库返回码："+code.toString());
        if(code == 1){
            svo.setCode(mailMapper.getMaxMid());
            svo.setMsg("用户发送邮件成功");
        }else{
            svo.setCode(-1);
            svo.setMsg("发送邮件失败");
        }
        return svo;
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
    public CollegeListVO getCollegeList()  {
        CollegeListVO cvo = new CollegeListVO();
        ArrayList<College_Table> ctList = collegeTableMapper.findAll();
        CollegePOJO[] cps = new CollegePOJO[ctList.size()];
        if (ctList == null){
            cvo.setCode(-1);
            cvo.setCollege(null);
            return cvo;
        }

        for (int i = 0; i < ctList.size(); i++) {
            cps[i] = new CollegePOJO();
            cps[i].setName(ctList.get(i).getCollege_name());
            cps[i].setCoid(ctList.get(i).getId());
        }
        cvo.setCode(ctList.size());
        cvo.setCollege(cps);
        return cvo;
    }


    /**
     * 获取所有社团列表，展示简单的数据
     * @return 返回一个AssOverviewPOJO数组
     */
    public AssOverviewPOJO[] get_association_list(){
        ArrayList<Association>  as= am.getAssOverview();
        if(as == null) return null;
        AssOverviewPOJO[] aos = new AssOverviewPOJO[as.size()];
        for (int i = 0; i < as.size(); i++) {
            aos[i] = new AssOverviewPOJO();
            aos[i].setAid(as.get(i).getId());
            aos[i].setName(as.get(i).getName());
        }
        return aos;
    }


}
