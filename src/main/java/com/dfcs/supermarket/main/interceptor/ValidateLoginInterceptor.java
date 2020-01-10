package com.dfcs.supermarket.main.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * @Author: Helon
 * @Description: 校验是否登录拦截器
 * @Data: Created in 2018/7/30 14:30
 * @Modified By:
 */
@Slf4j
public class ValidateLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String jwt = httpServletRequest.getHeader("token");
        Cookie cookies[] = httpServletRequest.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                 if (cookies[i].getName().equals("token")) {
                     jwt =  URLDecoder.decode(cookies[i].getValue(),"UTF-8");
                 }
            }
        }
        //首先从请求头中获取jwt串，与页面约定好存放jwt值的请求头属性名为User-Token
        log.info("[登录校验拦截器]-从header中获取的jwt为:{}", jwt);
        //判断jwt是否有效
        if(StringUtils.isNotBlank(jwt)){
            //校验jwt是否有效,有效则返回json信息，无效则返回空
            String retJson = JwtHelper.validateLogin(jwt);
            log.info("[登录校验拦截器]-校验JWT有效性返回结果:{}", retJson);
            //retJSON为空则说明jwt超时或非法
            if(StringUtils.isNotBlank(retJson)){
                JSONObject jsonObject = JSONObject.parseObject(retJson);
                //校验客户端信息
                String userAgent = httpServletRequest.getHeader("User-Agent");
                if (userAgent.equals(jsonObject.getString("userAgent"))) {
                    //获取刷新后的jwt值，设置到响应头中
                    httpServletResponse.setHeader("token", jsonObject.getString("freshToken"));
                    //将客户编号设置到session中
                    httpServletRequest.getSession().setAttribute("SESSION_CUSTOMER_NO_KEY", jsonObject.getString("userId"));
                    return true;
                }else{
                    log.warn("[登录校验拦截器]-客户端浏览器信息与JWT中存的浏览器信息不一致，重新登录。当前浏览器信息:{}", userAgent);
                }
            }else {
                log.warn("[登录校验拦截器]-JWT非法或已超时，重新登录");
            }
        }
        //输出响应流
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hmac", "");
        jsonObject.put("status", "");
        jsonObject.put("code", "503");
        jsonObject.put("msg", "未登录");
        jsonObject.put("data", "");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getOutputStream().write(jsonObject.toJSONString().getBytes("UTF-8"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

