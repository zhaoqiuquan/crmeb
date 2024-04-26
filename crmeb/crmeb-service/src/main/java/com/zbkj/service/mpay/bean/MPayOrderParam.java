package com.zbkj.service.mpay.bean;

import com.zbkj.common.model.order.StoreOrder;
import com.zbkj.service.mpay.util.MPayUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author zqq
 */
@Data
public class MPayOrderParam extends MPayBase {

    private Long merchantId;//商户号
    private String merchantOrderNo;//商户订单号
    private BigDecimal amount;//金额，保留2位小数
    private String clientIpAddress;// 客户端ip
    private String currencyType = "CN";//货币种类
    private String payWay;//支付渠道
    private String timestamp;//时间字符串 yyyyMMddHHmmss
    private String notifyUrl;//通知地址
    private String userId;//用户标识
    private String userRealName; //真实姓名
    private String userIdCardNo; //身份证
    private String userMobile;//手机号
    private String desc;//交易描述
    private String signType;
    private String sign;

    public MPayOrderParam(StoreOrder order) {
        this.merchantId = MPayConfig.merchantId;
        this.merchantOrderNo = String.valueOf(System.currentTimeMillis());
        this.amount = order.getPayPrice();
        this.clientIpAddress = "127.0.0.1";
        this.payWay = MPayConfig.payWay;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        this.notifyUrl = MPayConfig.notifyUrl;
        this.userId = "id";
        this.desc = "order";
        this.signType = MPayConfig.signType;

    }

}
