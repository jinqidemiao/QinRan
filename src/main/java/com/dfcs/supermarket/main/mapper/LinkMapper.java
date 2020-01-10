package com.dfcs.supermarket.main.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfcs.supermarket.main.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
public interface LinkMapper extends BaseMapper<Link> {

    IPage<Link> linkList(@Param("page") Page<Link> pagination, @Param("state") Integer state,@Param("link") String link);

}
