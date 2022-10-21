package cis.spring.security;

import cis.spring.users.User;
import cis.spring.users.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TokenUtil {

    @Value("${auth.secret}")
    private String TOKEN_SECRET;

    @Value("${auth.expiration}")
    private Long TOKEN_EXPIRATION;

    private String TOKEN_SUB="sub";
    private String TOKEN_CREATED="created";


    @Autowired
    private UserService userService;
    public String generateToken(UserDetails userDetails){
     Map<String ,Object> claims=new HashMap<>();
     claims.put(TOKEN_SUB,userDetails.getUsername());
     claims.put(TOKEN_CREATED,new Date());
       return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }

    public Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+TOKEN_EXPIRATION*1000);
    }
    public String getUsernameFromToken(String token)
    {
        try {
            Claims claims=getClaims(token);
             return claims.getSubject();
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean checkTokenValidty(UserDetails userDetails,String token)
    {
        if(userDetails.getUsername().equals(getUsernameFromToken(token))&&isTokenNotExpired(token))
        {
            return true;
        }
        else
            return false;
    }
    public Boolean isTokenNotExpired(String token){
        return (getExpirationDate(token).after(new Date()));
    }
    public Date getExpirationDate(String token){
        Claims claims;
        try {
            claims=getClaims(token);
            return claims.getExpiration();
        }catch (Exception ex){
            return null;
        }
    }
    public Claims getClaims(String token){
        Claims claims;
     try {
         claims=Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
     }catch (Exception ex){
       claims= null;
     }
     return claims;
    }
}
