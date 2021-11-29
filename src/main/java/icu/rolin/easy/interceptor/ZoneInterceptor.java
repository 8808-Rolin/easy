package icu.rolin.easy.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import icu.rolin.easy.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static icu.rolin.easy.utils.TransformCurrentTimeUtil.returnCurrentTime;

public class ZoneInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(ZoneInterceptor.class);


    /**
     * 判断当前空间是否是登录用户的空间
     * @param request Http请求
     * @param muid
     * @return
     */
    public static boolean verifyZoneStatus(HttpServletRequest request,Integer muid) {
        // 跨域请求通过
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 获取请求头的token
        String token = request.getHeader("token");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constant.TOKEN_SECRET))
                .withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(token);
        if (jwt.getClaim("uid").asInt().equals(muid)){
            logger.info("用户访问了自己的空间");
            return true;
        }else {
            logger.info("用户访问了他人的空间");
            return false;
        }
    }




}
