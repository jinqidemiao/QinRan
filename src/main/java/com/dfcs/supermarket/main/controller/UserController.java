package com.dfcs.supermarket.main.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dfcs.supermarket.main.common.BaseResponse;
import com.dfcs.supermarket.main.entity.Goods;
import com.dfcs.supermarket.main.entity.GoodsUser;
import com.dfcs.supermarket.main.entity.User;
import com.dfcs.supermarket.main.interceptor.JwtHelper;
import com.dfcs.supermarket.main.service.IGoodsService;
import com.dfcs.supermarket.main.service.IGoodsUserService;
import com.dfcs.supermarket.main.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/main/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsUserService goodsUserService;

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/login")
    public BaseResponse<User> user(String mobile, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String user_agent = request.getHeader("user-agent");
        Long userId = null;
        User dbUser = userService.getOne(new QueryWrapper<User>().eq("user_mobile", mobile));
        User user = null;
        if(null == dbUser){
            user = new User();
            user.setUserMobile(mobile);
            user.setUserAgent(user_agent);
            userId = user.getId();
            userService.save(user);
        }else {
            userId = dbUser.getId();
        }
        String str = JwtHelper.generateJWT(String.valueOf(userId), mobile, user_agent);
        JwtHelper.addToCookie(response,str);
        if(null != dbUser){
            dbUser.setToken(str);
        }
        if(null != user){
            user.setToken(str);
        }
        return BaseResponse.ok(null == dbUser?user:dbUser);
    }

    @GetMapping("/getAppointmentListByUser")
    public BaseResponse<List<GoodsUser>> getAppointmentListByUser(HttpServletRequest request){
        String token = request.getHeader("token");
        JSONObject jsonObject = JSONObject.parseObject(JwtHelper.validateLogin(token));
        Long userId = jsonObject.getLong("userId");
        List<GoodsUser> list = goodsUserService.getBaseMapper().selectList(new QueryWrapper<GoodsUser>().eq("user_id", userId));
        return BaseResponse.ok(list);
    }

    @PostMapping("/appointment")
    @Transactional
    public BaseResponse<String> appointment(
            @RequestBody List<GoodsUser> goodsUsers
    ){
        try {
            String pickupCode = "0";
            while(null != goodsUserService.getOne(new QueryWrapper<GoodsUser>().eq("pickup_code",pickupCode).eq("state",1))){
                String random = String.valueOf(Math.random() * 900000 + 100000);
                String ran = random.substring(0,random.lastIndexOf("."));
                pickupCode = ran;
            }
            for (GoodsUser goodsUser : goodsUsers) {
                Goods goods = goodsService.getById(goodsUser.getGoodsId());
                if(goods.getGoodsNum() <= 0){
                    return BaseResponse.ok("该商品已售罄");
                }
                if(goods.getGoodsNum() < goodsUser.getNum()){
                    return BaseResponse.ok("预约数量上限");
                }
                goods.setGoodsNum(goods.getGoodsNum() - goodsUser.getNum());
                if(0 == goods.getGoodsNum() || goods.getGoodsNum().equals(0)){
                    goods.setState(0);
                }
                goodsService.updateById(goods);
                goodsUser.setState(1);
                goodsUser.setCreateTime(new Date());
                goodsUser.setExtraAmount(new BigDecimal("0"));
                goodsUser.setGoodsAmount(goods.getGoodsPrice().multiply(new BigDecimal(goodsUser.getNum())));
                goodsUser.setSumAmount(goodsUser.getGoodsAmount().add(new BigDecimal("0")));
                goodsUser.setPickupCode(pickupCode);
            }
            return BaseResponse.ok(pickupCode);
        }catch (Exception e){
            return BaseResponse.err("预约异常");
        }
    }

    public static void main(String[] args) {

    }

}

