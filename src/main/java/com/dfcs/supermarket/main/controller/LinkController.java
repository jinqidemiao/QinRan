package com.dfcs.supermarket.main.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfcs.supermarket.main.common.BaseResponse;
import com.dfcs.supermarket.main.entity.Goods;
import com.dfcs.supermarket.main.entity.Link;
import com.dfcs.supermarket.main.service.IGoodsService;
import com.dfcs.supermarket.main.service.ILinkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  链接管理
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/main/link")
public class LinkController {

    @Autowired
    private ILinkService linkService;

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/linkList")
    public BaseResponse<IPage<Link>> linkList(
            @RequestParam(value = "state",required = false)Integer state,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "size",defaultValue = "10")Integer size,
            @RequestParam(value = "link",required = false)String link
    ){
        Page<Link> pagination = new Page<>(page, size);
        return BaseResponse.ok(linkService.linkList(pagination,state,link));
    }

    @GetMapping("/linkItem")
    public BaseResponse<Link> linkItem(Long id){
        Link link = linkService.getById(id);
        List<Goods> goodsList = goodsService.getBaseMapper().selectList(new QueryWrapper<Goods>().eq("goods_link_id", id));
        link.setGoodsList(goodsList);
        return BaseResponse.ok(link);
    }

    @PostMapping("/addLink")
    @Transactional
    public BaseResponse addLink(@RequestBody Link link){
        link.setLink("");//TODO:写好页面之后配置
        link.setCreateTime(new Date());
        linkService.save(link);
        updateGoodsLink(link);
        return BaseResponse.ok("操作成功");
    }

    @PostMapping("/updateLink")
    @Transactional
    public BaseResponse updateLink(@RequestBody Link link){
        linkService.updateById(link);
        updateGoodsLink(link);
        return BaseResponse.ok("操作成功");
    }

    @GetMapping("/updateLinkState")
    public BaseResponse updateLinkState(@RequestParam("id") Long id,@RequestParam("state")Integer state){
        Link link = linkService.getById(id);
        link.setState(state);
        return BaseResponse.ok("操作成功");
    }

    private void updateGoodsLink(Link link){
        String goodsIds = link.getGoodsIds();
        String[] goodsIdArr = goodsIds.split(",");
        List<Goods> goodsList = goodsService.getBaseMapper().selectList(new QueryWrapper<Goods>().in("id", goodsIdArr));
        goodsList.forEach(goods -> {
            goods.setGoodsLinkId(link.getId());
        });
        goodsService.updateBatchById(goodsList);
    }

}

