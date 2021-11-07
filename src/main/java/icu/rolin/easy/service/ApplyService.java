package icu.rolin.easy.service;

import icu.rolin.easy.mapper.ApplyCreateMapper;
import icu.rolin.easy.model.PO.CreateAssPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    @Autowired
    private ApplyCreateMapper applyCreateMapper;
    private final static Logger logger = LoggerFactory.getLogger(ApplyService.class);

    public boolean creatAssociation(CreateAssPO cap){
        Integer code = applyCreateMapper.insertCreatAssociationForm(cap.getUid(), cap.getAssname(), cap.getAssprofile(), cap.getAssintro(), cap.getOrg(), cap.getNote());
        if (code == null){
            logger.error("用户申请社团失败，数据库返回空集！");
            code = -1;
        }
        return code == 1;
    }

    public String getCAID(){
        return applyCreateMapper.getLastInsertId().toString();
    }

}
