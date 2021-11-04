package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.SendMailPO;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.model.VO.AssListVO;
import icu.rolin.easy.model.VO.CollegeListVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/tool")
public class ToolController {

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
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/upload-file")
    public ResponseVO upload_file(@RequestParam("file") MultipartFile file){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/send-email")
    public ResponseVO send_mail(SendMailPO sm){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-association-list")
    public ResponseVO get_association_list(){
        return new ResponseVO(new AssListVO());
    }

    @GetMapping(value = "/get-college-list")
    public ResponseVO get_colleges(){
        return new ResponseVO(new CollegeListVO());
    }
}
