package com.banyuan.blog.utils;


import com.banyuan.blog.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {
    public static String getJwt(long expireMiles, User user, String signKey){
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

        //time when jwt created
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        //create the private claims
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        claims.put("password",user.getPassword());

        String subject = user.getUsername();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(algorithm, signKey);

        if(expireMiles>0){
            long expireTimeMiles = currentTimeMillis + expireMiles;
            Date expireTime = new Date(expireMiles);
            jwtBuilder.setExpiration(expireTime);
        }

        return jwtBuilder.compact();
    }

    public static Boolean verify(String token,User user , String signKey){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("password").equals(user.getPassword());
    }
}
