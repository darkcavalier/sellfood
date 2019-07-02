package com.zyp.weixinsell.aspect;


import com.zyp.weixinsell.constant.CookieConstant;
import com.zyp.weixinsell.constant.RedisConstant;
import com.zyp.weixinsell.exception.SellerAuthorizeException;
import com.zyp.weixinsell.util.CookieUtil;
import com.zyp.weixinsell.util.CookieUtil1;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Aspect
@Component
@Slf4j
public class SellerAuthAspect {
    public static final String USERNAME = "root";
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * com.zyp.weixinsell.controller.Seller*.*(..))" +
            "&& !execution(public * com.zyp.weixinsell.controller.SellerLoginController.*(..))")
    public void verify(){}
    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        HttpServletRequest request = attributes.getRequest();
        // 查询Cookie
        /*Cookie cookie = CookieUtil.get(request,USERNAME);*/
        Cookie cookie = CookieUtil1.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.error("【登录校验】cookie为空，请重新输入");
            try {
                response.sendRedirect("/sell");
            } catch (IOException e) {
                throw new SellerAuthorizeException();
            }
        }/*else{
            String token = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            if (StringUtils.isEmpty(token)) {
                log.error("【登录校验】Redis中查不到指定token");
                try {
                    response.sendRedirect("/sell");
                } catch (IOException e) {
                    throw new SellerAuthorizeException();
                }
            }
        }*/


    }
}
