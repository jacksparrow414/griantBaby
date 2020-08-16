package com.graint.baby.code.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT token 工具类,提供JWT生成,校验,工作
 *
 * @Author jacksparrow414
 * @Date 2019-05-25
 * @desc: TODO
 */
@ConfigurationProperties(prefix = "dhb.jwt")
@Component
public class JwtUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String secret;
    private Long expire;
    private String header;


    /**
     *
     * 生成JWT token
     * @param userId
     * @return
     */
    public String generateToken(Long userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    /**
     *
     * 解析JWT token
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        try {

            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.info("解析token出错");
            return null;
        }
    }

    /**
     *
     * 校验token是否过期
     * @param expiprationTime
     * @return
     */
    public boolean isTokenExpired(Date expiprationTime){
        return expiprationTime.before(new Date());
    }
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
}
