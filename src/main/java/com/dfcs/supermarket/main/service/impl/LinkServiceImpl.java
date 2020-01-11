package com.dfcs.supermarket.main.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfcs.supermarket.main.entity.Link;
import com.dfcs.supermarket.main.mapper.LinkMapper;
import com.dfcs.supermarket.main.service.ILinkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoxinyu
 * @date 2020/01/11
 * @since 2020-01-09
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {
	/**
	 * 链接列表
	 *
	 * @param pagination 分页
	 * @param state 状态
	 * @param link 链接
	 *
	 * @return {@link IPage<Link>}
	 */
	@Override
    public IPage<Link> linkList(Page<Link> pagination, Integer state, String link) {
        return this.baseMapper.linkList(pagination,state,link);
    }
}
