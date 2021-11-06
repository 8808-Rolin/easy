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
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static icu.rolin.easy.utils.TransformCurrentTimeUtil.returnCurrentTime;


/*
* tokenStatus为作者自定义状态码
* tokenStatus:808---> token为空
* tokenStatus:809---> token过期
 */

public class UserInterceptor implements HandlerInterceptor {


    private final static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    @Value("${web.token.token}")
    private String TOKEN_SECRET;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {

        // 跨域请求通过
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 响应格式
        response.setCharacterEncoding("utf-8");

        // 获取请求头的token
        String token = request.getHeader("token");
        System.out.println("------拦截时间为："+returnCurrentTime()+"------");
        if (StringUtils.isEmpty(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.out.println("------No token------");
            response.setHeader("tokenStatus","808");
            logger.error("---用户缺失Token---");

            return false;
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            DateFormat expiration = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("------token认证通过------");
            System.out.println("用户id：" + jwt.getClaim("uid").asString());
            System.out.println("用户密码：" + jwt.getClaim("password").asString());
            System.out.println("------token到期时间：" + expiration.format(jwt.getExpiresAt())+"------");

            return true;
        } catch (TokenExpiredException e) {
            System.out.println("------用户token已过期，已向前端发送状态码------");
            response.setHeader("tokenStatus","809");
            logger.error("---用户Token已过期");

            return false;
        }
    }

}
