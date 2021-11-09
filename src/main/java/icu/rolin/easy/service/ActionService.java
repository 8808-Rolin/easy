package icu.rolin.easy.service;

import icu.rolin.easy.mapper.ActionMapper;
import icu.rolin.easy.mapper.JoinActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    @Autowired
    private ActionMapper actionMapper;
    @Autowired
    private JoinActionMapper joinActionMapper;
    private final static Logger logger = LoggerFactory.getLogger(ActionService.class);

    public boolean participateAction(Integer uid,Integer acid){
        Integer code = joinActionMapper.insertJoinActionForm(acid,uid);
        if (code == null){
            logger.warn("申请参加活动表插入失败，请检查数据准确性");
            code = 0;
        }
        return code == 1;
    }

}
