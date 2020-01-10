package com.dfcs.supermarket.main.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfcs.supermarket.main.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
public interface ILinkService extends IService<Link> {

    IPage<Link> linkList(Page<Link> pagination, Integer state, String link);
}
