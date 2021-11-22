package icu.rolin.easy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenUtil {

    public static String sign(Integer uid,String password){
        String token = null;
        try{
            Date expiresAt = new Date(System.currentTimeMillis()+Constant.EXPIRED_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("uid",uid)
                    .withClaim("password",password)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(Constant.TOKEN_SECRET));

        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

}
