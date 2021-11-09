package icu.rolin.easy.service;

import icu.rolin.easy.mapper.ApplyCreateMapper;
import icu.rolin.easy.mapper.ApplyJoinAssociationMapper;
import icu.rolin.easy.model.DO.Apply_Create;
import icu.rolin.easy.model.PO.CreateAssPO;
import icu.rolin.easy.model.PO.UserAssNotePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApplyService {

    @Autowired
    private ApplyCreateMapper applyCreateMapper;
    @Autowired
    private ApplyJoinAssociationMapper applyJoinAssociationMapper;
    private final static Logger logger = LoggerFactory.getLogger(ApplyService.class);


    /**
     * 用户提交创建社团申请，该方法用以处理该业务
     * @param cap 传入的参数对象
     * @return 返回一个Map对象，Key-1 代表处理结果 Key-2代表caid
     */
    public Map<Integer,Integer> createAssociation(CreateAssPO cap){
        Map<Integer,Integer> res = new HashMap<Integer,Integer>();
        Apply_Create ac = new Apply_Create();
        ac.setU_id(cap.getUid());
        ac.setName(cap.getAssname());
        ac.setLogo(cap.getAssprofile());
        ac.setIntro(cap.getAssintro());
        ac.setParent_organization(cap.getOrg());
        ac.setNote(cap.getNote());
        Integer code = applyCreateMapper.insertCreatAssociationForm(ac);
        if(code == 0){ //插入失败
            res.put(1,0);
            return res;
        }else if(code == 1){ //插入成功
            res.put(1,1);
            res.put(2,getCAID());
            return  res;
        }else{ //插入错误或者其他情况
            logger.error("用户申请社团失败，数据库返回空集！");
            res.put(1,-1);
            return res;
        }
    }

    /**
     * 用户申请加入社团时触发该业务
     * @author Joolum
     * @param uan 传入一个对象参数 Uid、Aid以及Note
     * @return 返回一个布尔值,表示提交申请是否成功
     */
    public boolean applyJoinAssociation(UserAssNotePO uan){
        Integer code = applyJoinAssociationMapper.insertJoinAssForm(uan.getUid(),uan.getAid(),uan.getNote());
        if (code == null){
            logger.warn("申请嘉然协会表插入失败，请检查数据准确性");
            code = 0;
        }
        return code == 1;
    }

    public int getCAID(){
        return applyCreateMapper.getLastInsertId();
    }

}
