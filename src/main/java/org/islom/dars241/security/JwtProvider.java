package org.islom.dars241.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    String secret="HEllopWorld";
    public   String generateToken(String username){

        long exprireTime=36000000;//millisecund yani 1000 millisecund 1 secund

        String token= Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exprireTime))
                . signWith(SignatureAlgorithm.HS512,secret)
                .   compact();//tokenlarni yeg'ib beradi
        System.out.println(token);
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public String getUserNameFromToken(String token){

        try{

            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(Exception e){
            return null;
        }
    }
}
