package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.SendMailPO;
import icu.rolin.easy.model.PO.UniVariablePO;
import icu.rolin.easy.model.VO.AssListVO;
import icu.rolin.easy.model.VO.CollegeListVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@RequestMapping(value = "/api/tool")
public class ToolController {

    @GetMapping(value = "/uni-variable")
    public ResponseVO uni_variable(UniVariablePO uv){
        return new ResponseVO(new SimpleVO());
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
