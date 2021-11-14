package icu.rolin.easy.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
    private final static String TOKEN_SECRET = "easy";

    public static boolean verifyZoneStatus(HttpServletRequest request, HttpServletResponse response,Integer muid) {

        // 跨域请求通过
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 响应格式
        response.setCharacterEncoding("utf-8");

        // 获取请求头的token
        String token = request.getHeader("token");
        System.out.println("------获取时间为：" + returnCurrentTime() + "------");
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            DateFormat expiration = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("------token认证通过------");
            System.out.println("用户id：" + jwt.getClaim("uid").asString());
            System.out.println("------token到期时间：" + expiration.format(jwt.getExpiresAt()) + "------");
            if (jwt.getClaim("uid").asInt() == muid){
                logger.info("用户正在访问自己空间");
                return true;
            }else {
                logger.info("用户正在访问他人空间");
                return false;
            }
        } catch (TokenExpiredException e) {
            System.out.println("------用户token已过期，已向前端发送状态码------");
            response.setHeader("tokenStatus", "809");
            logger.error("---用户Token已过期");

            return false;
        }
    }


}
