package icu.rolin.easy.controller;

import icu.rolin.easy.model.VO.AssMembersRespVO;
import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.model.VO.UserRespVO;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/info")
public class infoController {

    @PostMapping(value = "/get-common-person-information")
    public ResponseVO get_common_person_information( Integer uid){
        return new ResponseVO(new UserRespVO());
    }

    @GetMapping(value = "/get-member-list")
    public ResponseVO get_members(Integer aid){
        return new ResponseVO(new AssMembersRespVO());
    }
}
