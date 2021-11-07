package icu.rolin.easy.controller;

import com.rabbitmq.client.AMQP;
import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.POJO.ShowDataAssPOJO;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.BBSService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/bbs")
public class BBSController {

    @Autowired
    private BBSService bbsService;

    @GetMapping(value = "/get-simple-notice")
    public ResponseVO get_simple_notices(){
        return new ResponseVO(bbsService.showNotices());
    }

    @PostMapping(value = "/get-show-data")
    public ResponseVO get_show_data(Integer uid){
        ShowDataAssPOJO[] showDataAssPOJOS = bbsService.showDatas(uid);
        if (showDataAssPOJOS != null) return new ResponseVO(new ShowDataVO(0,"",showDataAssPOJOS));
        return new ResponseVO(new ShowDataVO(-1,"获取失败",null));
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

    @PostMapping(value = "/get-post-page-info")
    public ResponseVO get_post_info(Integer pid){
        return new ResponseVO(new PostVO());
    }

    @PostMapping(value = "/get-discuss-list")
    public ResponseVO get_discuss_list(GetDiscussPO gd){
        return new ResponseVO(new GetDiscussVO());
    }

    @PostMapping(value = "/release-discuss")
    public ResponseVO release_discuss(ReleaseDiscussPO rd){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/delete-post-discuss")
    public ResponseVO delete_context (Integer requestType,Integer typeid){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/modify-post")
    public ResponseVO modify_post(UpdatePostPO up){
        return new ResponseVO(new SimpleVO());
    }

    @PostMapping(value = "/favoriteProcess")
    public ResponseVO favorite(PostUserPO pu){
        return new ResponseVO(new SimpleVO());
    }




}
