package icu.rolin.easy.controller;


import icu.rolin.easy.model.PO.UidProfilePO;
import icu.rolin.easy.model.VO.GetInformationVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.SimpleVO;
import icu.rolin.easy.model.VO.ZonePostVO;
import icu.rolin.easy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/zone")
public class ZoneController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;


    @GetMapping(value = "/get-zone-status")
    public ResponseVO get_status(Integer muid, HttpServletRequest request, HttpServletResponse response) {
        Integer code = ss.verifyUserZoneStatus(muid, request, response);
        if (code == 0) {
            return new ResponseVO(new SimpleVO(code, "访问自己空间"));
        } else if (code == 1) {
            return new ResponseVO(new SimpleVO(code, "访问他人空间"));
        }else {
            return new ResponseVO(new SimpleVO(code,"当前空间不允许被访问!"));
        }

    }

    @GetMapping(value = "/get-post")
    public ResponseVO get_post(Integer type, @RequestParam(value = "zone-uid") Integer uid){
        return new ResponseVO(ss.getPost(type,uid));
    }

    @GetMapping(value = "/get-information")
    public ResponseVO get_information(Integer uid){
        return new ResponseVO(ss.getUserInformation(uid));
    }

    @PostMapping(value = "/update-name")
    public ResponseVO name_update(UidProfilePO up){
        return new ResponseVO(us.updateUserName(up));
    }

    @PostMapping(value = "/update-intro")
    public ResponseVO intro_update(UidProfilePO up){
        return new ResponseVO(us.updateUserIntro(up));
    }

    @PostMapping(value = "/update-profile")
    public ResponseVO profile_update(UidProfilePO up){
        return new ResponseVO(us.updateUserHeadImage(up));
    }
    @PostMapping(value = "/update-email")
    public ResponseVO email_update(UidProfilePO up){
        return new ResponseVO(us.updateUserEmail(up));
    }

    @PostMapping(value = "/update-birth")
    public ResponseVO birth_update(UidProfilePO up){
        return new ResponseVO(us.updateUserBirth(up));
    }

}
