package com.zyp.weixinsell.controller;


import com.zyp.weixinsell.constant.CookieConstant;
import com.zyp.weixinsell.constant.RedisConstant;
import com.zyp.weixinsell.entity.SellerInfo;
import com.zyp.weixinsell.enums.ExceptionEnum;
import com.zyp.weixinsell.service.ISellerInfoService;
import com.zyp.weixinsell.util.CookieUtil;
import com.zyp.weixinsell.util.CookieUtil1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerLoginController{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ISellerInfoService sellerInfoService;
  /*  @RequestMapping(value={"/login"} ,method = RequestMethod.GET)
    public String login() {

        return "sell/seller/login";
    }*/

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response) {
        // 用户信息校验
        SellerInfo sellerInfo = sellerInfoService.findByUserName(username);
        if (sellerInfo == null) {
            log.error("用户不存在，请重新输入");
            return "404";
        }
        if (!sellerInfo.getPassword().equals(password)) {
            log.error("密码不正确，请重新输入");
            return "404";
        }
//        // token写入Redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        /*stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), username, expire, TimeUnit.SECONDS);*/

        // token写入cookie
     /*   CookieUtil.set(username, token, response, EXPIRE_TIME);*/
        CookieUtil1.set(response, CookieConstant.TOKEN, token, expire);
        return "200";
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil1.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
           /* stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));*/

            //3. 清除cookie
            CookieUtil1.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ExceptionEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/sell");
        return new ModelAndView("comm/success", map);
    }
}
