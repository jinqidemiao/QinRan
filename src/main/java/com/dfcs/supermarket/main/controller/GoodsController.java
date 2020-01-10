package com.dfcs.supermarket.main.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfcs.supermarket.main.common.BaseResponse;
import com.dfcs.supermarket.main.entity.Goods;
import com.dfcs.supermarket.main.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  商品管理
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/main/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/goodsList")
    public BaseResponse<IPage<Goods>> goodsList(
            @RequestParam(value = "goodsName",required = false) String goodsName,
            @RequestParam(value = "startTime",required = false) String startTime,
            @RequestParam(value = "endTime",required = false) String endTime,
            @RequestParam(value = "goodsPrefix",required = false)String goodsPrefix,
            @RequestParam(value = "state",required = false)Integer state,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "size",defaultValue = "10")Integer size,
            @RequestParam(value = "goodsLinkId",required = false)Long goodsLinkId
    ){
        Page<Goods> pagination = new Page<>(page, size);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>()
                .eq(null != goodsLinkId,"goods_link_id",goodsLinkId)
                .eq(null != state, "state", state)
                .eq(StringUtils.isNotBlank(startTime), "start_time", startTime)
                .eq(StringUtils.isNotBlank(endTime), "end_time", endTime);
        if(StringUtils.isNotBlank(goodsName)){
            queryWrapper.like("goods_name", "%".concat(goodsName).concat("%"));
        }
        if(StringUtils.isNotBlank(goodsPrefix)){
            queryWrapper.like( "goods_prefix", goodsPrefix.concat("%"));
        }
        return BaseResponse.ok(goodsService.page(pagination,queryWrapper));
    }

    @PostMapping("/addGoods")
    public BaseResponse saveGoods(
            @RequestBody Goods goods
    ){
        if(null != goods.getGoodsNum()){
            if(0 != goods.getGoodsNum() && !goods.getGoodsNum().equals(0)){
                goodsService.save(goods);
                return BaseResponse.ok("操作成功");
            }else {
                return BaseResponse.ok("商品数量不可为空");
            }
        }else {
            return BaseResponse.ok("商品数量不可为空");
        }
    }

    @PostMapping("/updateGoods")
    public BaseResponse updateGoods(
            @RequestBody Goods goods
    ){
        goodsService.updateById(goods);
        return BaseResponse.ok("操作成功");
    }

    @GetMapping("/goodsItem")
    public BaseResponse<Goods> getGoodsById(Long id){
        return BaseResponse.ok(goodsService.getById(id));
    }

    @GetMapping("/updateState")
    public BaseResponse updateState(Long id,Integer state){
        Goods goods = goodsService.getById(id);
        if(1 == state && goods.getGoodsNum() <= 0){
            return BaseResponse.ok("商品库存不足，无法上架");
        }
        goods.setState(state);
        goodsService.updateById(goods);
        return BaseResponse.ok("操作成功");
    }

}

