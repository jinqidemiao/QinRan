package com.dfcs.supermarket.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class GoodsUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 预约数量
     */
    private Integer num;

    /**
     * 预约时间
     */
    private Date createTime;

    /**
     * 配送方式   0到店自取 1配送
     */
    private Integer sendWay;

    /**
     * 配送方式为0，则为null，若为1，则保存客户地址
     */
    private String address;

    /**
     * 附加信息（备注）
     */
    private String extraMessage;

    /**
     * 商品费用
     */
    private BigDecimal goodsAmount;

    /**
     * 额外费用（配送费）
     */
    private BigDecimal extraAmount;

    /**
     * 总费用
     */
    private BigDecimal sumAmount;

    /**
     * 取货码
     */
    private String pickupCode;

    /**
     * 状态 0取消订单 1可用
     */
    private Integer state;

    @TableField(exist = false)
    private Goods goods;

    @TableField(exist = false)
    private User user;

    /**
     * 修改时间
     */
    private Date updateTime;

}
