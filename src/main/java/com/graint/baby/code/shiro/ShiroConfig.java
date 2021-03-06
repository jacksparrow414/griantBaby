package com.graint.baby.code.shiro;

import com.graint.baby.code.sysenum.ShiroEnum;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类.
 */
@Configuration
public class ShiroConfig {
    
    /**
     * 这里session交给shiro默认管理,不去做详细配置.
     *
     * @return SecurityManager
     */
    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //传入自定义shiroRealm
        securityManager.setRealm(shiroRealm());
        //这里要注意,setRememberMeManager里传入的类型是CookieRememberMeManager,不要搞错了
        securityManager.setRememberMeManager(cookieRememberMe());
        return securityManager;
    }
    
    /**
     * shiroRealm.
     *
     * @return com.graint.baby.code.shiro.ShiroRealm
     */
    @Bean("shiroRealm")
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        return shiroRealm;
    }
    
    /**
     * cookie记住我配置.
     *
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     */
    @Bean("cookieRememberMe")
    public CookieRememberMeManager cookieRememberMe() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberCookie());
        return cookieRememberMeManager;
    }
    
    /**
     * 密码加密.
     *
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     */
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroEnum.HASH_ALGORITHMNAME.getName());
        hashedCredentialsMatcher.setHashIterations(ShiroEnum.HASH_ITERATIONS.getCode());
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
    
    /**
     * 记住我规则配置.
     *
     * @return org.apache.shiro.web.servlet.SimpleCookie
     */
    @Bean("rememberCookie")
    public SimpleCookie rememberCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setHttpOnly(true);
        simpleCookie.setName("remeberCookie");
        simpleCookie.setMaxAge(360000);
        return simpleCookie;
    }
    
    /**
     * 这里不设置loginUrl,传统的前后端不分离是可以配置.
     *
     * <p>因为这里前后端分离,前端没有获取到token自然会路由到登录页面进行登录操作,不再需要通过loginUrl定位到视图view,SpringBoot只负责后端
     * 同时注意这里的filterMap中对于拦截路径的配置要以 / 开头,否则找不到对应的Controller,
     * 切记:一定要把/** = authc放到最后!!!!!
     * 2019-05-12补充说明:对于loginUrl最终还是需要的,filter里会进行是否为登录url的判断。</p>
     *
     * @param securityManager 权限管理类
     * @param authFilter 自定义的权限过滤器
     * @return ShiroFilterFactoryBean
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(final SecurityManager securityManager, final AuthFilter authFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        //设置自定义filter
        Map<String, Filter> filter = new HashMap<>();
        filter.put("auth", authFilter);
        shiroFilterFactoryBean.setFilters(filter);
        //同时这里注意,使用LinkedHashMap来保证拦截器的顺序性
        Map<String, String> filterMap = new LinkedHashMap<>();
        //登录逻辑这里不用anno,统一走自定义filter
        filterMap.put("/user/mock", "anon");
        filterMap.put("/user/insert", "auth");
        filterMap.put("/**", "auth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    
    /**
     * Spring管理Shiro生命周期.
     *
     * @return LifecycleBeanPostProcessor
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
