package icu.rolin.easy.controller;


import icu.rolin.easy.model.PO.SendMailPO;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.model.POJO.AssOverviewPOJO;
import icu.rolin.easy.model.POJO.CollegePOJO;
import icu.rolin.easy.model.VO.AssListVO;
import icu.rolin.easy.model.VO.CollegeListVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/tool")
public class ToolController {

    //Service注入
    @Autowired
    private ToolService toolService;



    @GetMapping(value = "/uni-variable")
    public ResponseVO uni_variable(UniVariablePO uv){
        int key = toolService.verifyAccountUniqueness(uv);

        if (key == 0){
            return new ResponseVO(new SimpleVO(0,"均不重复"));
        }else if (key == 1){
            return new ResponseVO(new SimpleVO(1,"学号重复"));
        }else if (key == 2){
            return new ResponseVO(new SimpleVO(2,"手机号重复"));
        }else if (key == 3){
            return new ResponseVO(new SimpleVO(3,"均重复"));
        }else {
            return new ResponseVO(new SimpleVO(4,"后端操作数据库出现错误"));
        }

    }

    @PostMapping(value = "/upload-image")
    public ResponseVO upload_image(String imageBASE64){
        String path = toolService.base64ToImage(imageBASE64);
        if (path == null){
            return new ResponseVO(new SimpleVO(1,"图片上传失败"));
        }else {
            return new ResponseVO(new SimpleVO(0,"/images/"+path));
        }

    }

    @PostMapping(value = "/upload-file")
    public ResponseVO upload_file(@RequestParam("file") MultipartFile file){
        String fileName = toolService.uploadFile(file);
        if (fileName == null){
            return new ResponseVO(new SimpleVO(1,"文件上传失败"));
        }else {
            return new ResponseVO(new SimpleVO(0,fileName));
        }
    }


    @PostMapping(value = "/download-file")
    public ResponseEntity<byte[]> download_file(String fileName, Integer uid){
        ResponseEntity<byte[]> file = toolService.downloadFile(fileName);
        String userName = toolService.getUserNameById(uid);
        if (file != null){
            System.out.println("用户名为："+userName+"，正在下载名为："+fileName+"的文件...");
            return file;
        }else {
            System.out.println("用户名为："+userName+"，无法成功下载名为："+fileName+"的文件...");
            return null;
        }
    }

    @PostMapping(value = "/send-email")
    public ResponseVO send_mail(SendMailPO sm){
        if(sm.getIsSystem() == 0 && sm.getFromuid() != null) //用户
            return new ResponseVO(toolService.sendEmailWithUser(sm));
        else if(sm.getIsSystem() == 1)
            return new ResponseVO(toolService.sendEmailWithSystem(sm));
        else
            return new ResponseVO(202,"请求参数错误！",new SimpleVO(-1,"请求参数错误！"));

    }

    @GetMapping(value = "/get-association-list")
    public ResponseVO get_association_list(){
        AssOverviewPOJO[] aos = toolService.get_association_list();
        AssListVO alvo = new AssListVO();
        if (aos == null){
            alvo.setCode(0);
            alvo.setAss(null);
            return new ResponseVO("没有找到对呀的数据，或服务器出错",alvo);
        }else{
            alvo.setCode(aos.length);
            alvo.setAss(aos);
            return new ResponseVO(alvo);
        }
    }

    @GetMapping(value = "/get-college-list")
    public ResponseVO get_colleges(){
        ResponseVO rvo = new ResponseVO();
        CollegeListVO collegeListVO = new CollegeListVO();
        rvo.setData(toolService.getCollegeList());
        rvo.setStatus(200);
        rvo.setMessage("");
        return rvo.update();
    }
}
