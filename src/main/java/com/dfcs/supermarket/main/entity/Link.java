package com.dfcs.supermarket.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoxinyu
 * @since 2020-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Link implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 链接
     */
    private String link;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 0无效 1有效
     */
    private Integer state;

    @TableField(exist = false)
    private String goodsNames;

    @TableField(exist = false)
    private String goodsIds;

    @TableField(exist = false)
    private List<Goods> goodsList;

    /**
     * 描述
     */
    private String des;

}
