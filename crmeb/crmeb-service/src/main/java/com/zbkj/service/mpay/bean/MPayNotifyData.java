package com.zbkj.service.mpay.bean;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
public class MPayNotifyData extends MPayBase {

    //支付平台唯一流水号
    private Long merchantOrderId;

    //商户号
    private Long merchantId;

    //商户订单号
    private String merchantOrderNo;

    //充值金额
    private BigDecimal orderAmount;

    //订单状态
    private OrderState orderState;

    private String timestamp;
    private String signType;
    private String sign;
}
