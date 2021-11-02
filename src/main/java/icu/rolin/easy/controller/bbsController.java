package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.GetPostsPO;
import icu.rolin.easy.model.PO.ReleasePostPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import icu.rolin.easy.model.VO.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/bbs")
public class bbsController {

    @GetMapping(value = "/get-simple-notice")
    public ResponseVO get_simple_notices(){
        return new ResponseVO(new SimpleNoticeVO());
    }

    @PostMapping(value = "/get-show-data")
    public ResponseVO get_show_data(Integer uid){
        return new ResponseVO(new ShowDataVO());
    }

    @GetMapping(value = "/get-post-list")
    public ResponseVO get_post_list(GetPostsPO gppo){
        return new ResponseVO(new GetPostListVO());
    }

    @PostMapping("/release-post")
    public ResponseVO release_post(ReleasePostPO rppo){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping("/get-ass-information")
    public ResponseVO get_ass_info(UserAssNotePO ua){
        return new ResponseVO(new GetAssInfoVO());
    }







}
