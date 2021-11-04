package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.AssInfoUpdatePO;
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

    @GetMapping(value = "/delete-mail")
    public ResponseVO delete_mail (Integer uid){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-mail-content")
    public ResponseVO get_mail_content(Integer mid){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-ass-mails")
    public ResponseVO get_ass_mail_overview(Integer aid){
        return new ResponseVO(new GetMailOverviewVO());
    }

    @GetMapping(value = "/get-fixed-show-info")
    public ResponseVO get_show_info(Integer aid){
        return new ResponseVO(new AssShowInfoVO());
    }

    @GetMapping(value = "/get-person-act")
    public ResponseVO get_person_act(Integer aid){
        return new ResponseVO(new PersonActionVO());
    }

    @GetMapping(value = "/get-daily-act")
    public ResponseVO get_daily_act(Integer aid){
        return new ResponseVO(new DailyActionVO());
    }

    @GetMapping(value = "/get-ass-act")
    public ResponseVO get_ass_act(){
        return new ResponseVO(new PersonActionVO());
    }
    @GetMapping(value = "/get-sys-act")
    public ResponseVO get_system_active(){
        return new ResponseVO(new DailyActionVO());
    }

    @PostMapping(value = "/get-member-information-list")
    public ResponseVO get_member_list_ass(Integer aid){
        return new ResponseVO(new GetUsersVO());
    }

    @PostMapping(value = "/remove-user")
    public ResponseVO remove_user(UserAssNotePO ua){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/update-ass-info")
    public ResponseVO update_ass_info(AssInfoUpdatePO aiu){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-alluser-number")
    public ResponseVO get_user_number(){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-user-per")
    public ResponseVO get_user_per(){
        return new ResponseVO(new GetUserPerVO());
    }

    @GetMapping(value = "/get-action-list")
    public ResponseVO get_action_list(@RequestParam(value = "requestType")Integer rType,@RequestParam(value = "statusType") Integer sType){
        return new ResponseVO(new GetActionListVO());
    }

    @PostMapping(value = "/create-ass-reply")
    public ResponseVO create_ass_reply(Integer caid,Integer status){
        return new ResponseVO(new SimpleVO());
    }

    @GetMapping(value = "/get-ass-detail-list")
    public ResponseVO get_ass_detail(){
        return new ResponseVO(new AssDetailListVO());
    }

    @PostMapping(value = "/get-all-users")
    public ResponseVO get_all_user(){
        return new ResponseVO(new GetUsersVO());
    }
}
