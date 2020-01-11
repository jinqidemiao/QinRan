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
	
	/**
	 * 链接列表
	 *
	 * @param pagination 分页
	 * @param state 状态
	 * @param link 链接
	 *
	 * @return {@link IPage<Link>}
	 */
	IPage<Link> linkList(Page<Link> pagination, Integer state, String link);
}
