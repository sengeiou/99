package com.oty.sys.model.api;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单  
 */
@ApiModel(value="订单",description="订单")
public class OrderData implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
	@Getter @Setter
    private Integer id;
    /**
     * 订单Id
     */
	@Getter @Setter
    private Integer orderId;
    /**
     * 编号
     */
	@Getter @Setter
    private String orderNo;
    /**
     * 用户
     */
	@Getter @Setter
    private Integer weixinUserId;
    /**
     * 收货人姓名
     */
	@Getter @Setter
    private String name;
    /**
     * 收货人电话
     */
	@Getter @Setter
    private String moblie;
    /**
     * 商品
     */
	@Getter @Setter
    private Integer factoryActivityId;
    /**
     * 数量
     */
	@Getter @Setter
    private Integer num;
    /**
     * 推荐人
     */
	@Getter @Setter
    private Integer referee;
    /**
     * 推荐人得到的佣金
     */
	@Getter @Setter
    private BigDecimal commission; 
    /**
     * 状态（0待付款1待发货2已发货3已收货-1已过期）
     */
	@Getter @Setter
    private Integer status;
    /**
     * 订单金额
     */
	@Getter @Setter
    private BigDecimal amount;
    /**
     * 创建时间
     */
	@Getter @Setter
    private Date createTime;
    /**
     * 修改时间
     */
	@Getter @Setter
    private Date updateTime; 
    /**
     * 标题或者商品名称
     */
	@Getter @Setter
    private String title;
    //价格
	@Getter @Setter
    private BigDecimal price; 
    /**
     * 会员呢称
     */
	@Getter @Setter
    private String nickname;
    /**
     * 店铺名称
     */
	@Getter @Setter
    private String factoryName; 
    /**
     * 店铺地址
     */
	@Getter @Setter
    private String factoryAddress; 
    /**
     * 店铺电话
     */
	@Getter @Setter
    private String factoryPhone; 
    /**
     * 活动名称
     */
	@Getter @Setter
    private String factoryActivityName; 
    /**
     * 支付编号
     */
	@Getter @Setter
    private String paymentSn; 
    /**
     * 自动发货时间
     */
	@Getter @Setter
    private Date orderReceiptDate;
    /**
     * 自动收货天数
     */
	@Getter @Setter
    private Integer receiptDays;
 
}
