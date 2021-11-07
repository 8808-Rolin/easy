package icu.rolin.easy.service;

import icu.rolin.easy.mapper.AssociationMapper;
import icu.rolin.easy.mapper.AssociationUserMapper;
import icu.rolin.easy.mapper.PostMapper;
import icu.rolin.easy.model.DO.Association;
import icu.rolin.easy.model.DO.Association_User;
import icu.rolin.easy.model.DO.Post;
import icu.rolin.easy.model.POJO.ShowDataAssPOJO;
import icu.rolin.easy.model.POJO.SimpleNoticePOJO;
import icu.rolin.easy.model.VO.SimpleNoticeVO;
import icu.rolin.easy.utils.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BBSService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private AssociationUserMapper associationUserMapper;
    @Autowired
    private AssociationMapper associationMapper;
    private final static Logger logger = LoggerFactory.getLogger(BBSService.class);

    /**
     * 获取公告交流区的公告
     * @return 返回一个SimpleNoticePOJO数组
     */
    public SimpleNoticePOJO[] showNotices(){
        ArrayList<Post> posts = postMapper.findPostByPostType(0);
        if(posts.size() == 0) return null;

        SimpleNoticePOJO[] simpleNoticePOJOS = new SimpleNoticePOJO[posts.size()];
        for (int i = 0;i<posts.size();i++){
            simpleNoticePOJOS[i].setTitle(posts.get(i).getTitle());
            simpleNoticePOJOS[i].setDate(common.convertTimestamp2Date(posts.get(i).getCreate_time(),"yyyy-MM-dd"));
            simpleNoticePOJOS[i].setPid(posts.get(i).getId());
        }
        return simpleNoticePOJOS;
    }


    /**
     * 用以所有社团信息、用户加入信息业务处理
     * @param uid 传入一个UID
     * @return  返回一个ShowDataAssPOJO数组
     */
    public ShowDataAssPOJO[] showDatum(Integer uid){
        ArrayList<Association> associations = associationMapper.findAllAssociation();
        if(associations.size() == 0) return null;
        ShowDataAssPOJO[] sdaPOJOs = new ShowDataAssPOJO[associations.size()];
        for (int i = 0; i < associations.size(); i++) {
            sdaPOJOs[i].setAid(associations.get(i).getId());
            sdaPOJOs[i].setAssImage(associations.get(i).getLogo());
            sdaPOJOs[i].setAssName(associations.get(i).getName());
            //判断用户是否加入该社团
            Integer result = associationUserMapper.getUserIsJoinAssociation(sdaPOJOs[i].getAid(),uid);
            if (result > 1 || result < 0) {
                logger.error("获取用户是否参加社团数据失败，可能是数据库出现了不唯一性，请检查数据库\n数据处理结果Result："+result);
                return null;
            }
            sdaPOJOs[i].setIsJoin(result);
        }
        return sdaPOJOs;
    }

}
