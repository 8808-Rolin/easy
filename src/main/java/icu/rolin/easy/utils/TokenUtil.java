package icu.rolin.easy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenUtil {

    @Value("${web.token.token_time}")
    private static long EXPIRED_TIME;

    @Value("${web.token.token}")
    private static String TOKEN_SECRET;

    public static String sign(Integer uid,String password){
        String token = null;
        try{
            Date expiresAt = new Date(System.currentTimeMillis()+EXPIRED_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("uid",uid)
                    .withClaim("password",password)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

}
