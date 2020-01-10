package com.dfcs.supermarket.main.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfcs.supermarket.main.common.BaseResponse;
import com.dfcs.supermarket.main.entity.Goods;
import com.dfcs.supermarket.main.entity.GoodsUser;
import com.dfcs.supermarket.main.entity.Link;
import com.dfcs.supermarket.main.entity.User;
import com.dfcs.supermarket.main.service.IGoodsService;
import com.dfcs.supermarket.main.service.IGoodsUserService;
import com.dfcs.supermarket.main.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  预约管理
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/main/appointment")
public class GoodsUserController {

    @Autowired
    private IGoodsUserService goodsUserService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IUserService userService;

    @PostMapping("/appointmentList")
    public BaseResponse<IPage<GoodsUser>> appointmentList(
            @RequestParam(value = "startTime",required = false)String startTime,
            @RequestParam(value = "endTime",required = false)String endTime,
            @RequestParam(value = "state",required = false)Integer state,
            @RequestParam(value = "pickupCode",required = false)String pickupCode,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "size",defaultValue = "10")Integer size
    ){
        Page<GoodsUser> pagination = new Page<>(page, size);
        QueryWrapper<GoodsUser> queryWrapper = new QueryWrapper<GoodsUser>()
                .eq(null != state,"state",state)
                .gt(StringUtils.isNotBlank(startTime),"create_time",startTime)
                .lt(StringUtils.isNotBlank(endTime),"create_time",endTime)
                ;
        if(StringUtils.isNotBlank(pickupCode)){
            queryWrapper.like("pickup_code","%".concat(pickupCode).concat("%"));
        }
        return BaseResponse.ok(goodsUserService.page(pagination,queryWrapper));
    }

    @GetMapping("/appointmentItem")
    public BaseResponse<GoodsUser> appointmentItem(Long id){
        GoodsUser goodsUser = goodsUserService.getById(id);
        Goods goods = goodsService.getById(goodsUser.getGoodsId());
        goodsUser.setGoods(goods);
        User user = userService.getById(goodsUser.getUserId());
        goodsUser.setUser(user);
        return BaseResponse.ok(goodsUser);
    }

    @GetMapping("/deleteAppointment")
    public BaseResponse deleteAppointment(Long id){
        GoodsUser goodsUser = goodsUserService.getById(id);
        goodsUser.setState(0);
        return BaseResponse.ok("操作成功");
    }

}

