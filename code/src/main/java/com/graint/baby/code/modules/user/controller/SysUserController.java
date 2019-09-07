package com.graint.baby.code.modules.user.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.graint.baby.code.modules.user.service.SysUserService;
import com.graint.baby.code.utils.JwtUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dhb
 * @since 2019-09-07
 */
@RestController
@RequestMapping("/user/sys-user-entity")
public class SysUserController {

    @Autowired
    Producer producer;
    @Autowired
    SysUserService service;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        //设置response响应头,缓存设置为no-cache
        response.setHeader("Cache-Control","no-cache,no-store");
        //获取验证码
        BufferedImage image = producer.createImage(producer.createText());
        ServletOutputStream outputStream = response.getOutputStream();
        //利用输出流写会到页面
        ImageIO.write(image,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @PostMapping(value = "login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map login(String info){
        SysUserEntity user = JSON.parseObject(info,SysUserEntity.class);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        Map<String,Object> map = new HashMap<>();
        token.setRememberMe(true);
        try{
            subject.login(token);
            //返回的token
            String tokenBack = jwtUtil.generateToken(5L);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",tokenBack);
            map.put("data",jsonObject);

        }catch (Exception e){
            throw e;
            // System.out.println("对具体异常处理");
        }
        return map;
    }

    @PostMapping("insert")
    public void insert(@RequestBody String user){
        SysUserEntity addUser = JSON.parseObject(user,SysUserEntity.class);
        service.saveUser(addUser);

    }
}
