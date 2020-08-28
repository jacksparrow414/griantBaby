package com.graint.baby.code.modules.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.code.kaptcha.Producer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.graint.baby.code.modules.user.dao.SysUserMapper;
import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.graint.baby.code.modules.user.service.SysUserService;
import com.graint.baby.code.utils.JwtUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * 前端控制器.
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    
    @Autowired
    private Producer producer;
    
    @Autowired
    private SysUserService service;
    
    @Autowired
    private SysUserMapper userMapper;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    /**
     * 获取验证码.
     *
     * @param response 响应
     * @throws IOException IO异常
     */
    @GetMapping("captcha")
    public void captcha(final HttpServletResponse response) throws IOException {
        //设置response响应头,缓存设置为no-cache
        
        response.setHeader("Cache-Control", "no-cache,no-store");
        //获取验证码
        
        BufferedImage image = producer.createImage(producer.createText());
        ServletOutputStream outputStream = response.getOutputStream();
        //利用输出流写会到页面
        
        ImageIO.write(image, "jpg", outputStream);
        IOUtils.closeQuietly(outputStream);
    }
    
    /**
     * 只返回token,利用token再请求getInfo接口获取用户信息.
     *
     * @param info 用户登录信息
     * @return token
     */
    @PostMapping(value = "login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map login(final String info) {
        SysUserEntity user = JSON.parseObject(info, SysUserEntity.class);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Map<String, Object> map = new HashMap<>();
        token.setRememberMe(true);
        try {
            subject.login(token);
            //返回的token
            
            SysUserEntity userEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            String tokenBack = jwtUtil.generateToken(userEntity.getId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", tokenBack);
            map.put("data", jsonObject);
            
        } catch (AuthenticationException e) {
            throw e;
        }
        return map;
    }
    
    /**
     * 保存用户.
     *
     * @param user user信息字符串
     * @return 用户ID
     * @author duhongbo
     */
    @PostMapping("insert")
    public Long insert(@RequestBody final String user) {
        SysUserEntity addUser = JSON.parseObject(user, SysUserEntity.class);
        addUser.setId(IdWorker.getId());
        return service.saveUser(addUser);
        
    }
    
    /**
     * 这里其实菜单管理,用户角色,目前先用假的,以后开发完菜单,换成真的.
     *
     * @param token token
     * @return 要展示的菜单
     */
    @GetMapping("info")
    public Map<String, Object> info(final String token) {
        // 要展示哪些组件
        
        JSONObject child1 = new JSONObject();
        child1.put("path", "dynamic-table");
        child1.put("name", "DynamicTable");
        child1.put("title", "dynamicTable");
        child1.put("component", "table/dynamic-table/index");
        JSONObject child2 = new JSONObject();
        child2.put("path", "drag-table");
        child2.put("name", "DragTable");
        child2.put("title", "dragTable");
        child2.put("component", "table/drag-table");
        JSONObject child3 = new JSONObject();
        child3.put("path", "inline-edit-table");
        child3.put("name", "InlineEditTable");
        child3.put("title", "inlineEditTable");
        child3.put("component", "table/inline-edit-table");
        JSONObject child4 = new JSONObject();
        child4.put("path", "complex-table");
        child4.put("name", "ComplexTable");
        child4.put("title", "complexTable");
        child4.put("component", "table/complex-table");
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path", "/table");
        jsonObject.put("name", "Table");
        jsonObject.put("title", "Table");
        jsonObject.put("icon", "table");
        jsonObject.put("component", "Layout");
        Object[] array = {child1, child2, child3, child4};
        jsonObject.put("children", array);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(jsonObject);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }
    
    /**
     * 测试Mybatis.
     */
    @RequestMapping(value = "testMybatis")
    public void testMybatis() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setAge(89);
        sysUserEntity.setEmail("qq");
        sysUserEntity.setPassword("123");
        sysUserEntity.setUserName("dhb88");
        sysUserEntity.setSalt("io");
        userMapper.addUser(sysUserEntity);
    }
    
    /**
     * 退出.
     */
    @RequestMapping(value = "logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    
    /**
     * 用户信息.
     *
     * @return 用户信息
     */
    @PostMapping(value = "userInfo")
    public Map<String, Object> userInfo() {
        // 用户信息 暂时写死,以后从数据库查询
        JSONObject userInfo = new JSONObject();
        List<String> roles = Lists.newArrayList();
        roles.add("admin");
        String[] rolesArray = roles.toArray(new String[roles.size()]);
        userInfo.put("roles", roles);
        userInfo.put("introduction", "I am a super administrator");
        userInfo.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.put("name", "Super Admin");
        Map<String, Object> map = Maps.newHashMap();
        map.put("data", userInfo);
        return map;
    }
}
