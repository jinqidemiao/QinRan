package com.dfcs.supermarket.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品取货码生成规则（前缀）
     */
    private String goodsPrefix;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品描述
     */
    private String goodsDes;

    /**
     * 商品规格
     */
    private String goodsSpec;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品链接id
     */
    private Long goodsLinkId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 0禁用 1启用
     */
    private Integer state;

    /**
     * 开抢时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


}
