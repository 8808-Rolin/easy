package icu.rolin.easy.controller;

import icu.rolin.easy.model.PO.*;
import icu.rolin.easy.model.POJO.*;
import icu.rolin.easy.model.VO.*;
import icu.rolin.easy.service.*;
import icu.rolin.easy.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "/api/bbs")
public class BBSController {
    //注入五个Service
    @Autowired
    IncreaseService is;
    @Autowired
    DeleteService ds;
    @Autowired
    UpdateService us;
    @Autowired
    SelectService ss;




    @GetMapping(value = "/get-simple-notice")
    public ResponseVO get_simple_notices(){
        SimpleNoticePOJO[] sn = ss.showNotices();
        SimpleNoticeVO snvo = new SimpleNoticeVO();
        if (sn == null) {
            snvo.setCode(0);
            snvo.setNotice(null);
            return new ResponseVO("没有找到公告数据",snvo);
        }
        snvo.setCode(sn.length);
        snvo.setNotice(sn);
        return new ResponseVO(snvo);
    }

    // 3.3.1 获取论坛顶部数据
    @PostMapping(value = "/get-show-data")
    public ResponseVO get_show_data(Integer uid){
        ShowDataAssPOJO[] showDataAssPOJOS = ss.showDatum(uid);
        if (showDataAssPOJOS == null) return new ResponseVO(new ShowDataVO(0,"获取失败,可能是没有数据",null));
        return new ResponseVO(new ShowDataVO(showDataAssPOJOS.length,"获取成功",showDataAssPOJOS));
    }

    // 给注册用户以及非注册用户获取帖子列表使用，能够得到帖子数据的基本概要，需要传入一个页面参数，该参数可空，空则为第一页
    @GetMapping(value = "/get-post-list")
    public ResponseVO get_post_list(GetPostsPO gppo){
        Integer page = gppo.getPage();
        Integer limit = gppo.getLimit();
        if (page == null||page == 0) page = 1;
        if(limit == null||limit == 0) limit =10;
        PostsPOJO[] postsPOJOS = ss.getPosts(gppo);


        if (postsPOJOS == null || postsPOJOS.length==0)
            return new ResponseVO("没有帖子,可能是参数错误或者真就没有帖子",new GetPostListVO(0,null));
        return new ResponseVO(new GetPostListVO(postsPOJOS.length, Common.getPageLimitPost(postsPOJOS,page,limit)));
    }

    //发布帖子接口
    @PostMapping("/release-post")
    public ResponseVO release_post(ReleasePostPO rppo){
        Map<String,Integer> kv = is.publishPost(rppo);
        if(kv.get("status") == 0){//失败
            return new ResponseVO(new SimpleVO(-1,"请求错误！发表帖子错误"));
        }
        return new ResponseVO(new SimpleVO(kv.get("pid"),"发表成功！请刷新页面"));
    }


    //-1代表无任何权限,是黑户
    //用户点击进入论坛页面时，可以用该接口获取相关的个人权限以及论坛的基础信息
    @PostMapping("/get-ass-information")
    public ResponseVO get_ass_info(UserAssNotePO ua){
        GetAssInfoAssPOJO gaia = ss.getAssInformation(ua);
        Integer permissonCode = ss.getUserAssPermission(ua);
        GetAssInfoVO gai = new GetAssInfoVO();
        if (gaia == null || permissonCode == null) return new ResponseVO(new SimpleVO(1,"数据库查询错误！没有找到对应数据"));
        else {
            gai.setCode(0);
            gai.setMsg("获取成功");
            gai.setPermissionCode(permissonCode);
            gai.setAss(gaia);
            return new ResponseVO(gai);
        }
    }

    @PostMapping(value = "/get-post-page-info")
    public ResponseVO get_post_info(Integer pid,Integer uid){
        int permission = ss.getPermissionCodeWithPost(pid);
        PostPOJO post = ss.getPostInfoWithPost(pid, uid);
        MasterPOJO master = ss.getMasterInfoWithPost(pid);
        PostVO postVO = new PostVO();
        if(post == null) return new ResponseVO(new SimpleVO(1,"帖子获取失败"));
        if(permission == 2 || master == null) {
            postVO.setCode(0);
            postVO.setPermissionCode(2);
            postVO.setMsg("获取用户失败，该用户可能已经退出了该社团");
        }else{
            postVO.setCode(0);
            postVO.setPermissionCode(permission);
            postVO.setMsg("获取成功！！");
        }
        postVO.setMaster(master);
        postVO.setPost(post);
        return new ResponseVO(postVO);
    }


    @PostMapping(value = "/get-discuss-list")
    public ResponseVO get_discuss_list(GetDiscussPO gd){
        //进行一个参数的判断
        int MaxPerPage = 12;  //每页最多数量
        int page = 1;
        if (gd.getPid() == null) return new ResponseVO(new SimpleVO(-1,"缺失参数错误！"));
        if(gd.getPage() != null && gd.getPage()!=0) page = gd.getPage();
        //获取一个总页数
        int total_page = ss.getMaxPageInDiscuss(gd.getPid(),MaxPerPage);
        if(total_page == 0) return new ResponseVO(new SimpleVO(0,"该帖子暂无评论"));
        if(total_page < page) return new ResponseVO(new SimpleVO(-1,"页码参数错误！"));
        //实例化视图对象
        GetDiscussVO gdvo = new GetDiscussVO();
        gdvo.setCode(total_page);
        gdvo.setMsg("获取评论列表成功！");

        //获取一个评论列表并返回 传入gd、mpp，默认倒序排序
        DiscussPOJO[] discuss = ss.getDiscuss(page,gd.getPid(),MaxPerPage);
        gdvo.setDiscuss(discuss);
        return new ResponseVO(gdvo);
    }

    @PostMapping(value = "/release-discuss")
    public ResponseVO release_discuss(ReleaseDiscussPO rd){
        return new ResponseVO(is.sendComment(rd));
    }

    @PostMapping(value = "/delete-post-discuss")
    public ResponseVO delete_context (Integer requestType,Integer typeid){
        // 判断请求类型，调用相应的Service
        if (requestType == 0 && typeid != null)  // 删除帖子
            return new ResponseVO(ds.deletePost(typeid));
        else if(requestType == 1 && typeid != null) // 删除回复
            return new ResponseVO(ds.deleteComment(typeid));
        else //参数错误
            return new ResponseVO(new SimpleVO(1,"参数错误，请检查参数"));
    }

    @PostMapping(value = "/modify-post")
    public ResponseVO modify_post(UpdatePostPO up){
        if (up.getUid() == null ||
                up.getPid() == null ||
                up.getNewContent() == null ||
                up.getPid() == 0 ||
                up.getUid() == 0
        ) {
            return new ResponseVO(new SimpleVO(1,"参数错误，修改失败"));
        }
        return new ResponseVO(us.modifyPost(up));
    }

    @GetMapping(value = "/favoriteProcess")
    public ResponseVO favorite(PostUserPO pu){
        SimpleVO simpleVO = new SimpleVO();
        if (pu.getUid() == null || pu.getPid() == null || pu.getUid() == 0 || pu.getPid() == 0) {
            simpleVO.setMsg("请求参数错误");
            simpleVO.setCode(-1);
        }
        int uid = pu.getUid();
        int pid = pu.getPid();
        // 查询用户是否收藏了该帖子，是一个布尔值
        boolean status = ss.judgeUserFavoriteStatus(pid,uid);
        // 根据返回值决定调用收藏或者取消收藏业务方法
        boolean res;
        if(status) {//用户当前已收藏，取消收藏
            if (ds.removeFavorite(uid, pid)) {
                //删除成功
                simpleVO.setMsg("用户取消收藏成功！！！");
                simpleVO.setCode(1);
            } else {
                simpleVO.setMsg("用户取消收藏失败！！！");
                simpleVO.setCode(-1);
            }
        }else {
            if (is.collectPost(pid,uid)) {
                //收藏成功
                simpleVO.setMsg("用户收藏帖子成功！！！");
                simpleVO.setCode(0);
            } else {
                simpleVO.setMsg("用户收藏失败！！！");
                simpleVO.setCode(-1);
            }
        }
        return new ResponseVO(simpleVO);
    }

    @GetMapping(value = "/search")
    public ResponseVO search(SearchPO spo){
        // 判断参数，且设定默认参数
        if(spo == null  || spo.getKeyword() == null){
            return new ResponseVO(new SimpleVO(-500,"参数错误，关键字不能为空"));
        }
        int type = 0;
        if(spo.getType() != null) type = spo.getType();
        String key = spo.getKeyword();
        SearchVO svo = new SearchVO();
        if(type == 0){
            SearchPostPOJO[] sps =  ss.getSearchResultOfPost(key);
            svo.setMsg("搜索帖子结果展示");
            svo.setCode(sps.length);
            svo.setPosts(sps);
        }else if (type == 1){
            SearchUserPOJO[] sus = ss.getSearchResultOfUser(key);
            svo.setMsg("搜索用户结果展示");
            svo.setCode(sus.length);
            svo.setUsers(sus);
        }else{
            return new ResponseVO(new SimpleVO(-404,"参数错误，类型只能为1或者0或者空"));
        }

        return new ResponseVO(svo);
    }
}
