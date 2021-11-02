package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.*;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/info")
public class InfoController {

    @PostMapping(value = "/get-common-person-information")
    public ResponseVO get_common_person_information( Integer uid){
        return new ResponseVO(new UserRespVO());
    }

    @GetMapping(value = "/get-member-list")
    public ResponseVO get_members(Integer aid){
        return new ResponseVO(new AssMembersRespVO());
    }

    @PostMapping(value = "/get-action-overview")
    public ResponseVO get_action_overview(UserAssNotePO ua){
        return new ResponseVO(new GetActionOverviewVO());
    }

    @PostMapping(value = "/get-action-info")
    public ResponseVO get_action_info(Integer uid,Integer actid){
        return new ResponseVO(new GetActionInfoVO());
    }

}
