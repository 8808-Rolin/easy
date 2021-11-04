package icu.rolin.easy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class TokenUtil {

    private static final long EXPIRED_TIME = 1*60*1000; //有效时长为一天
    private static final String TOKEN_SECRET = "easy"; //密钥

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
