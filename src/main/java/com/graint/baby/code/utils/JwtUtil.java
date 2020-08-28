package com.graint.baby.code.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT token 工具类,提供JWT生成,校验,工作.
 */
@ConfigurationProperties(prefix = "dhb.jwt")
@Component
@Slf4j
@Getter
@Setter
public class JwtUtil {
    
    private String secret;
    
    private Long expire;
    
    private String header;
    
    
    /**
     * 生成JWT token.
     *
     * @param userId 用户id
     * @return JWT信息字符串
     */
    public String generateToken(final Long userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(userId + "").setIssuedAt(nowDate).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secret).compact();
        
    }
    
    /**
     * 解析JWT token.
     *
     * @param token token
     * @return io.jsonwebtoken.Claims
     */
    public Claims parseToken(final String token) {
        try {
            
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.error("解析token出错", e);
            return null;
        }
    }
    
    /**
     * 校验token是否过期.
     *
     * @param expiprationTime 过期时间
     * @return 是否过期
     */
    public boolean isTokenExpired(final Date expiprationTime) {
        return expiprationTime.before(new Date());
    }
    
}
