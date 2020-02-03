package com.graint.baby.code.modules.user.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.graint.baby.code.modules.user.dao.SysUserMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    Producer producer;
    @Autowired
    SysUserService service;
    @Autowired
    private SysUserMapper userMapper;

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

    /**
     * 这里其实菜单管理,目前先用假的,以后开发完菜单,换成真的
     * @param token
     * @return
     */
    @GetMapping("info")
    public Map<String,Object> info(String token){

        JSONObject child1 = new JSONObject();
        child1.put("path","dynamic-table");
        child1.put("name","DynamicTable");
        child1.put("title","dynamicTable");
        child1.put("component","table/dynamic-table/index");
        JSONObject child2 = new JSONObject();
        child2.put("path","drag-table");
        child2.put("name","DragTable");
        child2.put("title","dragTable");
        child2.put("component","table/drag-table");
        JSONObject child3 = new JSONObject();
        child3.put("path","inline-edit-table");
        child3.put("name","InlineEditTable");
        child3.put("title","inlineEditTable");
        child3.put("component","table/inline-edit-table");
        JSONObject child4 = new JSONObject();
        child4.put("path","complex-table");
        child4.put("name","ComplexTable");
        child4.put("title","complexTable");
        child4.put("component","table/complex-table");
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("roles",array);
//        jsonObject.put("introduction","I am a super administrator");
//        jsonObject.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        jsonObject.put("name","Super Admin");
        jsonObject.put("path","/table");
        jsonObject.put("name","Table");
        jsonObject.put("title","Table");
        jsonObject.put("icon","table");
        jsonObject.put("component","Layout");
        Object[] array = {child1,child2,child3,child4};
        jsonObject.put("children",array);
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(jsonObject);

        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    @RequestMapping(value = "testMybatis")
    public void testMybatis(){
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setAge("89");
        sysUserEntity.setEmail("qq");
        sysUserEntity.setPassword("123");
        sysUserEntity.setUserName("dhb88");
        sysUserEntity.setSalt("io");
        sysUserEntity.setTest(1);
        userMapper.addUser(sysUserEntity);
    }
}
