package com.graint.baby.code.shiro;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.graint.baby.code.exception.CustomException;
import com.graint.baby.code.modules.user.entity.SysUserEntity;
import com.graint.baby.code.modules.user.service.SysUserService;
import com.graint.baby.code.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author jacksparrow414
 * @Date 2019-05-12
 * @Description: 此过滤器只有一个目的, 验证token的合法性
 * 这里isAccessAllowed方法只有返回false才会执行onAccessDenied方法,
 * 因为shiro默认的判断方法是只要通过验证,subject.isAuthenticated就是为true
 * 所以接下来的每个请求就不会去请求onAccessDenied,具体可以点下面的代码看下源码即可
 * PS:目前返回给客户端的token是没有加过期时间验证的,不太好,因为SHA256没有解密算法,可以
 * 使用Base64来加密解密,这样就满足要求了。目前还没有切换成64。
 *
 * 2019-05-25更新:使用JWT token来完善目前已有的逻辑，包括代码的改写(老代码截图放在了 截图 文件夹里)
 *
 * 2019-09-14更新:使用全局异常处理类,无法捕获Filter中抛出的异常,
 * 具体原因见文章{@see <a href=https://blog.csdn.net/loophome/article/details/94723723></a>}
 */
@Component("authFilter")
public class AuthFilter extends FormAuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 判断token是否为空、过期
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
            String token = getRequestToken((HttpServletRequest) request);
            if (ObjectUtils.isNull(token)){
                return false;
            }
            if (StringUtils.isBlank(token)) {
                throw new CustomException(jwtUtil.getHeader()+"不能为空", HttpStatus.SC_UNAUTHORIZED);
            }
            Claims claims = jwtUtil.parseToken(token);
            if (ObjectUtils.isNull(claims) || jwtUtil.isTokenExpired(claims.getExpiration())) {
                throw new CustomException(jwtUtil.getHeader()+"token过期",HttpStatus.SC_UNAUTHORIZED);
            }
            String userId = claims.getSubject();
            SysUserEntity userEntity = sysUserService.getById(Long.valueOf(userId));
            if (ObjectUtils.isNull(userEntity)){
                throw new CustomException("无此用户",HttpStatus.SC_UNAUTHORIZED);
            }
        return true;
    }

    /**
     * 上面的方法如果返回false,则接下来会执行这个方法,如果返回为true,则不会执行这个方法
     * 判断是否为登录url,进一步判断请求是不是post
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return true;
            }
        }else {
            throw new CustomException("token不存在,非法请求",HttpStatus.SC_UNAUTHORIZED);
        }
        return false;
    }

    /**
     * 获取请求中的token,首先从请求头中获取,如果没有,则尝试从请求参数中获取
     *
     * @param request
     * @return
     */
    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader(jwtUtil.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtUtil.getHeader());
        }
        return token;
    }
}
