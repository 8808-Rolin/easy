package icu.rolin.easy.config;

import icu.rolin.easy.model.VO.ResponseVO;
import icu.rolin.easy.utils.common;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rolin
 * @desc 全局异常处理类，返回一个JSON字符串
 * @since 2021年11月7日
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 捕获所有Exception
     * @param e 异常参数
     * @return json格式类型
     */
    @ResponseBody
    @ExceptionHandler({Exception.class}) //指定拦截异常的类型
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //自定义浏览器返回状态码
    public ResponseVO customExceptionHandler(Exception e) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(500);
        responseVO.setData(null);
        responseVO.setMessage(common.formatException(e));
        e.printStackTrace();
        return responseVO.update();
    }
}
