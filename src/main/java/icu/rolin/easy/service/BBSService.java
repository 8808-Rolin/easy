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

    public SimpleNoticeVO showNotices(){
        ArrayList<Post> posts = postMapper.getAllPosts();
        SimpleNoticePOJO[] simpleNoticePOJOS = new SimpleNoticePOJO[posts.size()];
        for (int i = 0;i<posts.size();i++){
            simpleNoticePOJOS[i].setTitle(posts.get(i).getTitle());
            simpleNoticePOJOS[i].setDate(posts.get(i).getCreate_time().toString());
            simpleNoticePOJOS[i].setPid(posts.get(i).getId());
        }
        return new SimpleNoticeVO(posts.size(),simpleNoticePOJOS);
    }

    public ShowDataAssPOJO[] showDatas(Integer uid){
        ArrayList<Association_User> association_users = associationUserMapper.getAllAssociation_users();
        ArrayList<Association> associations = associationMapper.getAllAssociation();
        ArrayList<Integer> aids = new ArrayList<>();
        ShowDataAssPOJO[] showDataAssPOJOS = new ShowDataAssPOJO[associations.size()];
        for (int i=0;i<association_users.size();i++){
            if (association_users.get(i).getU_id()==uid){
                aids.set(i, association_users.get(i).getA_id());
                if (aids.get(i) == associations.get(i).getId()){
                    showDataAssPOJOS[i].setAssName(associations.get(i).getName());
                    showDataAssPOJOS[i].setAssImage(associations.get(i).getLogo());
                    showDataAssPOJOS[i].setAid(associations.get(i).getId());
                    showDataAssPOJOS[i].setIsJoin(1);
                }else {
                    showDataAssPOJOS[i].setAssName(associations.get(i).getName());
                    showDataAssPOJOS[i].setAssImage(associations.get(i).getLogo());
                    showDataAssPOJOS[i].setAid(associations.get(i).getId());
                    showDataAssPOJOS[i].setIsJoin(0);
                }
            }
        }
        return showDataAssPOJOS;
    }

}
