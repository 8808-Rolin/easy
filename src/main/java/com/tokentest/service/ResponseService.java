package com.tokentest.service;

import com.tokentest.model.ResponseBase;
import com.tokentest.utils.TransformCurrentTimeUtil;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    // 通用封装
    public ResponseBase setResult(Integer status, String tip, String time, Object data){
        return new ResponseBase(status,tip,time,data);
    }

    // 默认200成功返回体，携带data
    public ResponseBase setResultSuccess(Object data){
        return new ResponseBase(200,"客户端请求成功", TransformCurrentTimeUtil.returnCurrentTime(),data);
    }

    // 默认404失败返回体，无data
    public ResponseBase setResultCError(){
        return new ResponseBase(404,"未找到", TransformCurrentTimeUtil.returnCurrentTime(),null);
    }

    // 默认500失败返回体，携带data
    public ResponseBase setResultSError(Object data){
        return new ResponseBase(500,"内部服务器出错", TransformCurrentTimeUtil.returnCurrentTime(),data);
    }

    //重写200成功返回体，无data
    public ResponseBase setResultSuccess(){
        return new ResponseBase(200,"用户操作成功",TransformCurrentTimeUtil.returnCurrentTime(),null);
    }

    //重写200成功返回体，携带data，author自定义tip
    public ResponseBase setResultSuccess(String tip,Object data){
        return new ResponseBase(200,tip,TransformCurrentTimeUtil.returnCurrentTime(),data);
    }

    //重写200成功返回体，无data，author自定义tip
    public ResponseBase setResultSuccess(String tip){
        return new ResponseBase(200,tip,TransformCurrentTimeUtil.returnCurrentTime(),null);
    }

    //重写404失败返回体，author自定义tip
    public ResponseBase setResultCError(String tip){
        return new ResponseBase(404,tip,TransformCurrentTimeUtil.returnCurrentTime(),null);
    }

    // 默认500失败返回体，携带data,author自定义tip
    public ResponseBase setResultSError(String tip,Object data){
        return new ResponseBase(500,tip, TransformCurrentTimeUtil.returnCurrentTime(),data);
    }

    // 默认500失败返回体，author自定义tip
    public ResponseBase setResultSError(String tip){
        return new ResponseBase(500,tip, TransformCurrentTimeUtil.returnCurrentTime(),null);
    }

}
