package com.dfcs.supermarket.main.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfcs.supermarket.main.entity.Link;
import com.dfcs.supermarket.main.mapper.LinkMapper;
import com.dfcs.supermarket.main.service.ILinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public IPage<Link> linkList(Page<Link> pagination, Integer state, String link) {
        return linkMapper.linkList(pagination,state,link);
    }
}
