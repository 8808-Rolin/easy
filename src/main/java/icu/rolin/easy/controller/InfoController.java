package icu.rolin.easy.controller;

import com.alibaba.fastjson.JSON;
import icu.rolin.easy.model.DO.Association;
import icu.rolin.easy.model.PO.AssInfoUpdatePO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.POJO.AssMemberPOJO;
import icu.rolin.easy.model.POJO.MailOverviewPOJO;
import icu.rolin.easy.model.POJO.PersonActionPOJO;
import icu.rolin.easy.model.POJO.UserPOJO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/info")
public class InfoController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;



    @PostMapping(value = "/get-common-person-information")
    public ResponseVO get_common_person_information(Integer uid){

        UserPOJO userPOJO = ss.getPersonInformation(uid);
        if (userPOJO == null){
            return new ResponseVO(new SimpleVO(1,"获取用户信息失败"));
        }else {
            return new ResponseVO(new UserRespVO(0,"",userPOJO));
        }

    }

    @GetMapping(value = "/get-member-list")
    public ResponseVO get_members(Integer aid){
        AssMemberPOJO[] amps = ss.getAssMemberList(aid);
        AssMembersRespVO amrvo = new AssMembersRespVO();
        if (aid == null) return new ResponseVO(202,"参数错误",null);

        if (!ss.getAssIsExist(aid)){
            amrvo.setCode(0);
            amrvo.setMembers(null);
            return new ResponseVO("获取信息失败,该社团不存在",amrvo);
        }
        System.out.println(JSON.toJSONString(amps));
        amrvo.setCode(amps.length);
        amrvo.setMembers(amps);
        return new ResponseVO("获取成功",amrvo);
    }

    //获取社团展示列表
    @PostMapping(value = "/get-action-overview")
    public ResponseVO get_action_overview(UserAssNotePO ua){
        PersonActionPOJO[] personActionPOJOS = ss.getActionOverview(ua);
       if (personActionPOJOS==null || personActionPOJOS.length==0 )
            return new ResponseVO(new GetActionOverviewVO(0,"该社团暂无任何活动",personActionPOJOS));
       return new ResponseVO(new GetActionOverviewVO(personActionPOJOS.length, "获取活动列表成功",personActionPOJOS));
    }

    //3.3.7 获取活动详细信息接口
    @PostMapping(value = "/get-action-info")
    public ResponseVO get_action_info(Integer uid,Integer actid){
        if(uid == null || actid == null) return new ResponseVO(new SimpleVO(-1,"参数错误！！！"));
        return new ResponseVO(ss.getDetailedActionInformation(uid,actid));
    }


    @GetMapping(value = "/delete-mail")
    public ResponseVO delete_mail (Integer uid){
        if(uid == null || uid <= 0 ){
            return new ResponseVO(new SimpleVO(-1,"参数错误，请重试"));
        }
        return new ResponseVO(us.deleteMail(uid));
    }
    
    // 获取邮箱概要
    @GetMapping(value = "/get-mails")
    public ResponseVO get_mails(Integer uid) {
        if(uid == null || uid <= 0){
            return new ResponseVO(new SimpleVO(-1,"获取错误，请检查参数"));
        }
        GetMailOverviewVO gmovo = new GetMailOverviewVO();
        MailOverviewPOJO[] mo = ss.getMails(uid);
        gmovo.setCode(mo.length);
        gmovo.setMail(mo);
        return new ResponseVO(gmovo);
    }

    @GetMapping(value = "/get-mail-content")
    public ResponseVO get_mail_content(Integer mid){
        if(mid == null || mid <= 0){
            return new ResponseVO(new SimpleVO(-1,"获取错误，请检查参数"));
        }
        return new ResponseVO(ss.getMailContent(mid));
    }

    @GetMapping(value = "/get-ass-mails")
    public ResponseVO get_ass_mail_overview(Integer aid){
        return new ResponseVO(ss.getAssMails(aid));
    }

    @GetMapping(value = "/get-fixed-show-info")
    public ResponseVO get_show_info(Integer aid){
        return new ResponseVO(ss.getShowInfo(aid));
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
        return new ResponseVO(ss.getMemberInformation(aid));
    }

    @PostMapping(value = "/remove-user")
    public ResponseVO remove_user(UserAssNotePO ua){
        return new ResponseVO(ds.removeMember(ua));
    }

    @PostMapping(value = "/update-ass-info")
    public ResponseVO update_ass_info(AssInfoUpdatePO aiu){
        return new ResponseVO(us.updateAssociationInfo(aiu));
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

    @GetMapping(value = "/get-association-info")
    public ResponseVO get_ass_info(Integer aid){
        if (aid == null || aid == 0) return  new ResponseVO(-1,"参数错误,请重新输入");
        Association association = ss.getAssociationInfoById(aid);
        if(association == null) return new ResponseVO(-1,"不存在该社团，请检查aid");
        return new ResponseVO(0,association);

    }
}
